package com.hu.dg.repository;

import com.hu.dg.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 文章删除至回收站
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleRepository.java 2016/06/12 10:04
 */
@Repository
public interface RecycleBinArticleRepository {

    void create(Article article);

    List<Article> list(Map<String, Object> params);

    int getCount(Map<String, Object> params);

    int getMaxId();

    Article findById(int id);

    void delete(@Param("id") int id,@Param("userId") int userId);
}