package com.hu.dg.domain;

/**
 * 文章评论
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) Comment.java 2016/06/23 09:21
 */
public class Comment {

    private int  id;
    private int userId;
    private String userNickname;
    private int articleId;
    private String content;
    private long time;
    private int preId;//回复的评论id,-1时，直接回复的文章，

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getPreId() {
        return preId;
    }

    public void setPreId(int preId) {
        this.preId = preId;
    }
}