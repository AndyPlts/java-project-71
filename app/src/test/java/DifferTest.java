import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DifferTest {
    @Test
    public void test1() throws IOException {
        var expected1 = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        var expected2 = "{\n"
                + "  - chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  - checked: false\n"
                + "  - default: null\n"
                + "  - id: 45\n"
                + "  - key1: value1\n"
                + "  - numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  - setting1: Some value\n"
                + "  - setting2: 200\n"
                + "  - setting3: true\n"
                + "}";
        var expected3 = "{\n" + "}";
        var expected4 = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        var expected5 = "[{\"old_value\":[\"a\",\"b\",\"c\"],\"Key\":\"chars1\",\"status\":\"no_changes\"},"
                + "{\"old_value\":[\"d\",\"e\",\"f\"],\"Key\":\"chars2\",\"new_value\":false,\"status\":\"edited\"},"
                + "{\"old_value\":false,\"Key\":\"checked\",\"new_value\":true,\"status\":\"edited\"},"
                + "{\"old_value\":null,\"Key\":\"default\",\"new_value\":[\"value1\",\"value2\"],"
                + "\"status\":\"edited\"},{\"old_value\":45,\"Key\":\"id\",\"new_value\":null,\"status"
                + "\":\"edited\"},{\"old_value\":\"value1\",\"Key\":\"key1\",\"status\":\"removed\"}"
                + ",{\"old_value\":\"value2\",\"Key\":\"key2\",\"status\":\"added\"}"
                + ",{\"old_value\":[1,2,3,4],\"Key\":\"numbers1\",\"status\":\"no_changes\"}"
                + ",{\"old_value\":[2,3,4,5],\"Key\":\"numbers2\",\"new_value\":[22,33,44,55],"
                + "\"status\":\"edited\"},{\"old_value\":[3,4,5],\"Key\":\"numbers3\",\"status\":\"removed\"},"
                + "{\"old_value\":[4,5,6],\"Key\":\"numbers4\",\"status\":\"added\"},"
                + "{\"old_value\":{\"nestedKey\":\"value\",\"isNested\":true},\"Key\":\"obj1\",\"status\":\"added\"},"
                + "{\"old_value\":\"Some value\",\"Key\":\"setting1\",\"new_value\":"
                + "\"Another value\",\"status\":\"edited\"},{\"old_value\":200,\"Key\":\"setting2"
                + "\",\"new_value\":300,\"status\":\"edited\"},{\"old_value\":true,\"Key\":"
                + "\"setting3\",\"new_value\":\"none\",\"status\":\"edited\"}]";
        var actual1 = Differ.generate("./src/test/resources/test1file1.json",
                "./src/test/resources/test1file2.json", "stylish");
        assertEquals(expected1, actual1);
        var actual2 = Differ.generate("./src/test/resources/test1file1.yml",
                "./src/test/resources/test1file2.yml", "stylish");
        assertEquals(expected1, actual2);
        var actual3 = Differ.generate("./src/test/resources/test1file1.json",
                "./src/test/resources/test2file2.json", "stylish");
        assertEquals(expected2, actual3);
        var actual4 = Differ.generate("./src/test/resources/test2file1.json",
                "./src/test/resources/test2file2.json", "stylish");
        assertEquals(expected3, actual4);
        var actual5 = Differ.generate("./src/test/resources/test1file1.json",
                "./src/test/resources/test1file2.json", "plain");
        assertEquals(expected4, actual5);
        var actual6 = Differ.generate("./src/test/resources/test1file1.json",
                "./src/test/resources/test1file2.json", "json");
        assertEquals(expected5, actual6);
    }
}
