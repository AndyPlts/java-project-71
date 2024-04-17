package hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String getStylish(List<Map<String, Object>> diffResult) {
        var resultString = new StringBuilder();
        resultString.append("{");
        for (var field : diffResult) {
            if (field.get("status").equals("removed")) {
                correctRemoved(field, resultString);
            }
            if (field.get("status").equals("added")) {
                correctAdded(field, resultString);
            }
            if (field.get("status").equals("edited")) {
                correctEdited(field, resultString);
            }
            if (field.get("status").equals("no_changes")) {
                correctNoChanges(field, resultString);
            }
        }
        resultString.append("\n}");
        return resultString.toString();
    }

    private static void correctRemoved(Map<String, Object> field, StringBuilder resultString) {
        resultString.append("\n  ")
                .append("- ")
                .append(field.get("Key"))
                .append(": ")
                .append(field.get("old_value"));
    }

    private static void correctNoChanges(Map<String, Object> field, StringBuilder resultString) {
        resultString.append("\n  ")
                .append("  ")
                .append(field.get("Key"))
                .append(": ")
                .append(field.get("old_value"));
    }

    private static void correctAdded(Map<String, Object> field, StringBuilder resultString) {
        resultString.append("\n  ")
                .append("+ ")
                .append(field.get("Key"))
                .append(": ")
                .append(field.get("old_value"));
    }

    private static void correctEdited(Map<String, Object> field, StringBuilder resultString) {
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
}
