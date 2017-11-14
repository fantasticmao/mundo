package com.mundo.annotation;

import java.lang.annotation.*;

/**
 * Partition
 *
 * @author maodh
 * @since 2017/11/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Partition {

    int value() default 0;
}
