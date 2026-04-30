package com.interview.notes.code.year.y2026.april.common.test3;

public class DatabaseConnector {
    // 1. Private static instance variable (volatile to ensure visibility across threads)
    private static volatile DatabaseConnector instance;

    // 2. Private constructor to prevent instantiation from other classes
    private DatabaseConnector() {
        System.out.println("Initializing Database Connection...");
    }

    // 3. Static factory method to provide global access
    public static DatabaseConnector getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (DatabaseConnector.class) {
                if (instance == null) { // Second check (with locking)
                    instance = new DatabaseConnector();
                }
            }
        }
        return instance;
    }

    // Simple main method for testing
    public static void main(String[] args) {
        DatabaseConnector s1 = DatabaseConnector.getInstance();
        DatabaseConnector s2 = DatabaseConnector.getInstance();

        System.out.println("Are both instances the same? " + (s1 == s2));
        s1.connect();
    }

    public void connect() {
        System.out.println("Connected to DB successfully.");
    }
}