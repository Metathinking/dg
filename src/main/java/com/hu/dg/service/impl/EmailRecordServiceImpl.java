package com.hu.dg.service.impl;

import com.hu.dg.domain.EmailRecord;
import com.hu.dg.repository.EmailRecordRepository;
import com.hu.dg.service.EmailRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailRecordServiceImpl.java 2016/06/14 13:49
 */
@Service
public class EmailRecordServiceImpl implements EmailRecordService {

    @Autowired
    private EmailRecordRepository emailRecordRepository;

    public void create(EmailRecord emailRecord) {
        int maxId = emailRecordRepository.getMaxId();
        maxId++;
        emailRecord.setId(maxId);
        emailRecordRepository.create(emailRecord);
    }

    public List<EmailRecord> list(Map<String, Object> params) {
        return emailRecordRepository.list(params);
    }

    public int getCount(Map<String, Object> params) {
        return emailRecordRepository.getCount(params);
    }

    public EmailRecord findById(int id) {
        return emailRecordRepository.findById(id);
    }
}