package com.hu.dg.service;

import com.hu.dg.domain.Article;
import com.hu.dg.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FocusArticleService.java 2016/06/19 20:41
 */
public interface FocusArticleService {

    Map<String,Object> changeFocus(int articleId, boolean focus, User user);

    List<Article> list(Map<String, Object> params);

    int getCount(Map<String, Object> params);
}