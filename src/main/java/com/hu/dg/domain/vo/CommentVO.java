package com.hu.dg.domain.vo;

import com.hu.dg.domain.Comment;
import com.hu.dg.domain.CommentAboutInfo;
import com.hu.dg.domain.UserEffectComment;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CommentVO.java 2016/06/27 09:29
 */
public class CommentVO  {

    private Comment comment;
    private CommentAboutInfo commentAboutInfo;
//    private UserEffectComment userEffectComment;

    public CommentAboutInfo getCommentAboutInfo() {
        return commentAboutInfo;
    }

    public void setCommentAboutInfo(CommentAboutInfo commentAboutInfo) {
        this.commentAboutInfo = commentAboutInfo;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}