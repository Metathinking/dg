package com.hu.dg.repository;

import com.hu.dg.domain.EmailValidate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailValidateRepository.java 2016/06/16 10:30
 */
@Repository
public interface EmailValidateRepository {

    void create(EmailValidate emailValidate);

    void update(EmailValidate emailValidate);

    void delete(int id);

    EmailValidate findById(int id);

    int getMaxId();

    int getCount(Map<String,Object> params);
}