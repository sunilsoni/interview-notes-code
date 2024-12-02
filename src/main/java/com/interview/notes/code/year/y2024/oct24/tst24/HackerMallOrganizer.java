package com.interview.notes.code.year.y2024.oct24.tst24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HackerMallOrganizer {

    public static int getMinimumOperations(List<Integer> items) {
        int operations = 0;
        int n = items.size();
        boolean expectOdd = true;  // Start expecting odd

        for (int i = 0; i < n; i++) {
            int current = items.get(i);
            boolean isOdd = current % 2 == 1;

            if (isOdd != expectOdd) {
                operations += countOperations(current);
            }

            expectOdd = !expectOdd;  // Alternate expectation
        }

        return operations;
    }

    private static int countOperations(int num) {
        if (num % 2 == 1) {  // If odd, we need to make it even
            return 1;
        } else {  // If even, count trailing zeros
            return Integer.numberOfTrailingZeros(num);
        }
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = new ArrayList<>();
        testCases.add(Arrays.asList(6, 5, 4, 7, 3));
        testCases.add(Arrays.asList(2, 1, 4, 7, 2));
        testCases.add(Arrays.asList(4, 10, 10, 6, 2));
        testCases.add(Arrays.asList(1, 1, 1, 1, 1));
        testCases.add(Arrays.asList(1000000000, 1000000000, 1000000000, 1000000000));

        int[] expectedResults = {3, 0, 2, 2, 60};

        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> testCase = testCases.get(i);
            int result = getMinimumOperations(testCase);
            boolean passed = result == expectedResults[i];

            System.out.println("Test Case " + (i + 1) + ": " +
                    (passed ? "PASS" : "FAIL") +
                    " (Expected: " + expectedResults[i] +
                    ", Got: " + result + ")");
        }

        // Large input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add((int) (Math.random() * 1000000000) + 1);
        }
        long startTime = System.currentTimeMillis();
        int largeResult = getMinimumOperations(largeInput);
        long endTime = System.currentTimeMillis();

        System.out.println("\nLarge Input Test (100,000 elements):");
        System.out.println("Result: " + largeResult);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
