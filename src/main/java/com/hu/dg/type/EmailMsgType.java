package com.hu.dg.type;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailMsgType.java 2016/06/15 08:45
 */
public enum  EmailMsgType {

    REGISTER("注册验证"),
    FIND_PASSWORD("密码找回"),
    SYSTEM("系统消息"),
    OTHER("其他");


    EmailMsgType(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}