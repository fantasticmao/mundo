package cn.fantasticmao.mundo.core.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtil
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/5
 */
public final class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Java Object to JSON
     */
    public static String toJson(Object obj) throws JacksonException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    /**
     * JSON to Java Object
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws JacksonException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    /**
     * JSON to Java Object
     */
    public static <T> T fromJson(String json, TypeReference<T> reference) throws JacksonException {
        return OBJECT_MAPPER.readValue(json, reference);
    }

}
