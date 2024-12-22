package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
//    jdbc:mysql://localhost:3306/yourDatabase?useUnicode=true&characterEncoding=UTF-8

    private static final String URL = "jdbc:mysql://localhost:3306/diary?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "sun";
    private static final String PASSWORD = "031217";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
