package com.yuraplv.dao.impl;

import com.mysql.jdbc.Driver;
import com.yuraplv.dao.PersonDAO;

import java.sql.*;

public class PersonDAOImpl implements PersonDAO {

    private static Connection con = null;
    private static String username = "yuraplv";
    private static String password = "6176";
    private static String URL = "jdbc:mysql://localhost:3306/yuraplv";

    public boolean createPerson(String personName) throws SQLException {

        DriverManager.deregisterDriver(new Driver());
        PreparedStatement statement = null;
        try {
            con = DriverManager.getConnection(URL, username, password);
            con.setAutoCommit(false);
            statement = con.prepareStatement("INSERT INTO PERSON(NAME) VALUES (?)");
            statement.setString(1, personName);
            statement.execute();

            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                con.rollback();
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (statement != null) {
                statement.close();
            }
        }


        return true;
    }

    public static void main(String[] args) throws SQLException {
        new PersonDAOImpl().createPerson("name5");

    }

}

