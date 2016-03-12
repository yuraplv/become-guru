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

    protected Connection getConnection() throws SQLException {
        String URL = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(URL, username, password);
    }

    private static void loadProperties() {
        try(InputStream asStream = GenericDAO.class.getResourceAsStream("connection.properties")) {
            Class.forName("com.mysql.jdbc.Driver");
            properties.load(asStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
