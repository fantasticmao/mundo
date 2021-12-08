package cn.fantasticmao.mundo.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * PageUtilTest
 *
 * @author fantasticmao
 * @version 1.0.3
 * @since 2021-12-08
 */
class PageUtilTest {

    @Test
    void offset() {
        Assertions.assertEquals(100, PageUtil.offset(5, 20));
        Assertions.assertEquals(0, PageUtil.offset(-1, 20));
        Assertions.assertEquals(5, PageUtil.offset(5, -20));
    }

    @Test
    void size() {
        Assertions.assertEquals(20, PageUtil.size(20));
        Assertions.assertEquals(1, PageUtil.size(-20));
        Assertions.assertEquals(500, PageUtil.size(1000));
    }
}