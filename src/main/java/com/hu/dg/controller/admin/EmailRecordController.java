package com.hu.dg.controller.admin;

import com.hu.dg.domain.EmailRecord;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.EmailRecordService;
import com.hu.dg.util.EmailUtil;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailRecordController.java 2016/06/14 13:52
 */
@Controller
@RequestMapping("admin/email")
public class EmailRecordController {

    @Autowired
    private EmailRecordService emailRecordService;

    @RequestMapping(value = "send",method = RequestMethod.GET)
    public String gotoSend(@RequestParam(value="id",required = false)Integer id,Model model){
        if(id==null){
            id=-1;
        }
        model.addAttribute("id",id);
        return "admin/email-send";
    }

    @RequestMapping(value = "send",method = RequestMethod.POST)
    @ResponseBody
    public Tip sendEmail(@RequestBody EmailRecord emailRecord){
        try {
            EmailUtil.send(emailRecord);
            emailRecordService.create(emailRecord);
            return new Tip(true,100,"发送成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "findById",method = RequestMethod.POST)
    @ResponseBody
    public Tip findById(@RequestParam int id){
        try {
            EmailRecord emailRecord = emailRecordService.findById(id);
            if(emailRecord==null){
                emailRecord=  new EmailRecord();
            }
            return new Tip(true,100,"查询成功",emailRecord);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="list",method = RequestMethod.GET)
    public String gotoList(){
        return "admin/email-list";
    }

    @RequestMapping(value="list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody PageQuery query){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<EmailRecord> list = emailRecordService.list(map);
            int count = emailRecordService.getCount(map);
            query.setCount(count);
            Map<String,Object> backMap = new HashMap<String, Object>();
            backMap.put("list",list);
            backMap.put("pageQuery",query);
            return new Tip(true,100,"查询成功",backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }
}