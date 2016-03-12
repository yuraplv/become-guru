package com.yuraplv.dao;

import com.yuraplv.dao.impl.PersonDAOImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAOTest extends GenericDAO {

    private Connection connection;

    @BeforeMethod
    public void setUp() throws Exception {
        connection = getConnection();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        connection.createStatement().execute("DELETE FROM PERSON");
        closeConnection();
    }

    @Test
    public void savePersonTest() throws SQLException, ClassNotFoundException {
        PersonDAO personDAO = new PersonDAOImpl();
        String personName = "Uncle Bens";
        personDAO.createPerson(personName);

        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM PERSON WHERE name=?");
        preparedStatement.setString(1, personName);

        ResultSet resultSet = preparedStatement.executeQuery();


        int i;
        for (i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");

            Assert.assertTrue(id > 0);
            Assert.assertEquals(name, personName);
        }

        Assert.assertEquals(1, 1);


    }

}
