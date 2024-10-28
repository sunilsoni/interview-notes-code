package com.interview.notes.code.months.oct24.tst24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution class containing methods to organize the array with minimum operations.
 */
public class Solution2 {

    /**
     * Computes the minimum number of operations needed to organize the array.
     *
     * @param items a list of integers representing the stacks of items.
     * @return the minimum number of operations required.
     */
    public static int getMinimumOperations(List<Integer> items) {
        int n = items.size();
        int costPattern1 = 0; // Starting with even parity at index 0
        int costPattern2 = 0; // Starting with odd parity at index 0

        for (int i = 0; i < n; i++) {
            int x = items.get(i);

            // Pattern 1: Even indices even, odd indices odd
            int desiredParity1 = i % 2;
            int cost1 = computeCost(x, desiredParity1);
            costPattern1 += cost1;

            // Pattern 2: Even indices odd, odd indices even
            int desiredParity2 = (i + 1) % 2;
            int cost2 = computeCost(x, desiredParity2);
            costPattern2 += cost2;
        }

        return Math.min(costPattern1, costPattern2);
    }

    /**
     * Computes the cost to change the parity of a number to the desired parity.
     *
     * @param x             the original number.
     * @param desiredParity the desired parity to achieve.
     * @return the minimum number of operations required to achieve the desired parity.
     */
    private static int computeCost(int x, int desiredParity) {
        if (x % 2 == desiredParity) {
            return 0;
        } else {
            if (desiredParity == 0) {
                // Changing odd to even takes 1 operation
                return 1;
            } else {
                // Changing even to odd takes numberOfTrailingZeros operations
                return Integer.numberOfTrailingZeros(x);
            }
        }
    }

    /**
     * Main method to run test cases and check if they pass or fail.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                Arrays.asList(6, 5, 4, 7, 3),
                3
        ));

        // Example 2
        testCases.add(new TestCase(
                Arrays.asList(2, 1, 4, 7, 2),
                0
        ));

        // Example 3
        testCases.add(new TestCase(
                Arrays.asList(4, 10, 10, 6, 2),
                2
        ));

        // Additional test case: All elements have the same parity (even)
        int n = 10; // Adjust n for larger sizes
        List<Integer> allEvenItems = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allEvenItems.add(2);
        }
        testCases.add(new TestCase(
                allEvenItems,
                5
        ));

        // Additional test case: All elements have the same parity (odd)
        List<Integer> allOddItems = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allOddItems.add(3);
        }
        testCases.add(new TestCase(
                allOddItems,
                5
        ));

        // Large input test case: Alternating parities (already organized)
        int largeN = 100000;
        List<Integer> largeItems = new ArrayList<>(largeN);
        for (int i = 0; i < largeN; i++) {
            largeItems.add(i % 2 == 0 ? 2 : 3);
        }
        testCases.add(new TestCase(
                largeItems,
                0
        ));

        // Run test cases
        int testNumber = 1;
        for (TestCase testCase : testCases) {
            int result = getMinimumOperations(testCase.items);
            if (result == testCase.expected) {
                System.out.println("Test Case " + testNumber + ": PASS");
            } else {
                System.out.println("Test Case " + testNumber + ": FAIL");
                System.out.println("Expected: " + testCase.expected + ", Got: " + result);
            }
            testNumber++;
        }
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        List<Integer> items;
        int expected;

        public TestCase(List<Integer> items, int expected) {
            this.items = items;
            this.expected = expected;
        }
    }
}
