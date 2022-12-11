package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
                             @Nonnull DatabaseMetaData databaseMetaData,
                             RoutingStrategy<SEED> routingStrategy, Class<SEED> seedClass) {
        this(dataSources, routingStrategy, seedClass);

        DataSource defaultDataSource = new AbstractDataSource() {
            private final Connection connection = new AbstractConnection() {
                @Override
                public DatabaseMetaData getMetaData() {
                    return databaseMetaData;
                }
            };

            @Override
            public Connection getConnection() {
                return connection;
            }

            @Override
            public Connection getConnection(String username, String password) {
                return connection;
            }
        };
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    public RoutingDataSource(@Nonnull Map<Object, DataSource> dataSources,
                             @Nonnull DataSource defaultDataSource,
                             RoutingStrategy<SEED> routingStrategy, Class<SEED> seedClass) {
        this(dataSources, routingStrategy, seedClass);

        super.setDefaultTargetDataSource(defaultDataSource);
    }

    private RoutingDataSource(@Nonnull Map<Object, DataSource> dataSources,
                              RoutingStrategy<SEED> routingStrategy, Class<SEED> seedClass) {
        Map<Object, Object> targetDataSources = dataSources.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        super.setTargetDataSources(targetDataSources);

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
