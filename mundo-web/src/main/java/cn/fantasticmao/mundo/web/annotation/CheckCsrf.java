package cn.fantasticmao.mundo.web.annotation;

import java.lang.annotation.*;

/**
 * CheckCsrf
 *
 * @author maodh
 * @version 1.0
 * @since 2017/8/1
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CheckCsrf {

    boolean value() default true;
}
