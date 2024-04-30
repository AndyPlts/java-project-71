package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Formatter.Json;
import hexlet.code.Formatter.Plain;
import hexlet.code.Formatter.Stylish;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        List<Map<String, Object>> diffResult = new ArrayList<>();
        Path absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String fileExtension1 = getFileExtension(absolutePath1.toString());
        String fileExtension2 = getFileExtension(absolutePath1.toString());
        String fileString1 = Files.readString(absolutePath1);
        String fileString2 = Files.readString(absolutePath2);
        Map<String, Object> fileData1 = Parser.getParser(fileString1, fileExtension1);
        Map<String, Object> fileData2 = Parser.getParser(fileString2, fileExtension2);
        Comparator.compareMaps(fileData1, fileData2, diffResult);
        return getResultString(format, diffResult);
    }
    private static String getResultString(String format, List<Map<String, Object>> diffResult)
            throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.getStylish(diffResult);
            case "plain" -> Plain.getPlain(diffResult);
            case "json" -> Json.getJson(diffResult);
            default -> throw new RuntimeException("Format error");
        };
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getFileExtension(String absolutePath) {
        return absolutePath.split("\\.")[1];
    }
}
