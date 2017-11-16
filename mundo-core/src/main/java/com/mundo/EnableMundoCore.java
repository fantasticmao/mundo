package com.mundo;

import com.mundo.MundoCoreAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableMundoCore
 *
 * @author maodh
 * @since 2017/11/15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MundoCoreAutoConfiguration.class)
public @interface EnableMundoCore {
}
