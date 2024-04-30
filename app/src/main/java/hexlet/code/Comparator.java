package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {
    public static void compareMaps(
            Map<String, Object> fileData1, Map<String, Object> fileData2, List<Map<String, Object>> diffResult) {
        Set<String> allKeys = new TreeSet<>(fileData1.keySet());
        allKeys.addAll(fileData2.keySet());
        for (var key : allKeys) {
            if (!fileData2.containsKey(key)) {
                diffResult.add(getDiffMap(key, fileData1, "removed"));
            } else if (!fileData1.containsKey(key)) {
                diffResult.add(getDiffMap(key, fileData2, "added"));
            } else if (Objects.equals(fileData1.get(key), fileData2.get(key))) {
                diffResult.add(getDiffMap(key, fileData2, "no_changes"));
            } else {
                diffResult.add(getDiffMap(key, fileData1, fileData2));
            }
        }
    }

    private static Map<String, Object> getDiffMap(String key, Map<String, Object> fileData,
                                                  String status) {
        var resultMap = new HashMap<String, Object>();
        resultMap.put("Key", key);
        resultMap.put("status", status);
        resultMap.put("value", fileData.get(key));
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
