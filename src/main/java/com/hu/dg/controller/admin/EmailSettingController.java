package com.hu.dg.controller.admin;

import com.hu.dg.domain.EmailSetting;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.EmailSettingService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailController.java 2016/06/13 15:55
 */
@Controller
@RequestMapping("admin/emailSetting")
public class EmailSettingController {

    @Autowired
    private EmailSettingService emailSettingService;

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String gotoSetting() {
        return "admin/email-setting-edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(@RequestBody EmailSetting emailSetting) {
        try {
            if (StringUtils.isEmpty(emailSetting.getHostName())) {
                return new Tip(false, 101, "请设置邮件服务器地址", null);
            }
            if(emailSetting.getSmtpPort()==0){
                return new Tip(false,102,"请设置邮件服务器端口号",null);
            }
            if (StringUtils.isEmpty(emailSetting.getAuthenticationName())) {
                return new Tip(false, 103, "请设置发送邮箱", null);
            }
            if (StringUtils.isEmpty(emailSetting.getCharset())) {
                return new Tip(false, 104, "请设置编码方式", null);
            }
            if (StringUtils.isEmpty(emailSetting.getSendPerson())) {
                return new Tip(false, 105, "请添加发送人", null);
            }
            emailSettingService.edit(emailSetting);
            Map<String, Object> backMap = getList(new PageQuery());
            return new Tip(true, 100, "保存成功", backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody PageQuery query) {
        try {
            Map<String, Object> backMap = getList(query);
            return new Tip(true,100,"查询成功",backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="getDefault",method = RequestMethod.POST)
    @ResponseBody
    public Tip getDefault(){
        try {
            EmailSetting emailSetting = emailSettingService.getDefault();
            return new Tip(true,100,"查询成功",emailSetting);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    private Map<String,Object> getList(PageQuery query){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start",query.getStart());
        map.put("size",query.getSize());
        List<EmailSetting> list = emailSettingService.list(map);
        int count = emailSettingService.getCount(map);
        query.setCount(count);
        Map<String,Object> backMap = new HashMap<String, Object>();
        backMap.put("list",list);
        backMap.put("pageQuery",query);
        return backMap;
    }
}