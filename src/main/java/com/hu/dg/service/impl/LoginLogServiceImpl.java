package com.hu.dg.service.impl;

import com.hu.dg.domain.LoginLog;
import com.hu.dg.repository.LoginLogRepository;
import com.hu.dg.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) LoginLogServiceImpl.java 2016/06/07 15:25
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogRepository loginLogRepository;

    public List<LoginLog> list(Map<String, Object> params) {
        return loginLogRepository.list(params);
    }

    public int getCount(Map<String, Object> params) {
        return loginLogRepository.getCount(params);
    }
}