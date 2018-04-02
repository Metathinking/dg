package com.hu.dg.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) WelcomeController.java 2016/06/06 09:25
 */
@Controller
public class WelcomeController {

    @RequestMapping(value={"/","/welcome","search"},method = RequestMethod.GET)
    public String welcome(@RequestParam(value = "keywords", required = false) String keywords,
                          @RequestParam(value = "index", required = false) Integer index,
                          Model model) {
        model.addAttribute("keywords", keywords);
        if (index == null) {
            index = 1;
        }
        model.addAttribute("index", index);
        return "front/welcome";
    }

}