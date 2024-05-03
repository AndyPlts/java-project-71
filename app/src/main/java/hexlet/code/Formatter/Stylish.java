package hexlet.code.Formatter;

import java.util.List;
import java.util.Map;


public class Stylish {
    private static final String CORRECT_REMOVED_STRING = "\n  - %s: %s";
    private static final String CORRECT_NO_CHANGES_STRING = "\n    %s: %s";
    private static final String CORRECT_ADDED_STRING = "\n  + %s: %s";
    private static final String CORRECT_EDITED_STRING = "\n  - %s: %s\n  + %s: %s";
    public static String getStylish(List<Map<String, Object>> diffResult) {
        var resultString = new StringBuilder();
        resultString.append("{");
        for (var field : diffResult) {
            switch (field.get("status").toString()) {
                case "removed":
                    resultString.append(String.format(CORRECT_REMOVED_STRING, field.get("Key"), field.get("value")));
                    break;
                case "added":
                    resultString.append(String.format(CORRECT_ADDED_STRING, field.get("Key"), field.get("value")));
                    break;
                case "edited":
                    resultString.append(String.format(CORRECT_EDITED_STRING,
                            field.get("Key"), field.get("old_value"), field.get("Key"), field.get("new_value")));
                    break;
                case "no_changes":
                    resultString.append(String.format(CORRECT_NO_CHANGES_STRING, field.get("Key"), field.get("value")));
                    break;
                default:
            }
        }
        resultString.append("\n}");
        return resultString.toString();
    }
}
