package com.tobi.objectAndDependency.user.dao;

import com.tobi.objectAndDependency.user.domain.User;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao",UserDao.class);
        System.out.println(dao);


//        User user = new User();
//        user.setId("whiteShip");
//        user.setName("백기선");
//        user.setPassword("married");
//
//        dao.add(user);
//
//        System.out.println(user.getId() + "등록 성공 ");
//
//        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//        System.out.println(user.getId() + "조회 성공");

        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();
        System.out.println(dao1); //com.tobi.objectAndDependency.user.dao.UserDao@55536d9e
        System.out.println(dao2); //com.tobi.objectAndDependency.user.dao.UserDao@747edf66

        UserDao dao3 = context.getBean("userDao",UserDao.class);
        UserDao dao4 = context.getBean("userDao",UserDao.class);
        System.out.println(dao3); //com.tobi.objectAndDependency.user.dao.UserDao@3d1cfad4
        System.out.println(dao4); //com.tobi.objectAndDependency.user.dao.UserDao@3d1cfad4

    }

}
