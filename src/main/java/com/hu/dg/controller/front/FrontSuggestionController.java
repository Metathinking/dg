package com.hu.dg.controller.front;

import com.hu.dg.domain.Suggestion;
import com.hu.dg.domain.User;
import com.hu.dg.service.SuggestionService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) FrontSuggestionController.java 2016/07/03 17:46
 */
@Controller
@RequestMapping("suggestion")
public class FrontSuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String gotoEdit(){
        return "front/suggestion-edit";
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(@RequestBody Suggestion suggestion, HttpSession session, HttpServletRequest request){
        try{
            if(StringUtils.isEmpty(suggestion.getTitle())){
                return new Tip(false,101,"请输入标题",null);
            }
            if(StringUtils.isEmpty(suggestion.getContent())){
                return new Tip(false,102,"请填写您的建议",null);
            }
            if(StringUtils.isEmpty(suggestion.getContactWay())){
                suggestion.setContactWay("");
            }
            User user = null;
            Object userObj = session.getAttribute("USER");
            if(userObj!=null){
                user= (User) userObj;
            }
            suggestionService.create(suggestion,user,request);
            return new Tip(true,100,"非常感谢您的反馈，我们会努力做的更好的",null);
        }catch (Exception e){
            return ExceptionTipHandler.handler(e);
        }
    }
}