package com.xjtuse.mall.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Log logger = LogFactory.getLog(JsonUtil.class);

    public JsonUtil() {
    }

    public static String parseString(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                return leaf.asText();
            }
        } catch (IOException var5) {
            logger.error(var5.getMessage(), var5);
        }

        return null;
    }

    public static List<String> parseStringList(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                return (List)mapper.convertValue(leaf, new TypeReference<List<String>>() {
                });
            }
        } catch (IOException var5) {
            logger.error(var5.getMessage(), var5);
        }

        return null;
    }

    public static Integer parseInteger(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                return leaf.asInt();
            }
        } catch (IOException var5) {
            logger.error(var5.getMessage(), var5);
        }

        return null;
    }

    public static List<Integer> parseIntegerList(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                return (List)mapper.convertValue(leaf, new TypeReference<List<Integer>>() {
                });
            }
        } catch (IOException var5) {
            logger.error(var5.getMessage(), var5);
        }

        return null;
    }

    public static Boolean parseBoolean(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                return leaf.asBoolean();
            }
        } catch (IOException var5) {
            logger.error(var5.getMessage(), var5);
        }

        return null;
    }

    public static Short parseShort(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                Integer value = leaf.asInt();
                return value.shortValue();
            }
        } catch (IOException var6) {
            logger.error(var6.getMessage(), var6);
        }

        return null;
    }

    public static Byte parseByte(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                Integer value = leaf.asInt();
                return value.byteValue();
            }
        } catch (IOException var6) {
            logger.error(var6.getMessage(), var6);
        }

        return null;
    }

    public static <T> T parseObject(String body, String field, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            node = node.get(field);
            return mapper.treeToValue(node, clazz);
        } catch (IOException var6) {
            logger.error(var6.getMessage(), var6);
            return null;
        }
    }

    public static Object toNode(String json) {
        if (json == null) {
            return null;
        } else {
            ObjectMapper mapper = new ObjectMapper();

            try {
                return mapper.readTree(json);
            } catch (IOException var3) {
                logger.error(var3.getMessage(), var3);
                return null;
            }
        }
    }

    public static Map<String, String> toMap(String data) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return (Map)objectMapper.readValue(data, new TypeReference<Map<String, String>>() {
            });
        } catch (IOException var3) {
            logger.error(var3.getMessage(), var3);
            return null;
        }
    }

    public static String toJson(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
