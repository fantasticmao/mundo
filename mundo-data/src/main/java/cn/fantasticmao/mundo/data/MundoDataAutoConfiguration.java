package cn.fantasticmao.mundo.data;

import cn.fantasticmao.mundo.data.jdbc.RoutingRepositoryBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * Auto configuration for {@code mundo-data}.
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class MundoDataAutoConfiguration {

    @Bean
    public RoutingRepositoryBeanPostProcessor routingRepositoryBeanPostProcessor() {
        return new RoutingRepositoryBeanPostProcessor();
    }
}
