package hexlet.code;

import java.io.File;
import java.util.Map;

import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    public static Map<String, Object> getParser(String fileAbsolutePath)
            throws IOException {
        if (new File(fileAbsolutePath).length() == 0) {
            return Map.of();
        }
        String fileExtension = getFileExtension(fileAbsolutePath);
        return switch (fileExtension) {
            case "json" -> new JsonMapper().readValue(getFileData(fileAbsolutePath), new TypeReference<>() {
            });
            case "yml" -> new YAMLMapper().readValue(getFileData(fileAbsolutePath), new TypeReference<>() {
            });
            default -> throw new RuntimeException("Choose the other format");
        };
    }

    public static File getFileData(String absolutePath) {
        return new File(absolutePath);
    }

    public static String getFileExtension(String absolutePath) {
        return absolutePath.split("\\.")[1];
    }
}
