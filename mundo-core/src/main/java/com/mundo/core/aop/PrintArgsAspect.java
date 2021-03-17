package com.mundo.core.aop;

import com.mundo.core.annotation.PrintArgs;
import com.mundo.core.support.Constant;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * PrintArgsAspect
 *
 * @author maodh
 * @since 23/06/2018
 */
@Aspect
public class PrintArgsAspect extends AbstractAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintArgs.class);

    @Pointcut("@annotation(com.mundo.core.annotation.PrintArgs)")
    public void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        Method method = super.getMethod(joinPoint);
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String[] args = Stream.of(joinPoint.getArgs()).map(Objects::toString).toArray(String[]::new);
        String argument = StringUtils.join(args, Constant.Strings.COMMA_WITH_SPACE);
        LOGGER.info("Execute Method: {}#{}({})", className, methodName, argument);
    }
}
