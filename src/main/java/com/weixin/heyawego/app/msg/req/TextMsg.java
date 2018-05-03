package com.weixin.heyawego.app.msg.req;

/**
 * @Author : xuchang
 * @Description : 文本消息
 * @Date : 2018/4/28 14:05
 */
public class TextMsg extends BaseReqMsg {

    //文本消息内容
    private String Content ;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "TextMsg{" +
                "Content='" + Content + '\'' +
                '}';
    }
}
