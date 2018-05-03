package com.weixin.heyawego.app.msg.rtn;

/**
 * @Author : xuchang
 * @Description : 文本消息
 * @Date : 2018/4/28 14:05
 */
public class TextMsg extends BaseRtnMsg {

    //文本消息内容
    private String Content ;

    public TextMsg(){}

    public TextMsg(String toUserName,String fromUserName ,String msgType , long createTime){
        super.setToUserName(toUserName);
        super.setFromUserName(fromUserName);
        super.setMsgType(msgType);
        super.setCreateTime(createTime);
    }

    public TextMsg(String toUserName,String fromUserName ,String msgType , long createTime ,String content){
        super.setToUserName(toUserName);
        super.setFromUserName(fromUserName);
        super.setMsgType(msgType);
        super.setCreateTime(createTime);
        this.Content = content ;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public TextMsg(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "TextMsg{" +
                "Content='" + Content + '\'' +
                '}';
    }
}
