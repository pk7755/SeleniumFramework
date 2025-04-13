package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class   ConfigReader {
    private static Properties properties;

    public static void load(String env) {
        String filePath = "src/test/java/resources/config-" + env + ".properties";
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config for: " + env, e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
