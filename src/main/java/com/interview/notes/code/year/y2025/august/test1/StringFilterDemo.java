package com.interview.notes.code.year.y2025.august.test1;

import java.util.*;
import java.util.stream.Collectors;

public class StringFilterDemo {
    
    // Method to filter strings that start with given character, convert to uppercase and sort
    public static List<String> filterAndSortStrings(List<String> inputList, char startChar) {
        // Handle null input case to prevent NullPointerException
        if (inputList == null) return new ArrayList<>();
        
        // Using Stream API to process the list:
        // 1. Filter strings starting with startChar (case insensitive)
        // 2. Convert to uppercase
        // 3. Sort alphabetically
        // 4. Collect results to new list
        return inputList.stream()
                .filter(str -> str != null && str.toLowerCase().startsWith(String.valueOf(startChar).toLowerCase()))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case with mixed strings
        List<String> test1 = Arrays.asList("set", "food", "dog", "apple", "sky", "Shop");
        testCase("Test 1 - Normal case", test1, 's', Arrays.asList("SET", "SHOP", "SKY"));

        // Test Case 2: Empty list
        List<String> test2 = new ArrayList<>();
        testCase("Test 2 - Empty list", test2, 's', new ArrayList<>());

        // Test Case 3: Null list
        testCase("Test 3 - Null list", null, 's', new ArrayList<>());

        // Test Case 4: List with null elements
        List<String> test4 = Arrays.asList("sun", null, "sand", "moon");
        testCase("Test 4 - List with null elements", test4, 's', Arrays.asList("SAND", "SUN"));

        // Test Case 5: Large data test
        List<String> test5 = generateLargeDataset(100000);
        long startTime = System.currentTimeMillis();
        List<String> result5 = filterAndSortStrings(test5, 's');
        long endTime = System.currentTimeMillis();
        System.out.println("Test 5 - Large dataset processing time: " + (endTime - startTime) + "ms");
        System.out.println("Large dataset result size: " + result5.size());
    }

    // Helper method to test cases and print results
    private static void testCase(String testName, List<String> input, char startChar, List<String> expected) {
        List<String> result = filterAndSortStrings(input, startChar);
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    // Helper method to generate large dataset for performance testing
    private static List<String> generateLargeDataset(int size) {
        List<String> largeList = new ArrayList<>();
        Random random = new Random();
        String[] prefixes = {"s", "a", "b", "c", "d"};
        
        for (int i = 0; i < size; i++) {
            String prefix = prefixes[random.nextInt(prefixes.length)];
            largeList.add(prefix + "test" + i);
        }
        return largeList;
    }
}
