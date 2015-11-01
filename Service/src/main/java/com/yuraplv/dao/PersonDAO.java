package com.yuraplv.dao;

import java.sql.SQLException;

public interface PersonDAO {

    public boolean createPerson(String personName) throws SQLException;

}
