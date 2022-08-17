package cn.fantasticmao.mundo.data;

import cn.fantasticmao.mundo.data.jdbc.RoutingRepositoryBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MundoDataAutoConfiguration
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
@Configuration
public class MundoDataAutoConfiguration {

    @Bean
    public RoutingRepositoryBeanPostProcessor routingRepositoryBeanPostProcessor() {
        return new RoutingRepositoryBeanPostProcessor();
    }
}
