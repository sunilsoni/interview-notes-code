package com.interview.notes.code.year.y2025.jan24.test15;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeSorting {

    // Method to sort HashMap by values (names)
    public static Map<String, String> sortByEmployeeName(Map<String, String> employees) {
        if (employees == null || employees.isEmpty()) {
            return new HashMap<>();
        }

        return employees.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Normal case
        Map<String, String> test1 = new HashMap<>();
        test1.put("101", "Zack");
        test1.put("102", "Alice");
        test1.put("103", "Bob");

        // Test Case 2: Empty map
        Map<String, String> test2 = new HashMap<>();

        // Test Case 3: Large data
        Map<String, String> test3 = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            test3.put(String.valueOf(i), "Name" + (10000 - i));
        }

        // Test Case 4: Special characters
        Map<String, String> test4 = new HashMap<>();
        test4.put("201", "Ádam");
        test4.put("202", "Émily");
        test4.put("203", "John");

        // Run tests
        runTest("Test 1 - Normal case", test1);
        runTest("Test 2 - Empty map", test2);
        runTest("Test 3 - Large data", test3);
        runTest("Test 4 - Special characters", test4);
    }

    // Helper method to run tests
    private static void runTest(String testName, Map<String, String> input) {
        System.out.println("\nRunning " + testName);
        try {
            long startTime = System.currentTimeMillis();
            Map<String, String> result = sortByEmployeeName(input);
            long endTime = System.currentTimeMillis();

            // Verify sorting
            boolean isSorted = verifySorting(result);

            System.out.println("Time taken: " + (endTime - startTime) + "ms");
            System.out.println("Is correctly sorted: " + isSorted);
            System.out.println("Test Result: " + (isSorted ? "PASS" : "FAIL"));

            // Print first few entries for verification
            if (!result.isEmpty()) {
                System.out.println("First few entries: " +
                        result.entrySet().stream()
                                .limit(3)
                                .map(e -> e.getKey() + "=" + e.getValue())
                                .collect(Collectors.joining(", ")));
            }
        } catch (Exception e) {
            System.out.println("Test FAILED with exception: " + e.getMessage());
        }
    }

    // Helper method to verify sorting
    private static boolean verifySorting(Map<String, String> map) {
        if (map.size() <= 1) return true;

        String previous = null;
        for (String current : map.values()) {
            if (previous != null && current.compareTo(previous) < 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
