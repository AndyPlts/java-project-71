import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {
    @Test
    public void test1() throws IOException {
        var expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 20
                  + timeout: 50
                  + verbose: true
                }""";
        var actual = Differ.generate("./src/main/resources/file1.json", "./src/main/resources/file2.json");
        assertEquals(expected, actual);
    }
}