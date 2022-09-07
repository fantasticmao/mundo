package cn.fantasticmao.mundo.web.support;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * JsonApiTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017-03-19
 */
public class JsonApiTest {

    @Test
    public void fromJson() throws JacksonException {
        String json = "{\"status\":false,\"code\":404,\"message\":\"Not Found\",\"data\":\"hello\"}";
        TypeReference<JsonApi<String>> reference = new TypeReference<>() {
        };
        JsonApi<String> jsonApi = JsonUtil.fromJson(json, reference);
        Assertions.assertFalse(jsonApi.isStatus());
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), jsonApi.getCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), jsonApi.getMessage());
        Assertions.assertEquals("hello", jsonApi.getData());
    }

    @Test
    public void toJson1() throws JacksonException {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\"}";
        String actual = JsonUtil.toJson(JsonApi.ok(null));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toJson2() throws JacksonException {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":1}";
        String actual = JsonUtil.toJson(JsonApi.ok(1));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toJson3() throws JacksonException {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":true}";
        String actual = JsonUtil.toJson(JsonApi.ok(true));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toJson4() throws JacksonException {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":\"hello\"}";
        String actual = JsonUtil.toJson(JsonApi.ok("hello"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toJson5() throws JacksonException {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":[1,2,3]}";
        String actual = JsonUtil.toJson(JsonApi.ok(new int[]{1, 2, 3}));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toJson6() throws JacksonException {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":{\"hello\":123}}";
        Map<String, Integer> map = new HashMap<>();
        map.put("hello", 123);
        String actual = JsonUtil.toJson(JsonApi.ok(map));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toJson7() throws JacksonException {
        String expected = "{\"status\":true,\"code\":200,\"message\":\"OK\",\"data\":false}";
        String actual = JsonUtil.toJson(JsonApi.ok(false));
        Assertions.assertEquals(expected, actual);
    }
}
