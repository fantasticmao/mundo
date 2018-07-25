package com.mundo.data.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import javax.sql.DataSource;
import java.util.Map;

/**
 * PartitionDataSourceListener
 *
 * @author maodh
 * @since 2018/7/25
 */
public class PartitionDataSourceListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionDataSourceListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        final ConfigurableListableBeanFactory beanFactory = event.getApplicationContext().getBeanFactory();

        Map<String, DataSource> dataSourceMap = beanFactory.getBeansOfType(DataSource.class);
        PartitionLookupKeyStrategy lookupKeyStrategy = beanFactory.getBean(PartitionLookupKeyStrategy.class);
        if (dataSourceMap == null || dataSourceMap.isEmpty() || lookupKeyStrategy == null) return;

        LOGGER.info("Initializing [com.mundo.data.datasource.PartitionDataSource]");
        DataSource dataSource = new PartitionDataSource(lookupKeyStrategy, dataSourceMap);
        // TODO 保存 datasource bean
    }
}
