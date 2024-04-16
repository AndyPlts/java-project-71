package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String getStylish(List<Map<String, Object>> diffResult) {
        var resultString = new StringBuilder();
        resultString.append("{");
        for (var field : diffResult) {
            if (field.get("status").equals("removed")) {
                resultString.append("\n  ")
                        .append("- ")
                        .append(field.get("Key"))
                        .append(": ")
                        .append(field.get("old_value"));
            }
            if (field.get("status").equals("added")) {
                resultString.append("\n  ")
                        .append("+ ")
                        .append(field.get("Key"))
                        .append(": ")
                        .append(field.get("old_value"));
            }
            if (field.get("status").equals("edited")) {
                resultString.append("\n  ")
                        .append("- ")
                        .append(field.get("Key"))
                        .append(": ")
                        .append(field.get("old_value"));
                resultString.append("\n  ")
                        .append("+ ")
                        .append(field.get("Key"))
                        .append(": ")
                        .append(field.get("new_value"));
            }
            if (field.get("status").equals("no_changes")) {
                resultString.append("\n  ")
                        .append("  ")
                        .append(field.get("Key"))
                        .append(": ")
                        .append(field.get("old_value"));
            }
        }
        resultString.append("\n}");
        return resultString.toString();
    }
}
