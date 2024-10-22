package com.interview.notes.code.months.oct24.amz.test10;

import java.util.*;

class Outcome {
    public static List<Long> solve(int d, int k) {
        if (d <= 0 || k <= 0 || d > 100 || k > 900) {
            return Arrays.asList(-1L, -1L);
        }

        long smallest = findSmallest(d, k);
        long largest = findLargest(d, k);

        if (smallest == -1 || largest == -1) {
            return Arrays.asList(-1L, -1L);
        }

        return Arrays.asList(smallest, largest);
    }

    private static long findSmallest(int d, int k) {
        long start = (long) Math.pow(10, d - 1);
        long end = (long) Math.pow(10, d) - 1;

        for (long i = start; i <= end; i++) {
            if (sumOfDigits(i) == k) {
                return i;
            }
        }
        return -1;
    }

    private static long findLargest(int d, int k) {
        long start = (long) Math.pow(10, d) - 1;
        long end = (long) Math.pow(10, d - 1);

        for (long i = start; i >= end; i--) {
            if (sumOfDigits(i) == k) {
                return i;
            }
        }
        return -1;
    }

    private static int sumOfDigits(long num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        testCases();
    }

    private static void testCases() {
        // Test case 1
        assertResult(2, 2, 11, 20);

        // Test case 2
        assertResult(2, 1, 10, 10);

        // Edge cases
        assertResult(1, 9, 9, 9);
        assertResult(3, 27, 999, 999);

        // Invalid inputs
        assertResult(0, 5, -1, -1);
        assertResult(5, 0, -1, -1);
        assertResult(101, 5, -1, -1);
        assertResult(5, 901, -1, -1);

        // Large inputs
       // assertResult(100, 900, 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999L,
          //                9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999L);

        System.out.println("All test cases passed!");
    }

    private static void assertResult(int d, int k, long expectedSmallest, long expectedLargest) {
        List<Long> result = solve(d, k);
        if (result.get(0) != expectedSmallest || result.get(1) != expectedLargest) {
            throw new AssertionError(String.format("Test case failed for d=%d, k=%d. Expected: [%d, %d], Got: [%d, %d]", 
                d, k, expectedSmallest, expectedLargest, result.get(0), result.get(1)));
        }
    }
}
