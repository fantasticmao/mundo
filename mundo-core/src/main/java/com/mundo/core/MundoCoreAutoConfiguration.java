package com.mundo.core;

import com.mundo.core.aop.AssertFalseAspect;
import com.mundo.core.aop.TimeoutAspect;
import com.mundo.core.constant.Beans;
import com.mundo.core.util.SpringUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Properties;

/**
 * MundoCoreAutoConfiguration
 *
 * @author maodh
 * @since 2017/11/15
 */
@Configuration
public class MundoCoreAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MundoCoreAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    TimeoutAspect timeoutAspect() {
        return new TimeoutAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    AssertFalseAspect assertFalseAspect() {
        return new AssertFalseAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    SpringUtil springUtil() {
        return new SpringUtil();
    }

    /**
     * @see <a href="http://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=167">QQ邮箱的POP3与SMTP服务器是什么？</a>
     */
    @Bean
    JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setProtocol(JavaMailSenderImpl.DEFAULT_PROTOCOL);
        mailSender.setHost("smtp.qq.com");
        mailSender.setPort(587);
        mailSender.setUsername("fantasticmao@qq.com");
        mailSender.setPassword("ebwcytkpsirqbdbg");
        mailSender.setDefaultEncoding("UTF-8");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);

        javaMailProperties.put("mail.smtp.connectiontimeout", 10_000); // Connection time out
        javaMailProperties.put("mail.smtp.timeout", 10_000); // Read timed out
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    @ConditionalOnMissingBean
    HttpClient httpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setMaxConnTotal(500)
                .build();
        LOGGER.info("Instantiating core bean: httpClient.");
        return httpClient;
    }

    @Bean(name = Beans.REST_DEFAULT_TEMPLATE)
    @ConditionalOnMissingBean(name = Beans.REST_DEFAULT_TEMPLATE)
    RestTemplate defaultRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        LOGGER.info("Instantiating core bean: defaultRestTemplate.");
        return new RestTemplateBuilder()
                .requestFactory(requestFactory)
                .setReadTimeout(3_000)
                .setConnectTimeout(3_000)
                .build();
    }

    @Bean(name = Beans.REST_ANSYC_TEMPLATE)
    @ConditionalOnMissingBean(name = Beans.REST_ANSYC_TEMPLATE)
    AsyncRestTemplate ansycRestTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpComponentsAsyncClientHttpRequestFactory asyncRequestFactory = new HttpComponentsAsyncClientHttpRequestFactory();
        asyncRequestFactory.setReadTimeout(3_000);
        asyncRequestFactory.setConnectTimeout(3_000);
        asyncRequestFactory.setHttpClient(httpClient());
        LOGGER.info("Instantiating core bean: ansycRestTemplate.");
        return new AsyncRestTemplate(asyncRequestFactory, defaultRestTemplate());
    }

}
