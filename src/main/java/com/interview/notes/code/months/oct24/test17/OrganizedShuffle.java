package com.interview.notes.code.months.oct24.test17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrganizedShuffle {

    // Main method for testing
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase(Arrays.asList(3, 4, 5, 6, 7, 10, 2), 10, 5),
                new TestCase(Arrays.asList(20, 33, 44, 1), 11, -1),
                new TestCase(Arrays.asList(1, 2, 3, 4, 5, 6), 4, 3),
                new TestCase(Arrays.asList(5, 6, 7, 1, 2, 3, 4), 3, 5),
                new TestCase(Arrays.asList(10), 10, 0) // Single element
        );

        // Run all test cases
        for (TestCase testCase : testCases) {
            int result = solve(testCase.array, testCase.k);
            if (result == testCase.expected) {
                System.out.println("PASS for input " + testCase.array);
            } else {
                System.out.println("FAIL for input " + testCase.array + ". Expected " + testCase.expected + " but got " + result);
            }
        }

        // Large input test case
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(i + 1);
        }
        System.out.println("Testing large input...");
        long startTime = System.currentTimeMillis();
        int largeTestResult = solve(largeInput, 99999);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input result: " + (largeTestResult == 99998 ? "PASS" : "FAIL"));
        System.out.println("Time taken for large input: " + (endTime - startTime) + " ms");
    }

    // Function to solve the problem using binary search
    public static int solve(List<Integer> ar, int K) {
        int low = 0;
        int high = ar.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (ar.get(mid) == K) {
                return mid;
            }

            // Determine which side is sorted
            if (ar.get(low) <= ar.get(mid)) {
                // Left part is sorted
                if (K >= ar.get(low) && K < ar.get(mid)) {
                    high = mid - 1; // K lies in the left part
                } else {
                    low = mid + 1; // K lies in the right part
                }
            } else {
                // Right part is sorted
                if (K > ar.get(mid) && K <= ar.get(high)) {
                    low = mid + 1; // K lies in the right part
                } else {
                    high = mid - 1; // K lies in the left part
                }
            }
        }

        return -1; // Element not found
    }
}

// Helper class for test cases
class TestCase {
    List<Integer> array;
    int k;
    int expected;

    public TestCase(List<Integer> array, int k, int expected) {
        this.array = array;
        this.k = k;
        this.expected = expected;
    }
}
