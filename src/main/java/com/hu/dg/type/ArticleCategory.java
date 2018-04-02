package com.hu.dg.type;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleCategory.java 2016/06/18 10:53
 */
public enum ArticleCategory {
    ORIGINAL("原创"),
    REPRINT("转载");

    ArticleCategory(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}