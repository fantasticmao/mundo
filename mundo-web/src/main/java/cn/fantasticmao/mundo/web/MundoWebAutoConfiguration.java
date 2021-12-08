package cn.fantasticmao.mundo.web;

import cn.fantasticmao.mundo.web.interceptor.CheckCsrfInterceptor;
import cn.fantasticmao.mundo.web.interceptor.CheckLoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MundoWebAutoConfiguration
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/8/2
 */
@Configuration
@ComponentScan(value = "cn.fantasticmao.mundo.web.mvc")
public class MundoWebAutoConfiguration implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MundoWebAutoConfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkCsrfInterceptor());
        LOGGER.info("Registering [CheckCsrfInterceptor] for Spring MVC!");
        registry.addInterceptor(checkLoginInterceptor());
        LOGGER.info("Registering [CheckLoginInterceptor] for Spring MVC!");
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
}