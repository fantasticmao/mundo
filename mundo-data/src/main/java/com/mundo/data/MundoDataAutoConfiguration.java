package com.mundo.data;

import com.mundo.data.aop.PartitionAspect;
import com.mundo.data.datasource.PartitionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(MundoDataAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(PartitionAspect.class)
    PartitionAspect partitionAspect() {
        return new PartitionAspect();
    }

    @Bean
    @Primary
    PartitionDataSource partitionDataSource(@Autowired ApplicationContext context) {
        Map<String, DataSource> dataSourceMap = context.getBeansOfType(DataSource.class);

        Map<Object, Object> targetDataSources = new HashMap<>();
        dataSourceMap.keySet().forEach(datasource ->
                LOGGER.info("Get and group [javax.sql.Datasource] {} from the Spring applicationContext.", datasource));
        // TODO get and group the instances of PartitionDataSource from the Spring applicationContext
        LOGGER.info("Initializing [com.mundo.data.datasource.PartitionDataSource] Bean!");
        return new PartitionDataSource(null, targetDataSources,
                seed -> (int) (seed % dataSourceMap.keySet().size()) + 1);
    }
}
