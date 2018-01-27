package com.mundo.data;

import com.mundo.data.datasource.PartitionException;
import com.mundo.data.aop.PartitionAspect;
import com.mundo.data.datasource.PartitionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
    @ConditionalOnMissingBean
    PartitionAspect partitionAspect() {
        return new PartitionAspect();
    }

    @Bean
    @Primary
    @ConditionalOnBean(DataSource.class)
    PartitionDataSource partitionDataSource(@Autowired ApplicationContext context) {
        Map<String, DataSource> dataSourceMap = context.getBeansOfType(DataSource.class);

        DataSource defaultTargetDataSource = dataSourceMap.values().stream().findFirst()
                .orElseThrow(() -> new PartitionException("No [javax.sql.Datasource] instance found!"));
        Map<Object, Object> targetDataSources = new HashMap<>();
        dataSourceMap.keySet().forEach(datasource ->
                LOGGER.info("Get and group [javax.sql.Datasource] {} from the Spring ApplicationContext.", datasource));
        // TODO get and group the instances of PartitionDataSource from the Spring ApplicationContext

        LOGGER.info("Initializing [PartitionDataSource] Bean.");
        return new PartitionDataSource(defaultTargetDataSource, targetDataSources,
                seed -> (int) (seed % dataSourceMap.keySet().size()) + 1);
    }
}
