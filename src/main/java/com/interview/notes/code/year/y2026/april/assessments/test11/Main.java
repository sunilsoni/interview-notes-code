package com.interview.notes.code.year.y2026.april.assessments.test11;

// The Singleton Class
class DatabaseConnection {

    // 1. Private constructor prevents instantiation from other classes
    private DatabaseConnection() {
        System.out.println("Connecting to the Database...");
    }

    // 3. Global access point
    public static DatabaseConnection getInstance() {
        return Holder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello! I am the only instance of this class.");
    }

    // 2. Inner static class to hold the instance
    // It is not loaded into memory until the getInstance() method is called
    private static class Holder {
        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }
}

// The Execution Class
public class Main {
    public static void main(String[] args) {
        // Try to get two instances
        DatabaseConnection connection1 = DatabaseConnection.getInstance();
        DatabaseConnection connection2 = DatabaseConnection.getInstance();

        // Check if they are the same object in memory
        System.out.println("Connection 1 HashCode: " + connection1.hashCode());
        System.out.println("Connection 2 HashCode: " + connection2.hashCode());

        if (connection1 == connection2) {
            System.out.println("Success: Both variables point to the same instance.");
        }

        connection1.showMessage();
    }
}