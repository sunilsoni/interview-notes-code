package com.interview.notes.code.year.y2026.march.common.test2;

public class DatabaseConnection {

    // 1. A volatile private static variable to hold the single instance.
    // 'volatile' ensures changes to this variable are immediately visible to all threads.
    private static volatile DatabaseConnection instance;

    // 2. A private constructor prevents other classes from instantiating it using 'new'.
    private DatabaseConnection() {
        // Optional but recommended: Protect against reflection-based instantiation
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance.");
        }
    }

    // 3. A public static method to provide global access to the instance.
    public static DatabaseConnection getInstance() {
        // First check: If the instance exists, return it immediately without locking (fast).
        if (instance == null) {
            // Lock the class block so only one thread can enter at a time.
            synchronized (DatabaseConnection.class) {
                // Second check: Ensure another thread hasn't created it while waiting for the lock.
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    // Example business method
    public void executeQuery(String query) {
        System.out.println("Executing: " + query);
    }
}