package com.mundo.web;

import com.mundo.web.interceptor.CheckCsrfInterceptor;
import com.mundo.web.interceptor.CheckLoginInterceptor;
import com.mundo.web.support.Beans;
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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * MundoWebAutoConfiguration
 *
 * @author maodh
 * @since 2017/8/2
 */
@Configuration
@ComponentScan(value = "com.mundo.web.mvc")
public class MundoWebAutoConfiguration extends WebMvcConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MundoWebAutoConfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("Registering [CheckCsrfInterceptor] for Spring MVC!");
        registry.addInterceptor(checkCsrfInterceptor());
        LOGGER.info("Registering [CheckLoginInterceptor] for Spring MVC!");
        registry.addInterceptor(checkLoginInterceptor());
    }

    @Bean
    @ConditionalOnMissingBean
    CheckCsrfInterceptor checkCsrfInterceptor() {
        return new CheckCsrfInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    CheckLoginInterceptor checkLoginInterceptor() {
        return new CheckLoginInterceptor();
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
        LOGGER.info("Instantiating core bean: httpClient");
        return httpClient;
    }

    @Bean(name = Beans.DEFAULT_REST_TEMPLATE)
    @ConditionalOnMissingBean(name = Beans.DEFAULT_REST_TEMPLATE)
    RestTemplate defaultRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        LOGGER.info("Instantiating core bean: defaultRestTemplate");
        return new RestTemplateBuilder()
                .requestFactory(requestFactory)
                .setReadTimeout(5_000)
                .setConnectTimeout(5_000)
                .build();
    }

    @Bean(name = Beans.ANSYC_REST_TEMPLATE)
    @ConditionalOnMissingBean(name = Beans.ANSYC_REST_TEMPLATE)
    AsyncRestTemplate ansycRestTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpComponentsAsyncClientHttpRequestFactory asyncRequestFactory = new HttpComponentsAsyncClientHttpRequestFactory();
        asyncRequestFactory.setReadTimeout(5_000);
        asyncRequestFactory.setConnectTimeout(5_000);
        asyncRequestFactory.setHttpClient(httpClient());
        LOGGER.info("Instantiating core bean: ansycRestTemplate");
        return new AsyncRestTemplate(asyncRequestFactory, defaultRestTemplate());
    }
}