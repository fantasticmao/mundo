package com.mundo.support;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * ListBuilderTest
 *
 * @author maodh
 * @since 2018/1/10
 */
public class ListBuilderTest {

    @Test
    public void add() {
        List<Integer> list = ListBuilder.<Integer>create().add(1).add(2).add(3).add(4).build();
        System.out.println(list);
    }

    @Test
    public void ifAdd() {
        List<Integer> list = ListBuilder.<Integer>create().add(1).ifAdd(2, e -> false).ifAdd(3, e -> true).add(4).build();
        System.out.println(list);
    }

}