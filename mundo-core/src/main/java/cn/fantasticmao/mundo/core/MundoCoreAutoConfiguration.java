package cn.fantasticmao.mundo.core;

import cn.fantasticmao.mundo.core.aop.AssertFalseAspect;
import cn.fantasticmao.mundo.core.aop.PrintArgsAspect;
import cn.fantasticmao.mundo.core.aop.TimeoutAspect;
import cn.fantasticmao.mundo.core.util.SpringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MundoCoreAutoConfiguration
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/11/15
 */
@Configuration
public class MundoCoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    AssertFalseAspect assertFalseAspect() {
        return new AssertFalseAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    PrintArgsAspect printArgsAspect() {
        return new PrintArgsAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    TimeoutAspect timeoutAspect() {
        return new TimeoutAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    SpringUtil springUtil() {
        return new SpringUtil();
    }

}
