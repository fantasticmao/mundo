package com.mundo.web.annotation;

import java.lang.annotation.*;

/**
 * LogRequestInfo
 *
 * @author maomao
 * @since 2019-05-31
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogRequestInfo {
}
