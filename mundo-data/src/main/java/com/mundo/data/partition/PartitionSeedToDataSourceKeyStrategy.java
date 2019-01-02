package com.mundo.data.partition;

import java.util.function.Function;

/**
 * PartitionSeedToDataSourceKeyStrategy
 * 将 {@link PartitionSeedContext} 托管的 <code>PartitionSeed</code> 转换成对应 {@link javax.sql.DataSource} 的 Lookup Key
 *
 * @author maodh
 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineTargetDataSource()
 * @since 2018/7/25
 */
@FunctionalInterface
public interface PartitionSeedToDataSourceKeyStrategy extends Function<Object, String> {
}