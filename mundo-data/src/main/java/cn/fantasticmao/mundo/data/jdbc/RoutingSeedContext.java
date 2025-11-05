package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.core.NamedThreadLocal;

import javax.annotation.Nullable;

/**
 * Holder for the current {@link RoutingDataSource DataSource} route seed.
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-17
 */
final class RoutingSeedContext {
    private static final ThreadLocal<Object> CONTEXT
        = new NamedThreadLocal<>("Current DataSource Route Seed");

    public static void set(Object seed) {
        CONTEXT.set(seed);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clazz) throws ClassCastException {
        Object seed = CONTEXT.get();
        if (seed == null) {
            return null;
        }
        if (seed instanceof RoutingSeed annotation) {
            String value = annotation.value();
            if (Integer.class.isAssignableFrom(clazz)) {
                return (T) Integer.valueOf(value);
            } else if (Long.class.isAssignableFrom(clazz)) {
                return (T) Long.valueOf(value);
            } else if (String.class.isAssignableFrom(clazz)) {
                return (T) value;
            } else {
                throw new ClassCastException("@RoutingSeed currently supports: int, long, string, " +
                    "invalid seed value: " + seed);
            }
        } else {
            return clazz.cast(seed);
        }
    }

    public static void remove() {
        CONTEXT.remove();
    }
}
