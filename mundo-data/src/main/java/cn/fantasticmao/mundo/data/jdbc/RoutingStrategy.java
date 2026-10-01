package cn.fantasticmao.mundo.data.jdbc;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

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
    String getKey(@NonNull SEED seed);

    /**
     * Routes by {@code seed % num}, formatted with {@code format}.
     *
     * @param <SEED> numeric seed type
     */
    class ShardingByMod<SEED extends Number> implements RoutingStrategy<SEED> {
        private final String format;
        private final int num;

        /**
         * Creates a modulo sharding strategy.
         *
         * @param format lookup-key format, for example {@code "ds_%d"}
         * @param num    number of shards
         */
        public ShardingByMod(String format, int num) {
            this.format = format;
            this.num = num;
        }

        @Nullable
        @Override
        public String getKey(@NonNull SEED seed) {
            int index = seed.intValue() % num;
            return String.format(format, index);
        }
    }

    /**
     * Routes by formatting the tenant name with {@code format}.
     */
    class MultiTenant implements RoutingStrategy<String> {
        private final String format;

        /**
         * Creates a multi-tenant strategy.
         *
         * @param format lookup-key format, for example {@code "ds_%s"}
         */
        public MultiTenant(String format) {
            this.format = format;
        }

        @Nullable
        @Override
        public String getKey(@NonNull String tenant) {
            return String.format(format, tenant);
        }
    }

}
