package cn.fantasticmao.mundo.core.util;

import org.junit.Test;

/**
 * JsonUtilTest
 *
 * @author maodh
 * @since 2018/1/6
 */
public class JsonUtilTest {

    @Test
    public void toMap() {
        System.out.println(JsonUtil.toMap("{\"key\":\"value\"}").getClass());
    }

    @Test
    public void toList() {
        System.out.println(JsonUtil.toList("[1,2]").getClass());
    }

    @Test
    public void toSet() {
        System.out.println(JsonUtil.toSet("[1,2]").getClass());
    }
}
