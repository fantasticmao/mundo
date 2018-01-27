package com.mundo.web.annotation;

import com.mundo.web.mvc.JsonpControllerAdvice;

import java.lang.annotation.*;

/**
 * JsonpController
 *
 * @author maodh
 * @see JsonpControllerAdvice
 * @since 2018/1/12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JsonpController {

}
