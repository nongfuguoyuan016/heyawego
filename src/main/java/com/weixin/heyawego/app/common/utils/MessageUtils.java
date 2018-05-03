package com.weixin.heyawego.app.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.weixin.heyawego.app.msg.rtn.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : xuchang
 * @Description : 消息处理工具类
 * @Date : 2018/4/28 14:55
 */
public class MessageUtils {

    // 请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：语音
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：视频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    // 请求消息类型：小视频
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    // 请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    // 请求消息类型：事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    // 事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 事件类型：scan(用户已关注时的扫描带参数二维码)
    public static final String EVENT_TYPE_SCAN = "SCAN";
    // 事件类型：LOCATION(上报地理位置)
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    // 事件类型：CLICK(自定义菜单 点击菜单拉取消息时的事件推送)
    public static final String EVENT_TYPE_CLICK = "CLICK";
    // 事件类型：CLICK(自定义菜单 点击菜单跳转链接时的事件推送)
    public static final String EVENT_TYPE_VIEW = "VIEW";

    // 响应消息类型：文本
    public static final String RTN_MESSAGE_TYPE_TEXT = "text";
    // 响应消息类型：图片
    public static final String RTN_MESSAGE_TYPE_IMAGE = "image";
    // 响应消息类型：语音
    public static final String RTN_MESSAGE_TYPE_VOICE = "voice";
    // 响应消息类型：视频
    public static final String RTN_MESSAGE_TYPE_VIDEO = "video";
    // 响应消息类型：音乐
    public static final String RTN_MESSAGE_TYPE_MUSIC = "music";
    // 响应消息类型：图文
    public static final String RTN_MESSAGE_TYPE_NEWS = "news";

    /**
     * @Author : xuchang
     * @Description : 解析微信发来的请求(XML文件)
     * @Param : request
     * @Return : java.util.Map<java.lang.String,java.lang.String>
     * @Date : 2018/4/28 15:02
     */
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {
        //保存解析结果
        Map<String,String> map = new HashMap<>() ;
        //从request中获取流
        InputStream inputStream = request.getInputStream() ;
        //读取输入流
        SAXReader reader = new SAXReader() ;
        Document document = reader.read(inputStream) ;
        //获取跟元素
        Element root = document.getRootElement() ;
        //得到根元素的所有节点
        List<Element> elementList = root.elements() ;
        //遍历所有子节点
        elementList.forEach(e -> {
            map.put(e.getName() , e.getText());
        });
        //释放资源
        inputStream.close();
        inputStream = null ;

        return map ;
    }

    /**
     * 扩展xstream使其支持CDATA
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    public static String  messageToXml(ImgMsg msg){
        xstream.alias("xml",msg.getClass());
        return xstream.toXML(msg);
    }

    public static String  messageToXml(MusicMsg msg){
        xstream.alias("xml",msg.getClass());
        return xstream.toXML(msg);
    }

    public static String  messageToXml(TextMsg msg){
        xstream.alias("xml",msg.getClass());
        return xstream.toXML(msg);
    }

    public static String  messageToXml(VideoMsg msg){
        xstream.alias("xml",msg.getClass());
        return xstream.toXML(msg);
    }

    public static String  messageToXml(VoiceMsg msg){
        xstream.alias("xml",msg.getClass());
        return xstream.toXML(msg);
    }

    public static String  messageToXml(NewsMsg msg){
        xstream.alias("xml",msg.getClass());
        xstream.alias("item",new Article().getClass());
        return xstream.toXML(msg);
    }

    

}
