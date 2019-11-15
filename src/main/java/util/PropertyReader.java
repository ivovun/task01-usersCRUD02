package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Properties;

public class PropertyReader {
    private  static Properties properties;
    static {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
            properties = new Properties();
            properties.load(new FileInputStream(rootPath));
        } catch (IOException | NullPointerException e) {
            System.out.println("Error: config.properties is absent!");
        }
    }
    public static String getProperty(String prop) {
        return properties.getProperty(prop);
    }
 }
