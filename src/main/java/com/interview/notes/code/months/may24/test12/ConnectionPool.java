package com.interview.notes.code.months.may24.test12;

public class ConnectionPool {
    // Private static instance variable
    private static ConnectionPool instance;

    // Private constructor to prevent instantiation from outside
    private ConnectionPool() {
        // Initialize connection pool here
    }

    // Public static method to get the instance
    public static ConnectionPool getInstance() {
        // Lazy initialization: create instance if it's not already created
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    // Other methods related to the connection pool can be added here
    // For example: getConnection(), releaseConnection(), etc.
}
