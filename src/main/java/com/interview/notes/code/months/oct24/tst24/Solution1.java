package com.interview.notes.code.months.oct24.tst24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
    /*
     * Complete the 'getMinimumOperations' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY items as parameter.
     */
    public static int getMinimumOperations(List<Integer> items) {
        int n = items.size();
        // Initialize previous even and odd operation counts
        long prevEven = getOpsToEven(items.get(0));
        long prevOdd = getOpsToOdd(items.get(0));

        for (int i = 1; i < n; i++) {
            int x = items.get(i);
            // Current even and odd operation counts
            long currEven = Long.MAX_VALUE;
            long currOdd = Long.MAX_VALUE;

            // If previous was even, current must be odd
            if (prevEven != Long.MAX_VALUE) {
                long opsToOdd = getOpsToOdd(x);
                if (opsToOdd != Long.MAX_VALUE) {
                    currOdd = Math.min(currOdd, prevEven + opsToOdd);
                }
            }

            // If previous was odd, current must be even
            if (prevOdd != Long.MAX_VALUE) {
                long opsToEven = getOpsToEven(x);
                if (opsToEven != Long.MAX_VALUE) {
                    currEven = Math.min(currEven, prevOdd + opsToEven);
                }
            }

            // Update previous for next iteration
            prevEven = currEven;
            prevOdd = currOdd;
        }

        // Return the minimum of the two possibilities for the last element
        long result = Math.min(prevEven, prevOdd);
        return result == Long.MAX_VALUE ? -1 : (int) result;
    }

    // Function to get operations to make x even
    private static int getOpsToEven(int x) {
        if (x % 2 == 0) {
            return 0;
        }
        int ops = 0;
        while (x > 0 && x % 2 != 0) {
            x = x / 2;
            ops++;
        }
        if (x == 0) {
            // Made it even in ops operations
            return ops;
        }
        return ops;
    }

    // Function to get operations to make x odd
    private static int getOpsToOdd(int x) {
        if (x % 2 == 1) {
            return 0;
        }
        if (x == 0) {
            // Impossible to make 0 odd
            return Integer.MAX_VALUE;
        }
        int ops = 0;
        while (x > 0 && x % 2 == 0) {
            x = x / 2;
            ops++;
        }
        if (x > 0 && x % 2 == 1) {
            return ops;
        }
        // If x becomes 0, it's impossible
        return Integer.MAX_VALUE;
    }

    // Test method
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example1
        testCases.add(new TestCase(Arrays.asList(6, 5, 4, 7, 3), 2));

        // Example2
        testCases.add(new TestCase(Arrays.asList(2, 1, 4, 7, 2), 0));

        // Example3
        testCases.add(new TestCase(Arrays.asList(4, 10, 10, 6, 2), 2));

        // Additional Test Cases

        // TestCase4: Single element, already organized
        testCases.add(new TestCase(Arrays.asList(1), 0));

        // TestCase5: Single element, needs to be even
        testCases.add(new TestCase(Arrays.asList(3), 2));

        // TestCase6: All even, need to adjust every second element
        testCases.add(new TestCase(Arrays.asList(2, 4, 6, 8, 10), 3));

        // TestCase7: All odd, need to adjust every second element
        testCases.add(new TestCase(Arrays.asList(1, 3, 5, 7, 9), 4));

        // TestCase8: Mixed, already organized
        testCases.add(new TestCase(Arrays.asList(2, 3, 4, 5, 6), 0));

        // TestCase9: Large Input
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(i + 1); // 1 to 100000
        }
        testCases.add(new TestCase(largeInput, computeExpectedLarge(largeInput)));

        // Execute test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = getMinimumOperations(tc.items);
            if (result == tc.expected) {
                System.out.println("TestCase" + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("TestCase" + (i + 1) + ": FAIL. Expected " + tc.expected + " but got " + result);
            }
        }
        System.out.println(passed + "/" + testCases.size() + " Test Cases Passed.");
    }

    // Helper method to compute expected operations for large input
    private static int computeExpectedLarge(List<Integer> items) {
        // For large input, it's time-consuming to compute expected manually.
        // Thus, return 0 as a placeholder. In real testing, precompute the expected value.
        return 0;
    }

    // TestCase class
    static class TestCase {
        List<Integer> items;
        int expected;

        TestCase(List<Integer> items, int expected) {
            this.items = items;
            this.expected = expected;
        }
    }
}
