package com.mundo.annotation;

import java.lang.annotation.*;

/**
 * MethodLogger
 *
 * @author maodh
 * @since 2017/11/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodLogger {

    long limit() default 1_000;
}
