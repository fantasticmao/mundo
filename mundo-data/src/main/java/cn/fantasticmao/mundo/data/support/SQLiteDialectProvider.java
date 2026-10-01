package cn.fantasticmao.mundo.data.support;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jdbc.core.dialect.DialectResolver;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

/**
 * A provider for {@link SQLiteDialect}.
 *
 * @author fantasticmao
 * @version 1.0.9
 * @since 2022-12-10
 */
public class SQLiteDialectProvider implements DialectResolver.JdbcDialectProvider {

    @NonNull
    @Override
    public Optional<Dialect> getDialect(@NonNull JdbcOperations operations) {
        ConnectionCallback<Dialect> callback = this::getDialect;
        return Optional.ofNullable(operations.execute(callback));
    }

    private Dialect getDialect(@NonNull Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        String name = metaData.getDatabaseProductName().toLowerCase(Locale.ENGLISH);
        return name.contains("sqlite") ? SQLiteDialect.INSTANCE : null;
    }
}
