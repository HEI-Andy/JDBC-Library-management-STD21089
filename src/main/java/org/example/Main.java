package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // JDBC URL, username, and password of PostgreSQL server
        String url = System.getenv("url");
        String user = System.getenv("user");
        String password = System.getenv("password");

        // Test the database connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection to the database established successfully.");

            // Add your code to perform database operations here

        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
