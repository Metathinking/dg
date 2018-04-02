package com.hu.dg.service;

import com.hu.dg.domain.EmailRecord;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailRecordService.java 2016/06/14 13:49
 */
public interface EmailRecordService {

    void create(EmailRecord emailRecord);

    List<EmailRecord> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    EmailRecord findById(int id);
}