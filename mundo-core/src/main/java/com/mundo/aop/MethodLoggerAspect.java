package com.mundo.aop;

import com.mundo.annotation.MethodLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * MethodLoggerAspect
 *
 * @author maodh
 * @since 2017/11/14
 */
@Aspect
@Component
public class MethodLoggerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodLoggerAspect.class);
    private long startTime;
    private long endTime;

    @Pointcut("@annotation(com.mundo.annotation.MethodLogger)")
    public void annotationPointCut() {

    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        startTime = System.nanoTime();
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        endTime = System.nanoTime();
        long durationTime = endTime - startTime;

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getName();
        String className = method.getClass().getName();
        MethodLogger methodLogger = method.getAnnotation(MethodLogger.class);
        long limitTime = methodLogger.limit();

        if (limitTime > 0 && durationTime > limitTime) {
            LOGGER.warn("{} {} 执行超时", className, methodName);
        }
    }
}
