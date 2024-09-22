package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/baticuisine";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to the PostgreSQL database successful!");
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database!");
            e.printStackTrace();
        }
        return conn;
    }
}
