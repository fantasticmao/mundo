package com.mundo;

import com.mundo.aop.TimeoutComponent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * ApplicationTest
 * 附：可以使用 <code>@TestConfiguration</code> 覆盖 <code>@Configuration</code> 中已存在的 Bean
 *
 * @author maodh
 * @since 2017/11/15
 */
@EnableMundoCore
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApplicationTest {

    @Bean
    TimeoutComponent timeoutComponent() {
        return new TimeoutComponent();
    }

}
