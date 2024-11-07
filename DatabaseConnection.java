package com.usermanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/sreenivasan81003", 
                "sreenivasan81003@gmail.com", 
                "sreenivasan81003");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Connection failed", e);
        }
    }
}

