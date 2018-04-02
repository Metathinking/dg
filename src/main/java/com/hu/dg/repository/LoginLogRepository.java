package com.hu.dg.repository;

import com.hu.dg.domain.LoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) LoginLogRepository.java 2016/06/07 15:25
 */
@Repository
public interface LoginLogRepository {

    void create(LoginLog loginLog);

    List<LoginLog> list(Map<String,Object> params);

    int getCount(Map<String,Object> params);

    int getMaxId();
}