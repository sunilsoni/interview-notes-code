package com.interview.notes.code.year.y2025.June.common.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NestedListCalculator {

    // Main method to demonstrate and test the solution
    public static void main(String[] args) {
        // Test Case 1: Given example
        List<Object> input1 = Arrays.asList(1, 2, 3, Arrays.asList(1, 2), 3,
                Arrays.asList(1, Arrays.asList(2, 4)), 3, 4);
        testCase(input1, 4536, "Basic Test Case");

        // Test Case 2: Simple list without nesting
        List<Object> input2 = Arrays.asList(1, 2, 3);
        testCase(input2, 6, "Simple List Test");

        // Test Case 3: Empty list
        List<Object> input3 = new ArrayList<>();
        testCase(input3, 0, "Empty List Test");

        // Test Case 4: Single level nesting
        List<Object> input4 = Arrays.asList(2, Arrays.asList(3, 4));
        testCase(input4, 14, "Single Level Nesting");
    }

    // Main calculation method that processes nested list
    public static int calculateNestedList(List<Object> list) {
        // Handle empty list case
        if (list == null || list.isEmpty()) {
            return 0;
        }

        // Initialize result to 1 for multiplication
        int result = 1;

        // Iterate through each element in the list
        for (Object element : list) {
            if (element instanceof List) {
                // If element is a list, calculate sum of nested elements
                result *= calculateSum((List<Object>) element);
            } else {
                // If element is a number, multiply with result
                result *= (Integer) element;
            }
        }
        return result;
    }

    // Helper method to calculate sum of nested elements
    private static int calculateSum(List<Object> list) {
        int sum = 0;
        // Process each element in the nested list
        for (Object element : list) {
            if (element instanceof List) {
                // Recursively calculate sum for nested lists
                sum += calculateSum((List<Object>) element);
            } else {
                // Add number to sum
                sum += (Integer) element;
            }
        }
        return sum;
    }

    // Test helper method to verify results
    private static void testCase(List<Object> input, int expectedOutput, String testName) {
        int result = calculateNestedList(input);
        boolean passed = result == expectedOutput;

        System.out.println("Test: " + testName);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expectedOutput);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}
