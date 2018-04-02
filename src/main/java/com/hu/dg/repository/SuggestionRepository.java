package com.hu.dg.repository;

import com.hu.dg.domain.Suggestion;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SuggestionRepository.java 2016/07/03 17:30
 */
@Repository
public interface SuggestionRepository {

    void create(Suggestion suggestion);

    void update(Suggestion suggestion);

    List<Suggestion> list(Map<String,Object> params);

    void delete(int id);

    int getCount(Map<String,Object> params);

    int getMaxId();

    Suggestion findById(int id);
}