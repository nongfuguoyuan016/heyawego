package com.weixin.heyawego.app.msg.req;

/**
 * @Author : xuchang
 * @Description : 链接消息
 * @Date : 2018/4/28 14:17
 */
public class LinkMsg extends BaseReqMsg {

    //消息标题
    private String Title ;

    //消息描述
    private String Description ;

    //消息链接
    private String Url ;

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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public String toString() {
        return "LinkMsg{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }
}
