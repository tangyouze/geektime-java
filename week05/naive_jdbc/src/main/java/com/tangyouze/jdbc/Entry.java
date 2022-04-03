package com.tangyouze.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author tyz
 */
public class Entry {

    public static void main(String[] args) throws Exception {

        ConnectionPlay play = new ConnectionPlay();
//        play.run();
        play.runTransaction();
    }
}
