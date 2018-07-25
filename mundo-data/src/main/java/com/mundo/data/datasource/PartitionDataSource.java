package com.mundo.data.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * PartitionDataSource
 * 动态选择数据源 <code>DataSource</code>
 *
 * @author maodh
 * @since 2017/11/16
 */
public class PartitionDataSource extends AbstractRoutingDataSource implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionDataSource.class);
    private static final String DEFAULT_DATA_SOURCE = "defaultDataSource";
    private PartitionLookupKeyStrategy lookupKeyStrategy;

    public PartitionDataSource() {
    }

    public PartitionDataSource(PartitionLookupKeyStrategy lookupKeyStrategy) {
        this.lookupKeyStrategy = lookupKeyStrategy;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initLookupKey(applicationContext);
        initTargetDataSources(applicationContext);
        initDefaultTargetDataSource(applicationContext);
    }

    @Override
    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        if (lookupKey instanceof String) {
            LOGGER.info("Loaded lookupKey: \"{}\"", lookupKey);
            return super.resolveSpecifiedLookupKey(lookupKey);
        }
        throw new PartitionException("lookupKey should be [javax.sql.DataSource] instance's bean name");
    }

    @Override
    protected DataSource resolveSpecifiedDataSource(Object dataSource) throws IllegalArgumentException {
        if (dataSource instanceof DataSource) {
            LOGGER.info("Loaded dataSource: \"{}\"", dataSource);
            return super.resolveSpecifiedDataSource(dataSource);
        }
        throw new PartitionException("datasource should be [javax.sql.DataSource] instance");
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Assert.notNull(lookupKeyStrategy, "lookupKeyStrategy must not be null");
        return lookupKeyStrategy.apply("");
    }

    // init method

    private void initLookupKey(ApplicationContext applicationContext) {
        if (this.lookupKeyStrategy == null) {
            if (!applicationContext.getBeansOfType(PartitionLookupKeyStrategy.class).isEmpty()) {
                this.lookupKeyStrategy = applicationContext.getBean(PartitionLookupKeyStrategy.class);
            } else {
                throw new NullPointerException("lookupKeyStrategy must not be null");
            }
        }
    }

    private void initTargetDataSources(ApplicationContext applicationContext) {
        final Map<String, DataSource> dataSourceMap = applicationContext.getBeansOfType(DataSource.class);
        if (dataSourceMap != null && !dataSourceMap.isEmpty()) {
            // 转换 Map<String, DataSource> 为 Map<Object, Object>
            Map<Object, Object> map = new HashMap<>(dataSourceMap.size());
            for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            super.setTargetDataSources(map);
        }
    }

    private void initDefaultTargetDataSource(ApplicationContext applicationContext) {
        if (applicationContext.containsBean(DEFAULT_DATA_SOURCE)) {
            Object defaultDataSource = applicationContext.getBean(DEFAULT_DATA_SOURCE);
            if (defaultDataSource instanceof DataSource)
                super.setDefaultTargetDataSource(defaultDataSource);
        }
    }
}
