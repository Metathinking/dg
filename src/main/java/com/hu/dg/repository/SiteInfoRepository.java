package com.hu.dg.repository;

import com.hu.dg.domain.SiteInfo;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteInfoRepository.java 2016/07/03 21:57
 */
@Repository
public interface SiteInfoRepository {

    void create(SiteInfo siteInfo);

    void update(SiteInfo siteInfo);

    SiteInfo findById(int id);
}