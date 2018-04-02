package com.hu.dg.service.impl;

import com.hu.dg.domain.BrowseRecord;
import com.hu.dg.repository.BrowseRecordRepository;
import com.hu.dg.service.BrowseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) BrowseRecordServiceImpl.java 2016/08/02 00:28
 */
@Service
public class BrowseRecordServiceImpl implements BrowseRecordService {

    @Autowired
    private BrowseRecordRepository browseRecordRepository;

    public List<BrowseRecord> list(Map<String, Object> params) {
        return browseRecordRepository.list(params);
    }
}