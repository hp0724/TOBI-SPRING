package com.tobi.objectAndDependency.user.domain;

import com.tobi.objectAndDependency.user.dao.ConnectionMaker;

public class User {
    String id;
    String name;
    String password;

    public User(){

    }

    public User(ConnectionMaker connectionMaker1) {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
