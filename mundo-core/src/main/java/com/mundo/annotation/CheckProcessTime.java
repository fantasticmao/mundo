package com.mundo.annotation;

import java.lang.annotation.*;

/**
 * CheckProcessTime
 *
 * @author maodh
 * @since 2017/11/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CheckProcessTime {

    long limitTime() default 1_000;
}
