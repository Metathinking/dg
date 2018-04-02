package com.hu.dg.service;

import com.hu.dg.domain.Article;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RecycleBinArticleService.java 2016/06/18 20:34
 */
public interface RecycleBinArticleService {

    List<Article> list(Map<String,Object> params);

    int getCount(Map<String, Object> params);

    Article findById(int id);

    void delete(int id, int userId);

    void restore(int id, int userId);
}