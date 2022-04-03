package com.tangyouze.jdbc;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author tyz
 */
public class ConnectionPlay {
    private Connection conn;
    private final String URL = "jdbc:mysql://localhost:3306/mysql";
    private final String NAME = "root";
    private final String PASSWORD = "";

    void run() throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获得数据库的连接
        conn = DriverManager.getConnection(URL, NAME, PASSWORD);

        try {
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            insert();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            select();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dropTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private @NotNull String randomString() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int number = (int) (Math.random() * length);
            sb.append(str.charAt(number));
        }
        return sb.toString();

    }

    private void insert() throws Exception {
        Statement stmt = conn.createStatement();
        for (int i = 0; i < 10; i++) {
            // generate random string
            boolean execute = stmt.execute("insert into play1(name) values ('" + randomString() + "')");
            System.out.println(execute);
        }

    }

    private void select() throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from play1");
        System.out.println(resultSet);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
        }

    }

    void createTable() throws Exception {
        Statement stmt = conn.createStatement();
        boolean execute = stmt.execute("create table play1(id int auto_increment primary key ,name varchar(20))");
        System.out.println(execute);
    }

    void dropTable() throws Exception {
        Statement stmt = conn.createStatement();
        boolean execute = stmt.execute("drop table play1");
        System.out.println(execute);
    }
}
