package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderUtility {
    private static Properties properties = new Properties();
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        try {

            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load " + CONFIG_FILE_PATH);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(key + " not found in " + CONFIG_FILE_PATH);
        }
        return value;
    }

    public static String getBrowser() {
        return System.getProperty("browser", get("browser"));
    }

    public static String getUrl() {
        return System.getProperty("url", get("url"));
    }
}
