package com.mundo.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

/**
 * CollectionUtil
 *
 * @author maomao
 * @since 2017/3/4
 */
public interface CollectionUtil {
    static boolean isEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    static boolean isNotEmpty(final Collection<?> collection) {
        return !isEmpty(collection);
    }

    static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }

    static <E> E findFirstMatch(Collection<E> collection, Predicate<E> predicate) {
        if (isNotEmpty(collection)) {
            for (E e : collection) {
                if (predicate.test(e)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * 并集
     */
    static <T> Collection<T> union(Collection<T> collection1, Collection<T> collection2) {
        Collection<T> complementCollection = new ArrayList<>(collection1);
        complementCollection.addAll(collection2);
        return complementCollection;
    }

    /**
     * 补集、差集
     */
    static <T> Collection<T> complement(Collection<T> collection1, Collection<T> collection2) {
        Collection<T> complementCollection = new ArrayList<>(collection1);
        complementCollection.removeAll(collection2);
        return complementCollection;
    }

    /**
     * 交集
     */
    static <T> Collection<T> intersection(Collection<T> collection1, Collection<T> collection2) {
        Collection<T> intersectionCollection = new ArrayList<>(collection1);
        intersectionCollection.retainAll(collection2);
        return intersectionCollection;
    }

}
