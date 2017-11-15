package com.mundo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableMundoWeb
 *
 * @author maodh
 * @since 2017/8/2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MundoCoreAutoConfiguration.class, MundoWebAutoConfiguration.class})
public @interface EnableMundoWeb {
}
