package com.mundo.web;

import com.mundo.web.interceptor.CheckLoginInterceptor;
import com.mundo.web.interceptor.CheckCsrfInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MundoWebAutoConfiguration
 *
 * @author maodh
 * @since 2017/8/2
 */
@Configuration
public class MundoWebAutoConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    @ConditionalOnMissingBean(CheckCsrfInterceptor.class)
    CheckCsrfInterceptor checkCsrfInterceptor() {
        return new CheckCsrfInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(CheckLoginInterceptor.class)
    CheckLoginInterceptor checkLoginInterceptor() {
        return new CheckLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkCsrfInterceptor());
        registry.addInterceptor(checkLoginInterceptor());
    }
}