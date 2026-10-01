package cn.fantasticmao.mundo.core.util;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * Operations on {@link JsonMapper Jackson JsonMapper}.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017-03-05
 */
public final class JsonUtil {
    private static final JsonMapper JSON_MAPPER = JsonMapper.builder()
        .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false)
        .configure(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS, true)
        .build();

    /**
     * Java Object to JSON String
     *
     * @param obj Java object
     * @return JSON String
     * @throws JacksonException to JSON String error
     */
    public static String toJson(Object obj) throws JacksonException {
        return JSON_MAPPER.writeValueAsString(obj);
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
        return JSON_MAPPER.readValue(json, clazz);
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
        return JSON_MAPPER.readValue(json, reference);
    }

}
