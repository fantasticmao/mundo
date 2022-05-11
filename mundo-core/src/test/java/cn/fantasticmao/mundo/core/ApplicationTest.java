package cn.fantasticmao.mundo.core;

import cn.fantasticmao.mundo.core.aop.AssertFalseComponent;
import cn.fantasticmao.mundo.core.aop.PrintArgsComponent;
import cn.fantasticmao.mundo.core.aop.TimeoutComponent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * ApplicationTest
 * <p>
 * 附：可以使用 <code>@TestConfiguration</code> 覆盖 <code>@Configuration</code> 中已存在的 Bean
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/11/15
 */
@SpringBootApplication
public class ApplicationTest {

    @Bean
    AssertFalseComponent assertFalseComponent() {
        return new AssertFalseComponent();
    }

    @Bean
    PrintArgsComponent printArgsComponent() {
        return new PrintArgsComponent();
    }

    @Bean
    TimeoutComponent timeoutComponent() {
        return new TimeoutComponent();
    }

}
