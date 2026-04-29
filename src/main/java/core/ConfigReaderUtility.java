package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderUtility {
    private static Properties prop = new Properties();
    private static final String ConfigFilePath = "src/test/resources/config.properties";
    static {
        try {

            FileInputStream fileInputStream = new FileInputStream(ConfigFilePath);
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load "+ConfigFilePath);
        }
    }

    public static String get(String key)
    {
        String value = prop.getProperty(key);
        if (value == null) {
            throw new RuntimeException(key + " not found in "+ConfigFilePath);
        }
        return value;
    }
    public static String getBrowser()
    {
        return System.getProperty("browser", get("browser"));
    }
    public static String geturl()
    {
        return System.getProperty("url", get("url"));
    }
}
