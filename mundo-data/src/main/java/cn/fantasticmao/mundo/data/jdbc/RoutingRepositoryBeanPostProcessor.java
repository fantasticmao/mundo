package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactoryCustomizer;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

import javax.annotation.Nonnull;

/**
 * {@link BeanPostProcessor} to apply a {@link RoutingRepositoryFactoryCustomizer}
 * to all {@link RepositoryFactorySupport repository factories}.
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
public class RoutingRepositoryBeanPostProcessor implements BeanPostProcessor {
    private final RoutingRepositoryFactoryCustomizer customizer;

    public RoutingRepositoryBeanPostProcessor() {
        RepositoryProxyPostProcessor processor = new RoutingRepositoryProxyPostProcessor();
        this.customizer = new RoutingRepositoryFactoryCustomizer(processor);
    }

    @Override
    public Object postProcessBeforeInitialization(@Nonnull Object bean, @Nonnull String beanName)
        throws BeansException {
        if (bean instanceof RepositoryFactoryBeanSupport) {
            ((RepositoryFactoryBeanSupport<?, ?, ?>) bean).addRepositoryFactoryCustomizer(customizer);
        }
        return bean;
    }

    private static class RoutingRepositoryFactoryCustomizer implements RepositoryFactoryCustomizer {
        private final RepositoryProxyPostProcessor processor;

        public RoutingRepositoryFactoryCustomizer(RepositoryProxyPostProcessor processor) {
            this.processor = processor;
        }

        @Override
        public void customize(RepositoryFactorySupport factory) {
            factory.addRepositoryProxyPostProcessor(processor);
        }
    }

}
