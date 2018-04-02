package com.hu.dg.service.impl;

import com.hu.dg.domain.Suggestion;
import com.hu.dg.domain.User;
import com.hu.dg.repository.SuggestionRepository;
import com.hu.dg.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SuggestionServiceImpl.java 2016/07/03 17:35
 */
@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;

    public void create(Suggestion suggestion,User user, HttpServletRequest request) {
        int maxId = suggestionRepository.getMaxId();
        maxId++;
        suggestion.setId(maxId);
        if(user!=null){
            suggestion.setUserId(user.getId());
        }
        suggestion.setHandled(false);
        suggestion.setIp(request.getRemoteAddr());
        suggestion.setTime(System.currentTimeMillis());
        suggestionRepository.create(suggestion);
    }

    public void handle(int id) {
        Suggestion dbSuggestion = suggestionRepository.findById(id);
        dbSuggestion.setHandled(true);
        suggestionRepository.update(dbSuggestion);
    }

    public void delete(int id) {
        suggestionRepository.delete(id);
    }

    public List<Suggestion> list(Map<String, Object> params) {
        return suggestionRepository.list(params);
    }

    public int getCount(Map<String, Object> params) {
        return suggestionRepository.getCount(params);
    }
}