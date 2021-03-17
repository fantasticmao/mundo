package cn.fantasticmao.mundo.data.partition;

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
 * 从 {@link PartitionSeedContext} 获取 <code>PartitionSeed</code>，再由 {@link PartitionSeedToDataSourceKeyStrategy}
 * 转换成对应 {@link javax.sql.DataSource} 的 Lookup Key，再从 {@link #resolvedDataSources} 中动态选择数据源。
 *
 * @author maodh
 * @version 1.0
 * @since 2017/11/16
 */
public class PartitionDataSource extends AbstractRoutingDataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionDataSource.class);

    private PartitionSeedToDataSourceKeyStrategy partitionSeedToDataSourceKeyStrategy;

    public PartitionDataSource(@Nonnull Map<String, DataSource> dataSources,
                               @Nullable DataSource defaultDataSource,
                               @Nonnull PartitionSeedToDataSourceKeyStrategy partitionSeedToDataSourceKeyStrategy) {
        initTargetDataSources(dataSources);
        initDefaultTargetDataSource(defaultDataSource);
        this.partitionSeedToDataSourceKeyStrategy = partitionSeedToDataSourceKeyStrategy;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Assert.notNull(partitionSeedToDataSourceKeyStrategy, "partitionSeedToDataSourceKeyStrategy must not be null");
        Object seedObject = PartitionSeedContext.pop();
        String dataSourceKey = partitionSeedToDataSourceKeyStrategy.apply(seedObject);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("use seed \"{}\" to determine the current lookup key \"{}\"", seedObject, dataSourceKey);
        }
        return dataSourceKey;
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