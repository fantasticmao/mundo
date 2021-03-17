package cn.fantasticmao.mundo.core.annotation;

import java.lang.annotation.*;

/**
 * AssertFalse
 *
 * @author maodh
 * @version 1.0
 * @since 02/05/2018
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AssertFalse {

    String message() default "";
}
