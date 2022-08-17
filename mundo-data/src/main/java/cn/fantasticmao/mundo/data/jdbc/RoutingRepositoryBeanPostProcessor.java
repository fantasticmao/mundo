package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

import javax.annotation.Nonnull;

/**
 * RoutingRepositoryBeanPostProcessor
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
public class RoutingRepositoryBeanPostProcessor implements BeanPostProcessor {
    private final RepositoryProxyPostProcessor processor;

    public RoutingRepositoryBeanPostProcessor() {
        this.processor = new RoutingRepositoryProxyPostProcessor();
    }

    @Override
    public Object postProcessBeforeInitialization(@Nonnull Object bean, @Nonnull String beanName)
        throws BeansException {
        if (bean instanceof RepositoryFactoryBeanSupport) {
            ((RepositoryFactoryBeanSupport<?, ?, ?>) bean).addRepositoryFactoryCustomizer(factory ->
                factory.addRepositoryProxyPostProcessor(this.processor)
            );
        }
        return bean;
    }

}