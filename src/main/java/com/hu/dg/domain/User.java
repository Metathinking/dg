package com.hu.dg.domain;

/**
 * 用户
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) User.java 2016/06/06 09:33
 */
public class User {

    private int id;
    private String nickname;//12
    private String email;//48
    private boolean emailValidate;//邮箱是否已验证
    private String realName;//24
    private String password;//12
    /**
     * @see com.hu.dg.type.UserStatus
     */
    private String status;
    private long time;

    public boolean isEmailValidate() {
        return emailValidate;
    }

    public void setEmailValidate(boolean emailValidate) {
        this.emailValidate = emailValidate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return password;
    }
}