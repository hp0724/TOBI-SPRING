package com.tobi.objectAndDependency.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

//    @Bean
//    public  UserDao userDao() {
//        //UserDao가 사용할 connectionMaker 구현 클래스를 결정하고 오브젝트를 만든다.
//        // 1. UserDao 생성
//        // 2. 사용할 connectionMaker 타입의 오브젝트 제공
//        return new UserDao(connectionMaker());
//    }
    @Bean
    public  UserDao userDao (){
        UserDao userDao = new UserDao() ;
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }
    @Bean
    public ConnectionMaker connectionMaker() {
        return new DconnectionMaker();
    }




}

