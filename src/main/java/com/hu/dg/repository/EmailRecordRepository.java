package com.hu.dg.repository;

import com.hu.dg.domain.EmailRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailRecordRepository.java 2016/06/14 13:47
 */
@Repository
public interface EmailRecordRepository {

    void create(EmailRecord emailRecord);

    List<EmailRecord> list(Map<String,Object> params);

    EmailRecord findById(int id);

    int getCount(Map<String,Object> params);

    int getMaxId();
}