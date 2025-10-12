package com.interview.notes.code.year.y2025.october.common.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringNumberSorter {
    
    // Main method to demonstrate and test the solution
    public static void main(String[] args) {
        // Test Case 1: Basic sorting
        testCase1();
        
        // Test Case 2: Large dataset
        testCase2();
        
        // Test Case 3: Edge cases
        testCase3();
    }
    
    // Method to sort strings based on last two digits
    public static List<String> sortByLastTwoDigits(List<String> input) {
        // Using streams to sort the list
        return input.stream()
            // Extract last two digits and convert to integer for comparison
            .sorted((s1, s2) -> {
                // Get last two characters from each string
                int num1 = Integer.parseInt(s1.substring(s1.length() - 2));
                int num2 = Integer.parseInt(s2.substring(s2.length() - 2));
                // Compare the numbers
                return Integer.compare(num1, num2);
            })
            // Collect results back to list
            .toList();
    }
    
    // Test Case 1: Basic functionality test
    private static void testCase1() {
        System.out.println("Test Case 1: Basic Sorting");
        List<String> input = Arrays.asList("ABC25", "DEF10", "GHI88", "XYZ14");
        List<String> expected = Arrays.asList("DEF10", "XYZ14", "ABC25", "GHI88");
        List<String> result = sortByLastTwoDigits(input);
        
        // Verify results
        System.out.println("Input: " + input);
        System.out.println("Output: " + result);
        System.out.println("Expected: " + expected);
        System.out.println("Test Result: " + (result.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    // Test Case 2: Large dataset test
    private static void testCase2() {
        System.out.println("Test Case 2: Large Dataset");
        // Create large dataset
        List<String> largeInput = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeInput.add("TEST" + String.format("%02d", i % 100));
        }
        
        // Time the sorting operation
        long startTime = System.currentTimeMillis();
        List<String> result = sortByLastTwoDigits(largeInput);
        long endTime = System.currentTimeMillis();
        
        // Verify sorting is correct
        boolean isSorted = true;
        for (int i = 1; i < result.size(); i++) {
            int prev = Integer.parseInt(result.get(i-1).substring(result.get(i-1).length() - 2));
            int curr = Integer.parseInt(result.get(i).substring(result.get(i).length() - 2));
            if (prev > curr) {
                isSorted = false;
                break;
            }
        }
        
        System.out.println("Processing Time: " + (endTime - startTime) + "ms");
        System.out.println("Test Result: " + (isSorted ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    // Test Case 3: Edge cases test
    private static void testCase3() {
        System.out.println("Test Case 3: Edge Cases");
        // Test with same numbers
        List<String> input = Arrays.asList("ABC00", "DEF00", "GHI00");
        List<String> result = sortByLastTwoDigits(input);
        boolean sameNumbersTest = result.size() == input.size();
        
        System.out.println("Same Numbers Test: " + (sameNumbersTest ? "PASS" : "FAIL"));
        System.out.println();
    }
}
