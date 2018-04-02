package com.hu.dg.interceptor;

import com.hu.dg.cache.SiteCache;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 添加网站基础设置信息
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) BaseInfoInterceptor.java 2016/07/05 11:07
 */
public class BaseInfoInterceptor implements HandlerInterceptor {

    private final String SITE_INFO = "siteInfo";
    private final String SITE_SETTING = "siteSetting";
    public static final String COLUMN_LIST = "columnList";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(SITE_INFO) == null) {
            session.setAttribute(SITE_INFO, SiteCache.getSiteInfo());
        }
        if (session.getAttribute(SITE_SETTING) == null) {
            session.setAttribute(SITE_SETTING, SiteCache.getSiteSetting());
        }
        if(session.getAttribute(COLUMN_LIST)==null){
            session.setAttribute(COLUMN_LIST,SiteCache.getColumnList());
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}