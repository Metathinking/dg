package com.hu.dg.service.impl;

import com.hu.dg.domain.EmailValidate;
import com.hu.dg.repository.EmailValidateRepository;
import com.hu.dg.service.EmailValidateService;
import com.hu.dg.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailValidateServiceImpl.java 2016/06/16 12:35
 */
@Service
public class EmailValidateServiceImpl implements EmailValidateService {

    @Autowired
    private EmailValidateRepository emailValidateRepository;

    public EmailValidate findById(int id) {
        return emailValidateRepository.findById(id);
    }

    public EmailValidate create(int userId) {
        EmailValidate emailValidate = new EmailValidate();
        int emailValidateRepositoryMaxId = emailValidateRepository.getMaxId();
        emailValidateRepositoryMaxId++;
        emailValidate.setId(emailValidateRepositoryMaxId);
        emailValidate.setUserId(userId);
        emailValidate.setCode(CodeUtil.getRandomCode());
        emailValidate.setTime(System.currentTimeMillis());
        emailValidate.setValidated(false);
        emailValidateRepository.create(emailValidate);
        return emailValidate;
    }

    public void validate(int id) {
        EmailValidate validate = emailValidateRepository.findById(id);
        if(validate==null){
            // TODO: 2016/6/16.0016  系统错误
        }else{
            validate.setValidated(true);
            emailValidateRepository.update(validate);
        }
    }
}