package com.mundo.data.datasource;

import java.util.Stack;

/**
 * PartitionContext
 *
 * @author maodh
 * @since 2017/11/16
 */
public class PartitionContext implements AutoCloseable {
    private static final ThreadLocal<Stack<Long>> CONTEXT = new ThreadLocal<>();

    public static synchronized Long push(Long seed) {
        if (CONTEXT.get() == null) {
            CONTEXT.set(new Stack<>());
        }
        return CONTEXT.get().push(seed);
    }

    public static synchronized Long pop() {
        Stack<Long> stack = CONTEXT.get();
        return (stack != null && !stack.empty()) ? stack.pop() : 0L;
    }

    @Override
    public void close() throws Exception {
        CONTEXT.remove();
    }
}
