package com.mundo.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {
    private String str = "hello world";

    @Test
    public void isEmpty1() {
        String str = "";
        Assert.assertTrue(StringUtil.isEmpty(str));
    }

    @Test
    public void isEmpty2() {
        String[] str = {"a", ""};
        Assert.assertTrue(StringUtil.isEmpty(str));
    }

    @Test
    public void isNotEmpty1() {
        String str = "a";
        Assert.assertTrue(StringUtil.isNotEmpty(str));
    }

    @Test
    public void isNotEmpty2() {
        String[] str = {"a", "b"};
        Assert.assertTrue(StringUtil.isNotEmpty(str));
    }

    @Test
    public void reverse() {
        String expected = "dlrow olleh";
        String actual = StringUtil.reverse(str);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void slice1() {
        String expected = "world";
        String actual = StringUtil.slice(str, 6);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void slice2() {
        String expected = "d";
        String actual = StringUtil.slice(str, -1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void slice3() {
        String expected = " ";
        String actual = StringUtil.slice(str, 5, 6);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void slice4() {
        String expected = " ";
        String actual = StringUtil.slice(str, 5, -5);
        Assert.assertEquals(expected, actual);
    }
}
