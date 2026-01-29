package com.interview.notes.code.year.y2026.jan.common.test5;

import java.util.Arrays;
import java.util.stream.Stream;

public class FirstMissingPositive {

    public static void main(String[] args) {

        // 1. Define Test Cases including Edge Cases and a Large Data Case
        // We use Stream.of to organize tests clearly using Java 8+ Stream API
        Stream.of(
                new TestCase(new int[]{9, -3, -1, 10, 0}, 1, "User Example"),
                new TestCase(new int[]{1, 2, 0}, 3, "Simple Sequence"),
                new TestCase(new int[]{3, 4, -1, 1}, 2, "Unsorted with Gap"),
                new TestCase(new int[]{7, 8, 9, 11, 12}, 1, "All numbers > length"),
                new TestCase(new int[]{1, 1, 2, 2}, 3, "Duplicates"), // Edge case: duplicates
                createLargeTestCase() // Large Data generation
        ).forEach(FirstMissingPositive::runTest); // Process each test using method reference
    }

    /**
     * Runs a single test case and prints PASS/FAIL.
     */
    private static void runTest(TestCase test) {
        // Clone input array so we don't modify the original test data for printing if needed
        int[] inputCopy = Arrays.copyOf(test.input, test.input.length);

        // Start timing for performance check
        long start = System.nanoTime();

        // Calculate result
        int actual = findFirstMissingPositive(inputCopy);

        // Calculate elapsed time in ms
        double time = (System.nanoTime() - start) / 1_000_000.0;

        // Check if logic matches expected output
        boolean passed = actual == test.expected;

        // Use Java 15+ Text Blocks and formatted printing for clear output
        System.out.printf("[%s] %s -> Expected: %d, Got: %d (Time: %.3f ms)\n",
                passed ? "PASS" : "FAIL",
                test.description,
                test.expected,
                actual,
                time
        );
    }

    /**
     * Core Logic: Finds the first missing positive integer.
     * Uses Cyclic Sort to achieve O(N) time and O(1) space.
     */
    private static int findFirstMissingPositive(int[] nums) {
        int n = nums.length; // Store the length of array to avoid repeated calls
        int i = 0; // Initialize iterator index

        // Phase 1: Place each number in its right place (x -> index x-1)
        while (i < n) { // Iterate through the array
            // Check 3 conditions to decide if we swap:
            // 1. nums[i] > 0: We only care about positive integers
            // 2. nums[i] <= n: The number must fit within the array bounds to map to an index
            // 3. nums[i] != nums[nums[i] - 1]: The number is not already at its correct target position (handles duplicates too)
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {

                // Swap nums[i] with the number at its target position (nums[i] - 1)
                // We use a helper function or inline swap. Inline is faster for primitives.
                int correctIndex = nums[i] - 1; // Calculate where the current number should go
                int temp = nums[correctIndex]; // Save the value at the target index
                nums[correctIndex] = nums[i]; // Put the current number in the target index
                nums[i] = temp; // Put the saved value back into the current index

                // Note: We do NOT increment 'i' here because the new number swapped into 'i'
                // might also need to be moved. We check it again in the next iteration.
            } else {
                i++; // If condition fails (number is negative, too big, or correct), move to next index
            }
        }

        // Phase 2: Scan to find the first index missing its correct number
        for (int j = 0; j < n; j++) { // Iterate through the sorted array
            if (nums[j] != j + 1) { // Check if the number at index j is (j + 1)
                return j + 1; // If not, then (j + 1) is the first missing positive
            }
        }

        // Phase 3: If all positions 0 to n-1 are correct (e.g., input was {1, 2, 3}),
        // then the missing number is the next one after the array size.
        return n + 1;
    }

    /**
     * Helper to create a large dataset for stress testing.
     * Creates an array [1, 2, 3 ... 9999] and removes one number.
     */
    private static TestCase createLargeTestCase() {
        int size = 1_000_000; // Define large size (1 million)
        int[] largeArr = new int[size]; // Allocate memory
        // Fill array with 1 to 1,000,000 using concise Stream API (Java 8+)
        // Arrays.setAll is very fast for initialization
        Arrays.setAll(largeArr, index -> index + 1);

        largeArr[size - 1] = -5; // Corrupt the last element (1,000,000) to make it missing

        // We expect the missing number to be 1,000,000 because we removed it
        // Actually, let's remove 500.
        largeArr[499] = -5; // Remove number 500 (at index 499)

        return new TestCase(largeArr, 500, "Large Data (1M items)");
    }

    // Record is a Java feature (standard in Java 16+) for immutable data carriers.
    // We use it here to define Test Cases concisely.
    record TestCase(int[] input, int expected, String description) {
    }
}