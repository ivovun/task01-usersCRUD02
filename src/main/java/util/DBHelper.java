package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(PropertyReader.getProperty("jdbcSqlDriver"));
            connection = DriverManager.getConnection(PropertyReader.getProperty("jdbcURL"),
                    PropertyReader.getProperty("jdbcUsername"),
                    PropertyReader.getProperty("jdbcPassword"));
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
}
