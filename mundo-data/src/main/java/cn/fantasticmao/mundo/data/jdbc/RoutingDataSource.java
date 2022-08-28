package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link javax.sql.DataSource} implementation that routes {@link #getConnection()}
 * calls to one of various target DataSources based on a lookup key.
 *
 * @author fantasticmao
 * @version 1.0.6
 * @see #determineTargetDataSource()
 * @see RoutingSeed
 * @since 2022-08-16
 */
public class RoutingDataSource<SEED> extends AbstractRoutingDataSource {
    private final RoutingStrategy<SEED> routingStrategy;
    private final Class<SEED> seedClass;

    public RoutingDataSource(@Nonnull Map<Object, DataSource> dataSources,
                             RoutingStrategy<SEED> routingStrategy, Class<SEED> seedClass) {
        this(dataSources, null, routingStrategy, seedClass);
    }

    public RoutingDataSource(@Nonnull Map<Object, DataSource> dataSources,
                             @Nullable DataSource defaultDataSource,
                             RoutingStrategy<SEED> routingStrategy, Class<SEED> seedClass) {
        Map<Object, Object> targetDataSources = dataSources.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        super.setTargetDataSources(targetDataSources);
        if (defaultDataSource != null) {
            super.setDefaultTargetDataSource(defaultDataSource);
        }

        this.routingStrategy = routingStrategy;
        this.seedClass = seedClass;
    }

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        SEED seed = RoutingSeedContext.get(seedClass);
        return seed != null ? routingStrategy.getKey(seed) : null;
    }
}
