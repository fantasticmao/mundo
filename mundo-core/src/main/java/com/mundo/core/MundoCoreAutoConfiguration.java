package com.mundo.core;

import com.mundo.core.aop.AssertFalseAspect;
import com.mundo.core.aop.TimeoutAspect;
import com.mundo.core.util.SpringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MundoCoreAutoConfiguration
 *
 * @author maodh
 * @since 2017/11/15
 */
@Configuration
public class MundoCoreAutoConfiguration {

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

}
