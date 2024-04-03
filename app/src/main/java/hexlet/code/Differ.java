package hexlet.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        Path path1 = Paths.get(filepath1);
        Path absolutePath1 = path1.toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, Object> map = ObjectMapper.readValue(Files.readString(absolutePath1), new TypeReference<Map<String,Object>>() {});
        return Files.readString(absolutePath1);
    }
}
