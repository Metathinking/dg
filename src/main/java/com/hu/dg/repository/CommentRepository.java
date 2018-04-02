package com.hu.dg.repository;

import com.hu.dg.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) CommentRepository.java 2016/06/24 10:46
 */
@Repository
public interface CommentRepository {

    void create(Comment comment);

    void delete(int id);

    List<Comment> list(Map<String,Object> params);

    Comment findById(int id);

    int getMaxId();

    int getCount(Map<String,Object> params);
}