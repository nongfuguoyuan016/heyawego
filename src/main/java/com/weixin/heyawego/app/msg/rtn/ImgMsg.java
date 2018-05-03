package com.weixin.heyawego.app.msg.rtn;

/**
 * @Author : xuchang
 * @Description : 图片消息
 * @Date : 2018/4/28 14:07
 */
public class ImgMsg extends BaseRtnMsg {

    private Image Image;

    public ImgMsg(){}

    public ImgMsg(String toUserName,String fromUserName ,String msgType , long createTime){
        super.setToUserName(toUserName);
        super.setFromUserName(fromUserName);
        super.setMsgType(msgType);
        super.setCreateTime(createTime);
    }

    public ImgMsg(String toUserName,String fromUserName ,String msgType , long createTime ,Image Image){
        super.setToUserName(toUserName);
        super.setFromUserName(fromUserName);
        super.setMsgType(msgType);
        super.setCreateTime(createTime);
        this.Image = Image ;
    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "ImgMsg{" +
                ", Image='" + Image + '\'' +
                '}';
    }
}
