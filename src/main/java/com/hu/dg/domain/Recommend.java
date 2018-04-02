package com.hu.dg.domain;

/**
 * 类的功能，目的，描述等写在此处推广的商品
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) Recommend.java 2016/06/29 19:44
 */
public class Recommend {

    private int id;
    private String image; //图片
    private String description;//描述
    private String shopUrl;//购买链接
    private String recommendUrl;//推广链接
    private long time;
    private int userId;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getRecommendUrl() {
        return recommendUrl;
    }

    public void setRecommendUrl(String recommendUrl) {
        this.recommendUrl = recommendUrl;
    }
}