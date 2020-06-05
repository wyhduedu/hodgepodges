package com.wy.hodgepodges.service.repertory;


/**
 * @Author: wkl
 * @Date: 2019/3/14 11:35 PM
 */
public interface MailService {

    /**
     * 发送邮件
     * @param toEmail
     * @param title
     * @param content
     */
    void send(String[] toEmail, String title, String content);



    /**
     * 发送邮件
     * @param toEmail 收件人
     * @param title 标题
     * @param fileName 文件名称
     * @param content 内容
     * @param isHtml 是否html
     */
    void send(String[] toEmail, String title,String fileName, String content,Boolean isHtml);
}
