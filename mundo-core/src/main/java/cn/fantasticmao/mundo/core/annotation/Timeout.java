package cn.fantasticmao.mundo.core.annotation;

import java.lang.annotation.*;

/**
 * Timeout
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/11/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Timeout {

    long time() default 1_000;
}
