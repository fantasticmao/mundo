package cn.fantasticmao.mundo.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * AbstractAspect
 *
 * @author maodh
 * @since 2017/11/17
 */
public abstract class AbstractAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAspect.class);

    /**
     * @param joinPoint must be the instance of the MethodSignature
     * @return the method of the implementation class or the interface
     * @see org.aspectj.lang.reflect.MethodSignature
     */
    protected Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            Method method = ((MethodSignature) signature).getMethod();
            if (method.getDeclaringClass().isInterface()) {
                String methodName = joinPoint.getSignature().getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                try {
                    return joinPoint.getTarget().getClass().getDeclaredMethod(methodName, parameterTypes);
                } catch (NoSuchMethodException e) {
                    LOGGER.error("AbstractAspect cannot get the method of the implementation class.", e);
                }
            } else {
                return method;
            }
        }
        throw new ClassCastException("joinPoint.getSignature() cannot be cast to org.aspectj.lang.reflect.MethodSignature.");
    }
}
