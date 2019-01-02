package com.mundo.data.partition;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;

/**
 * PartitionDataSourcePostProcessor
 *
 * @author maodh
 * @since 2019/1/2
 */
public class PartitionDataSourcePostProcessor implements RepositoryProxyPostProcessor {

    @Override
    public void postProcess(@Nonnull ProxyFactory factory, @Nonnull RepositoryInformation repositoryInformation) {
        factory.addAdvice(PartitionMethodInterceptor.INSTANCE);
    }

    enum PartitionMethodInterceptor implements MethodInterceptor {
        INSTANCE;

        private final Logger LOGGER = LoggerFactory.getLogger(PartitionMethodInterceptor.class);

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Annotation[][] annotations = invocation.getMethod().getParameterAnnotations();
            int pos = -1;
            posFlag:
            for (int i = 0; i < annotations.length; i++) {
                for (int j = 0; j < annotations[i].length; j++) {
                    Annotation ann = annotations[i][j];
                    if (ann instanceof PartitionParam) {
                        pos = i;
                        break posFlag;
                    }
                }
            }

            if (pos >= 0) {
                Object[] arguments = invocation.getArguments();
                Object argument = arguments[pos];
                if (argument instanceof Integer) {
                    PartitionSeedContext.push(((Integer) argument) % 4);
                }
            }

            Object result = invocation.proceed();
            PartitionSeedContext.clear();
            return result;
        }
    }
}
