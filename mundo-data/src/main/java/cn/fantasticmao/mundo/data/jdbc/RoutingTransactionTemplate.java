package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Another {@link TransactionTemplate} that supports routing datasource.
 *
 * @author fantasticmao
 * @version 1.0.7
 * @since 2022-09-04
 */
public class RoutingTransactionTemplate {
    private final TransactionTemplate delegate;

    public RoutingTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.delegate = transactionTemplate;
    }

    @Nullable
    public <T> T execute(@Nonnull Object seed, TransactionCallback<T> action)
        throws TransactionException {
        RoutingSeedContext.set(seed);
        try {
            return delegate.execute(action);
        } finally {
            RoutingSeedContext.remove();
        }
    }

    public void executeWithoutResult(@Nonnull Object seed, Consumer<TransactionStatus> action)
        throws TransactionException {
        RoutingSeedContext.set(seed);
        try {
            delegate.executeWithoutResult(action);
        } finally {
            RoutingSeedContext.remove();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoutingTransactionTemplate)) {
            return false;
        }
        RoutingTransactionTemplate that = (RoutingTransactionTemplate) o;
        return Objects.equals(delegate, that.delegate);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return "RoutingTransactionTemplate{delegate=" + delegate + "}";
    }
}
