package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.util.Map;

/**
 * {@link javax.sql.DataSource} implementation that routes {@link #getConnection()}
 * calls to one of various target DataSources based on a lookup key.
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-16
 */
public class RoutingDataSource<SEED> extends AbstractRoutingDataSource {
    private final RoutingStrategy<SEED> routingStrategy;
    private final Class<SEED> seedClass;

    public RoutingDataSource(@Nonnull Map<Object, Object> dataSources,
                             @Nonnull DataSource defaultDataSource,
                             RoutingStrategy<SEED> routingStrategy, Class<SEED> seedClass) {
        super.setTargetDataSources(dataSources);
        super.setDefaultTargetDataSource(defaultDataSource);
        this.routingStrategy = routingStrategy;
        this.seedClass = seedClass;
    }

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        SEED seed;
        try {
            seed = RoutingSeedContext.get(seedClass);
        } finally {
            RoutingSeedContext.remove();
        }

        if (seed == null) {
            return null;
        }
        return routingStrategy.getKey(seed);
    }
}
