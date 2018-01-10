package com.mundo.support;

import org.junit.Test;

import java.util.Map;

/**
 * MapBuilderTest
 *
 * @author maodh
 * @since 2018/1/8
 */
public class MapBuilderTest {

    @Test
    public void put() {
        Map<String, Object> map = MapBuilder.<String, Object>create()
                .put("one", 1)
                .put("two", 2)
                .put("three", 3)
                .put("four", 4)
                .build();
        System.out.println(map);
    }

    @Test
    public void ifPut() {
        Map<String, Object> map = MapBuilder.<String, Object>create(MapBuilder.Type.LINKED_HASH)
                .put("one", 1)
                .ifPut("two", 2, (k, v) -> false)
                .ifPut("three", 2, (k, v) -> true)
                .put("four", 4)
                .build();
        System.out.println(map);
    }

}