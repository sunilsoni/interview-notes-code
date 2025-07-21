package com.interview.notes.code.year.y2025.july.amazon.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class to find all unique triplets in an array that sum to zero
 * Uses sorting and two-pointer technique for optimal performance
 */
public class ThreeSumZero {

    /**
     * Finds all unique combinations of three integers that sum to zero
     *
     * @param nums Input array of integers
     * @return List of Lists containing triplets that sum to zero
     */
    public static List<List<Integer>> findThreeSumZero(int[] nums) {
        // ArrayList to store our results - using ArrayList for O(1) add operations
        List<List<Integer>> results = new ArrayList<>();

        // Sort array to handle duplicates and enable two-pointer technique
        // Sorting is O(n log n) but allows for more efficient searching
        Arrays.sort(nums);

        // Iterate through array, fixing one number and finding pairs for remaining sum
        // We stop at length-2 because we need at least 2 more numbers after i
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for i to avoid duplicate triplets
            // If current number is same as previous, we've already processed all its combinations
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Two-pointer technique: left pointer starts after i, right pointer at end
            // This allows us to search remaining space in O(n) time
            int left = i + 1;
            int right = nums.length - 1;

            // Continue while left and right pointers haven't met
            while (left < right) {
                // Calculate current sum of three numbers
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Found a valid triplet, add to results
                    // Using Arrays.asList for convenient List creation
                    results.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate values for left pointer
                    // This prevents duplicate triplets in result
                    while (left < right && nums[left] == nums[left + 1]) left++;

                    // Skip duplicate values for right pointer
                    // This prevents duplicate triplets in result
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    // Move both pointers inward after finding a triplet
                    left++;
                    right--;
                } else if (sum < 0) {
                    // Sum is too small, need larger numbers, move left pointer right
                    left++;
                } else {
                    // Sum is too large, need smaller numbers, move right pointer left
                    right--;
                }
            }
        }
        return results;
    }

    /**
     * Main method containing test cases
     * Uses simple testing approach without external frameworks
     */
    public static void main(String[] args) {
        // Test various scenarios
        testCase(new int[]{-1, 0, 1}, "Basic test with obvious solution");
        testCase(new int[]{-1, -1, 2}, "Test with negative numbers");
        testCase(new int[]{1, 2, 3}, "Test with no solution");
        testCase(new int[]{0, 0, 0}, "Test with all zeros");
        testCase(new int[]{-2, -1, 0, 1, 2}, "Test with larger array");

        // Test with large dataset
        int[] largeArray = generateLargeTestArray(1000);
        testCase(largeArray, "Large dataset test");
    }

    /**
     * Helper method to run and validate test cases
     *
     * @param input    Test input array
     * @param testName Description of the test case
     */
    private static void testCase(int[] input, String testName) {
        System.out.println("\nRunning test: " + testName);
        System.out.println("Input: " + Arrays.toString(input));

        // Measure execution time for performance analysis
        long startTime = System.currentTimeMillis();
        List<List<Integer>> result = findThreeSumZero(input);
        long endTime = System.currentTimeMillis();

        System.out.println("Found combinations: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println("Test " + (result != null ? "PASSED" : "FAILED"));
        System.out.println("------------------------");
    }

    /**
     * Generates large test array with random numbers
     *
     * @param size Size of the array to generate
     * @return Array of random integers
     */
    private static int[] generateLargeTestArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(1001) - 500; // Random numbers between -500 and 500
        }
        return array;
    }
}
