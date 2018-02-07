package com.mundo.core.support;

import com.mundo.core.util.JsonUtil;
import org.apache.commons.lang3.builder.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ListBuilder
 *
 * @author maodh
 * @since 2017/7/25
 */
public class ListBuilder<E> implements Builder<List<E>> {
    private List<E> list;

    private ListBuilder() {
        throw new AssertionError("No com.mundo.core.support.ListBuilder instances for you!");
    }

    private ListBuilder(List<E> list) {
        this.list = list;
    }

    public static <E> ListBuilder<E> create() {
        return create(ArrayList::new);
    }

    public static <E> ListBuilder<E> create(int capacity) {
        return create(() -> new ArrayList<E>(capacity));
    }

    public static <E> ListBuilder<E> create(Supplier<List<E>> supplier) {
        return new ListBuilder<>(supplier.get());
    }

    public ListBuilder<E> add(E e) {
        list.add(e);
        return this;
    }

    public ListBuilder<E> addIfAbsent(E e) {
        return addIf(e, element -> !list.contains(element));
    }

    public ListBuilder<E> addIfNonNull(E e) {
        return addIf(e, Objects::nonNull);
    }

    public ListBuilder<E> addIf(E e, Predicate<E> predicate) {
        if (predicate.test(e)) {
            list.add(e);
        }
        return this;
    }

    @Override
    public List<E> build() {
        return list;
    }

    public String toJson() {
        return JsonUtil.toJson(list).orElse(null);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
