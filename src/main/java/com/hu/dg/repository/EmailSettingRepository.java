package com.hu.dg.repository;

import com.hu.dg.domain.EmailSetting;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailSettingRepository.java 2016/06/13 18:41
 */
@Repository
public interface EmailSettingRepository {

    void create(EmailSetting emailSetting);

    void update(EmailSetting emailSetting);

    List<EmailSetting> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    int getMaxId();

    EmailSetting findById(int id);

    List<EmailSetting> listByUse();

}