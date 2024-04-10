package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;


public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        StringBuilder result = new StringBuilder();
        Path absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, Object> fileData1 = Parser.getParser(absolutePath1.toString());
        Map<String, Object> fileData2 = Parser.getParser(absolutePath2.toString());
        for (Map.Entry<String, Object> entry1 : fileData1.entrySet()) {
            for (Map.Entry<String, Object> entry2 : fileData2.entrySet()) {
                if (entry1.getValue().equals(entry2.getValue()) && entry1.getKey().equals(entry2.getKey())) {
                    if (!result.toString().contains(entry1.getValue().toString())) {
                        result.append("* ").append(entry1.getKey()).append(": ")
                              .append(entry1.getValue()).append("\n  ");
                    }
                }
                if (!entry1.getValue().equals(entry2.getValue()) && entry1.getKey().equals(entry2.getKey())) {
                    if (!result.toString().contains(entry1.getValue().toString())
                        && !result.toString().contains(entry2.getKey())) {
                        result.append("- ")
                              .append(entry1.getKey()).append(": ").append(entry1.getValue()).append("\n  ");
                        result.append("+ ")
                              .append(entry1.getKey()).append(": ").append(entry2.getValue()).append("\n  ");
                    }
                }
                if (!fileData1.containsKey(entry2.getKey())) {
                    if (!result.toString().contains(entry2.getKey())) {
                        result.append("+ ")
                              .append(entry2.getKey()).append(": ").append(entry2.getValue()).append("\n  ");
                    }
                }
                if (!fileData2.containsKey(entry1.getKey())) {
                    if (!result.toString().contains(entry1.getKey())) {
                        result.append("- ")
                              .append(entry1.getKey()).append(": ").append(entry1.getValue()).append("\n  ");
                    }
                }
            }
        }
        var resultString1 = result.toString();
        List<String> resultList = new ArrayList<>();
        String[] resultStringArray = resultString1.split("\n {2}");
        Collections.addAll(resultList, resultStringArray);
        resultList = resultList.stream()
                .sorted((Comparator.comparing(str -> str.split("\\s")[1])))
                .toList();
        Arrays.sort(resultStringArray);
        return resultList.toString()
                .replace("[", "{\n  ")
                .replace("]", "\n}")
                .replace(",", "\n ")
                .replace("*", " ");
    }
}
