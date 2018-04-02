package com.hu.dg.type;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleStatus.java 2016/06/07 14:34
 */
public enum ArticleStatus {

    OPEN("打开"),
    CLOSE("关闭");

    ArticleStatus(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}