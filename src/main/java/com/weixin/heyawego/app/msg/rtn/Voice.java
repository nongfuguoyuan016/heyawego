package com.weixin.heyawego.app.msg.rtn;

/**
 * Created by admin on 2018/5/2.
 */
public class Voice {

    public Voice(){}

    public Voice(String mediaId){
        this.MediaId = mediaId ;
    }

    //语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String MediaId ;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @Override
    public String toString() {
        return "Voice{" +
                "MediaId='" + MediaId + '\'' +
                '}';
    }
}
