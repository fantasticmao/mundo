package com.mundo.aop;

import com.mundo.annotation.Monitor;
import com.mundo.util.CollectionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MonitorAspect
 *
 * @author maodh
 * @since 2017/11/14
 */
@Aspect
@Order(1)
@Component
public class MonitorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorAspect.class);
    private long startTime;
    private long endTime;

    @Pointcut("@annotation(com.mundo.annotation.Monitor)")
    public void annotationPointCut() {

    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        startTime = System.nanoTime();
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        endTime = System.nanoTime();
        long durationTime = (endTime - startTime) / 1_000_000;

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        List<String> parameterTypeList = Arrays.stream(parameterTypes).map(Class::getSimpleName).collect(Collectors.toList());
        String signature = CollectionUtil.join(parameterTypeList);

        Monitor monitor = method.getAnnotation(Monitor.class);
        long limitTime = monitor.time();

        if (limitTime > 0 && durationTime > limitTime) {
            LOGGER.warn("{}.{}({}) 执行超时。限时时间：{}ms，超时时间：{}ms", className, methodName, signature, limitTime, durationTime);
        }
    }
}