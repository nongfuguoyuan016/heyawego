package com.weixin.heyawego.app.msg.rtn;

/**
 * @Author : xuchang
 * @Description : 回复消息基类
 * @Date : 2018/4/28 14:01
 */
public class BaseRtnMsg {

    //开发者微信号
    private String ToUserName ;

    //发送方账号(一个OpenId)
    private String FromUserName ;

    //消息创建时间(整型)
    private long CreateTime ;

    //text , image , voice , video , shortvideo , location ,link , music , news
    private String MsgType ;

    public BaseRtnMsg(){}

    public BaseRtnMsg(String ToUserName,String FromUserName ,String MsgType , long CreateTime){
        this.ToUserName = ToUserName ;
        this.FromUserName = FromUserName ;
        this.MsgType = MsgType ;
        this.CreateTime = CreateTime ;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    @Override
    public String toString() {
        return "BaseRtnMsg{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }
}
