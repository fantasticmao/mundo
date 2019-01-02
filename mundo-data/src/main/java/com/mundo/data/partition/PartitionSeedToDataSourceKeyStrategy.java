package com.mundo.data.partition;

import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * 将 {@link PartitionSeedContext} 托管的 <code>PartitionSeed</code> 转换成对应 {@link javax.sql.DataSource} 的 Lookup Key。
 *
 * @author maodh
 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineTargetDataSource()
 * @since 2018/7/25
 */
@FunctionalInterface
public interface PartitionSeedToDataSourceKeyStrategy extends Function<Object, String> {

    class NumberModulusStrategy implements PartitionSeedToDataSourceKeyStrategy {
        private String formatStr;
        private int dataSourceSize;

        public NumberModulusStrategy(String formatStr, int dataSourceSize) {
            this.formatStr = formatStr;
            this.dataSourceSize = dataSourceSize;
        }

        @Override
        public String apply(@Nullable Object seedObject) {
            if (seedObject == null) { // 选择默认数据源
                return null;
            }

            if (seedObject instanceof PartitionSeedProvider) {
                seedObject = ((PartitionSeedProvider) seedObject).getSeed();
            }

            if (seedObject instanceof Number) {
                Number seedNumber = (Number) seedObject;
                return String.format(formatStr, (seedNumber.longValue() % dataSourceSize) + 1);
            } else {
                throw new IllegalArgumentException("cannot cast the partition seed object '" + seedObject + "' to 'java.lang.Number'");
            }
        }
    }

    class TimeRangeStrategy implements PartitionSeedToDataSourceKeyStrategy {

        @Override
        public String apply(Object o) {
            // TODO 基于时间范围分库
            return null;
        }
    }
}