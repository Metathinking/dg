package com.hu.dg.service;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleService.java 2016/06/12 10:11
 */
public interface ArticleService {

    void edit(Article article);

    Article findById(int id);

    Map<String,Object> findArticlePageMsg(int id, User user, HttpServletRequest request,HttpServletResponse response);

    List<Article> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    void changeOpen(Article article,int userId);

    void delete(int articleId, int userId);

    List<Article> listHotArticle(int articleId);

    List<Article> listNewArticle(int articleId);
}