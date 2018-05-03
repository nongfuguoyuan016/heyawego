package com.weixin.heyawego.app.msg.rtn;


public class Image {

    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String MediaId ;

    public Image(){}

    public Image(String mediaId){
        this.MediaId = mediaId ;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "MediaId='" + MediaId + '\'' +
                '}';
    }
}
