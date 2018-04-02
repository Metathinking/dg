package com.hu.dg.service;

import com.hu.dg.domain.EmailSetting;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailSettingService.java 2016/06/13 18:42
 */
public interface EmailSettingService {

    void edit(EmailSetting emailSetting);

    List<EmailSetting> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    EmailSetting getDefault();
}