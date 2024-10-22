package com.interview.notes.code.months.oct24.test17;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.Arrays;

/**
 * PairFinder class encapsulates the logic to find a pair in an array that sums up to a target value.
 * It also contains methods to execute and verify multiple test cases.
 */
public class PairFinder {

    /**
     * Finds a pair of numbers in the array that add up to the target.
     *
     * @param nums   The input array of integers.
     * @param target The target sum value.
     * @return A string indicating whether a pair is found and the pair itself, or that no pair exists.
     */
    public static String findPair(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return "Pair found (" + complement + ", " + num + ")";
            }
            seen.add(num);
        }
        return "Pair not found";
    }

    /**
     * Represents a test case with input array, target, expected output, and a description.
     */
    static class TestCase {
        int[] nums;
        int target;
        String expected;
        String description;

        TestCase(int[] nums, int target, String expected, String description) {
            this.nums = nums;
            this.target = target;
            this.expected = expected;
            this.description = description;
        }
    }

    /**
     * Main method to execute and verify all test cases.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define all test cases
        TestCase[] testCases = new TestCase[]{
            new TestCase(
                new int[]{8, 7, 2, 5, 3, 1},
                10,
                "Pair found (8, 2)orPair found (7, 3)",
                "Example Test Case 1"
            ),
            new TestCase(
                new int[]{5, 2, 6, 8, 1, 9},
                12,
                "Pair not found",
                "Example Test Case 2"
            ),
            // Additional test cases for edge scenarios
            new TestCase(
                new int[]{},
                5,
                "Pair not found",
                "Empty Array"
            ),
            new TestCase(
                new int[]{1},
                2,
                "Pair not found",
                "Single Element"
            ),
            new TestCase(
                new int[]{4, 4},
                8,
                "Pair found (4, 4)",
                "Pair with Same Elements"
            ),
            new TestCase(
                new int[]{-1, -2, -3, -4, -5},
                -8,
                "Pair found (-3, -5)",
                "Negative Numbers"
            ),
            new TestCase(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                19,
                "Pair found (9, 10)",
                "Larger Array"
            )
        };

        // Execute and verify each test case
        for (TestCase testCase : testCases) {
            String result = findPair(testCase.nums, testCase.target);
            boolean pass = false;

            if (testCase.expected.contains("or")) {
                // If multiple correct outputs are possible
                String[] possibleOutcomes = testCase.expected.split("or");
                for (String outcome : possibleOutcomes) {
                    if (result.equals(outcome.trim())) {
                        pass = true;
                        break;
                    }
                }
            } else {
                pass = result.equals(testCase.expected);
            }

            System.out.println(testCase.description + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  Expected: " + testCase.expected);
                System.out.println("  Got     : " + result);
            }
        }

        // Additional test case for large data inputs
        largeDataTestCase();
    }

    /**
     * Executes a test case with a large dataset to ensure performance and correctness.
     */
    private static void largeDataTestCase() {
        int size = 1000000; // 1 million elements
        int[] largeArray = new int[size];
        Random rand = new Random(0); // Seed for reproducibility
        for (int i = 0; i < size; i++) {
            largeArray[i] = rand.nextInt(1000000); // Random integers between 0 and 999,999
        }
        // Define a target that is guaranteed to have a pair
        largeArray[0] = 500000;
        largeArray[1] = 500000;
        int target = 1000000;
        String expected = "Pair found (500000, 500000)";
        String result = findPair(largeArray, target);
        boolean pass = result.equals(expected);
        System.out.println("Large Data Test Case: " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Got     : " + result);
        }
    }
}