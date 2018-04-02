package com.hu.dg.service;

import com.hu.dg.domain.EmailMsgTemplate;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailMsgTemplateService.java 2016/06/15 08:57
 */
public interface EmailMsgTemplateService {

    void edit(EmailMsgTemplate emailMsgTemplate);

    void delete(int id);

    List<EmailMsgTemplate> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    EmailMsgTemplate findById(int id);

    void setDefault(int id);

    EmailMsgTemplate findDefaultByType(String type);
}