package cn.fantasticmao.mundo.core.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * CollectionUtilTest
 *
 * @author maodh
 * @since 18/01/2018
 */
public class CollectionUtilTest {
    private Collection<Integer> collection1 = Arrays.asList(1, 2, 3);
    private Collection<Integer> collection2 = Arrays.asList(3, 4, 5);

    @Test
    public void complement1() {
        System.out.println(CollectionUtil.complement(collection1, collection2));
    }

    @Test
    public void complement2() {
        System.out.println(CollectionUtil.complement(collection2, collection1));
    }

}