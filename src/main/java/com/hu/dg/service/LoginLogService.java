package com.hu.dg.service;

import com.hu.dg.domain.LoginLog;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) LoginLogService.java 2016/06/07 15:24
 */
public interface LoginLogService {

    List<LoginLog> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);
}