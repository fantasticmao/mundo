package com.mundo.support;

import com.mundo.util.JsonUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiPredicate;

/**
 * MapBuilder
 *
 * @author maomao
 * @since 2017/3/5
 */
public class MapBuilder<K, V> {
    private Map<K, V> map;

    public enum Type {
        HASH, TREE, LINKED_HASH
    }

    private MapBuilder() {
        throw new AssertionError("No com.maomao.support.MapBuilder instances for you!");
    }

    private MapBuilder(Type type) {
        switch (type) {
            case HASH:
                map = new HashMap<>();
                break;
            case TREE:
                map = new TreeMap<>();
                break;
            case LINKED_HASH:
                map = new LinkedHashMap<>();
                break;
        }
    }

    public static <K, V> MapBuilder<K, V> create() {
        return new MapBuilder<>(Type.HASH);
    }

    public static <K, V> MapBuilder<K, V> create(Type type) {
        return new MapBuilder<>(type);
    }

    public MapBuilder<K, V> put(K k, V v) {
        map.put(k, v);
        return this;
    }

    public MapBuilder<K, V> ifPut(K k, V v, BiPredicate<K, V> biPredicate) {
        if (biPredicate.test(k, v)) {
            map.put(k, v);
        }
        return this;
    }

    public Map<K, V> build() {
        return map;
    }

    public String toJson() {
        return JsonUtil.toJson(map).orElse(null);
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
