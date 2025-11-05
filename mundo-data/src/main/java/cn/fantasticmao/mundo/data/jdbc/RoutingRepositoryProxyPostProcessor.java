package cn.fantasticmao.mundo.data.jdbc;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;

/**
 * {@link RepositoryProxyPostProcessor} that extracts the route seed and sets up
 * to the {@link RoutingSeedContext context} before the method is invoked.
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
        Advisor[] advisors = factory.getAdvisors();
        int i = advisors.length - 1;
        for (; i > 0; i--) {
            /*
             * if `TransactionInterceptor` exists, add `RoutingMethodInterceptor` to the
             * previous index, if not, add to the first index.
             */
            if (advisors[i].getAdvice() instanceof TransactionInterceptor) {
                break;
            }
        }
        factory.addAdvice(i, new RoutingMethodInterceptor(repositoryInformation));
    }

    private static class RoutingMethodInterceptor implements MethodInterceptor {
        private final RepositoryInformation repositoryInformation;

        public RoutingMethodInterceptor(RepositoryInformation repositoryInformation) {
            this.repositoryInformation = repositoryInformation;
        }

        @Nullable
        @Override
        public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
            final Method method = invocation.getMethod();
            final Object[] arguments = invocation.getArguments();

            Object seedObj = RoutingSeedExtractor.fromEntityFields(arguments,
                repositoryInformation.getDomainType());
            if (seedObj != null) {
                return invokeWithSeed(invocation, seedObj);
            }

            seedObj = RoutingSeedExtractor.fromMethodArguments(arguments,
                method.getParameterAnnotations());
            if (seedObj != null) {
                return invokeWithSeed(invocation, seedObj);
            }

            RoutingSeed seedAnnotation = RoutingSeedExtractor.fromMethodDeclaration(method);
            if (seedAnnotation != null) {
                return invokeWithSeed(invocation, seedAnnotation);
            }

            seedAnnotation = RoutingSeedExtractor.fromClassDeclaration(method.getDeclaringClass());
            if (seedAnnotation != null) {
                return invokeWithSeed(invocation, seedAnnotation);
            }

            return invocation.proceed();
        }

        private Object invokeWithSeed(@Nonnull MethodInvocation invocation, @Nonnull Object seed)
            throws Throwable {
            try {
                RoutingSeedContext.set(seed);
                return invocation.proceed();
            } finally {
                RoutingSeedContext.remove();
            }
        }
    }
}
