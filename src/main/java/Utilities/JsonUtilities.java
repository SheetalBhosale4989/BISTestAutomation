package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtilities {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJsonAsObject(String filePath, Class<T> pojClass) {
        try {
            return mapper.readValue(new File(filePath), pojClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
