package cn.fantasticmao.mundo.core.aop;

import cn.fantasticmao.mundo.core.annotation.Timeout;
import cn.fantasticmao.mundo.core.support.Constant;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TimeoutAspect
 *
 * @author maodh
 * @since 2017/11/14
 */
@Aspect
public class TimeoutAspect extends AbstractAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutAspect.class);
    private long startTime;
    private long endTime;

    @Pointcut("@annotation(cn.fantasticmao.mundo.core.annotation.Timeout)")
    public void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before() {
        startTime = System.nanoTime();
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        endTime = System.nanoTime();
        long durationTime = (endTime - startTime) / 1_000_000;

        Method method = super.getMethod(joinPoint);
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        List<String> parameterTypeList = Arrays.stream(parameterTypes).map(Class::getSimpleName).collect(Collectors.toList());
        String signature = StringUtils.join(parameterTypeList, Constant.Strings.COMMA_WITH_SPACE);

        Timeout timeout = method.getAnnotation(Timeout.class);
        long limitTime = timeout.time();

        if (limitTime > 0 && durationTime > limitTime) {
            LOGGER.warn("{}#{}({}) 执行超时。限时时间：{}ms，超时时间：{}ms", className, methodName, signature, limitTime, durationTime);
        }
    }
}
