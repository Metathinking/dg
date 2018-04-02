package com.hu.dg.service.impl;

import com.hu.dg.domain.AdminLoginLog;
import com.hu.dg.repository.AdminLoginLogRepository;
import com.hu.dg.service.AdminLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) AdminLoginLogServiceImpl.java 2016/07/04 22:46
 */
@Service
public class AdminLoginLogServiceImpl implements AdminLoginLogService {

    @Autowired
    private AdminLoginLogRepository adminLoginLogRepository;

    public List<AdminLoginLog> list(Map<String, Object> params) {
        return adminLoginLogRepository.list(params);
    }

    public int getCount() {
        return adminLoginLogRepository.getCount();
    }
}