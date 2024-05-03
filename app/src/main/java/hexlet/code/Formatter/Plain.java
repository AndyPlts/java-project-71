package hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

public class Plain {
    private static final String CORRECT_ADDED_STRING = "Property '%s' was added with value: %s\n";
    private static final String CORRECT_EDITED_STRING = "Property '%s' was updated. From %s to %s\n";
    public static String getPlain(List<Map<String, Object>> diffResult) {
        StringBuilder resultString = new StringBuilder();
        for (var field : diffResult) {
            switch (field.get("status").toString()) {
                case "removed":
                    resultString.append(String.format("Property '%s' was removed\n", field.get("Key")));
                    break;
                case "added":
                    resultString.append(String.format(CORRECT_ADDED_STRING,
                            field.get("Key"), getFormattedValue(field.get("value"))));
                    break;
                case "edited":
                    resultString.append(String.format(CORRECT_EDITED_STRING,
                            field.get("Key"), getFormattedValue(field.get("old_value")),
                            getFormattedValue(field.get("new_value"))));
                    break;
                default:
            }
        }
        return resultString.toString().trim();
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
