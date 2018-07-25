package com.mundo.data;

import com.mundo.core.MundoCoreAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableMundoData
 *
 * @author maodh
 * @since 2017/11/16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MundoCoreAutoConfiguration.class, MundoDataAutoConfiguration.class})
public @interface EnableMundoData {
}
