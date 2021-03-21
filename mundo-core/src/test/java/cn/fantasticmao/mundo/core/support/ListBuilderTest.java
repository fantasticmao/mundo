package cn.fantasticmao.mundo.core.support;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * ListBuilderTest
 *
 * @author maodh
 * @version 1.0
 * @since 2018/1/10
 */
public class ListBuilderTest {

    @Test
    public void add() {
        List<Integer> list = ListBuilder.<Integer>create(4).add(1).add(2).add(3).add(4).build();
        System.out.println(list);
    }

    @Test
    public void addIfAbsent() {
        List<Integer> list = ListBuilder.<Integer>create(3).add(1).addIfAbsent(1).addIfAbsent(2).build();
        System.out.println(list);
    }

    @Test
    public void addIfNonNull() {
        List<Integer> list = ListBuilder.<Integer>create(LinkedList::new).add(1).addIfNonNull(null).addIfNonNull(3).build();
        System.out.println(list);
    }

    @Test
    public void ifAdd() {
        List<Integer> list = ListBuilder.<Integer>create().add(1).addIf(2, e -> false).addIf(3, e -> true).add(4).build();
        System.out.println(list);
    }

}