package com.mundo.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * HashUtilTest
 *
 * @author maomao
 * @since 17-4-24
 */
public class HashUtilTest {

    @Test
    public void encode() {
        String expected = HashUtil.encode("s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw");
        String actual = "czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void decode() {
        String expected = HashUtil.decode("czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3");
        String actual = "s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void digestBytes() {
        String expected = HashUtil.MD5.hash("13291297303".getBytes());
        String actual = "647d92b144f1fa91496ac402bff0ba4c";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void digestString() {
        String expected = HashUtil.MD5.hash("13291297303");
        String actual = "647d92b144f1fa91496ac402bff0ba4c";
        Assert.assertEquals(expected, actual);
    }

}