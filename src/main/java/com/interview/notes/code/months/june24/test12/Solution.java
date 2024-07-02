package com.interview.notes.code.months.june24.test12;

public class Solution {
    /**
     * Returns the second smallest element in array x.
     * If x has fewer than 2 elements, returns 0.
     */
    public static int secondSmallest(int[] x) {
        if (x.length < 2) {
            return 0;
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int value : x) {
            if (value < smallest) {
                secondSmallest = smallest;
                smallest = value;
            } else if (value < secondSmallest && value != smallest) {
                secondSmallest = value;
            }
        }

        return secondSmallest;
    }

    /**
     * Runs various tests. Returns true if tests pass. Otherwise, returns false.
     */
    public static boolean doTestsPass() {
        boolean result = true;
        int[] a = {0}; // Test case with fewer than 2 elements
        int[] b = {0, 1}; // Test case with exactly 2 elements
        int[] c = {1, 2, 3, 4, 5}; // Test case with more than 2 elements
        int[] d = {5, 5, 5, 1}; // Test case where elements are the same except one
        int[] e = {Integer.MAX_VALUE, Integer.MIN_VALUE}; // Test case with extreme values

        result &= secondSmallest(a) == 0;
        result &= secondSmallest(b) == 1;
        result &= secondSmallest(c) == 2;
        result &= secondSmallest(d) == 5;
        result &= secondSmallest(e) == Integer.MAX_VALUE;

        if (result) {
            System.out.println("All tests pass.");
        } else {
            System.out.println("There are test failures.");
        }
        return result;
    }

    /**
     * Execution entry point.
     */
    public static void main(String[] args) {
        doTestsPass();
    }
}
