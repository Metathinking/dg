package com.hu.dg.controller.admin;

import com.hu.dg.domain.LoginLog;
import com.hu.dg.query.PageQuery;
import com.hu.dg.service.LoginLogService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @(#) LoginLogController.java 2016/06/09 09:00
 */
@Controller
@RequestMapping("admin/loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String gotoLoginLog(){
        return "admin/login-log";
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public Tip list(@RequestBody PageQuery query){
        try {
//            query.setSize(10);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("start",query.getStart());
            map.put("size",query.getSize());
            List<LoginLog> list = loginLogService.list(map);
            int count = loginLogService.getCount(map);
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