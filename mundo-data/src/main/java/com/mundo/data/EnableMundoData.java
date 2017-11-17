package com.mundo.data;

import com.mundo.MundoCoreAutoConfiguration;
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
    // TODO 制定配置文件，读取数据源
}
