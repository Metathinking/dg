package com.hu.dg.domain;

/**
 * 网站设置
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteSetting.java 2016/07/03 21:47
 */
public class SiteSetting {

    private int id;
    private int mainArticleCount;//首页文章数 12
    private int articleCommentCount;//文章下显示评论数量 12
    private int articlePageSize;//W文章分页每页数两 20
    private int commentPageSize;//评论分页 每页数量 30
    private long overTime;//邮件验证，超时时间 30分钟
    private long time;

    public long getOverTime() {
        return overTime;
    }

    public void setOverTime(long overTime) {
        this.overTime = overTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainArticleCount() {
        return mainArticleCount;
    }

    public void setMainArticleCount(int mainArticleCount) {
        this.mainArticleCount = mainArticleCount;
    }

    public int getArticlePageSize() {
        return articlePageSize;
    }

    public void setArticlePageSize(int articlePageSize) {
        this.articlePageSize = articlePageSize;
    }

    public int getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(int articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public int getCommentPageSize() {
        return commentPageSize;
    }

    public void setCommentPageSize(int commentPageSize) {
        this.commentPageSize = commentPageSize;
    }
}