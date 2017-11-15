package com.mundo;

import com.mundo.aop.MonitorAspect;
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
    MonitorAspect monitorAspect() {
        return new MonitorAspect();
    }
}
