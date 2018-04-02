package com.hu.dg.domain;

/**
 * 浏览记录
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) BrowseRecord.java 2016/08/02 00:17
 */
public class BrowseRecord {

    private int id;
    private int articleId;
    private int userId;
    private long time;
    private String ip;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}