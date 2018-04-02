package com.hu.dg.repository;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.FocusArticle;
import com.hu.dg.domain.FocusUser;
import com.hu.dg.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FocusArticleRepository.java 2016/06/19 19:36
 */
@Repository
public interface FocusUserRepository {

    void create(FocusUser focusUser);

    void delete(int id);

    int getMaxId();

    FocusUser find(@Param("userId") int userId, @Param("focusId") int focusId);

    List<User> listFocusUser(Map<String, Object> params);

    int getFocusUserCount(Map<String, Object> params);

    List<User> listFans(Map<String, Object> params);

    int getFansCount(Map<String, Object> params);
}