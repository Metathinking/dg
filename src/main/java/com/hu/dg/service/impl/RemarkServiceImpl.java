package com.hu.dg.service.impl;

import com.hu.dg.domain.Remark;
import com.hu.dg.domain.User;
import com.hu.dg.repository.RemarkRepository;
import com.hu.dg.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RemarkServiceImpl.java 2016/06/07 14:48
 */
@Service
public class RemarkServiceImpl implements RemarkService {

    @Autowired
    private RemarkRepository remarkRepository;

    public void remark(User user) {
        Remark remark = new Remark();
        int id = user.getId()*2+1;
        remark.setId(id);
        remark.setRemark(user.getRemark());
        remarkRepository.remark(remark);
    }
}