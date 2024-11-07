package com.usermanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/your_db_name", 
                "your_username", 
                "your_password");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Connection failed", e);
        }
    }
}

