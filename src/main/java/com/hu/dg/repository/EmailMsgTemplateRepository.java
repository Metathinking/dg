package com.hu.dg.repository;

import com.hu.dg.domain.EmailMsgTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailMsgTemplateRepository.java 2016/06/15 08:55
 */
@Repository
public interface EmailMsgTemplateRepository {

    void create(EmailMsgTemplate emailMsgTemplate);

    void update(EmailMsgTemplate emailMsgTemplate);

    void delete(int id);

    List<EmailMsgTemplate> list(Map<String,Object> params);

    EmailMsgTemplate findById(int id);

    int getCount(Map<String,Object> params);

    int getMaxId();

    List<EmailMsgTemplate> listOfUseByType(String type);
}