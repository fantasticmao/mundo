package cn.fantasticmao.mundo.data.partition;

import java.util.Stack;

/**
 * 使用 {@link java.lang.ThreadLocal} 管理 {@link PartitionParam} 参数生成的 <code>PartitionSeed</code>。
 *
 * @author maodh
 * @version 1.0
 * @since 2017/11/16
 */
public class PartitionSeedContext {
    private static final ThreadLocal<Stack<Object>> SEED_STACK = ThreadLocal.withInitial(Stack::new);

    public static Object push(Object seed) {
        return SEED_STACK.get().push(seed);
    }

    public static Object pop() {
        Stack<Object> stack = SEED_STACK.get();
        return !stack.empty() ? stack.pop() : null;
    }

    public static void clear() {
        PartitionSeedContext.SEED_STACK.remove();
    }
}
