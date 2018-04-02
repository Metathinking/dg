package com.hu.dg.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) LoginInterceptor.java 2016/07/12 10:12
 */
public class LoginInterceptor implements HandlerInterceptor {


    private final String PRE_PAGE="prePage";

    private final String LOGIN_URI="/login.html";

    private final String REGISTER_URI="/register.html";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        if("GET".equals(request.getMethod())){
           String requestURI = getFullUri(request);
            if(requestURI.contains(".rest")){
                return true;
            }
            if(requestURI.startsWith(contextPath+"/admin")||
                    requestURI.startsWith(contextPath+"/user")||
                    requestURI.startsWith(contextPath+"/res")||
                    requestURI.equals(contextPath+"/")){
                return true;
            }
            if(requestURI.equals(contextPath+LOGIN_URI)||requestURI.equals(contextPath+REGISTER_URI)){
                return true;
            }
            String substring = requestURI.substring( contextPath.length(),requestURI.length());
            session.setAttribute(PRE_PAGE,substring);
        }
        return true;
    };

    private String getFullUri(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if(parameterMap.size()>0){
            requestURI+="?";
        }
        for(Map.Entry<String, String[]> entry:parameterMap.entrySet()){
            String key = entry.getKey();
            String[] value = entry.getValue();
            for(String string:value){
                requestURI+=key+"="+string+"&";
            }
        }
        return requestURI;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}