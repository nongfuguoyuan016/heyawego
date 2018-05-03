package com.weixin.heyawego.app.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.weixin.heyawego.app.common.utils.*;
import com.weixin.heyawego.app.common.utils.StringUtils;
import com.weixin.heyawego.app.menu.Button;
import com.weixin.heyawego.app.menu.CommonButton;
import com.weixin.heyawego.app.menu.ComplexButton;
import com.weixin.heyawego.app.menu.Menu;
import com.weixin.heyawego.app.msg.rtn.*;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CoreService {

    //获取临时素材接口 , 需参数 access_token 和 media_id
    private static final String GET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/get" ;

    public  String processRequest(HttpServletRequest request){
        String rtnXml = "success" ;
        String downloadPath = request.getServletContext().getRealPath(File.separator)+"/file/" + DateUtils.formatDate(new Date()) +"/";
        try {
            //获取请求参数
            Map<String,String> map = MessageUtils.parseXml(request);
            System.out.println("\n------------------------------ REQ_MSG ------------------------");
            for(String key : map.keySet()){ //打印输出
                System.out.println(key + " : " + map.get(key));
            }
            //构造返回xml的字符串
            rtnXml = buildRtnMsg(map , downloadPath) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtnXml ;
    }

    /**
     * @Author : xuchang
     * @Description : 获取AccessToken
     * @Param :
     * @Return : java.util.Map<java.lang.String,java.lang.String>
     * @Date : 2018/4/28 17:21
     */
    public String getAccessToken(){
        //从缓存中获取token
        String token =(String) CacheUtils.get("accessToken") ;
        if(null == token){ //如果没有则请求微信接口
            //设置请求参数
            String accessTokenUrl = Global.getConfig("accessTokenUrl") ;
            String grant_type = Global.getConfig("grant_type") ;
            String appid = Global.getConfig("appid") ;
            String secret = Global.getConfig("secret") ;
            Map<String,String> params = new HashMap<>() ;
            params.put("grant_type",grant_type);
            params.put("appid",appid);
            params.put("secret",secret);
            try {
                //请求接口
                String response = HttpClient.send(accessTokenUrl , params , "UTF-8") ;
                JSONObject json = JSON.parseObject(response) ;
                String errorCode = (String) json.get("errorcode") ;
                System.out.println("response : "+response);
                if(null == errorCode){//请求成功
                    token = (String) json.get("access_token") ;
                    //保存到缓存
                    CacheUtils.put("accessToken",token);
                    CacheUtils.put("accessTime",new Date());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return token ;
    }

    /**
     * @Author : xuchang
     * @Description :         构造返回xml的字符串
     * @Param : map           请求的参数MAP
     * @Param : downloadPath  下载临时素材的保存路径
     * @Return : java.lang.String
     * @Date : 2018/5/2 15:45
     */
    private String buildRtnMsg(Map<String,String> map , String downloadPath){
        String rtnXml = "success" ;
        //构造公共返回参数
        String fromUserName = map.get("FromUserName") ;
        String toUserName = map.get("ToUserName") ;
        String msgType = map.get("MsgType") ;
        long createTime = new Date().getTime() ;
        //获取 accessToken
        String token = getAccessToken() ;
        //构造返回体
        if(MessageUtils.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
            //文字消息
            TextMsg textMsg = new TextMsg(fromUserName,toUserName,msgType,createTime,map.get("Content")) ;
            rtnXml = MessageUtils.messageToXml(textMsg) ;
        }else if(MessageUtils.REQ_MESSAGE_TYPE_VOICE.equals(msgType) || MessageUtils.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)){
            //图片或音频消息
            if(MessageUtils.REQ_MESSAGE_TYPE_VOICE.equals(msgType)){
                //音频消息
                VoiceMsg voiceMsg = new VoiceMsg(fromUserName,toUserName,msgType,createTime,new Voice(map.get("MediaId")));
                rtnXml = MessageUtils.messageToXml(voiceMsg) ;
            }else{
                //图片消息
                ImgMsg imgMsg = new ImgMsg(fromUserName,toUserName,msgType,createTime,new Image(map.get("MediaId")));
                rtnXml = MessageUtils.messageToXml(imgMsg) ;
            }
        }else if(MessageUtils.REQ_MESSAGE_TYPE_VIDEO.equals(msgType)){//视频消息

        }else if(MessageUtils.REQ_MESSAGE_TYPE_SHORTVIDEO.equals(msgType)){//短视频消息

        }else if(MessageUtils.REQ_MESSAGE_TYPE_LINK.equals(msgType)){//链接消息

        }else if(MessageUtils.REQ_MESSAGE_TYPE_LOCATION.equals(msgType)){//位置消息

        }else if(MessageUtils.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){//事件
            String eventType = map.get("Event") ;
            if(MessageUtils.EVENT_TYPE_SUBSCRIBE.equals(eventType)){
                //文字消息
                TextMsg textMsg = new TextMsg(fromUserName,toUserName,MessageUtils.RTN_MESSAGE_TYPE_TEXT,createTime,"欢迎订阅,我就喜欢你这么有眼光的人!!") ;
                rtnXml = MessageUtils.messageToXml(textMsg) ;
            }else if(MessageUtils.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)){

            }else if(MessageUtils.EVENT_TYPE_CLICK.equals(eventType)){

            }else if(MessageUtils.EVENT_TYPE_VIEW.equals(eventType)){

            }else if(MessageUtils.EVENT_TYPE_SCAN.equals(eventType)){

            }else if(MessageUtils.EVENT_TYPE_LOCATION.equals(eventType)){

            }
        }
        //将临时素材下载到本地
        if(StringUtils.isNotEmpty(downloadPath)){
            downloadPath = downloadPath + msgType + "/" + fromUserName + "/" ;
            String url  = GET_MATERIAL + "?access_token=" + token+ "&media_id=" + map.get("MediaId");
            HttpClient.download(url,downloadPath);
        }
        return rtnXml ;
    }



    public String createMenu(){
        Menu menu = getMenu() ;
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken() ;
        String rtnStr = HttpClient.stringBodyPost(url , null , JSONObject.toJSONString(menu), "UTF-8");
        return rtnStr ;
    }

    private Menu getMenu(){
        //第一个 一级菜单
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("搜索");

        CommonButton btn11 = new CommonButton();
        btn11.setName("百度搜索");
        btn11.setType("view");
        btn11.setKey("11");
        btn11.setUrl("https://www.baidu.com");

        CommonButton btn12 = new CommonButton();
        btn12.setName("搜狐搜索");
        btn12.setType("view");
        btn12.setKey("12");
        btn12.setUrl("http://www.sohu.com");

        //第一个 一级菜单下有两个子菜单
        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12 });

        //第二个一级菜单
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("休闲驿站");

        CommonButton btn21 = new CommonButton();
        btn21.setName("歌曲点播");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("经典游戏");
        btn22.setType("click");
        btn22.setKey("22");

        //第二个 一级菜单下有两个子菜单
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22});

        //第三个 一级菜单 (最多三个)
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");

        CommonButton btn31 = new CommonButton();
        btn31.setName("经典游戏");
        btn31.setType("click");
        btn31.setKey("22");

        //第三个 一级菜单下有一个子菜单
        mainBtn3.setSub_button(new CommonButton[] { btn31 });

        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
        return menu;
    }





}
