package com.yuraplv.dao.impl;

import com.yuraplv.dao.GenericDAO;
import com.yuraplv.dao.PersonDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDAOImpl extends GenericDAO implements PersonDAO {

    private static Connection con = null;

    public boolean createPerson(String personName) {

        //STEP 2: Register JDBC driver
        PreparedStatement statement = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            statement = con.prepareStatement("INSERT INTO PERSON(NAME) VALUES (?)");
            statement.setString(1, personName);
            statement.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection();
        } finally {
            try {
                closeConnection();
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return true;
    }

}

