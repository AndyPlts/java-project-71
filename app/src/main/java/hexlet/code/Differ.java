package hexlet.code;

import hexlet.code.Formatter.Plain;
import hexlet.code.Formatter.Stylish;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
                diffResult.add(getDiffMap(key, fileData1, "removed"));
            } else if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
                diffResult.add(getDiffMap(key, fileData2, "added"));
            } else if (fileData1.containsKey(key) && fileData2.containsKey(key)
                    && Objects.equals(fileData1.get(key), fileData2.get(key))) {
                diffResult.add(getDiffMap(key, fileData2, "no_changes"));
            } else {
                diffResult.add(getDiffMap(key, fileData1, fileData2));
            }
        }
        return switch (format) {
            case "stylish" -> Stylish.getStylish(diffResult);
            case "plain" -> Plain.getPlain(diffResult);
            default -> "";
        };
    }

    private static Map<String, Object> getDiffMap(String key, Map<String, Object> fileData,
                                                  String status) {
        var resultMap = new HashMap<String, Object>();
        resultMap.put("Key", key);
        resultMap.put("status", status);
        resultMap.put("old_value", fileData.get(key));
        return resultMap;
    }

    private static Map<String, Object> getDiffMap(String key, Map<String, Object> fileData1,
                                                  Map<String, Object> fileData2) {
        var resultMap = new HashMap<String, Object>();
        resultMap.put("Key", key);
        resultMap.put("status", "edited");
        resultMap.put("old_value", fileData1.get(key));
        resultMap.put("new_value", fileData2.get(key));
        return resultMap;
    }
}
