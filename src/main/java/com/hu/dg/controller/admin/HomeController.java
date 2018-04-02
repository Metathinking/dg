package com.hu.dg.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) HomeController.java 2016/06/08 14:11
 */
@Controller
@RequestMapping("admin")
public class HomeController {

    @RequestMapping(value = "home",method = RequestMethod.GET)
    public String gotoHome(){
        return "admin/user-manager";
    }
}