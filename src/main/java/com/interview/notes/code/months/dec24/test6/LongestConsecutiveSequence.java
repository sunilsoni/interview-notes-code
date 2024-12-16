package com.interview.notes.code.months.dec24.test6;

import java.util.HashSet;
import java.util.Set;

/*
**Longest Consecutive**

Have the function `LongestConsecutive(arr)` take the array of positive integers stored in `arr` and return the length of the longest consecutive subsequence (LCS). An LCS is a subset of the original list where the numbers are in sorted order, from lowest to highest, and are in a consecutive, increasing order. The sequence does not need to be contiguous, and there can be several different subsequences. For example: if `arr` is [4, 3, 8, 1, 2, 6, 100, 9], then a few consecutive sequences are [1, 2, 3, 4], and [8, 9]. For this input, your program should return 4 because that is the length of the longest consecutive subsequence.

**Examples**

- Input: `new int[] {6, 7, 3, 1, 100, 102, 6, 12}`
  Output: 2

- Input: `new int[] {5, 6, 1, 2, 8, 9, 7}`
  Output: 5

 */
public class LongestConsecutiveSequence {

    /**
     * Finds the length of the longest consecutive subsequence in the array.
     *
     * @param arr Array of positive integers.
     * @return Length of the longest consecutive subsequence.
     */
    public static int LongestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : arr) {
            numSet.add(num);
        }

        int maxLength = 1;

        for (int num : numSet) {
            // Check if it's the start of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Incrementally check for the next consecutive numbers
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                maxLength = Math.max(maxLength, currentStreak);
            }
        }

        return maxLength;
    }

    /**
     * Test cases to validate the LongestConsecutive method.
     */
    public static void main(String[] args) {
        // Define test cases
        TestCase[] testCases = new TestCase[]{
                new TestCase(new int[]{6, 7, 3, 1, 100, 102, 6, 12}, 2),
                new TestCase(new int[]{5, 6, 1, 2, 8, 9, 7}, 5),
                new TestCase(new int[]{4, 3, 8, 1, 2, 6, 100, 9}, 4),
                new TestCase(new int[]{}, 0),
                new TestCase(new int[]{1}, 1),
                new TestCase(new int[]{2, 2, 2, 2}, 1),
                new TestCase(new int[]{10, 5, 12, 3, 55, 30, 4, 11, 2}, 4), // [2,3,4,5]
                new TestCase(generateLargeArray(100000), 1) // Large array with unique elements
        };

        // Execute test cases
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int result = LongestConsecutive(tc.input);
            if (result == tc.expectedOutput) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expectedOutput + ", Got " + result + ")");
            }
        }
    }

    /**
     * Helper method to generate a large array of unique elements.
     *
     * @param size Size of the array.
     * @return Array of unique integers from 1 to size.
     */
    private static int[] generateLargeArray(int size) {
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = i + 1;
        }
        return largeArray;
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        int[] input;
        int expectedOutput;

        TestCase(int[] input, int expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }
}
