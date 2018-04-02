package com.hu.dg.domain;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CommentAboutInfo.java 2016/06/24 10:55
 */
public class CommentAboutInfo {

    private int commentId;
    private int supportCount;
    private int opposeCount;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(int supportCount) {
        this.supportCount = supportCount;
    }

    public int getOpposeCount() {
        return opposeCount;
    }

    public void setOpposeCount(int opposeCount) {
        this.opposeCount = opposeCount;
    }
}