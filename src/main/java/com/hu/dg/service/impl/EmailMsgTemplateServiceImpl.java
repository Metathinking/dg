package com.hu.dg.service.impl;

import com.hu.dg.domain.EmailMsgTemplate;
import com.hu.dg.repository.EmailMsgTemplateRepository;
import com.hu.dg.service.EmailMsgTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailMsgTemplateServiceImpl.java 2016/06/15 09:01
 */
@Service
public class EmailMsgTemplateServiceImpl implements EmailMsgTemplateService {

    @Autowired
    private EmailMsgTemplateRepository templateRepository;

    public void edit(EmailMsgTemplate emailMsgTemplate) {
        if (emailMsgTemplate.getId() == 0) {
            save(emailMsgTemplate);
        } else {
            EmailMsgTemplate dbTemplate = templateRepository.findById(emailMsgTemplate.getId());
            if (dbTemplate == null) {
                save(emailMsgTemplate);
            } else {
                dbTemplate.setType(emailMsgTemplate.getType());
                dbTemplate.setSubject(emailMsgTemplate.getSubject());
                dbTemplate.setDescription(emailMsgTemplate.getDescription());
                dbTemplate.setContent(emailMsgTemplate.getContent());
                dbTemplate.setUse(emailMsgTemplate.isUse());
                dbTemplate.setTime(System.currentTimeMillis());
                templateRepository.update(dbTemplate);
            }
        }
        if (emailMsgTemplate.isUse()) {
           resetOtherTemplate(emailMsgTemplate);
        }
    }

    private void save(EmailMsgTemplate emailMsgTemplate) {
        int maxId = templateRepository.getMaxId();
        maxId++;
        emailMsgTemplate.setId(maxId);
        emailMsgTemplate.setTime(System.currentTimeMillis());
        templateRepository.create(emailMsgTemplate);
    }

    public void delete(int id) {
        templateRepository.delete(id);
    }

    public List<EmailMsgTemplate> list(Map<String, Object> params) {
        return templateRepository.list(params);
    }

    public int getCount(Map<String, Object> params) {
        return templateRepository.getCount(params);
    }

    public EmailMsgTemplate findById(int id) {
        return templateRepository.findById(id);
    }

    public void setDefault(int id) {
        EmailMsgTemplate emailMsgTemplate = templateRepository.findById(id);
        emailMsgTemplate.setUse(true);
        templateRepository.update(emailMsgTemplate);

        resetOtherTemplate(emailMsgTemplate);
    }

    private void resetOtherTemplate(EmailMsgTemplate emailMsgTemplate){
        List<EmailMsgTemplate> list = templateRepository.listOfUseByType(emailMsgTemplate.getType());
        for (EmailMsgTemplate template : list) {
            if (template.getId() == emailMsgTemplate.getId()) {
                continue;
            }
            template.setUse(false);
            templateRepository.update(template);
        }
    }

    public EmailMsgTemplate findDefaultByType(String type) {
         return templateRepository.listOfUseByType(type).get(0);
    }
}