package com.mundo.annotation;

import java.lang.annotation.*;

/**
 * Monitor
 * 此注解不具有传递性
 *
 * @author maodh
 * @since 2017/11/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Monitor {

    long time() default 1_000;
}
