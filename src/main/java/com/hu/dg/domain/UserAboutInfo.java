package com.hu.dg.domain;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserAboutInfo.java 2016/07/08 22:08
 */
public class UserAboutInfo {

    private int userId;
    private int articleCount;//文章数量
    private int recommendCount;//推荐数量
    private int focusUserCount;//关注的人的数量
    private int fansCount;//粉丝数量
    private int focusArticleCount;//收藏文章的数量

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }

    public int getFocusUserCount() {
        return focusUserCount;
    }

    public void setFocusUserCount(int focusUserCount) {
        this.focusUserCount = focusUserCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFocusArticleCount() {
        return focusArticleCount;
    }

    public void setFocusArticleCount(int focusArticleCount) {
        this.focusArticleCount = focusArticleCount;
    }
}