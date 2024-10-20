package com.interview.notes.code.months.oct24.test13;

import java.util.*;
import java.util.stream.Collectors;

public class UserAgeFilter {

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Alice", 30, "123 Main St"),
                new User("Bob", 45, "456 Elm St"),
                new User("Charlie", 35, "789 Oak St"),
                new User("David", 50, "101 Pine St"),
                new User("Eve", 25, "202 Maple St")
        );

        testAgeFilter(users);
        testLargeDataSet();
    }

    public static Map<String, List<User>> filterUsersByAge(List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(
                        user -> user.age > 40 ? "over40" : "under40",
                        Collectors.toList()
                ));
    }

    public static void testAgeFilter(List<User> users) {
        Map<String, List<User>> filteredUsers = filterUsersByAge(users);

        // Test case 1: Check if users over 40 are correctly filtered
        boolean test1 = filteredUsers.get("over40").stream().allMatch(user -> user.age > 40);
        System.out.println("Test 1 (Users over 40): " + (test1 ? "PASS" : "FAIL"));

        // Test case 2: Check if users under or equal to 40 are correctly filtered
        boolean test2 = filteredUsers.get("under40").stream().allMatch(user -> user.age <= 40);
        System.out.println("Test 2 (Users under or equal to 40): " + (test2 ? "PASS" : "FAIL"));

        // Test case 3: Check if all users are accounted for
        boolean test3 = filteredUsers.get("over40").size() + filteredUsers.get("under40").size() == users.size();
        System.out.println("Test 3 (All users accounted for): " + (test3 ? "PASS" : "FAIL"));
    }

    public static void testLargeDataSet() {
        List<User> largeUserList = new ArrayList<>();
        Random random = new Random();

        // Generate a large dataset of 1 million users
        for (int i = 0; i < 1_000_000; i++) {
            largeUserList.add(new User("User" + i, random.nextInt(80) + 1, "Address" + i));
        }

        long startTime = System.currentTimeMillis();
        Map<String, List<User>> filteredLargeUsers = filterUsersByAge(largeUserList);
        long endTime = System.currentTimeMillis();

        System.out.println("Large dataset processing time: " + (endTime - startTime) + " ms");

        // Test case 4: Check if large dataset is processed correctly
        boolean test4 = filteredLargeUsers.get("over40").size() + filteredLargeUsers.get("under40").size() == largeUserList.size();
        System.out.println("Test 4 (Large dataset processed correctly): " + (test4 ? "PASS" : "FAIL"));
    }

    static class User {
        String name;
        int age;
        String address;

        User(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }
}
