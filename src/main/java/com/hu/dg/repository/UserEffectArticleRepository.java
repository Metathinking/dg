package com.hu.dg.repository;

import com.hu.dg.domain.UserEffectArticle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserEffectArticleRepository.java 2016/06/20 11:39
 */
@Repository
public interface UserEffectArticleRepository {

    void create(UserEffectArticle userEffectArticle);

    void update(UserEffectArticle userEffectArticle);

    int getMaxId();

    UserEffectArticle find(@Param("userId")int userId,@Param("articleId")int articleId);
}