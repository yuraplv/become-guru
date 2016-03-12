package com.yuraplv.dao;

import java.sql.SQLException;

public interface PersonDAO {

    boolean createPerson(String personName) throws SQLException, ClassNotFoundException;

}
