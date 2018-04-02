package com.hu.dg.service;

import com.hu.dg.domain.Recommend;
import com.hu.dg.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RecommendService.java 2016/06/29 19:56
 */
public interface RecommendService {

    void edit(Recommend recommend,User user);

    List<Recommend> list(Map<String,Object> params);

    void delete(int id);

    int getCount(Map<String,Object> params);

    Recommend findById(int id);
}