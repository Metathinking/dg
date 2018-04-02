package com.hu.dg.service.impl;

import com.hu.dg.domain.Admin;
import com.hu.dg.domain.AdminLoginLog;
import com.hu.dg.exception.ServiceException;
import com.hu.dg.repository.AdminLoginLogRepository;
import com.hu.dg.repository.AdminRepository;
import com.hu.dg.service.AdminService;
import com.hu.dg.util.Md5Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) AdminServiceImpl.java 2016/07/04 22:12
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private final String id="renshuang";
    private final String pw="13754678850.";

    @Autowired
    private AdminLoginLogRepository adminLoginLogRepository;

    public Admin login(Admin admin,String ip) {
        int count = adminRepository.getCount();
        if(count==0){
            Admin newAdmin = new Admin();
            newAdmin.setId(id);
            newAdmin.setPassword(Md5Factory.encoding(pw));
            adminRepository.create(newAdmin);
        }
        Admin dbAdmin = adminRepository.find(admin.getId(), admin.getPassword());
        if(dbAdmin==null){
            throw new ServiceException(201,"用户名或密码错误");
        }else{
            createLoginLog(admin,ip);
            admin.setPassword("");
            return admin;
        }
    }

    private void createLoginLog(Admin admin,String ip) {
        AdminLoginLog adminLoginLog = new AdminLoginLog();
        int maxId = adminLoginLogRepository.getMaxId();
        maxId++;
        adminLoginLog.setId(maxId);
        adminLoginLog.setUserId(admin.getId());
        adminLoginLog.setTime(System.currentTimeMillis());
        adminLoginLog.setIp(ip);
        adminLoginLogRepository.create(adminLoginLog);
    }
}