package com.mundo.web;

import com.mundo.web.interceptor.CheckCsrfInterceptor;
import com.mundo.web.interceptor.CheckLoginInterceptor;
import com.mundo.web.interceptor.LogRequestInfoInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MundoWebAutoConfiguration
 *
 * @author maodh
 * @since 2017/8/2
 */
@Configuration
@ComponentScan(value = "com.mundo.web.mvc")
@EnableConfigurationProperties(LogRequestInfoInterceptor.Properties.class)
public class MundoWebAutoConfiguration implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MundoWebAutoConfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkCsrfInterceptor());
        LOGGER.info("Registering [CheckCsrfInterceptor] for Spring MVC!");
        registry.addInterceptor(checkLoginInterceptor());
        LOGGER.info("Registering [CheckLoginInterceptor] for Spring MVC!");
        LogRequestInfoInterceptor logRequestInfoInterceptor = this.logRequestInfoInterceptor();
        registry.addInterceptor(logRequestInfoInterceptor);
        LOGGER.info("Registering [LogRequestInfoInterceptor] for Spring MVC! {}", logRequestInfoInterceptor.getProperties().toString());
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
    LogRequestInfoInterceptor logRequestInfoInterceptor() {
        return new LogRequestInfoInterceptor();
    }
}