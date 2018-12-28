package com.mundo.data.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
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
    private static final String DEFAULT_PARTITION_LOOKUP_KEY_STRATEGY = "partitionLookupKeyStrategy";

    private PartitionLookupKeyStrategy lookupKeyStrategy;

    public PartitionDataSource() {
    }

    public PartitionDataSource(PartitionLookupKeyStrategy lookupKeyStrategy) {
        this.lookupKeyStrategy = lookupKeyStrategy;
    }

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        initLookupKey(applicationContext);
        initTargetDataSources(applicationContext);
        initDefaultTargetDataSource(applicationContext);
    }

    @Override
    @Nonnull
    protected Object resolveSpecifiedLookupKey(@Nonnull Object lookupKey) {
        if (lookupKey instanceof String) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("loaded lookupKey: \"{}\"", lookupKey);
            }
            return super.resolveSpecifiedLookupKey(lookupKey);
        }
        throw new PartitionException("lookupKey should be [javax.sql.DataSource] instance's bean name");
    }

    @Override
    @Nonnull
    protected DataSource resolveSpecifiedDataSource(@Nonnull Object dataSource) throws IllegalArgumentException {
        if (dataSource instanceof DataSource) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("loaded dataSource: \"{}\"", dataSource);
            }
            return super.resolveSpecifiedDataSource(dataSource);
        }
        throw new PartitionException("datasource should be [javax.sql.DataSource] instance");
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Assert.notNull(lookupKeyStrategy, "lookupKeyStrategy must not be null");
        Object seedObject = PartitionSeedContext.pop();
        String lookupKey = lookupKeyStrategy.apply(seedObject);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("use seed \"{}\" to determine the current lookup key \"{}\"", seedObject, lookupKey);
        }
        return lookupKey;
    }

    // init method

    private void initLookupKey(ApplicationContext applicationContext) {
        if (this.lookupKeyStrategy == null) {
            try {
                this.lookupKeyStrategy = applicationContext.getBean(DEFAULT_PARTITION_LOOKUP_KEY_STRATEGY, PartitionLookupKeyStrategy.class);
            } catch (NoSuchBeanDefinitionException e) {
                throw new PartitionException("cannot find " + DEFAULT_PARTITION_LOOKUP_KEY_STRATEGY + " bean", e);
            }
        }
    }

    private void initTargetDataSources(ApplicationContext applicationContext) {
        final Map<String, DataSource> dataSourceMap = applicationContext.getBeansOfType(DataSource.class);
        if (dataSourceMap != null && !dataSourceMap.isEmpty()) {
            // 转换 Map<String, DataSource> 为 Map<Object, Object>
            Map<Object, Object> map = new HashMap<>(dataSourceMap.size());
            for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
                final String key = entry.getKey();
                final DataSource dataSource = entry.getValue();
                if (!(dataSource instanceof PartitionDataSource)) {
                    map.put(key, dataSource);
                }
            }
            super.setTargetDataSources(map);
        }
    }

    private void initDefaultTargetDataSource(ApplicationContext applicationContext) {
        if (applicationContext.containsBean(DEFAULT_DATA_SOURCE)) {
            Object defaultDataSource = applicationContext.getBean(DEFAULT_DATA_SOURCE);
            if (defaultDataSource != null && defaultDataSource instanceof DataSource
                    && !(defaultDataSource instanceof PartitionDataSource)) {
                super.setDefaultTargetDataSource(defaultDataSource);
            }
        }
    }
}
