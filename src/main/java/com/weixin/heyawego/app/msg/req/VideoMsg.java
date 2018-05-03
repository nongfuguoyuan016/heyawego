package com.weixin.heyawego.app.msg.req;

/**
 * @Author : xuchang
 * @Description : 视频消息
 * @Date : 2018/4/28 14:11
 */
public class VideoMsg extends BaseReqMsg {

    //视频消息媒体id，可以调用多媒体文件下载接口拉取数据
    private String MediaId ;

    //视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
    private String ThumbMediaId ;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @Override
    public String toString() {
        return "VideoMsg{" +
                "MediaId='" + MediaId + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                '}';
    }
}
