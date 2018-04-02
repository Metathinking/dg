package com.hu.dg.service;

import com.hu.dg.domain.EmailValidate;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailValidateService.java 2016/06/16 12:34
 */
public interface EmailValidateService {

    EmailValidate findById(int id);

    EmailValidate create(int userId);

    void validate(int id);
}