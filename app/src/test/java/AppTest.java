import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {
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
    }
}
