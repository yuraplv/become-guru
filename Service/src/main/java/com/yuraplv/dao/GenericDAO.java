package com.yuraplv.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GenericDAO {

    private static Properties properties = new Properties();

    static {
        loadProperties();
    }

    private Connection connection;

    protected Connection getConnection() throws SQLException {
        String URL = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(URL, username, password);
        return connection;
    }

    protected void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadProperties() {
        try(InputStream asStream = GenericDAO.class.getClassLoader().getResourceAsStream("connection.properties")) {
            Class.forName("com.mysql.jdbc.Driver");
            properties.load(asStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
