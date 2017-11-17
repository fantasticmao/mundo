package com.mundo.data;

import com.mundo.data.aop.PartitionAspect;
import com.mundo.data.datasource.PartitionDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    // 数据源应从应用中加载
    //@Bean
    DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    //@Bean
    DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    //@Bean
    //@Primary
    DataSource partitionDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSource1", dataSource1());
        targetDataSources.put("dataSource2", dataSource2());
        return new PartitionDataSource(dataSource1(), targetDataSources);
    }
}
