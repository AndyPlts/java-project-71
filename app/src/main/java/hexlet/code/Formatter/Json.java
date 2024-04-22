package hexlet.code.Formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String getJson(List<Map<String, Object>> diffResult) throws JsonProcessingException {
        ObjectMapper resultMapper = new ObjectMapper();
        return resultMapper.writeValueAsString(diffResult);
    }
}
