package com.tobi.objectAndDependency.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class NconnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        return null;
    }
}
