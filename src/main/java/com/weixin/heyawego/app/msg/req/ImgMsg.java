package com.weixin.heyawego.app.msg.req;

/**
 * @Author : xuchang
 * @Description : 图片消息
 * @Date : 2018/4/28 14:07
 */
public class ImgMsg extends BaseReqMsg {

    //图片链接(由系统生成)
    private String PicUrl ;

    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String MediaId ;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @Override
    public String toString() {
        return "ImgMsg{" +
                "PicUrl='" + PicUrl + '\'' +
                ", MediaId='" + MediaId + '\'' +
                '}';
    }
}
