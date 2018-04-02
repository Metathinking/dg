package com.hu.dg.service.impl;

import com.hu.dg.domain.SiteSetting;
import com.hu.dg.repository.SiteSettingRepository;
import com.hu.dg.service.SiteSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteSettingServiceImpl.java 2016/07/03 22:08
 */
@Service
public class SiteSettingServiceImpl implements SiteSettingService {

    @Autowired
    private SiteSettingRepository siteSettingRepository;

    private final int id=9527;

    public SiteSetting edit(SiteSetting siteSetting) {
        SiteSetting dbSiteSetting = siteSettingRepository.findById(id);
        if(dbSiteSetting==null){
            siteSetting.setId(id);
            siteSetting.setTime(System.currentTimeMillis());
            siteSettingRepository.create(siteSetting);
            return siteSetting;
        }else{
            dbSiteSetting.setArticleCommentCount(siteSetting.getArticleCommentCount());
            dbSiteSetting.setMainArticleCount(siteSetting.getMainArticleCount());
            dbSiteSetting.setArticlePageSize(siteSetting.getArticlePageSize());
            dbSiteSetting.setCommentPageSize(siteSetting.getCommentPageSize());
            dbSiteSetting.setTime(siteSetting.getTime());
            dbSiteSetting.setOverTime(siteSetting.getOverTime());
            siteSettingRepository.update(dbSiteSetting);
            return dbSiteSetting;
        }
    }

    public SiteSetting find() {
        SiteSetting setting = siteSettingRepository.findById(id);
        if(setting==null){
            setting=new SiteSetting();
            setting.setId(id);
            setting.setMainArticleCount(12);
            setting.setArticleCommentCount(12);
            setting.setArticlePageSize(20);
            setting.setCommentPageSize(30);
            setting.setOverTime(30*60*1000);
            setting.setTime(System.currentTimeMillis());
            siteSettingRepository.create(setting);
        }
        return setting;
    }
}