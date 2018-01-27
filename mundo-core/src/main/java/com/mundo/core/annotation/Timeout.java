package com.mundo.core.annotation;

import java.lang.annotation.*;

/**
 * Timeout
 * 此注解不具有传递性
 *
 * @author maodh
 * @since 2017/11/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Timeout {

    long time() default 1_000;
}
