package com.hu.dg.domain;

/**
 * 关注的人
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FocusUser.java 2016/06/06 09:45
 */
public class FocusUser {

    private int id;
    private int userId;//关注人
    private int focusId;//被关注人


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

    public int getFocusId() {
        return focusId;
    }

    public void setFocusId(int focusId) {
        this.focusId = focusId;
    }
}