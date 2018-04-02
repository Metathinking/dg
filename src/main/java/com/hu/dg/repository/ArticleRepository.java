package com.hu.dg.repository;

import com.hu.dg.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleRepository.java 2016/06/12 10:04
 */
@Repository
public interface ArticleRepository {

    void create(Article article);

    List<Article> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    int getMaxId();

    Article findById(int id);

    /**
     *
     * 查询文章简单信息，不包括 内容
     * @param id
     * @return
     */
    Article findSimpleById(int id);// TODO: 2016/6/20.0020 根据需要添加参数

    void changeOpen(@Param("id") int id, @Param("open") boolean open);

    void delete(@Param("id") int id,@Param("userId") int userId);

    void update(Article article);

    List<Article> listHotArticle(Map<String, Object> map);

    /**
     * 查询文章的作者ID
     * @param articleId
     * @return
     */
    int findUserIdByArticleId(int articleId);
}