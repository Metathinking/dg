package com.hu.dg.domain;

/**
 * 文章相关信息：关注数量，浏览数，支持，反对
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleAboutInfo.java 2016/06/06 09:47
 */
public class ArticleAboutInfo {

    private int articleId;
    private int focusCount;//关注数量
    private int browseCount;//浏览数量
    private int supportCount;//支持数
    private int opposeCount;//反对数
    private int commentCount;//评论数

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(int focusCount) {
        this.focusCount = focusCount;
    }

    public int getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
    }

    public int getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(int supportCount) {
        this.supportCount = supportCount;
    }

    public int getOpposeCount() {
        return opposeCount;
    }

    public void setOpposeCount(int opposeCount) {
        this.opposeCount = opposeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}