package cn.fantasticmao.mundo.data.support;

import org.springframework.data.relational.core.dialect.AbstractDialect;
import org.springframework.data.relational.core.dialect.LimitClause;
import org.springframework.data.relational.core.dialect.LockClause;
import org.springframework.data.relational.core.sql.LockOptions;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * An SQL dialect for SQLite.
 *
 * @author fantasticmao
 * @version 1.0.9
 * @since 2022-12-10
 */
public class SQLiteDialect extends AbstractDialect {

    /**
     * Singleton instance.
     */
    public static final SQLiteDialect INSTANCE = new SQLiteDialect();

    @Nonnull
    @Override
    public LimitClause limit() {
        return SQLiteLimitClause.INSTANCE;
    }

    @Nonnull
    @Override
    public LockClause lock() {
        return SQLiteLockClause.INSTANCE;
    }

    @Nonnull
    @Override
    public Collection<Object> getConverters() {
        return List.of(
            SQLiteConverters.StringToLocalTime.INSTANCE,
            SQLiteConverters.StringToLocalDate.INSTANCE,
            SQLiteConverters.StringToLocalDateTime.INSTANCE,
            SQLiteConverters.LocalTimeToString.INSTANCE,
            SQLiteConverters.LocalDateToString.INSTANCE,
            SQLiteConverters.LocalDateTimeToString.INSTANCE
        );
    }

    enum SQLiteLimitClause implements LimitClause {
        INSTANCE;

        @Nonnull
        @Override
        public String getLimit(long limit) {
            return "LIMIT " + limit;
        }

        @Nonnull
        @Override
        public String getOffset(long offset) {
            return "OFFSET " + offset;
        }

        @Nonnull
        @Override
        public String getLimitOffset(long limit, long offset) {
            return String.format("LIMIT %d OFFSET %d", limit, offset);
        }

        @Nonnull
        @Override
        public Position getClausePosition() {
            return Position.AFTER_ORDER_BY;
        }
    }

    enum SQLiteLockClause implements LockClause {
        INSTANCE;

        @Nonnull
        @Override
        public String getLock(@Nonnull LockOptions lockOptions) {
            return "";
        }

        @Nonnull
        @Override
        public Position getClausePosition() {
            return Position.AFTER_ORDER_BY;
        }
    }

}
