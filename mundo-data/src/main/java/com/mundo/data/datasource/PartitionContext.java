package com.mundo.data.datasource;

import java.util.Stack;

/**
 * PartitionContext
 *
 * @author maodh
 * @since 2017/11/16
 */
public class PartitionContext implements AutoCloseable {
    private static final ThreadLocal<Stack<Long>> seedContext = new ThreadLocal<>();

    public static synchronized Long push(Long seed) {
        if (seedContext.get() == null) {
            seedContext.set(new Stack<>());
        }
        return seedContext.get().push(seed);
    }

    public static synchronized Long pop() {
        Stack<Long> stack = seedContext.get();
        return (stack != null && !stack.empty()) ? stack.pop() : 0L;
    }

    @Override
    public void close() throws Exception {
        seedContext.remove();
    }
}
