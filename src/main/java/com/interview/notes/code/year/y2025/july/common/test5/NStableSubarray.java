package com.interview.notes.code.year.y2025.july.common.test5;

import java.util.*;
import java.util.stream.*;

public class NStableSubarray {

    // Function to find the longest N-stable subarray length
    public static int longestNStableSubarray(int[] arr, int N) {
        // TreeMap to maintain counts of each element in current window,
        // allows us to get current min and max in O(log n) time
        TreeMap<Integer, Integer> window = new TreeMap<>();

        int left = 0; // Left boundary of window
        int maxLen = 0; // Track max length found

        // Iterate over array as right boundary
        for (int right = 0; right < arr.length; right++) {
            // Add current element to window counts
            window.put(arr[right], window.getOrDefault(arr[right], 0) + 1);

            // While the window is not N-stable, move left boundary right
            while (window.lastKey() - window.firstKey() > N) {
                // Remove leftmost element from window counts
                int count = window.get(arr[left]);
                if (count == 1) {
                    window.remove(arr[left]); // Remove completely if last occurrence
                } else {
                    window.put(arr[left], count - 1); // Otherwise just decrement count
                }
                left++; // Move left boundary right
            }

            // Update maximum window length found so far
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Main method to run all test cases
    public static void main(String[] args) {
        // Helper function for test output and pass/fail check
        // Each test: arr, N, expected
        List<TestCase> tests = Arrays.asList(
            new TestCase(new int[]{4,2,3,6,2,2,3,2,7}, 1, 4), // Example 1
            new TestCase(new int[]{8,2,4,7}, 4, 2),           // Example 2
            new TestCase(new int[]{1,3,4,5,6,7,8,9,9,9}, 7, 9), // Example 3
            new TestCase(new int[]{1,1,1,1,1}, 0, 5),         // All same, N=0
            new TestCase(new int[]{10, 20, 30, 40, 50}, 5, 1),// No subarray longer than 1
            new TestCase(IntStream.range(0, 100_000).toArray(), 1_000_000, 100_000), // Large data, large N
            new TestCase(IntStream.range(0, 100_000).map(i -> (i % 10)).toArray(), 9, 100_000) // Large, all in range
        );

        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            int result = longestNStableSubarray(tc.arr, tc.N);
            System.out.printf("Test %d: Expected=%d, Got=%d -- %s\n",
                i+1, tc.expected, result, result == tc.expected ? "PASS" : "FAIL");
            if (result == tc.expected) passed++;
        }
        System.out.printf("Passed %d/%d tests\n", passed, tests.size());
    }

    // Helper class for tests
    static class TestCase {
        int[] arr;
        int N;
        int expected;
        TestCase(int[] arr, int N, int expected) {
            this.arr = arr;
            this.N = N;
            this.expected = expected;
        }
    }
}
