package cn.fantasticmao.mundo.data.jdbc;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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

    /**
     * Creates a template that delegates to the given transaction template.
     *
     * @param transactionTemplate transaction template to delegate to
     */
    public RoutingTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.delegate = transactionTemplate;
    }

    /**
     * Executes the action in a transaction after setting the route seed.
     * <p>
     * The seed is removed from the routing context when the call returns.
     *
     * @param seed   route seed for the current thread
     * @param action callback to execute
     * @param <T>    result type
     * @return result of the callback
     * @throws TransactionException if the transaction fails
     */
    @Nullable
    public <T> T execute(@NonNull Object seed, TransactionCallback<T> action)
        throws TransactionException {
        RoutingSeedContext.set(seed);
        try {
            return delegate.execute(action);
        } finally {
            RoutingSeedContext.remove();
        }
    }

    /**
     * Executes the action in a transaction after setting the route seed.
     * <p>
     * The seed is removed from the routing context when the call returns.
     *
     * @param seed   route seed for the current thread
     * @param action callback to execute
     * @throws TransactionException if the transaction fails
     */
    public void executeWithoutResult(@NonNull Object seed, Consumer<TransactionStatus> action)
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
