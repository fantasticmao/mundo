package cn.fantasticmao.mundo.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * HashUtilTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 17-4-24
 */
public class HashUtilTest {

    @Test
    public void digestBytes() {
        String expected = HashUtil.MD5.hash("13291297303".getBytes());
        String actual = "647d92b144f1fa91496ac402bff0ba4c";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void digestString() {
        String expected = HashUtil.MD5.hash("13291297303");
        String actual = "647d92b144f1fa91496ac402bff0ba4c";
        Assertions.assertEquals(expected, actual);
    }

}