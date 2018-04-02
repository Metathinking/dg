package com.hu.dg.cache;

import com.hu.dg.domain.Column;
import com.hu.dg.domain.SiteInfo;
import com.hu.dg.domain.SiteSetting;
import com.hu.dg.service.ColumnService;
import com.hu.dg.service.SiteInfoService;
import com.hu.dg.service.SiteSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteCache.java 2016/07/05 10:47
 */
@Component
public class SiteCache {

    private static Map<String,Object> map = new HashMap<String, Object>();

    @Autowired
    private  SiteInfoService siteInfoService;
    @Autowired
    private  SiteSettingService siteSettingService;
    @Autowired
    private ColumnService columnService;

    private static SiteInfoService staticSiteInfoService;

    private static SiteSettingService staticSiteSettingService;

    private static ColumnService staticColumnService;

    @PostConstruct
    public void init(){
        staticSiteInfoService = this.siteInfoService;
        staticSiteSettingService=this.siteSettingService;
        staticColumnService = this.columnService;
    }

    public static void put(String key,Object value){
        map.put(key,value);
    }

    public static final String SITE_INFO="siteInfo";
    public static final String SITE_SETTING="siteSetting";
    public static final String COLUMN_LIST = "columnList";

    public static SiteInfo getSiteInfo(){
        Object object = map.get(SITE_INFO);
        if(object==null){
            SiteInfo siteInfo = staticSiteInfoService.find();
            map.put(SITE_INFO,siteInfo);
            return siteInfo;
        }
        return (SiteInfo) object;
    }

    public static SiteSetting getSiteSetting(){
        Object object = map.get(SITE_SETTING);
        if(object==null){
            SiteSetting siteSetting = staticSiteSettingService.find();
            map.put(SITE_SETTING,siteSetting);
            return siteSetting;
        }
        return (SiteSetting) object;
    }

    public static List<Column> getColumnList(){
        Object object = map.get(COLUMN_LIST);
        if(object==null){
            List<Column> list = staticColumnService.list();
            map.put(COLUMN_LIST,list);
            return list;
        }
        return (List<Column>) object;
    }
}