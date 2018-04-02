package com.hu.dg.domain;

/**
 * 关注的文章
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FocusArticle.java 2016/06/06 09:46
 */
public class FocusArticle {

    private int id;
    private int userId;
    private int articleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
}