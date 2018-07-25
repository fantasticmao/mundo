package com.mundo.data;

import com.mundo.data.aop.PartitionAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MundoDataAutoConfiguration
 *
 * @author maodh
 * @since 2017/11/16
 */
@Configuration
public class MundoDataAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    PartitionAspect partitionAspect() {
        return new PartitionAspect();
    }
}
