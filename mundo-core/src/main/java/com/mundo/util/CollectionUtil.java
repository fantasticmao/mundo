package com.mundo.util;

import com.mundo.constant.Strings;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

/**
 * CollectionUtil
 *
 * @author maomao
 * @since 2017/3/4
 */
public final class CollectionUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <E> E findFirstMatch(Collection<E> collection, Predicate<E> predicate) {
        if (isNotEmpty(collection)) {
            for (E e : collection) {
                if (predicate.test(e)) {
                    return e;
                }
            }
        }
        return null;
    }

    public static String join(Collection<String> collection) {
        return join(collection, Strings.COMMA);
    }

    public static String join(Collection<String> collection, String separator) {
        String[] strArray = collection.toArray(new String[collection.size()]);
        return ArrayUtil.join(strArray, separator);
    }
}
