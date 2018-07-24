package com.mundo.data.datasource;

import java.util.Stack;

/**
 * PartitionSeedContext
 *
 * @author maodh
 * @since 2017/11/16
 */
public class PartitionSeedContext implements AutoCloseable {
    private static final ThreadLocal<Stack<Object>> SEED_STACK = new ThreadLocal<>();

    public static Object push(Long seed) {
        if (SEED_STACK.get() == null) {
            SEED_STACK.set(new Stack<>());
        }
        return SEED_STACK.get().push(seed);
    }

    public static Object pop() {
        Stack<Object> stack = SEED_STACK.get();
        if (stack != null && !stack.empty()) {
            return stack.pop();
        }
        throw new PartitionException("seed stack is null or empty");
    }

    @Override
    public void close() throws Exception {
        PartitionSeedContext.SEED_STACK.remove();
    }
}
