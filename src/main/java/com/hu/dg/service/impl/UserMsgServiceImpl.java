package com.hu.dg.service.impl;

import com.hu.dg.domain.UserMsg;
import com.hu.dg.repository.UserMsgRepository;
import com.hu.dg.service.UserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserMsgServiceImpl.java 2016/07/09 21:42
 */
@Service
public class UserMsgServiceImpl implements UserMsgService {

    @Autowired
    private UserMsgRepository userMsgRepository;


    public UserMsg edit(UserMsg userMsg) {
        UserMsg dbUserMsg = userMsgRepository.findById(userMsg.getId());
        if (dbUserMsg == null) {
            userMsgRepository.create(userMsg);
            return userMsg;
        } else {
            dbUserMsg.setIcon(userMsg.getIcon());
            dbUserMsg.setIntroduction(userMsg.getIntroduction());
            dbUserMsg.setSex(userMsg.getSex());
            dbUserMsg.setHeight(userMsg.getHeight());
            dbUserMsg.setWeight(userMsg.getWeight());
            dbUserMsg.setBust(userMsg.getBust());
            dbUserMsg.setWaist(userMsg.getWaist());
            dbUserMsg.setHips(userMsg.getHips());
            userMsgRepository.update(dbUserMsg);
            return dbUserMsg;
        }
    }

    public UserMsg findById(int id) {
        return userMsgRepository.findById(id);
    }
}