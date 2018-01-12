package com.mundo.web.annotation;

import java.lang.annotation.*;

/**
 * EnableJsonpInterceptor
 *
 * @author maodh
 * @since 2018/1/12
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableJsonp {

    boolean value() default true;
}
