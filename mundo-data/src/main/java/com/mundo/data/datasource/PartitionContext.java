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

    public static Long push(Long seed) {
        return seedContext.get().push(seed);
    }

    public static Long pop() {
        Stack<Long> stack = seedContext.get();
        return stack.empty() ? 0L : stack.pop();
    }

    @Override
    public void close() throws Exception {
        seedContext.remove();
    }
}
