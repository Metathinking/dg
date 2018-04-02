package com.hu.dg.service;

import com.hu.dg.domain.Comment;
import com.hu.dg.domain.CommentAboutInfo;
import com.hu.dg.domain.User;
import com.hu.dg.domain.vo.CommentVO;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CommentService.java 2016/06/24 14:24
 */
public interface CommentService {

    CommentVO create(Comment comment, User user);

    List<Comment> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    void delete(int id);

    CommentAboutInfo changeEffect(Integer id, Boolean effect, User user);
}