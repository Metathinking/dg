package com.hu.dg.controller.user;

import com.hu.dg.domain.User;
import com.hu.dg.domain.UserMsg;
import com.hu.dg.domain.vo.UserVO;
import com.hu.dg.service.UserMsgService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) UserMsgController.java 2016/07/09 21:49
 */
@Controller
@RequestMapping("user/userMsg")
public class UserMsgController {

    @Autowired
    private UserMsgService userMsgService;

    private final double notContent = -1;

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String gotoEdit() {
        return "user/user-msg-edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(@RequestBody UserMsg userMsg, HttpSession session) {
        try {
            User user = (User) session.getAttribute("USER");
            userMsg.setId(user.getId());
            if(StringUtils.isEmpty(userMsg.getIcon())){
                userMsg.setIcon("");
            }
            if(StringUtils.isEmpty(userMsg.getIntroduction())){
                userMsg.setIntroduction("这是一个好人");
            }
            if(StringUtils.isEmpty(userMsg.getHeight())){
                userMsg.setHeight(notContent);
            }
            if(StringUtils.isEmpty(userMsg.getWeight())){
                userMsg.setWeight(notContent);
            }
            if(StringUtils.isEmpty(userMsg.getBust())){
                userMsg.setBust(notContent);
            }
            if(StringUtils.isEmpty(userMsg.getWaist())){
                userMsg.setWaist(notContent);
            }
            if(StringUtils.isEmpty(userMsg.getHips())){
                userMsg.setHips(notContent);
            }
            UserMsg dbUserMsg = userMsgService.edit(userMsg);
            UserVO userVO = (UserVO) session.getAttribute("USER_VO");
            userVO.setUserMsg(dbUserMsg);
            return new Tip(true,100,"更新成功",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "find",method = RequestMethod.POST)
    @ResponseBody
    public Tip find(HttpSession session){
        try {
            User user = (User) session.getAttribute("USER");
            UserMsg userMsg = userMsgService.findById(user.getId());
            return new Tip(true,100,"查询成功",userMsg);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }
}