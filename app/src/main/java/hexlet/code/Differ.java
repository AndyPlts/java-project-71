package hexlet.code;

import hexlet.code.Formatter.Plain;
import hexlet.code.Formatter.Stylish;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        List<Map<String, Object>> diffResult = new ArrayList<>();
        Path absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, Object> fileData1 = Parser.getParser(absolutePath1.toString());
        Map<String, Object> fileData2 = Parser.getParser(absolutePath2.toString());
        Set<String> allKeys = new TreeSet<>(fileData1.keySet());
        allKeys.addAll(fileData2.keySet());
        for (var key : allKeys) {
            if (fileData1.containsKey(key) && !fileData2.containsKey(key)) {
                diffResult.add(Map.of("Key", key, "status", "removed",
                        "old_value", fileData1.get(key)));
            } else if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
                diffResult.add(Map.of("Key", key, "status", "added",
                        "old_value", fileData2.get(key)));
            } else if (fileData1.containsKey(key) && fileData2.containsKey(key)
                    && Objects.equals(fileData1.get(key), fileData2.get(key))) {
                diffResult.add(Map.of("Key", key, "status", "no_changes",
                        "old_value", fileData2.get(key)));
            } else {
                diffResult.add(Map.of("Key", key, "status", "edited",
                        "old_value", fileData1.get(key), "new_value", fileData2.get(key)));
            }
        }
        return switch (format) {
            case "stylish" -> Stylish.getStylish(diffResult);
            case "plain" -> Plain.getPlain(diffResult);
            default -> "";
        };
    }
}
