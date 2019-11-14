package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static String getProperty(String prop) {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
            Properties property = new Properties();

            property.load(new FileInputStream(rootPath));
            return property.getProperty(prop);
        } catch (IOException | NullPointerException e) {
            System.out.println("Error: config.properties is absent!");
        }
        return "";
    }
}
