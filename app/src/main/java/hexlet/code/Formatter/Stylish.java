package hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String getStylish(List<Map<String, Object>> diffResult) {
        var resultString = new StringBuilder();
        resultString.append("{");
        for (var field : diffResult) {
            switch (field.get("status").toString()) {
                case "removed":
                    resultString.append(correctRemoved(field));
                    break;
                case "added":
                    resultString.append(correctAdded(field));
                    break;
                case "edited":
                    resultString.append(correctEdited(field));
                    break;
                case "no_changes":
                    resultString.append(correctNoChanges(field));
                    break;
                default:
            }
        }
        resultString.append("\n}");
        return resultString.toString();
    }

    private static String correctRemoved(Map<String, Object> field) {
        return String.format("\n  - %s: %s", field.get("Key"), field.get("old_value"));
    }

    private static String correctNoChanges(Map<String, Object> field) {
        return String.format("\n    %s: %s", field.get("Key"), field.get("old_value"));
    }

    private static String correctAdded(Map<String, Object> field) {
        return String.format("\n  + %s: %s", field.get("Key"), field.get("old_value"));
    }

    private static String correctEdited(Map<String, Object> field) {
        return String.format("\n  - %s: %s\n  + %s: %s",
                field.get("Key"), field.get("old_value"), field.get("Key"), field.get("new_value"));
    }
}
