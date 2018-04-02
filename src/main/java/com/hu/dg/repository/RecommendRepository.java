package com.hu.dg.repository;

import com.hu.dg.domain.Recommend;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RecommendRepository.java 2016/06/29 19:54
 */
@Repository
public interface RecommendRepository {

    void create(Recommend recommend);

    void update(Recommend recommend);

    void delete(int id);

    List<Recommend> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    int getMaxId();

    Recommend findById(int id);
}