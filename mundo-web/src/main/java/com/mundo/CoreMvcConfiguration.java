package com.mundo;

import com.mundo.interceptor.CheckCsrfInterceptor;
import com.mundo.interceptor.CheckLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * CoreMvcConfiguration
 *
 * @author maodh
 * @since 2017/8/2
 */
@EnableWebMvc
@Configuration
public class CoreMvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public CheckCsrfInterceptor checkCsrfInterceptor() {
        return new CheckCsrfInterceptor();
    }

    @Bean
    public CheckLoginInterceptor checkLoginInterceptor() {
        return new CheckLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkCsrfInterceptor());
        registry.addInterceptor(checkLoginInterceptor());
    }
}