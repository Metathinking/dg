package com.hu.dg.service;

import com.hu.dg.domain.AdminLoginLog;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) AdminLoginLogService.java 2016/07/04 22:45
 */
public interface AdminLoginLogService {

    List<AdminLoginLog> list(Map<String,Object> params);

    int getCount();
}