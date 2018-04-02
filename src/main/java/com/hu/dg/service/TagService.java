package com.hu.dg.service;

import com.hu.dg.domain.Tag;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) TagService.java 2016/08/01 23:08
 */
public interface TagService {

    List<Tag> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);
}