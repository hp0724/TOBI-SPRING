package com.tobi.objectAndDependency.user.dao;

import com.tobi.objectAndDependency.user.domain.User;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class UserDaoTest {

    private UserDao dao ;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.dao = context.getBean("userDao",UserDao.class);
        this.user1 = new User("hp0724","황수하","springno1");
        this.user2 = new User("hp0204","황준하","springno2");
        this.user3 = new User("hp0519","황준하","springno3");
    }
    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {


        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(),is(2));

        User userget1 =dao.get(user1.getId());
        assertThat(userget1.getName(),is(user1.getName()));
        assertThat(userget1.getPassword(),is(user1.getPassword()));

        User userget2 =dao.get(user2.getId());
        assertThat(userget2.getName(),is(user2.getName()));
        assertThat(userget2.getPassword(),is(user2.getPassword()));




//        DaoFactory factory = new DaoFactory();
//        UserDao dao1 = factory.userDao();
//        UserDao dao2 = factory.userDao();
//        System.out.println(dao1); //com.tobi.objectAndDependency.user.dao.UserDao@55536d9e
//        System.out.println(dao2); //com.tobi.objectAndDependency.user.dao.UserDao@747edf66
//
//        UserDao dao3 = context.getBean("userDao",UserDao.class);
//        UserDao dao4 = context.getBean("userDao",UserDao.class);
//        System.out.println(dao3); //com.tobi.objectAndDependency.user.dao.UserDao@3d1cfad4
//        System.out.println(dao4); //com.tobi.objectAndDependency.user.dao.UserDao@3d1cfad4

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {



        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.add(user1);
        assertThat(dao.getCount(),is(1));

        dao.add(user2);
        assertThat(dao.getCount(),is(2));

        dao.add(user3);
        assertThat(dao.getCount(),is(3));

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.get("unknown_id");
    }

}
