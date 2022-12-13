package cn.fantasticmao.mundo.data;

import cn.fantasticmao.mundo.data.jdbc.RoutingRepositoryBeanPostProcessor;
import cn.fantasticmao.mundo.data.jdbc.RoutingTransactionTemplate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.RenderContextFactory;
import org.springframework.data.relational.core.sql.render.SqlRenderer;
import org.springframework.transaction.support.TransactionTemplate;

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

    @Bean
    @ConditionalOnSingleCandidate(TransactionTemplate.class)
    public RoutingTransactionTemplate routingTransactionTemplate(
        TransactionTemplate transactionTemplate) {
        return new RoutingTransactionTemplate(transactionTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlRenderer sqlRenderer(Dialect dialect) {
        RenderContextFactory factory = new RenderContextFactory(dialect);
        return SqlRenderer.create(factory.createRenderContext());
    }

}
