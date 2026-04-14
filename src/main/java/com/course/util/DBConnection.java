package com.course.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection utility class
 * Provides methods to establish and close database connections
 */
public class DBConnection {
    
    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/course_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Sara@2304"; // Replace with your actual MySQL root password
    
    static {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }
    
    /**
     * Get database connection
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    /**
     * Close database connection
     * @param connection Connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    
    /**
     * Test database connection
     * @return true if connection is successful, false otherwise
     */
    public static boolean testConnection() {
        Connection connection = null;
        try {
            connection = getConnection();
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            return false;
        } finally {
            closeConnection(connection);
        }
    }
}
