package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.model.dto.mail.MailDTO;

public interface EmailService {
    
    /**
     * 发送简单邮件
     *
     * @param mailDTO 邮件信息
     */
    void sendSimpleMail(MailDTO mailDTO);
    
    /**
     * 发送HTML邮件
     *
     * @param mailDTO 邮件信息
     */
    void sendHtmlMail(MailDTO mailDTO);
}