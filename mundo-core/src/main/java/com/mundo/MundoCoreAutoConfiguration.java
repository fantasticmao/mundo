package com.mundo;

import com.mundo.aop.TimeoutAspect;
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
    TimeoutAspect timeoutAspect() {
        return new TimeoutAspect();
    }
}
