package com.weixin.heyawego.app.msg.rtn;

import java.util.List;

/**
 * @Author : xuchang
 * @Description : 返回的图文消息 MsgType为news
 * @Date : 2018/4/28 14:34
 */
public class NewsMsg extends BaseRtnMsg {

    //图文消息个数，限制为8条以内
    private int ArticleCount ;

    //多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
    private List<Article> Articles ;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }

    @Override
    public String toString() {
        return "NewsMsg{" +
                "ArticleCount=" + ArticleCount +
                ", Articles=" + Articles +
                '}';
    }
}
