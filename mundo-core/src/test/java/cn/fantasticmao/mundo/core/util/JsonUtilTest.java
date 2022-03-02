package cn.fantasticmao.mundo.core.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * JsonUtilTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018/1/6
 */
public class JsonUtilTest {

    @Test
    public void toJson() throws JacksonException {
        User user = new User();
        user.setName("Tom");
        user.setAge(20);
        String json = JsonUtil.toJson(user);
        Assertions.assertEquals("{\"name\":\"Tom\",\"age\":20}", json);
    }

    @Test
    public void fromJson1() throws JacksonException {
        String json = "{\"name\":\"Tom\",\"age\":20}";
        User user = JsonUtil.fromJson(json, User.class);
        Assertions.assertEquals("Tom", user.getName());
        Assertions.assertEquals(20, user.getAge());
    }

    @Test
    public void fromJson2() throws JacksonException {
        String json = "{\"name\":\"Tom\",\"age\":20}";
        TypeReference<Map<String, Object>> reference = new TypeReference<>() {
        };
        Map<String, Object> map = JsonUtil.fromJson(json, reference);
        Assertions.assertEquals("Tom", map.get("name"));
        Assertions.assertEquals(20, map.get("age"));
    }

    private static class User {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
