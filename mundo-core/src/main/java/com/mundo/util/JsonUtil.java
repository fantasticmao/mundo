package com.mundo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * JsonUtil
 *
 * @author maomao
 * @since 2017/3/5
 */
public final class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Object -> JSON
     */
    public static Optional<String> toJson(Object obj) {
        try {
            String str = OBJECT_MAPPER.writeValueAsString(obj);
            return Optional.ofNullable(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Json -> Class
     */
    public static <T> Optional<T> toClass(String json, Class<T> clazz) {
        try {
            T t = OBJECT_MAPPER.readValue(json.getBytes(), clazz);
            return Optional.ofNullable(t);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * JsonNode -> Class
     */
    public static <T> Optional<T> toClass(JsonNode node, Class<T> clazz) {
        try {
            T t = OBJECT_MAPPER.treeToValue(node, clazz);
            return Optional.ofNullable(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Json -> JsonNode -> Class
     */
    public static <T> Optional<T> findNodeToClass(String json, String fieldName, Class<T> clazz) {
        JsonNode node = toJsonNode(json, fieldName);
        if (node != null && !node.isMissingNode()) {
            return toClass(node, clazz);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Json -> JsonNode
     */
    public static JsonNode toJsonNode(String json) {
        if (StringUtil.isNotEmpty(json)) {
            try {
                return OBJECT_MAPPER.readTree(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Json -> JsonNode
     */
    public static JsonNode toJsonNode(String json, String fieldName) {
        if (StringUtil.isNotEmpty(json, fieldName)) {
            try {
                return OBJECT_MAPPER.readTree(json).findPath(fieldName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Map toMap(String json) {
        return toClass(json, Map.class).orElse(Collections.emptyMap());
    }

    public static List toList(String json) {
        return toClass(json, List.class).orElse(Collections.emptyList());
    }

    public static Set toSet(String json) {
        return toClass(json, Set.class).orElse(Collections.emptySet());
    }
}
