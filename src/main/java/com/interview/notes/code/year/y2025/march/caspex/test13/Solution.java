package com.interview.notes.code.year.y2025.march.caspex.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    // Example method: Sum of integers using Java 8 Streams.
    public static int processData(List<Integer> data) {
        return data.stream().mapToInt(Integer::intValue).sum();
    }

    // Test method to check PASS/FAIL.
    public static boolean runTest(String testName, List<Integer> input, int expected) {
        int result = processData(input);
        boolean passed = result == expected;
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        return passed;
    }

    // Main method for testing.
    public static void main(String[] args) {
        boolean allPassed = true;

        // Test Case 1: Normal data
        allPassed &= runTest("Test Case 1", Arrays.asList(1, 2, 3), 6);

        // Test Case 2: Empty list
        allPassed &= runTest("Test Case 2", new ArrayList<>(), 0);

        // Test Case 3: Single element
        allPassed &= runTest("Test Case 3", List.of(5), 5);

        // Test Case 4: Large data input
        List<Integer> largeData = IntStream.rangeClosed(1, 1000000)
                .boxed()
                .collect(Collectors.toList());
        int expectedSum = (1000000 * (1000000 + 1)) / 2;
        allPassed &= runTest("Test Case 4", largeData, expectedSum);

        System.out.println("All tests " + (allPassed ? "passed." : "failed."));
    }
}
