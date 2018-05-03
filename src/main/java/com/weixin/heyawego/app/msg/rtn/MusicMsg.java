package com.weixin.heyawego.app.msg.rtn;

/**
 * @Author : xuchang
 * @Description : 回复音乐消息
 * @Date : 2018/4/28 14:27
 */
public class MusicMsg extends BaseRtnMsg {

    //音乐标题(非必须)
    private String Title ;

    //音乐描述(非必须)
    private String Description ;

    //音乐链接(非必须)
    private String MusicURL ;

    //高质量音乐链接，WIFI环境优先使用该链接播放音乐(非必须)
    private String HQMusicUrl ;

    //缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
    private String ThumbMediaId ;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicURL() {
        return MusicURL;
    }

    public void setMusicURL(String musicURL) {
        MusicURL = musicURL;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @Override
    public String toString() {
        return "MusicMsg{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", MusicURL='" + MusicURL + '\'' +
                ", HQMusicUrl='" + HQMusicUrl + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                '}';
    }
}
