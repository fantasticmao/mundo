package com.mundo.core.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;

/**
 * MailUtilTest
 *
 * @author maodh
 * @since 14/01/2018
 */
public class MailUtilTest extends SpringUtil {
    @Resource
    private JavaMailSender mailSender;

    //@Test
    public void send() {
        mailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom("fantasticmao@qq.com");
            message.setTo("maomao8017@gmail.com");
            message.setSubject("my subject");
            message.setText("my text", true);
        });
    }
}