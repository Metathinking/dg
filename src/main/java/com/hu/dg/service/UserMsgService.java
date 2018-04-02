package com.hu.dg.service;

import com.hu.dg.domain.UserMsg;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserMsgService.java 2016/07/09 21:42
 */
public interface UserMsgService {

    UserMsg edit(UserMsg userMsg);

    UserMsg findById(int id);
}