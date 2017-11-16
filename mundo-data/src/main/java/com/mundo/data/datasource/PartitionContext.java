package com.mundo.data.datasource;

/**
 * PartitionContext
 *
 * @author maodh
 * @since 2017/11/16
 */
public class PartitionContext implements AutoCloseable {
    private static final ThreadLocal<Integer> CONTEXT = new ThreadLocal<>();

    @Override
    public void close() throws Exception {
        CONTEXT.remove();
    }
}
