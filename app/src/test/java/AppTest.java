import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {
    @Test
    public void test1() throws IOException {
        var expected = """
                
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        var actual1 = Differ.generate("./src/test/resources/test1file1.json", "./src/test/resources/test1file2.json");
        assertEquals(expected, actual1);
        //var actual2 = Differ.generate("./src/test/resources/test1file1.yml", "./src/test/resources/test1file2.yml");
        //assertEquals(expected, actual2);
    }
}
