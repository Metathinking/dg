package com.hu.dg.service.impl;

import com.hu.dg.domain.Tag;
import com.hu.dg.repository.TagRepository;
import com.hu.dg.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) TagServiceImpl.java 2016/08/01 23:36
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> list(Map<String, Object> params) {
        return tagRepository.list(params);
    }

    public int getCount(Map<String, Object> params) {
        return tagRepository.getCount(params);
    }
}