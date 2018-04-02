package com.hu.dg.service;

import com.hu.dg.domain.Admin;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) AdminService.java 2016/07/04 22:12
 */
public interface AdminService {

    Admin login(Admin admin,String ip);
}