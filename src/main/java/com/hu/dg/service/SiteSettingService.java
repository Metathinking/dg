package com.hu.dg.service;

import com.hu.dg.domain.SiteSetting;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteSettingService.java 2016/07/03 22:00
 */
public interface SiteSettingService {

    SiteSetting edit(SiteSetting siteSetting);

    SiteSetting find();
}