package com.tangyouze.jdbc;

import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Soundbank;
import java.sql.*;

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

    private void select() {
        try {

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from play1");
            System.out.println(resultSet);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    private void insertWithOutTransaction() throws Exception {
        Statement stmt = conn.createStatement();
        for (int i = 0; i < 10; i++) {
            // generate random string
            boolean execute = stmt.execute("insert into play1(id, name) values ( 1, '" + randomString() + "')");
            System.out.println(execute);
        }
    }

    private void insertWithTransaction(int start, int end) throws Exception {
        conn.setAutoCommit(false);
        try {

            PreparedStatement pstmt = conn.prepareStatement("insert into play1(id, name) values ( ?, ?)");
            for (int i = start; i < end; i++) {
                // generate random string
                pstmt.setInt(1, start);
                pstmt.setString(2, randomString());
                System.out.println(pstmt);
                pstmt.execute();
//                boolean execute = stmt.execute("insert into play1(id, name) values ( 1, '" + randomString() + "')");
//                System.out.println(execute);
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        }
    }

    public void runTransaction() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获得数据库的连接
        conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        try {
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            insertWithOutTransaction();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 不用 transaction 这里可以查到一个记录
//        select();

        // 这里碰到了一个第一次知道的feature mysql 如果往id 里面插入0 的话 他不会当0 , 会当成自增的添加进去 所以这里不会失败
        // amazing
        insertWithTransaction(1, 5);
        // 应该一条都插入不成功
        select();

        // 可以插入一个
        insertWithTransaction(11, 12);
        select();

        // 不用 transaction 这里可以查到一个记录

        try {
            dropTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
