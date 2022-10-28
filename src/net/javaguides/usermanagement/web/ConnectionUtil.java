package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {

    private static String url;
    private static String user;
    private static String password;
    private static Connection connection = null;

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(input);
            url = properties.getProperty("datasource.url");
            user = properties.getProperty("datasource.user");
            password = properties.getProperty("datasource.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                return connection;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
