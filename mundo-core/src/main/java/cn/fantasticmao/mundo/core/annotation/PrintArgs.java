package cn.fantasticmao.mundo.core.annotation;

import java.lang.annotation.*;

/**
 * PrintArgsAspect
 *
 * @author maodh
 * @version 1.0
 * @since 23/06/2018
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PrintArgs {
}
