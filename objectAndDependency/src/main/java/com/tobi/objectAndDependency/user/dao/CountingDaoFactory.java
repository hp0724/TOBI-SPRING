package com.tobi.objectAndDependency.user.dao;

import org.springframework.context.annotation.Bean;

public class CountingDaoFactory {
    @Bean
    public  UserDao userDao(){
        return new UserDao(connectionMaker());
    }
    @Bean
    public  ConnectionMaker  connectionMaker(){
        return new CountingConnectionMaker(realConnectionMaker());
    }
    @Bean
    private ConnectionMaker realConnectionMaker() {
        return new DconnectionMaker();
    }
}
