package com.mundo.web.support;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonApiTest {

    @Test
    public void testJsonApi1() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\"}";
        String actual = JsonApi.success().data(null).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi2() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":1}";
        String actual = JsonApi.success().data(1).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi3() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":true}";
        String actual = JsonApi.success().data(true).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi4() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":\"hello\"}";
        String actual = JsonApi.success().data("hello").toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi5() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":[1,2,3]}";
        String actual = JsonApi.success().data(new int[]{1, 2, 3}).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testJsonApi6() {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":{\"hello\":123}}";
        Map<String, Integer> map = new HashMap<>();
        map.put("hello", 123);
        String actual = JsonApi.success().data(map).toString();
        Assert.assertEquals(expected, actual);
    }
}
