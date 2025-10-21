package com.interview.notes.code.year.y2025.october.common.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindTwoNumberSum {

    // Main method to run our test cases
    public static void main(String[] args) {
        // Test Case 1: Basic case with small numbers
        testFindPair(new int[]{1, 2, 3, 4, 5}, 7);

        // Test Case 2: Numbers not found
        testFindPair(new int[]{1, 2, 3}, 10);

        // Test Case 3: Large array test
        int[] largeArray = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeArray[i] = i + 1;
        }
        testFindPair(largeArray, 999);

        // Test Case 4: Array with duplicate numbers
        testFindPair(new int[]{4, 4, 4, 4}, 8);

        // Test Case 5: Empty array
        testFindPair(new int[]{}, 5);
    }

    // Method to find two numbers that sum to target
    public static int[] findPair(int[] numbers, int targetSum) {
        // Create a map to store number and its index
        Map<Integer, Integer> numMap = new HashMap<>();

        // Loop through each number in array
        for (int i = 0; i < numbers.length; i++) {
            // Find what number we need to reach target
            int needed = targetSum - numbers[i];

            // If we found the needed number in our map
            if (numMap.containsKey(needed)) {
                // Return both indexes
                return new int[]{numMap.get(needed), i};
            }

            // Store current number and its index
            numMap.put(numbers[i], i);
        }

        // Return empty array if no pair found
        return new int[]{};
    }

    // Method to test and print results
    public static void testFindPair(int[] numbers, int targetSum) {
        System.out.println("\nTest Case:");
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Target Sum: " + targetSum);

        int[] result = findPair(numbers, targetSum);

        if (result.length == 2) {
            System.out.println("PASS: Found indexes " + result[0] + " and " + result[1]);
            System.out.println("Numbers: " + numbers[result[0]] + " + " + numbers[result[1]] + " = " + targetSum);
        } else {
            System.out.println("FAIL: No two numbers found that sum to " + targetSum);
        }
    }
}
