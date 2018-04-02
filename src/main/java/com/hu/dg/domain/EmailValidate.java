package com.hu.dg.domain;

/**
 * 邮箱验证
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailValidate.java 2016/06/16 09:27
 */
public class EmailValidate {

    private int id;
    private int userId;
    private String code;
    private long time;
    private boolean validated;//是否已验证

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}