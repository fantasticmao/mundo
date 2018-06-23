package com.mundo.core.aop;

import com.mundo.core.annotation.PrintArgs;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public void before() {

    }
}
