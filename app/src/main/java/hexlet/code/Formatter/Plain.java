package hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String getPlain(List<Map<String, Object>> diffResult) {
        StringBuilder resultString = new StringBuilder();
        for (var field : diffResult) {
            switch (field.get("status").toString()) {
                case "removed":
                    resultString.append(String.format("Property '%s' was removed\n", field.get("Key")));
                    break;
                case "added":
                    resultString.append(correctAdded(field));
                    break;
                case "edited":
                    resultString.append(correctEdited(field));
                    break;
                default:
            }
        }
        return resultString.toString().trim();
    }

    private static String correctEdited(Map<String, Object> field) {
        return String.format("Property '%s' was updated. From %s to %s\n",
                field.get("Key"), getFormattedValue(field.get("old_value")), getFormattedValue(field.get("new_value")));
    }

    private static String correctAdded(Map<String, Object> field) {
        return String.format("Property '%s' was added with value: %s\n",
                field.get("Key"), getFormattedValue(field.get("value")));
    }

    private static <T> String getFormattedValue(T value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else {
            return String.valueOf(value);
        }
    }
}
