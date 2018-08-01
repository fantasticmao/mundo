package com.mundo.core.util;

import com.mundo.core.support.Constant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * CollectionUtil
 *
 * @author maomao
 * @since 2017/3/4
 */
public interface CollectionUtil {

    static String toString(Collection<?> collection) {
        return toString(collection, Constant.Strings.COMMA);
    }

    /**
     * @see java.lang.String#join(CharSequence, CharSequence...)
     * @see java.lang.String#join(CharSequence, Iterable)
     */
    static String toString(Collection<?> collection, String delimiter) {
        return collection.stream().map(Objects::toString).collect(Collectors.joining(delimiter));
    }

    static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    static boolean isNotEmpty(Map<?, ?> map) {
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
