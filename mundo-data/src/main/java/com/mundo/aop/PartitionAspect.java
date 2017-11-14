package com.mundo.aop;

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

    @Pointcut("@annotation(com.mundo.annotation.Partition)")
    public void annotationPointCut() {

    }

    @Before("annotationPointCut()")
    public void before() {
        // TODO 选择数据源
    }
}
