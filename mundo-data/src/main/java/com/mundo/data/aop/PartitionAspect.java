package com.mundo.data.aop;

import com.mundo.core.aop.AbstractAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * PartitionAspect
 *
 * @author maodh
 * @since 2017/11/14
 */
@Aspect
@Order(1)
public class PartitionAspect extends AbstractAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionAspect.class);

    @Pointcut("@annotation(com.mundo.data.annotation.Partition)")
    public void targetPointCut() {

    }

    @Before("targetPointCut()")
    public void before(JoinPoint joinPoint) {
        // TODO 获取 @Partition 注解参数
        // TODO 获取指定字段
        // TODO 获取字段值
        // TODO PartitionSeedContext.push(seed);
        LOGGER.info("");
    }
}
