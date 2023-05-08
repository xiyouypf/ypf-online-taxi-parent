package com.ypf.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者
 * @date 2023/05/04 17:31
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> void registerModule(Class<T> clazz) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(clazz, new JsonSerializer<T>() {
            @Override
            public void serialize(T arg0, JsonGenerator jgen,
                                  SerializerProvider arg2) throws IOException {
                jgen.writeString(clazz.getCanonicalName());
            }
        });
        objectMapper.registerModule(module);
    }

    public static <T> T readValue(String jsonStr, Class<T> clazz) throws IOException {
        return objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * 不需要catch异常处理，但需要判null。
     */
    public static <T> T readValueQuietly(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
        }
        return null;
    }

    public static <T> T readValue(String jsonStr, JavaType javaType) throws IOException {
        return objectMapper.readValue(jsonStr, javaType);
    }

    /**
     * 不需要catch异常处理，但需要判null。
     */
    public static <T> T readValueQuietly(String jsonStr, JavaType javaType) {
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
        }
        return null;
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String jsonStr, JavaType javaType) {
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 不需要catch异常处理，但需要判null。
     */
    public static <T> T fromJsonQuietly(String jsonStr, JavaType javaType) {
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
        }
        return null;
    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeJsonStr(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static String writePrettyJsonStr(Object obj) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    public static void writeJsonStream(OutputStream out, Object obj) throws IOException {
        objectMapper.writeValue(out, obj);
    }

    public static <T> List<T> readListValue(String jsonStr, Class<T> clazz) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        return objectMapper.readValue(jsonStr, javaType);
    }

    /**
     * 不需要catch异常处理，但需要判null。
     */
    public static <T> List<T> readListValueQuietly(String jsonStr, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
        }
        return null;
    }

    public static <T> List<T> fromListValue(String jsonStr, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> readMapValue(String jsonStr, Class<K> kclazz, Class<V> vclazz) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(HashMap.class, kclazz, vclazz);
        return objectMapper.readValue(jsonStr, javaType);
    }

    public static ArrayNode readArray(String jsonStr) throws IOException {
        JsonNode node = objectMapper.readTree(jsonStr);
        if(node.isArray()) {
            return (ArrayNode) node;
        }
        return null;
    }

    public static JsonNode readNode(String jsonStr) throws IOException {
        return objectMapper.readTree(jsonStr);
    }

}
