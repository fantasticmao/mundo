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
    public void encode() {
        byte[] expected = HashUtil.encode("s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw");
        byte[] actual = "czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3".getBytes();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void decode() {
        byte[] expected = HashUtil.decode("czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3");
        byte[] actual = "s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw".getBytes();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void encodeToString() {
        String expected = HashUtil.encodeToString("s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw");
        String actual = "czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void decodeToString() {
        String expected = HashUtil.decodeToString("czZCaGRSa3F0Mzo3RmpmcDBaQnIxS3REUmJuZlZkbUl3");
        String actual = "s6BhdRkqt3:7Fjfp0ZBr1KtDRbnfVdmIw";
        Assertions.assertEquals(expected, actual);
    }

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