package com.hu.dg.repository;

import com.hu.dg.domain.SiteSetting;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteSetting.java 2016/07/03 21:58
 */
@Repository
public interface SiteSettingRepository {

    void create(SiteSetting siteSetting);

    void update(SiteSetting siteSetting);

    SiteSetting findById(int id);
}