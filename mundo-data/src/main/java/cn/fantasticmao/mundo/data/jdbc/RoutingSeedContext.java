package cn.fantasticmao.mundo.data.jdbc;

import javax.annotation.Nullable;

/**
 * RoutingSeedContext
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
public class RoutingSeedContext {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void set(String seed) {
        CONTEXT.set(seed);
    }

    @Nullable
    public static <T> T get(Class<T> seedClass) {
        String seed = CONTEXT.get();
        if (seed == null || seed.isEmpty()) {
            return null;
        }
        // FIXME
        if (seedClass.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(seed);
        } else if (seedClass.isAssignableFrom(Long.class)) {
            return (T) Long.valueOf(seed);
        } else if (seedClass.isAssignableFrom(String.class)) {
            return (T) seed;
        } else {
            throw new ClassCastException("seed: " + seed);
        }
    }

    public static void remove() {
        CONTEXT.remove();
    }
}
