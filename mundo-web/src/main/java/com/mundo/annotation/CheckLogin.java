package com.mundo.annotation;

import java.lang.annotation.*;

/**
 * CheckLogin
 *
 * @author maodh
 * @since 2017/8/1
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CheckLogin {

    boolean value() default true;
}
