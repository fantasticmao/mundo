package com.mundo.data;

import com.mundo.data.aop.PartitionComponent;
import com.mundo.data.aop.PartitionInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * ApplicationTest
 *
 * @author maodh
 * @since 2017/11/17
 */
@EnableMundoData
@EnableAspectJAutoProxy
@Configuration
public class ApplicationTest {

    @Bean
    PartitionInterface partitionComponent() {
        return new PartitionComponent();
    }
}
