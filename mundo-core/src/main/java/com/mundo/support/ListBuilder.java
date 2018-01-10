package com.mundo.support;

import com.mundo.util.JsonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * ListBuilder
 *
 * @author maodh
 * @since 2017/7/25
 */
public class ListBuilder<E> {
    private List<E> list;

    public enum Type {
        ARRAY, LINKED
    }

    private ListBuilder() {
        throw new AssertionError("No com.maomao.support.ListBuilder instances for you!");
    }

    private ListBuilder(Type type) {
        switch (type) {
            case ARRAY:
                list = new ArrayList<>();
                break;
            case LINKED:
                list = new LinkedList<>();
                break;
        }
    }

    public static <E> ListBuilder<E> create() {
        return new ListBuilder<>(Type.ARRAY);
    }

    public static <E> ListBuilder<E> create(Type type) {
        return new ListBuilder<>(type);
    }

    public ListBuilder<E> add(E e) {
        list.add(e);
        return this;
    }

    public ListBuilder<E> ifAdd(E e, Predicate<E> predicate) {
        if (predicate.test(e)) {
            list.add(e);
        }
        return this;
    }

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
