package com.hu.dg.service.impl;

import com.hu.dg.domain.SiteInfo;
import com.hu.dg.repository.SiteInfoRepository;
import com.hu.dg.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteInfoServiceImpl.java 2016/07/03 22:00
 */
@Service
public class SiteInfoServiceImpl implements SiteInfoService {

    @Autowired
    private SiteInfoRepository siteInfoRepository;

    private final int id=9527;

    public SiteInfo edit(SiteInfo siteInfo) {
        SiteInfo dbSiteInfo = siteInfoRepository.findById(id);
        if(dbSiteInfo==null){
            siteInfo.setId(id);
            siteInfo.setTime(System.currentTimeMillis());
            siteInfoRepository.create(siteInfo);
            return siteInfo;
        }else{
            dbSiteInfo.setName(siteInfo.getName());
            dbSiteInfo.setKeywords(siteInfo.getKeywords());
            dbSiteInfo.setDescription(siteInfo.getDescription());
            dbSiteInfo.setIcon(siteInfo.getIcon());
            dbSiteInfo.setFooter(siteInfo.getFooter());
            dbSiteInfo.setUrl(siteInfo.getUrl());
            dbSiteInfo.setTime(System.currentTimeMillis());
            siteInfoRepository.update(dbSiteInfo);
            return dbSiteInfo;
        }
    }

    public SiteInfo find() {
        return siteInfoRepository.findById(id);
    }
}