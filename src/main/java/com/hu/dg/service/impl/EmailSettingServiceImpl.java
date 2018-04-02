package com.hu.dg.service.impl;

import com.hu.dg.domain.EmailSetting;
import com.hu.dg.repository.EmailSettingRepository;
import com.hu.dg.service.EmailSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailSettingServiceImpl.java 2016/06/13 18:43
 */
@Service
public class EmailSettingServiceImpl implements EmailSettingService {

    @Autowired
    private EmailSettingRepository emailSettingRepository;

    /**
     * 编辑
     * @param emailSetting
     */
    public void edit(EmailSetting emailSetting) {
        if (emailSetting.getId()<=0){
          save(emailSetting);
        }else{
            EmailSetting dbEmailSetting = emailSettingRepository.findById(emailSetting.getId());
            if(dbEmailSetting==null){
                save(emailSetting);
            }else{
                dbEmailSetting.setHostName(emailSetting.getHostName());
                dbEmailSetting.setSmtpPort(emailSetting.getSmtpPort());
                dbEmailSetting.setAuthenticationName(emailSetting.getAuthenticationName());
                dbEmailSetting.setAuthenticationPassword(emailSetting.getAuthenticationPassword());
                dbEmailSetting.setCharset(emailSetting.getCharset());
                dbEmailSetting.setSendPerson(emailSetting.getSendPerson());
                dbEmailSetting.setUse(emailSetting.isUse());
                emailSettingRepository.update(dbEmailSetting);
            }
        }
        if(emailSetting.isUse()){
            List<EmailSetting> emailSettingList = emailSettingRepository.listByUse();
            if(emailSettingList!=null&&!emailSettingList.isEmpty()){
                for(EmailSetting setting:emailSettingList){
                    if(setting.getId()==emailSetting.getId()){
                        continue;
                    }
                    setting.setUse(false);
                    emailSettingRepository.update(setting);
                }
            }
        }
    }

    private void save(EmailSetting emailSetting){
        int maxId = emailSettingRepository.getMaxId();
        maxId++;
        emailSetting.setId(maxId);
        emailSetting.setTime(System.currentTimeMillis());
        emailSettingRepository.create(emailSetting);
    }

    public List<EmailSetting> list(Map<String, Object> params) {
        List<EmailSetting> list = emailSettingRepository.list(params);
        return list;
    }

    public int getCount(Map<String, Object> params) {
        return emailSettingRepository.getCount(params);
    }

    public EmailSetting getDefault() {
        List<EmailSetting> emailSettingList = emailSettingRepository.listByUse();
        if(emailSettingList.isEmpty()){
            return new EmailSetting();
        }
        return emailSettingList.get(0);
    }
}