package com.interview.notes.code.year.y2025.april.amazon.test3;

import java.util.*;
import java.util.stream.Collectors;

public class UserDataProcessor {
    // Simulated database of users
    private static List<User> userDatabase = new ArrayList<>();

    // Main method to demonstrate and test the solution
    public static void main(String[] args) {
        // Initialize test data
        setupTestData();

        // Test Case 1: Process normal user data
        System.out.println("Test Case 1: Processing normal user data");
        List<User> activeUsers = processUserData(25);
        System.out.println("Pass: " + (activeUsers.size() == 2));

        // Test Case 2: Empty database
        userDatabase.clear();
        System.out.println("\nTest Case 2: Empty database");
        List<User> emptyResult = processUserData(25);
        System.out.println("Pass: " + (emptyResult.isEmpty()));

        // Test Case 3: Large dataset
        generateLargeDataset(10000);
        System.out.println("\nTest Case 3: Large dataset processing");
        long startTime = System.currentTimeMillis();
        List<User> largeResult = processUserData(25);
        long endTime = System.currentTimeMillis();
        System.out.println("Pass: Processing time < 1000ms: " + (endTime - startTime < 1000));
    }

    // Main processing method that fetches and processes user data
    public static List<User> processUserData(int ageThreshold) {
        return userDatabase.stream()
            .filter(user -> user.isActive())                    // Filter active users
            .filter(user -> user.getAge() > ageThreshold)      // Filter by age
            .sorted(Comparator.comparing(User::getName))        // Sort by name
            .collect(Collectors.toList());                      // Collect results
    }

    // Helper method to set up initial test data
    private static void setupTestData() {
        userDatabase.add(new User("John", 30, true));
        userDatabase.add(new User("Alice", 20, true));
        userDatabase.add(new User("Bob", 35, true));
        userDatabase.add(new User("Carol", 28, false));
    }

    // Helper method to generate large test dataset
    private static void generateLargeDataset(int size) {
        userDatabase.clear();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            userDatabase.add(new User(
                "User" + i,
                random.nextInt(50) + 18,
                random.nextBoolean()
            ));
        }
    }
}

// Simple User class to represent user data
class User {
    private String name;
    private int age;
    private boolean active;

    public User(String name, int age, boolean active) {
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public boolean isActive() { return active; }
}
