package com.mundo.data.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * PartitionAspect
 *
 * @author maodh
 * @since 2017/11/14
 */
@Aspect
@Component
public class PartitionAspect {

    @Pointcut("@args(com.mundo.data.annotation.Partition)")
    public void argsPointCut() {

    }

    @Before("argsPointCut()")
    public void before(JoinPoint joinPoint) {
        // TODO 获取 PartitionSeed

        // TODO 选择数据源
    }
}
