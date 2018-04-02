package com.hu.dg.repository;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.FocusArticle;
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
public interface FocusArticleRepository {

    void create(FocusArticle focusArticle);

    void delete(int id);

    List<Article> list(Map<String,Object> params);

    int getMaxId();

    FocusArticle find(@Param("userId") int userId,@Param("articleId") int articleId);

    int getCount(Map<String,Object> params);
}