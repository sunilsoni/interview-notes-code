package com.interview.notes.code.year.y2025.october.common.test8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Main {

    // Method to find the duplicated entry in array
    private static int findDuplicate(int[] arr) {
        // Create a set to track seen elements
        Set<Integer> seen = new HashSet<>();
        // Stream through array and find first element already seen
        return Arrays.stream(arr)
                .filter(n -> !seen.add(n)) // add() returns false if already present
                .findFirst()
                .orElse(-1); // Return -1 if no duplicate found
    }

    public static void main(String[] args) {

        // ------------------- TESTING SECTION -------------------
        // Each test case in form of "input", expectedOutput
        List<TestCase> tests = Arrays.asList(
                new TestCase("5;0,1,2,3,2", 2),
                new TestCase("6;0,1,2,4,3,4", 4),
                new TestCase("4;0,1,1,2", 1),
                new TestCase("3;0,2,2", 2),
                new TestCase("2;0,0", 0)
        );

        // Run all test cases
        tests.forEach(t -> {
            long start = System.currentTimeMillis();
            int result = runTest(t.input);
            long end = System.currentTimeMillis();
            String status = result == t.expected ? "PASS" : "FAIL";
            System.out.printf("Input=%s | Output=%d | Expected=%d | Result=%s | Time=%d ms%n",
                    t.input, result, t.expected, status, (end - start));
        });

        // --------------- LARGE DATA TEST CASE ------------------
        int n = 1_000_00; // Large array with 100000 elements
        int[] largeArray = IntStream.range(0, n - 1).toArray();
        largeArray[n - 2] = 99999; // Introduce duplicate
        long startLarge = System.currentTimeMillis();
        int largeResult = findDuplicate(largeArray);
        long endLarge = System.currentTimeMillis();
        System.out.printf("Large Data Test | Output=%d | Expected=99999 | Time=%d ms%n",
                largeResult, (endLarge - startLarge));
    }

    // Helper method: parse input and find duplicate
    private static int runTest(String input) {
        // Split into size and numbers
        String[] parts = input.split(";");
        // Parse numbers
        int[] arr = Arrays.stream(parts[1].split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        // Find duplicate using helper
        return findDuplicate(arr);
    }

    // Inner class to hold test case
    static class TestCase {
        String input;
        int expected;

        TestCase(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
