package com.wy.hodgepodges.service.repertory.impl;

import com.wy.hodgepodges.service.repertory.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: wkl
 * @Date: 2019/3/14 11:37 PM
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender javaMailSender;

    // 服务器
    String smtp = "smtp.mxhichina.com";
    //是否需要验证密码
    Boolean auth = true;
    // 超时时间
    int timeout = 25000;
    //  发件人信箱
    String from = "wy_hdu_edu@163.com";
    //用户名
    String username = "wy_hdu_edu@163.com";
    //密码
    String password = "aaaaa";
    //端口
    int port = 465;
    //默认mail
    String email = "wy_hdu_edu@163.com";


    @Override
    public void send(String[] toEmail, String title, String content) {
        send( email, toEmail, title, content);
    }

    public void send(String fromEmail, String[] toEmail, String title, String content) {
        log.info("开始发送邮件, fromEmail={}, toEmail={}", fromEmail, toEmail);
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(title);
            messageHelper.setText(content);
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        log.info("发送邮件结束, fromEmail={}, toEmail={}", fromEmail, toEmail);
    }

    @Override
    public void send(String[] toEmail, String title, String fileName, String content, Boolean isHtml) {
        log.info("开始发送邮件, fromEmail={}, toEmail={}", email, toEmail);
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
            messageHelper.setFrom(email);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(title);
            messageHelper.setText(content, isHtml);

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("发送邮件结束, fromEmail={}, toEmail={}", email, toEmail);

    }


}
