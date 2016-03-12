package com.yuraplv.dao.impl;

import com.yuraplv.dao.GenericDAO;
import com.yuraplv.dao.PersonDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDAOImpl extends GenericDAO implements PersonDAO {

    private static Connection con = null;

    public boolean createPerson(String personName) throws SQLException, ClassNotFoundException {

        //STEP 2: Register JDBC driver
        PreparedStatement statement = null;
        try {
            con = getConnection();
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

}

