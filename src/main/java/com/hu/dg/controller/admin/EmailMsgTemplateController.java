package com.hu.dg.controller.admin;

import com.hu.dg.domain.EmailMsgTemplate;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.EmailMsgTemplateService;
import com.hu.dg.type.EmailMsgType;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailMsgTemplateController.java 2016/06/15 09:28
 */
@Controller
@RequestMapping("admin/emailMsgTemplate")
public class EmailMsgTemplateController {

    @Autowired
    private EmailMsgTemplateService templateService;

    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String gotoEdit(@RequestParam(value="id",required = false) Integer id , Model model){
        if(id==null){
            id=-1;
        }
        model.addAttribute("id",id);
        return "admin/email-msg-template-edit";
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(@RequestBody EmailMsgTemplate emailMsgTemplate){
        try {
            templateService.edit(emailMsgTemplate);
            return new Tip(true,100,"模板添加成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="findById",method = RequestMethod.POST)
    @ResponseBody
    public Tip findById(@RequestParam int id){
        try {
            EmailMsgTemplate msgTemplate = templateService.findById(id);
            if(msgTemplate==null){
                msgTemplate=  new EmailMsgTemplate();
            }
            return new Tip(true,100,"查询成功",msgTemplate);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value="setDefault",method = RequestMethod.POST)
    @ResponseBody
    public Tip setDefault(@RequestParam int id){
        try {
            templateService.setDefault(id);
            return new Tip(true,100,"查询成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String gotoList(){
        return "admin/email-msg-template-list";
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody PageQuery query){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<EmailMsgTemplate> list = templateService.list(map);
            for(EmailMsgTemplate template:list){
                template.setType(EmailMsgType.valueOf(template.getType()).getDescription());
            }
            int count = templateService.getCount(map);
            query.setCount(count);
            Map<String,Object> backMap = new HashMap<String, Object>();
            backMap.put("list",list);
            backMap.put("pageQuery",query);
            return new Tip(true,100,"查询成功",backMap);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "getTypes",method = RequestMethod.POST)
    @ResponseBody
    public Tip getTypes(){
        EmailMsgType[] values = EmailMsgType.values();
        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        for(EmailMsgType type:values){
            Map<String,String> map = new HashMap<String, String>();
            map.put("name",type.name());
            map.put("description",type.getDescription());
            list.add(map);
        }
        return new Tip(true,100,"查询成功",list);
    }
}