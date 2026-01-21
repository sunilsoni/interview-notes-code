package com.interview.notes.code.year.y2025.december.common.test5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TwoSum {

    // Main method to find two indices that sum to target
    public static int[] findTwoSum(int[] nums, int target) {
        // HashMap stores number as key and its index as value
        // This helps us quickly check if complement exists
        Map<Integer, Integer> seen = new HashMap<>();

        // Loop through each number in array
        for (int i = 0; i < nums.length; i++) {
            // Calculate what number we need to reach target
            int complement = target - nums[i];

            // Check if complement already exists in our map
            if (seen.containsKey(complement)) {
                // Found pair! Return both indices
                return new int[]{seen.get(complement), i};
            }

            // Store current number with its index for future lookups
            seen.put(nums[i], i);
        }

        // No valid pair found, return empty array
        return new int[]{};
    }

    // Test helper - compares expected vs actual result
    public static void runTest(String name, int[] nums, int target, int[] expected) {
        // Call our solution
        int[] result = findTwoSum(nums, target);

        // Check if result matches expected (order may vary)
        boolean pass = Arrays.equals(result, expected) ||
                (result.length == 2 && expected.length == 2 &&
                        nums[result[0]] + nums[result[1]] == target);

        // Print test outcome
        System.out.println(name + ": " + (pass ? "PASS ✓" : "FAIL ✗"));

        // Show details if failed
        if (!pass) {
            System.out.println("  Expected: " + Arrays.toString(expected));
            System.out.println("  Got: " + Arrays.toString(result));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Two Sum Test Cases ===\n");

        // Test 1: Basic case - 5+7=12
        runTest("Test 1: Basic",
                new int[]{5, 7, 3, 2}, 12,
                new int[]{0, 1});

        // Test 2: Numbers at different positions
        runTest("Test 2: Different positions",
                new int[]{2, 7, 11, 15}, 9,
                new int[]{0, 1});

        // Test 3: Target at end of array
        runTest("Test 3: End elements",
                new int[]{3, 2, 4}, 6,
                new int[]{1, 2});

        // Test 4: Same numbers used twice
        runTest("Test 4: Duplicate values",
                new int[]{3, 3}, 6,
                new int[]{0, 1});

        // Test 5: Negative numbers included
        runTest("Test 5: Negative numbers",
                new int[]{-1, -2, -3, -4, -5}, -8,
                new int[]{2, 4});

        // Test 6: Mixed positive and negative
        runTest("Test 6: Mixed signs",
                new int[]{-3, 4, 3, 90}, 0,
                new int[]{0, 2});

        // Test 7: Large array - performance test
        int[] largeArray = IntStream.range(0, 100000).toArray();
        // Finding 99997 + 99998 = 199995
        runTest("Test 7: Large data (100k)",
                largeArray, 199995,
                new int[]{99997, 99998});

        // Test 8: Zero in array
        runTest("Test 8: Zero included",
                new int[]{0, 4, 3, 0}, 0,
                new int[]{0, 3});

        // Test 9: Single pair possible
        runTest("Test 9: Exact match",
                new int[]{1, 2, 3, 4, 5}, 9,
                new int[]{3, 4});

        // Test 10: Very large numbers
        runTest("Test 10: Large values",
                new int[]{Integer.MAX_VALUE - 1, 1, 2}, Integer.MAX_VALUE,
                new int[]{0, 1});

        System.out.println("\n=== All Tests Complete ===");
    }
}