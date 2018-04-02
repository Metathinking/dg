package com.hu.dg.service;

import com.hu.dg.domain.BrowseRecord;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) BrowseRecordService.java 2016/08/02 00:27
 */
public interface BrowseRecordService {

    List<BrowseRecord> list(Map<String,Object> params);

}