package com.hu.dg.repository;

import com.hu.dg.domain.UserEffectComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserEffectCommentRepository.java 2016/06/24 10:52
 */
@Repository
public interface UserEffectCommentRepository {

    void create(UserEffectComment userEffectComment);

    int getMaxId();

    UserEffectComment find(@Param("userId")int userId, @Param("commentId")int commentId);
}