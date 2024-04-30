package hexlet.code;

import java.util.Map;

import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    public static Map<String, Object> getParser(String fileString, String fileExtension)
            throws IOException {
        return switch (fileExtension) {
            case "json" -> new JsonMapper().readValue(fileString, new TypeReference<>() {
            });
            case "yml" -> new YAMLMapper().readValue(fileString, new TypeReference<>() {
            });
            default -> throw new RuntimeException("Choose the other format");
        };
    }
}
