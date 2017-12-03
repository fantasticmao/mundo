package com.mundo;

import com.mundo.aop.TimeoutAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(MundoCoreAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    TimeoutAspect timeoutAspect() {
        return new TimeoutAspect();
    }
}
