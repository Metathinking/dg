package com.hu.dg.util;

import com.hu.dg.domain.EmailRecord;
import com.hu.dg.domain.EmailSetting;
import com.hu.dg.domain.EmailValidate;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) EmailUtil.java 2016/06/14 14:03
 */
public class EmailUtil {

    private static Logger logger =  LogManager.getLogger(EmailUtil.class);
    public static void send(EmailRecord emailRecord) throws EmailException {
        Thread thread = new Thread(new EmailRunnable(emailRecord));
        thread.start();

    }

    private static class EmailRunnable implements Runnable{

        private EmailRecord emailRecord;

        public EmailRunnable(EmailRecord emailRecord) {
            this.emailRecord = emailRecord;
        }

        public void run() {
            try {
                long start = System.currentTimeMillis();
                HtmlEmail email = new HtmlEmail();
                email.setHostName(emailRecord.getHostName());
                email.setSmtpPort(emailRecord.getSmtpPort());
                email.setAuthentication(emailRecord.getAuthenticationName(), emailRecord.getAuthenticationPassword());
                email.setCharset(emailRecord.getCharset());
                email.addTo(emailRecord.getToEmail());
                email.setFrom(emailRecord.getAuthenticationName(), emailRecord.getSendPerson());
                email.setSubject(emailRecord.getSubject());
                email.setHtmlMsg(emailRecord.getHtmlMsg());
                email.send();
                long end = System.currentTimeMillis();
                System.out.println("发送时长："+(end-start));
            } catch (EmailException e) {
                e.printStackTrace();
                logger.log(Level.WARN,"邮件发送失败",e.getMessage());
            }

        }
    }

    public static void send(EmailSetting emailSetting,
                            EmailValidate emailValidate,
                            String toEmail) throws EmailException {
      HtmlEmail email = new HtmlEmail();
        email.setHostName(emailSetting.getHostName());
        email.setSmtpPort(emailSetting.getSmtpPort());
        email.setAuthentication(emailSetting.getAuthenticationName(),emailSetting.getAuthenticationPassword());
        email.setCharset(emailSetting.getCharset());
        email.addTo(toEmail);
        email.setFrom(emailSetting.getAuthenticationName(),emailSetting.getSendPerson());
        email.setSubject("'我来说'导购网——邮箱验证");
        String link = "http://localhost:8080/emailValidate.html?user=&validate=&code=";

        email.setHtmlMsg("o");
        email.send();
    }
}