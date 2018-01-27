package com.mundo.core.util;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {
    private Integer[] integers = new Integer[]{1, 2, 3, 4, 5};

    @Test
    public void join1() {
        String expected = "1,2,3,4,5";
        String actual = ArrayUtil.join(integers);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void join2() {
        String expected = "1||2||3||4||5";
        String actual = ArrayUtil.join(integers, "||");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void reverse() {
        Integer[] expected = new Integer[]{5, 4, 3, 2, 1};
        Integer[] actual = integers;
        ArrayUtil.reverse(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void concat() {
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        Integer[] actual = ArrayUtil.concat(integers, 6, 7);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void slice1() {
        Integer[] expected = new Integer[]{2, 3, 4, 5};
        Integer[] actual = ArrayUtil.slice(integers, 1);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void slice2() {
        Integer[] expected = new Integer[]{5};
        Integer[] actual = ArrayUtil.slice(integers, -1);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void slice3() {
        Integer[] expected = new Integer[]{3, 4};
        Integer[] actual = ArrayUtil.slice(integers, 2, 4);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void slice4() {
        Integer[] expected = new Integer[]{1, 2, 3, 4};
        Integer[] actual = ArrayUtil.slice(integers, 0, -1);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void slice5() {
        Integer[] expected = new Integer[]{};
        Integer[] actual = ArrayUtil.slice(integers, 0, 0);
        Assert.assertArrayEquals(expected, actual);
    }
}
