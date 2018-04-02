package com.hu.dg.controller.front;

import com.hu.dg.domain.User;
import com.hu.dg.domain.bo.UserBO;
import com.hu.dg.domain.vo.UserVO;
import com.hu.dg.service.UserService;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Md5Factory;
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
 * @(#) LoginController.java 2016/06/06 17:14
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private final String USER = "USER";
    private final String USER_VO = "USER_VO";

    @RequestMapping(value = "login", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String gotoLogin(HttpSession session, HttpServletRequest request) {
        if (session.getAttribute(USER) != null) {
            String contextPath = request.getContextPath();
            Object prePageObject = session.getAttribute("prePage");
            if (prePageObject == null) {
                return "redirect:/welcome.html";
            }
            String prePage = (String) prePageObject;
            System.out.println("contextPath:" + contextPath + "\nprePage:" + prePage);
            return "redirect:" + prePage;
        }
        return "front/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Tip login(@RequestBody UserBO user, HttpSession session, HttpServletRequest request) {
        if (StringUtils.isEmpty(user.getNickname())) {
            return new Tip(false, 101, "请输入用户名或邮箱", null);
        }
        if (StringUtils.isEmpty(user.getPassword()) || user.getPassword().equals(Md5Factory.encoding(""))) {
            return new Tip(false, 102, "请输入密码", null);
        }
        Object codeObject = session.getAttribute("CODE");
        if (codeObject == null) {
            return new Tip(false, 104, "验证码失效，请刷新验证码", null);
        }
        if (StringUtils.isEmpty(user.getCode())) {
            return new Tip(false, 103, "请输入验证码", null);
        }
        String code = (String) codeObject;
        if (!user.getCode().equalsIgnoreCase(code)) {
            return new Tip(false, 105, "验证码错误", null);
        }
        try {

            UserVO userVO = userService.login(user, request);
            session.setAttribute(USER, userVO.getUser());
            session.setAttribute(USER_VO, userVO);
            return new Tip(true, 100, "登录成功", null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.setAttribute(USER, null);
        return "redirect:/welcome.html";
    }

}