package com.mundo.data;

import com.mundo.data.aop.PartitionAspect;
import com.mundo.data.datasource.PartitionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * MundoDataAutoConfiguration
 *
 * @author maodh
 * @since 2017/11/16
 */
@Configuration
public class MundoDataAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(PartitionAspect.class)
    PartitionAspect partitionAspect() {
        return new PartitionAspect();
    }

    @Bean
    @Primary
    DataSource partitionDataSource(@Autowired ApplicationContext context) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        // TODO get and group the instances of PartitionDataSource from the Spring applicationContext
        return new PartitionDataSource(null, targetDataSources);
    }
}
