package cn.fantasticmao.mundo.web.annotation;

import java.lang.annotation.*;

/**
 * CheckLogin
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/8/1
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CheckLogin {

    boolean value() default true;
}
