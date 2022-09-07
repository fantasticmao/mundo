package cn.fantasticmao.mundo.core.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Operations on {@link ObjectMapper Jackson ObjectMapper}.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017-03-05
 */
public final class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Java Object to JSON String
     *
     * @param obj Java object
     * @return JSON String
     * @throws JacksonException to JSON String error
     */
    public static String toJson(Object obj) throws JacksonException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    /**
     * JSON String to Java Object
     *
     * @param json  JSON String
     * @param clazz Java Object Class
     * @param <T>   Java Object Type
     * @return Java Object
     * @throws JacksonException parse from JSON error
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws JacksonException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    /**
     * JSON String to Java Object
     *
     * @param json      JSON String
     * @param reference Java Object Type Reference
     * @param <T>       Java Object Type
     * @return Java Object
     * @throws JacksonException parse from JSON error
     */
    public static <T> T fromJson(String json, TypeReference<T> reference) throws JacksonException {
        return OBJECT_MAPPER.readValue(json, reference);
    }

}
