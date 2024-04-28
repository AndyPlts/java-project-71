package hexlet.code;

import hexlet.code.Formatter.Json;
import hexlet.code.Formatter.Plain;
import hexlet.code.Formatter.Stylish;

import java.io.IOException;
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
        Map<String, Object> fileData1 = Parser.getParser(absolutePath1.toString());
        Map<String, Object> fileData2 = Parser.getParser(absolutePath2.toString());
        Comparator.compareMaps(fileData1, fileData2, diffResult);
        return switch (format) {
            case "stylish" -> Stylish.getStylish(diffResult);
            case "plain" -> Plain.getPlain(diffResult);
            case "json" -> Json.getJson(diffResult);
            default -> "";
        };
    }
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

}
