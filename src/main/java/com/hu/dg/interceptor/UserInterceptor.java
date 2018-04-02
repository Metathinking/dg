package com.hu.dg.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserInterceptor.java 2016/06/17 17:23
 */
public class UserInterceptor implements HandlerInterceptor {

    private final String TIME = "TIME";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("USER");
        String contextPath = request.getContextPath();
        if (user == null) {
            response.sendRedirect(contextPath+"/login.html");
            return false;
        } else {
            if (session.getAttribute(TIME) == null) {
                session.setAttribute(TIME, System.currentTimeMillis());
            } else {
                Long preTime = (Long) session.getAttribute(TIME);
                long now = System.currentTimeMillis();
                // TODO: 2016/6/17.0017 系统设置登录超时
                if ((now - preTime )> (30 * 60 * 1000)) {
                    session.setAttribute("USER",null);
                    session.setAttribute(TIME,null);
                    response.sendRedirect(contextPath+"/login.html");
                    return false;
                } else {
                    session.setAttribute(TIME, now);
                }
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}