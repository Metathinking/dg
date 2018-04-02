package com.hu.dg.domain;

/**
 * 文章
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) Article.java 2016/06/06 09:38
 */
public class Article {

    private int id;
    private String title;//标题 48
    private int userId;//作者
    private String userNickname;
    /**
     * @see com.hu.dg.type.ArticleCategory
     */
    private String category;//分类(原创，转载)
    private String content;//内容
    private String description;//描述：自定义，或指定
    private String tag;//标签50,|分割
    private long time;
    /**
     * @see com.hu.dg.type.ArticleStatus
     */
    private String status;
    private boolean open;//公开
    private String image;//标题图片，必须添加
    private String originalUrl;//原文地址，原创时，为本文地址；转载时，原文地址

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}