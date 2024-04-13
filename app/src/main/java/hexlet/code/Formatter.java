package hexlet.code;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Formatter {
    public static String getStylish(String resultString) {
        List<String> resultList = new ArrayList<>();
        String[] resultStringArray = resultString.split("\n {2}");
        Collections.addAll(resultList, resultStringArray);
        resultList = resultList.stream()
                .sorted((Comparator.comparing(str -> str.split("\\s")[1])))
                .toList();
        Arrays.sort(resultStringArray);
        return resultList.toString()
                .replace(", -", "\n -")
                .replace(", `", "\n  ")
                .replace(", +", "\n +")
                .replace("[- ", "{\n  -")
                .replace("[` ", "{\n   ")
                .replace("[+ ", "{\n  +")
                .replaceAll(".$", "\n}");
    }
}
