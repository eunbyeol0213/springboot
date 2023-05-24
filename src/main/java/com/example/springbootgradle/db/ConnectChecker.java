package com.example.springbootgradle.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectChecker {
    public void check() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        //DB 접속
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SHOW DATABASES");

        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    //users table에 insert하는 메소드
    public void add() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-15-165-35-16.ap-northeast-2.compute.amazonaws.com/spring-db",
                "root", "password");

        //
        PreparedStatement pstmt = con. prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, "1");
        pstmt.setString(2, "eunbyeol");
        pstmt.setString(3, "12345");
        pstmt.executeUpdate();
    }
    //users table에 select하는 메소드
    public void select() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-15-165-35-16.ap-northeast-2.compute.amazonaws.com/spring-db",
                "root", "password");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while (rs.next()) {
            String col1 = rs.getString(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            System.out.printf("%s %s %s", col1, col2, col3);
        }
    }
        public static void main (String[]args) throws SQLException, ClassNotFoundException {
            ConnectChecker cc = new ConnectChecker();
//            cc.add();
           cc.check();
 //           cc.select();
        }
    }
