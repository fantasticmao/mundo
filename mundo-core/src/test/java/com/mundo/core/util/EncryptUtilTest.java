package com.mundo.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * EncryptUtilTest
 *
 * @author maomao
 * @since 17-4-24
 */
public class EncryptUtilTest {

    @Test
    public void atob() throws Exception {
        String expected = EncryptUtil.atob("czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3");
        String actual = "s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void btoa() throws Exception {
        String expected = EncryptUtil.btoa("s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw");
        String actual = "czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void digest() throws Exception {
        String expected = EncryptUtil.digest(EncryptUtil.Type.MD5, "13291297303");
        String actual = "647d92b144f1fa91496ac402bff0ba4c";
        Assert.assertEquals(expected, actual);
    }

}