package com.mundo.data;

import com.mundo.data.aop.PartitionAspect;
import com.mundo.data.datasource.PartitionDataSource;
import com.mundo.data.datasource.PartitionLookupKeyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * MundoDataAutoConfiguration
 *
 * @author maodh
 * @since 2017/11/16
 */
@Configuration
public class MundoDataAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MundoDataAutoConfiguration.class);

    @Resource
    private Map<String, DataSource> dataSourceMap;

    @Bean
    @ConditionalOnMissingBean
    PartitionAspect partitionAspect() {
        return new PartitionAspect();
    }

    @Bean
    @ConditionalOnBean(DataSource.class)
    PartitionDataSource partitionDataSource() {
        // TODO 定制 lookupKey 策略
        PartitionLookupKeyStrategy lookupKeyStrategy = key -> "lang";
        LOGGER.info("Initializing [PartitionDataSource] Bean.");
        return new PartitionDataSource(lookupKeyStrategy, dataSourceMap);
    }
}
