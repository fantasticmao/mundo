package com.mundo.data.aop;

import com.mundo.aop.AbstractAspect;
import com.mundo.data.annotation.Partition;
import com.mundo.data.datasource.PartitionContext;
import com.mundo.data.datasource.PartitionException;
import com.mundo.data.domain.AbstractPartitionDomain;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * PartitionAspect
 *
 * @author maodh
 * @since 2017/11/14
 */
@Aspect
@Order(3)
public class PartitionAspect extends AbstractAspect {

    @Pointcut("target(com.mundo.data.datasource.PartitionSupport)")
    public void targetPointCut() {

    }

    @Before("targetPointCut()")
    public void before(JoinPoint joinPoint) {
        // 获取 @Partition 注解参数
        Method method = super.getMethod(joinPoint);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int index = -1;
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation.annotationType() == Partition.class) {
                    index = i;
                    break;
                }
            }
        }

        // 计算 PartitionSeed 数值
        if (index == -1) {
            // 不存在 @Partition 注解参数
            return;
        } else {
            Object arg = joinPoint.getArgs()[index];
            long seed;
            if (arg instanceof AbstractPartitionDomain) {
                seed = ((AbstractPartitionDomain) arg).getSeed().longValue();
            } else if (arg instanceof Byte) {
                seed = ((Byte) arg).longValue();
            } else if (arg instanceof Short) {
                seed = ((Short) arg).longValue();
            } else if (arg instanceof Integer) {
                seed = ((Integer) arg).longValue();
            } else if (arg instanceof Long) {
                seed = (Long) arg;
            } else {
                throw new PartitionException("the @Partition argument must be the instance of AbstractPartitionDomain or Number.");
            }
            PartitionContext.push(seed);
        }
    }
}
