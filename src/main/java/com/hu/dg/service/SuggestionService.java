package com.hu.dg.service;

import com.hu.dg.domain.Suggestion;
import com.hu.dg.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SuggestionService.java 2016/07/03 17:33
 */
public interface SuggestionService {

    void create(Suggestion suggestion, User user, HttpServletRequest request);

    void handle(int id);

    void delete(int id);

    List<Suggestion> list(Map<String,Object>  params);

    int getCount(Map<String,Object> params);


}