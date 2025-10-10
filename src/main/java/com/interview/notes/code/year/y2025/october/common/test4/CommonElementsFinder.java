package com.interview.notes.code.year.y2025.october.common.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonElementsFinder {
    
    // Main method to find common elements and test the solution
    public static void main(String[] args) {
        // Test Case 1: Basic test with given input arrays
        List<Integer> arr1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> arr2 = Arrays.asList(0, 9, 8, 7, 6, 5, 1);
        List<Integer> arr3 = Arrays.asList(1, 4, 7, 9, 11, 44);
        
        testCase(arr1, arr2, arr3, List.of(1), "Basic Test Case");

        // Test Case 2: Arrays with no common elements
        List<Integer> test2Arr1 = Arrays.asList(1, 2, 3);
        List<Integer> test2Arr2 = Arrays.asList(4, 5, 6);
        List<Integer> test2Arr3 = Arrays.asList(7, 8, 9);
        
        testCase(test2Arr1, test2Arr2, test2Arr3, List.of(), "No Common Elements");

        // Test Case 3: Arrays with multiple common elements
        List<Integer> test3Arr1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> test3Arr2 = Arrays.asList(2, 3, 4, 5, 6);
        List<Integer> test3Arr3 = Arrays.asList(3, 4, 5, 6, 7);
        
        testCase(test3Arr1, test3Arr2, test3Arr3, Arrays.asList(3, 4, 5), "Multiple Common Elements");
    }

    // Method to find common elements using Java 8 Stream API
    public static List<Integer> findCommonElements(List<Integer> arr1, List<Integer> arr2, List<Integer> arr3) {
        // Convert first array to stream and filter elements that exist in both arr2 and arr3
        return arr1.stream()
                  // Filter elements that exist in arr2
                  .filter(arr2::contains)
                  // Filter elements that exist in arr3
                  .filter(arr3::contains)
                  // Sort the results for consistent output
                  .sorted()
                  // Collect results to a List
                  .collect(Collectors.toList());
    }

    // Helper method to test cases and print results
    private static void testCase(List<Integer> arr1, List<Integer> arr2, List<Integer> arr3, 
                               List<Integer> expectedResult, String testName) {
        // Find common elements using our method
        List<Integer> result = findCommonElements(arr1, arr2, arr3);
        
        // Compare result with expected output
        boolean passed = result.equals(expectedResult);
        
        // Print test results
        System.out.println("Test Case: " + testName);
        System.out.println("Input Arrays:");
        System.out.println("Array 1: " + arr1);
        System.out.println("Array 2: " + arr2);
        System.out.println("Array 3: " + arr3);
        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Actual Result: " + result);
        System.out.println("Test Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}
