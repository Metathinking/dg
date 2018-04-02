package com.hu.dg.controller.front;

import com.hu.dg.domain.*;
import com.hu.dg.domain.bo.UserBO;
import com.hu.dg.service.*;
import com.hu.dg.type.EmailMsgType;
import com.hu.dg.util.EmailUtil;
import com.hu.dg.util.Tip;
import com.hu.dg.util.ValidateUtil;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) RegisterController.java 2016/06/06 17:44
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSettingService emailSettingService;

    @Autowired
    private EmailValidateService emailValidateService;

    @Autowired
    private EmailMsgTemplateService templateService;

    @Autowired
    private EmailRecordService emailRecordService;

    private final String USER = "USER";

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String gotoRegister(HttpSession session) {
        if (session.getAttribute(USER) != null) {
            Object prePageObject = session.getAttribute("prePage");
            if(prePageObject==null){
                return "redirect:/welcome.html";
            }
            String prePage = (String) prePageObject;
            return "redirect:"+prePage;
        }
        return "front/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Tip register(@RequestBody UserBO userBO) {
        if (StringUtils.isEmpty(userBO.getNickname())) {
            return new Tip(false, 101, "用户名为空", null);
        }
        if (userBO.getNickname().length() > 12 || userBO.getNickname().length() < 4) {
            return new Tip(false, 102, "用户名长度必须为4——12个字符之间", null);
        }
        if (StringUtils.isEmpty(userBO.getEmail())) {
            return new Tip(false, 108, "请输入邮箱", null);
        }
        if (!ValidateUtil.checkEmail(userBO.getEmail())) {
            return new Tip(false, 109, "邮箱格式不正确", null);
        }
        if (StringUtils.isEmpty(userBO.getPassword())) {
            return new Tip(false, 103, "密码不能为空", null);
        }
        if (StringUtils.isEmpty(userBO.getRePassword())) {
            return new Tip(false, 104, "确认密码不能为空", null);
        }
        if (!userBO.getPassword().endsWith(userBO.getRePassword())) {
            return new Tip(false, 105, "两次密码输入不一致", null);
        }
        if (userBO.getPassword().length() < 4 || userBO.getPassword().length() > 12) {
            return new Tip(false, 106, "密码长度必须在4——12个字符之间", null);
        }
        try {
            User user =  userService.register(userBO);
            sendEmailValidate(user);
            return new Tip(true, 100, "注册成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip(false, 107, e.getMessage(), null);
        }
    }

    private void sendEmailValidate( User user) throws EmailException {
        EmailValidate emailValidate = emailValidateService.create(user.getId());
        EmailSetting emailSetting = emailSettingService.getDefault();
        EmailMsgTemplate template = templateService.findDefaultByType(EmailMsgType.REGISTER.name());
        EmailRecord emailRecord = new EmailRecord();
        emailRecord.setHostName(emailSetting.getHostName());
        emailRecord.setSmtpPort(emailSetting.getSmtpPort());
        emailRecord.setAuthenticationName(emailSetting.getAuthenticationName());
        emailRecord.setAuthenticationPassword(emailSetting.getAuthenticationPassword());
        emailRecord.setCharset(emailSetting.getCharset());
        emailRecord.setToEmail(user.getEmail());
        emailRecord.setSubject(template.getSubject());
        emailRecord.setSendPerson(emailSetting.getSendPerson());
        // TODO: 2016/6/16.0016 服务器地址
        String linkAddress = "http://localhost:8080/emailValidate.html?user="+emailValidate.getUserId()
                +"&validate="+emailValidate.getId()+"&code="+emailValidate.getCode();
        String link = "<a href='"+linkAddress+"'>"+linkAddress+"</a>";
        String htmlMsg = template.getContent().replaceAll("\\{info\\}", link);
        emailRecord.setHtmlMsg(htmlMsg);
        EmailUtil.send(emailRecord);
        emailRecordService.create(emailRecord);
    }

    @RequestMapping(value = "emailValidate", method = RequestMethod.GET)
    public String emailValidate(@RequestParam Integer user,
                                @RequestParam Integer validate,
                                @RequestParam String code,
                                Model model) {
        try {
            if (user == null || validate == null || code == null) {
                model.addAttribute("backInfo", "邮箱验证信息错误,请重新验证");
            }
            User dbUser = userService.findById(user);
            if (dbUser == null) {
                model.addAttribute("backInfo", "邮箱验证信息错误,请重新验证");
            }
            if (dbUser.isEmailValidate()) {
                model.addAttribute("backInfo", "邮箱已验证通过，无需再次验证");
            }
            EmailValidate dbValidate = emailValidateService.findById(validate);
            if (dbValidate == null) {
                model.addAttribute("backInfo", "邮箱验证信息错误,请重新验证");
            }
            if (!dbValidate.getCode().equals(code)) {
                model.addAttribute("backInfo", "邮箱验证信息错误,请重新验证");
            }
            long start = dbValidate.getTime();
            long end = System.currentTimeMillis();
            // TODO: 2016/6/16.0016 使用系统设置超时 时间
            if (end - start > 24 * 60 * 60 * 1000) {
                model.addAttribute("backInfo", "验证超时,请重新验证");
            }
            userService.emailValidate(user);
            emailValidateService.validate(validate);
            model.addAttribute("backInfo","邮箱验证成功，请登录");
        } catch (Exception e) {
            model.addAttribute("backInfo", e.getMessage());
        }
        return "front/email-validate-back";
    }
}