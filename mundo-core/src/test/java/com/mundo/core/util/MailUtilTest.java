package com.mundo.core.util;

import com.mundo.core.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * MailUtilTest
 *
 * @author maodh
 * @since 14/01/2018
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MailUtilTest {
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