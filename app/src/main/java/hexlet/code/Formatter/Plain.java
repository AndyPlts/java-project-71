package hexlet.code.Formatter;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String getPlain(List<Map<String, Object>> diffResult) {
        var resultString = new StringBuilder();
        for (var field : diffResult) {
            if (field.get("status").equals("removed")) {
                resultString.append("Property '")
                            .append(field.get("Key"))
                            .append("' was removed\n");
            }
            if (field.get("status").equals("added")) {
                correctAdded(field, resultString);
            }
            if (field.get("status").equals("edited")) {
                correctEdited(field, resultString);
            }
        }
        return resultString.toString().trim();
    }

    private static void correctEdited(Map<String, Object> field, StringBuilder resultString) {
        resultString.append("Property '")
                .append(field.get("Key"))
                .append("' was updated. From ")
                .append(getFormattedValue(field.get("old_value")))
                .append(" to ")
                .append(getFormattedValue(field.get("new_value")))
                .append("\n");
    }

    private static void correctAdded(Map<String, Object> field, StringBuilder resultString) {
        resultString.append("Property '")
                .append(field.get("Key"))
                .append("' was added with value: ")
                .append(getFormattedValue(field.get("old_value")))
                .append("\n");
    }

    private static <T> String getFormattedValue(T value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof Array || value instanceof Map) {
            return "[complex value]";
        } else {
            return value.toString();
        }
    }
}
