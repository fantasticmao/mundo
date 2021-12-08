package cn.fantasticmao.mundo.core.aop;

import cn.fantasticmao.mundo.core.annotation.AssertFalse;
import cn.fantasticmao.mundo.core.support.Constant;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * AssertFalseAspect
 *
 * @author fantasticmao
 * @version 1.0
 * @since 02/05/2018
 */
@Aspect
public class AssertFalseAspect extends AbstractAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssertFalse.class);

    @Pointcut("@annotation(cn.fantasticmao.mundo.core.annotation.AssertFalse)")
    public void annotationPointCut() {
    }

    @AfterReturning(pointcut = "annotationPointCut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        if (returnValue instanceof Boolean && !(boolean) returnValue) {
            Method method = getMethod(joinPoint);
            AssertFalse assertFalse = method.getAnnotation(AssertFalse.class);
            String[] args = Stream.of(joinPoint.getArgs()).map(Objects::toString).toArray(String[]::new);
            String argument = StringUtils.join(args, Constant.Strings.COMMA_WITH_SPACE);
            LOGGER.warn(assertFalse.message());
            LOGGER.warn("arguments: {}", argument);
        }
    }

}
