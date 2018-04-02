package com.hu.dg.repository;

import com.hu.dg.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) TagRepository.java 2016/08/01 23:04
 */
@Repository
public interface TagRepository {

    void create(Tag tag);

    void update(Tag tag);

    List<Tag> list(Map<String,Object> params);

    Tag findByName(String name);

    int getCount(Map<String,Object> params);

    int getMaxId();
}