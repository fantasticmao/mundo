package com.mundo.web.annotation;

import java.lang.annotation.*;

/**
 * JsonpController
 *
 * @author maodh
 * @see com.mundo.web.mvc.JsonpControllerAdvice
 * @since 2018/1/12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JsonpController {

}
