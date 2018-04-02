package com.hu.dg.domain;

/**
 * 用户对文章的影响（支持|反对）
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserEffectArticle.java 2016/06/06 10:02
 */
public class UserEffectArticle {

    private int id;
    private int userId;
    private int articleId;
    private boolean effect;//false：反对；true，支持

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

    public boolean isEffect() {
        return effect;
    }

    public void setEffect(boolean effect) {
        this.effect = effect;
    }
}