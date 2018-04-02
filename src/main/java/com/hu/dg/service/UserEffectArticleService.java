package com.hu.dg.service;

import com.hu.dg.domain.User;

import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserEffectArticleService.java 2016/06/20 11:50
 */
public interface UserEffectArticleService {

    Map<String,Object> changeEffect(int articleId, boolean effect, User user);
}