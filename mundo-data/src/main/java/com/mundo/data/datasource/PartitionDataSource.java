package com.mundo.data.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
public class PartitionDataSource extends AbstractRoutingDataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionDataSource.class);

    private PartitionLookupKeyStrategy lookupKeyStrategy;

    public PartitionDataSource(@Nonnull Map<String, DataSource> dataSources,
                               @Nullable DataSource defaultDataSource,
                               @Nonnull PartitionLookupKeyStrategy lookupKeyStrategy) {
        initTargetDataSources(dataSources);
        initDefaultTargetDataSource(defaultDataSource);
        this.lookupKeyStrategy = lookupKeyStrategy;
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

    private void initTargetDataSources(Map<String, DataSource> dataSources) {
        if (dataSources != null && !dataSources.isEmpty()) {
            // 转换 Map<String, DataSource> 为 Map<Object, Object>
            Map<Object, Object> targetDataSources = new HashMap<>(dataSources.size());
            for (Map.Entry<String, DataSource> entry : dataSources.entrySet()) {
                targetDataSources.put(entry.getKey(), entry.getValue());
            }
            super.setTargetDataSources(targetDataSources);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("loading targetDataSources: \"{}\"", targetDataSources);
            }
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("loading targetDataSources is null or empty");
            }
        }
    }

    private void initDefaultTargetDataSource(DataSource defaultDataSource) {
        if (defaultDataSource != null) {
            super.setDefaultTargetDataSource(defaultDataSource);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("loading defaultTargetDataSource: \"{}\"", defaultDataSource);
            }
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("loading defaultTargetDataSource is null");
            }
        }
    }

}