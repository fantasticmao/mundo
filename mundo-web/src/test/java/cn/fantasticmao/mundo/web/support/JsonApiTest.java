package cn.fantasticmao.mundo.web.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * JsonApiTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/19
 */
public class JsonApiTest {

    @Test
    public void testJsonApi1() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\"}";
        String actual = JsonApi.success().data(null).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi2() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":1}";
        String actual = JsonApi.success().data(1).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi3() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":true}";
        String actual = JsonApi.success().data(true).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi4() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":\"hello\"}";
        String actual = JsonApi.success().data("hello").toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi5() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":[1,2,3]}";
        String actual = JsonApi.success().data(new int[]{1, 2, 3}).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi6() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":{\"hello\":123}}";
        Map<String, Integer> map = new HashMap<>();
        map.put("hello", 123);
        String actual = JsonApi.success().data(map).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi7() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":false}";
        String actual = JsonApi.success().data(true).data(false).toString();
        Assertions.assertEquals(expected, actual);
    }
}
