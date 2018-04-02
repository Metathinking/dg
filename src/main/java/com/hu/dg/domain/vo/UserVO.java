package com.hu.dg.domain.vo;

import com.hu.dg.domain.User;
import com.hu.dg.domain.UserAboutInfo;
import com.hu.dg.domain.UserMsg;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserVO.java 2016/07/09 00:45
 */
public class UserVO {

    private User user;
    private UserAboutInfo userAboutInfo;
    private UserMsg userMsg;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAboutInfo getUserAboutInfo() {
        return userAboutInfo;
    }

    public void setUserAboutInfo(UserAboutInfo userAboutInfo) {
        this.userAboutInfo = userAboutInfo;
    }

    public UserMsg getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(UserMsg userMsg) {
        this.userMsg = userMsg;
    }
}