package com.hu.dg.controller.admin;

import com.hu.dg.cache.SiteCache;
import com.hu.dg.domain.SiteSetting;
import com.hu.dg.service.SiteSettingService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) SiteSettingController.java 2016/07/03 22:25
 */
@Controller
@RequestMapping("admin/siteSetting")
public class SiteSettingController {

    @Autowired
    private SiteSettingService siteSettingService;

    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String gotoEdit(){
        return "admin/site-setting-edit";
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(@RequestBody SiteSetting siteSetting, HttpSession session){
        try{
            if(siteSetting.getMainArticleCount()<12){
                siteSetting.setMainArticleCount(12);
            }
            if(siteSetting.getArticleCommentCount()<12){
                siteSetting.setArticleCommentCount(12);
            }
            if(siteSetting.getArticlePageSize()<20){
                siteSetting.setArticlePageSize(20);
            }
            if(siteSetting.getCommentPageSize()<30){
                siteSetting.setCommentPageSize(30);
            }
            if(siteSetting.getOverTime()<(30*60*1000)){
                siteSetting.setOverTime(30*60*1000);
            }
           SiteSetting dbSiteSetting= siteSettingService.edit(siteSetting);
            updateSessionAndCache(session,dbSiteSetting);
            return new Tip(true,100,"更新成功",dbSiteSetting);
        }catch (Exception e){
            return ExceptionTipHandler.handler(e);
        }
    }

    private void updateSessionAndCache(HttpSession session,SiteSetting siteSetting){
        SiteCache.put(SiteCache.SITE_SETTING,siteSetting);
        session.setAttribute(SiteCache.SITE_SETTING,siteSetting);
    }

    @RequestMapping(value = "find",method = RequestMethod.POST)
    @ResponseBody
    public Tip find(){
        try{
            SiteSetting siteSetting = siteSettingService.find();
            return new Tip(true,100,"查询成功",siteSetting);
        }catch (Exception e){
            return ExceptionTipHandler.handler(e);
        }
    }
}