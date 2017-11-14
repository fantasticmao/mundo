package com.mundo.annotation;

import com.mundo.CoreMvcConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableCoreMvc
 *
 * @author maodh
 * @since 2017/8/2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CoreMvcConfiguration.class)
public @interface EnableCoreMvc {
}
