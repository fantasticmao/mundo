package com.mundo.core;

import com.mundo.core.aop.TimeoutComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * ApplicationTest
 * 附：可以使用 <code>@TestConfiguration</code> 覆盖 <code>@Configuration</code> 中已存在的 Bean
 *
 * @author maodh
 * @since 2017/11/15
 */
@EnableMundoCore
@EnableAspectJAutoProxy
@Configuration
public class ApplicationTest {

    @Bean
    TimeoutComponent timeoutComponent() {
        return new TimeoutComponent();
    }

    @Bean
    JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setProtocol(JavaMailSenderImpl.DEFAULT_PROTOCOL);
        mailSender.setHost("${host}");
        mailSender.setPort(587);
        mailSender.setUsername("${username}");
        mailSender.setPassword("${password}");
        mailSender.setDefaultEncoding("UTF-8");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);

        javaMailProperties.put("mail.smtp.connectiontimeout", 10_000); // Connection time out
        javaMailProperties.put("mail.smtp.timeout", 10_000); // Read timed out
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

}
