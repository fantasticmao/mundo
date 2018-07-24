package com.mundo.data.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

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

    public PartitionDataSource(PartitionLookupKeyStrategy lookupKeyStrategy, Map<String, DataSource> targetDataSources) {
        this(lookupKeyStrategy, targetDataSources, null);
    }

    public PartitionDataSource(PartitionLookupKeyStrategy lookupKeyStrategy, Map<String, DataSource> targetDataSources,
                               DataSource defaultTargetDataSource) {
        this.lookupKeyStrategy = lookupKeyStrategy;

        // 转换 Map<String, DataSource> 为 Map<Object, Object>
        Map<Object, Object> map = new HashMap<>(targetDataSources.size());
        for (Map.Entry<String, DataSource> entry : targetDataSources.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        setTargetDataSources(map);

        if (defaultTargetDataSource != null)
            setDefaultTargetDataSource(defaultTargetDataSource);
    }

    @Override
    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        LOGGER.info("Loaded lookupKey \"{}\"", lookupKey);
        return super.resolveSpecifiedLookupKey(lookupKey);
    }

    @Override
    protected DataSource resolveSpecifiedDataSource(Object dataSource) throws IllegalArgumentException {
        LOGGER.info("Loaded dataSource \"{}\"", dataSource);
        return super.resolveSpecifiedDataSource(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return lookupKeyStrategy.apply("");
    }
}
