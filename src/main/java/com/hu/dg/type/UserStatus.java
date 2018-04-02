package com.hu.dg.type;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserStatus.java 2016/06/07 14:33
 */
public enum  UserStatus {
    OPEN("打开"),
    CLOSE("关闭");

    UserStatus(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}