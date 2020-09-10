package tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public JsonUtil() {
    }

    public static String beanToJson(Object bean) {
        try {
            return mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String beanToJsonPretty(Object bean) throws
            JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bean);
    }

    public static <T> T jsonToBean(String json, Class<T> type){
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonToBeans(String json, Class<T> type) {
        try {
            JavaType javaType = getCollectionType(List.class, type);
            JsonNode node = mapper.readTree(json);
            return (List)mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
