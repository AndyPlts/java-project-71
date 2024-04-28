import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;

public class DifferTest {

    private static String stylishExpect = "";
    private static String plainExpect = "";
    private static String jsonExpect = "";

    @BeforeAll
    public static void makeExpect() throws IOException {
        stylishExpect = Files.readString(Paths.get(
                "./src/test/resources/stylishTestResult.txt"));
        plainExpect = Files.readString(Paths.get(
                "./src/test/resources/plainTestResult.txt"));
        jsonExpect = Files.readString(Paths.get(
                "./src/test/resources/jsonTestResult.txt"));
    }

    @Test
    public void stylishFormatTest() throws IOException {
        var expected = stylishExpect;
        var jsonActual = Differ.generate(
                "./src/test/resources/test1file1.json", "./src/test/resources/test1file2.json", "stylish");
        assertEquals(expected, jsonActual);
        var ymlActual = Differ.generate(
                "./src/test/resources/test1file1.yml", "./src/test/resources/test1file2.yml", "stylish");
        assertEquals(expected, ymlActual);
    }

    @Test
    public void plainFormatTest() throws IOException {
        var expected = plainExpect;
        var jsonActual = Differ.generate(
                "./src/test/resources/test1file1.json", "./src/test/resources/test1file2.json", "plain");
        assertEquals(expected, jsonActual);
        var ymlActual = Differ.generate(
                "./src/test/resources/test1file1.yml", "./src/test/resources/test1file2.yml", "plain");
        assertEquals(expected, ymlActual);
    }

    @Test
    public void jsonFormatTest() throws IOException {
        var expected = jsonExpect;
        var jsonActual = Differ.generate(
                "./src/test/resources/test1file1.json", "./src/test/resources/test1file2.json", "json");
        assertEquals(expected, jsonActual);
        var ymlActual = Differ.generate(
                "./src/test/resources/test1file1.yml", "./src/test/resources/test1file2.yml", "json");
        assertEquals(expected, ymlActual);
    }

    @Test
    public void defaultFormatTest() throws IOException {
        var expected = stylishExpect;
        var jsonActual = Differ.generate(
                "./src/test/resources/test1file1.json", "./src/test/resources/test1file2.json", "stylish");
        assertEquals(expected, jsonActual);
        var ymlActual = Differ.generate(
                "./src/test/resources/test1file1.yml", "./src/test/resources/test1file2.yml", "stylish");
        assertEquals(expected, ymlActual);
    }
}
