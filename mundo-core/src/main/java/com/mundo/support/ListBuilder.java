package com.mundo.support;

import com.mundo.util.JsonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public static ListBuilder create() {
        return new ListBuilder(Type.ARRAY);
    }

    public static ListBuilder create(Type type) {
        return new ListBuilder(type);
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

    public ListBuilder<E> add(E e) {
        list.add(e);
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
