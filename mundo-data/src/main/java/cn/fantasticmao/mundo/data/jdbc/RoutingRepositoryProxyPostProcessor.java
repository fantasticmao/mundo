package cn.fantasticmao.mundo.data.jdbc;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * RoutingDataSourcePostProcessor
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
public class RoutingRepositoryProxyPostProcessor implements RepositoryProxyPostProcessor {

    public RoutingRepositoryProxyPostProcessor() {
    }

    @Override
    public void postProcess(@Nonnull ProxyFactory factory,
                            @Nonnull RepositoryInformation repositoryInformation) {
        factory.addAdvice(new RoutingMethodInterceptor(repositoryInformation));
    }

    private static class RoutingMethodInterceptor implements MethodInterceptor {
        private final RepositoryInformation repositoryInformation;

        public RoutingMethodInterceptor(RepositoryInformation repositoryInformation) {
            this.repositoryInformation = repositoryInformation;
        }

        @Nullable
        @Override
        public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
            // FIXME
            Class<?> clazz = repositoryInformation.getRepositoryInterface();
            MergedAnnotations classAnnotations = MergedAnnotations.from(clazz);
            MergedAnnotation<RoutingSeed> mergedAnnotation = classAnnotations.get(RoutingSeed.class);
            if (mergedAnnotation.isPresent()) {
                String seed = mergedAnnotation.getString("value");
                RoutingSeedContext.set(seed);
            }
            return invocation.proceed();
        }

    }
}
