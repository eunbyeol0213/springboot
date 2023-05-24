package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public abstract class UserDao {
    ConnectionMaker connectionMaker;

    public UserDao() {
        this.connectionMaker = new DConnectionMaker();
    }



    public void add(User user) throws SQLException, ClassNotFoundException {

        Connection con = connectionMaker.makeConnection();

        PreparedStatement pstmt = con. prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        //insert 실행
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection con = connectionMaker.makeConnection();

        PreparedStatement pstmt = con.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);

        //select는 결과값이 있음
        ResultSet rs = pstmt.executeQuery();
        rs.next(); //MySQL에서 결과를 보기위한 ctrl + enter 와 같음

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        pstmt.close();
        con.close();
        rs.close();

        return user;
    }

    public static void main (String[]args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new NUserDao();
        User user = new User();
        user.setId("3");
        user.setName("고등어");
        user.setPassword("123");
        userDao.add(user);

        User selectedUser = userDao.get("1");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());

    }
}