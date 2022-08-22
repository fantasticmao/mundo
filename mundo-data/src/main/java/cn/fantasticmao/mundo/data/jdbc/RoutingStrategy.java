package cn.fantasticmao.mundo.data.jdbc;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * {@link RoutingDataSource DataSoure} route strategy based on the {@link RoutingSeed seed}.
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-16
 */
public interface RoutingStrategy<SEED> {

    /**
     * Get the {@link RoutingDataSource} lookup key by the current {@link RoutingSeed}.
     *
     * @param seed the current thread-local route seed
     * @return datasource lookup key
     */
    @Nullable
    String getKey(@Nonnull SEED seed);

    class ShardingByMod<SEED extends Number> implements RoutingStrategy<SEED> {
        private final String format;
        private final int num;

        public ShardingByMod(String format, int num) {
            this.format = format;
            this.num = num;
        }

        @Nullable
        @Override
        public String getKey(@Nonnull SEED seed) {
            int index = seed.intValue() % num;
            return String.format(format, index);
        }
    }

    class MultiTenant implements RoutingStrategy<String> {
        private final String format;

        public MultiTenant(String format) {
            this.format = format;
        }

        @Nullable
        @Override
        public String getKey(@Nonnull String tenant) {
            return String.format(format, tenant);
        }
    }

}
