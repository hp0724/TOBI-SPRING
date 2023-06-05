package com.tobi.objectAndDependency.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DconnectionMaker  implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        System.out.println("Connection code");
        return null;
    }
}
