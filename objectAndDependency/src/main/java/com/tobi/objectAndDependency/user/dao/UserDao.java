package com.tobi.objectAndDependency.user.dao;

import com.tobi.objectAndDependency.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;

public   class UserDao {


    private ConnectionMaker connectionMaker;

    private DataSource dataSource;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private Connection c;
    private User user;

//    <bean id="userDao" class="springboo.dao.UserDao">
//        <property name="connectionMaker" ref="connectionMaker"/>
//    </bean>
    public  UserDao(){
        connectionMaker = new DconnectionMaker();
    }
    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
         StatementStrategy st =new AddStatement(user);
         jdbcContextWithStatementStrategy(st);
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        this.c  = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id =?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user =null;
        if(rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }
        rs.close();
        ps.close();
        c.close();
        if(user==null)throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() throws SQLException {
        StatementStrategy st = new DeleteAllStatement();
        jdbcContextWithStatementStrategy(st);

    }

    public int getCount() throws SQLException, ClassNotFoundException {
        this.c=null;
        PreparedStatement ps=null;
        ResultSet rs =null;
        try{
            this.c = connectionMaker.makeConnection();
            ps= c.prepareStatement("select count(*) from users");


            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e){
            throw e;
        }finally {

            if(rs!=null) {
                try {
                    rs.close();
                }catch (SQLException e){

                }
            }
            if (ps!=null) {
                try {
                    ps.close();
                }catch (SQLException e) {

                }
            }
            if (c!=null) {
                try {
                    c.close();
                }catch (SQLException e) {

                }
            }
        }





    }

    private PreparedStatement makeStatement(Connection c) throws  SQLException {
        PreparedStatement ps ;
        ps=c.prepareStatement("delete from users");
        return ps;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps=null;

        try {
            c = connectionMaker.makeConnection();
            ps=stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (ps!=null) {
                try {
                    ps.close();
                }catch (SQLException e) {

                }
            }
            if (c!=null) {
                try {
                    c.close();
                }catch (SQLException e) {

                }
            }
        }
    }





}

