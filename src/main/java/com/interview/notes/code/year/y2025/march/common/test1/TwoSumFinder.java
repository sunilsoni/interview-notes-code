package com.interview.notes.code.year.y2025.march.common.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumFinder {

    // Method to find two numbers that sum up to target
    public static int[] findTwoSum(int[] numbers, int target) {
        // Using HashMap to store complement values
        Map<Integer, Integer> numMap = new HashMap<>();

        // Iterate through array once - O(n) time complexity
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];

            // If complement exists in map, we found our pair
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            // Store current number and its index
            numMap.put(numbers[i], i);
        }
        // Return empty array if no solution found
        return new int[]{};
    }

    // Test method to validate solutions
    public static void testTwoSum(int[] numbers, int target, int[] expected) {
        int[] result = findTwoSum(numbers, target);
        String testCase = String.format("Input: %s, Target: %d",
                Arrays.toString(numbers), target);

        if (Arrays.equals(result, expected)) {
            System.out.println("PASS: " + testCase);
        } else {
            System.out.println("FAIL: " + testCase);
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got: " + Arrays.toString(result));
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        testTwoSum(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1});

        // Test Case 2: Numbers not in sequence
        testTwoSum(new int[]{3, 2, 4}, 6, new int[]{1, 2});

        // Test Case 3: Same number twice
        testTwoSum(new int[]{3, 3}, 6, new int[]{0, 1});

        // Test Case 4: No solution
        testTwoSum(new int[]{1, 2, 3}, 7, new int[]{});

        // Test Case 5: Large numbers
        testTwoSum(new int[]{1000000, 2000000, 3000000}, 5000000, new int[]{1, 2});

        // Test Case 6: Negative numbers
        testTwoSum(new int[]{-1, -2, -3, -4}, -5, new int[]{1, 2});
    }
}
