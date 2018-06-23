package com.mundo.core.annotation;

import java.lang.annotation.*;

/**
 * PrintArgsAspect
 *
 * @author maodh
 * @since 23/06/2018
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PrintArgs {
}
