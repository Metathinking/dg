package com.hu.dg.controller.front;

import com.hu.dg.domain.*;
import com.hu.dg.domain.bo.UserBO;
import com.hu.dg.service.*;
import com.hu.dg.type.EmailMsgType;
import com.hu.dg.util.EmailUtil;
import com.hu.dg.util.ExceptionTipHandler;
import com.hu.dg.util.Tip;
import com.hu.dg.util.ValidateUtil;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ForgetPasswordController.java 2016/06/16 22:40
 */
@Controller
public class ForgetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSettingService emailSettingService;

    @Autowired
    private EmailMsgTemplateService templateService;

    @Autowired
    private EmailRecordService emailRecordService;

    @Autowired
    private EmailValidateService emailValidateService;

    @RequestMapping(value="forgetPassword",method = RequestMethod.GET)
    public String gotoForgetPassword(){
        return "front/forget-password-send";
    }

    @RequestMapping(value="sendEmailForPassword",method = RequestMethod.POST)
    @ResponseBody
    public Tip sendEmailForPassword(@RequestParam String email){
        if(StringUtils.isEmpty(email)){
            return new Tip(false,100,"请输入注册邮箱",null);
        }
        if(!ValidateUtil.checkEmail(email)){
            return new Tip(false,101,"请输入正确的邮箱",null);
        }
        List<User> userList = userService.listByEmail(email);
        if(userList.size()==0){
            return new Tip(false,102,"该邮箱还未注册",null);
        }
        if(userList.size()>1){
            // TODO: 2016/6/16.0016 系统内部错误，通知管理员方式处理
            return new Tip(false,103,"系统内部错误，请联系管理员",null);
        }
        try {
            User user = userList.get(0);
            sendEmail(user);
            return new Tip(true,100,"密码重置邮件已发送至你的邮箱："+email+"\n" +
                    "\n" +
                    "请在24小时内登录你的邮箱接收邮件，链接激活后可重置密码。",null);
        } catch (Exception e) {
            return ExceptionTipHandler.handler(e);
        }
    }

    private void sendEmail(User user) throws EmailException {
        EmailSetting emailSetting = emailSettingService.getDefault();
        if(emailSetting==null){
            // TODO: 2016/6/16.0016 系统未添加默认设置
        }
        EmailMsgTemplate template = templateService.findDefaultByType(EmailMsgType.FIND_PASSWORD.name());
        if(template==null){
            // TODO: 2016/6/16.0016 系统未添加默认模板
        }
        EmailValidate emailValidate = emailValidateService.create(user.getId());
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
        String linkAddress = "http://localhost:8080/validateForPassword.html?user="+emailValidate.getUserId()
                +"&validate="+emailValidate.getId()+"&code="+emailValidate.getCode();
        String link = "<a href='"+linkAddress+"'>"+linkAddress+"</a>";
        String htmlMsg = template.getContent().replaceAll("\\{info\\}", link);
        emailRecord.setHtmlMsg(htmlMsg);
        EmailUtil.send(emailRecord);
        emailRecordService.create(emailRecord);
    }

    @RequestMapping(value = "validateForPassword", method = RequestMethod.GET)
    public String validateForPassword(@RequestParam Integer user,
                                @RequestParam Integer validate,
                                @RequestParam String code,
                                Model model) {
        try {
            if (user == null || validate == null || code == null) {
                model.addAttribute("backInfo", "邮箱验证信息错误,请重新申请");
                return "front/email-validate-back";
            }
            EmailValidate dbValidate = emailValidateService.findById(validate);
            if (dbValidate == null) {
                model.addAttribute("backInfo", "邮箱验证信息错误,请重新申请");
                return "front/email-validate-back";
            }
            if (!dbValidate.getCode().equals(code)) {
                model.addAttribute("backInfo", "邮箱验证信息错误,请重新验证");
                return "front/email-validate-back";
            }
            long start = dbValidate.getTime();
            long end = System.currentTimeMillis();
            // TODO: 2016/6/16.0016 使用系统设置超时 时间
            if (end - start > 24 * 60 * 60 * 1000) {
                model.addAttribute("backInfo", "申请超时,请重新申请");
                return "front/email-validate-back";
            }
            if(dbValidate.isValidated()){
                model.addAttribute("backInfo","验证信息已失效，请重新申请");
                return "front/email-validate-back";
            }
            emailValidateService.validate(validate);
            User dbUser = userService.findById(user);
            model.addAttribute("email",dbUser.getEmail());
            model.addAttribute("backInfo","邮箱验证成功，请重新设置密码");
            return "front/forget-password-reset";
        } catch (Exception e) {
            model.addAttribute("backInfo", e.getMessage());
            return "front/email-validate-back";
        }
    }

    @RequestMapping(value = "resetPasswordForForget",method = RequestMethod.POST)
    @ResponseBody
    public Tip resetPasswordForForget(@RequestBody UserBO userBO){
        if (StringUtils.isEmpty(userBO.getEmail())) {
            return new Tip(false, 108, "信息错误，请重新申请", null);
        }
        if (!ValidateUtil.checkEmail(userBO.getEmail())) {
            return new Tip(false, 109, "信息错误，请重新申请", null);
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
            userService.updatePasswordForForget(userBO.getEmail(),userBO.getPassword());
            return new Tip(true, 100, "密码已重置", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip(false, 107, e.getMessage(), null);
        }
    }
}