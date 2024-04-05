package hexlet.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        StringBuilder result = new StringBuilder();
        Path absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, Object> fileData1 =
                new ObjectMapper().readValue(Files.readString(absolutePath1), new TypeReference<>() {});
        Map<String, Object> fileData2 =
                new ObjectMapper().readValue(Files.readString(absolutePath2), new TypeReference<>() {});
        for (Map.Entry<String, Object> entry1 : fileData1.entrySet()) {
            for (Map.Entry<String, Object> entry2 : fileData2.entrySet()) {
                if (entry1.getValue().equals(entry2.getValue()) && entry1.getKey().equals(entry2.getKey())) {
                    if (!result.toString().contains(entry1.getValue().toString())) {
                        result.append("* ").append(entry1.getKey()).append(": ").append(entry1.getValue()).append("\n  ");
                    }
                }
                if (!entry1.getValue().equals(entry2.getValue()) && entry1.getKey().equals(entry2.getKey())) {
                    if (!result.toString().contains(entry1.getValue().toString()) && !result.toString().contains(entry2.getKey())) {
                        result.append("- ").append(entry1.getKey()).append(": ").append(entry1.getValue()).append("\n  ");
                        result.append("+ ").append(entry1.getKey()).append(": ").append(entry2.getValue()).append("\n  ");
                    }
                }
                if (!fileData1.containsKey(entry2.getKey())) {
                    if (!result.toString().contains(entry2.getKey())) {
                        result.append("+ ").append(entry2.getKey()).append(": ").append(entry2.getValue()).append("\n  ");
                    }
                }
                if (!fileData2.containsKey(entry1.getKey())) {
                    if (!result.toString().contains(entry1.getKey())) {
                        result.append("- ").append(entry1.getKey()).append(": ").append(entry1.getValue()).append("\n  ");
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
                .replace("]","\n}")
                .replace(",", "\n ")
                .replace("*", " ");
    }
}
