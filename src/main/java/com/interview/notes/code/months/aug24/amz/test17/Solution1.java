package com.interview.notes.code.months.aug24.amz.test17;

public class Solution1 {

    /**
     * int secondSmallest(int[] x)
     * Returns second smallest element in array x. If x has fewer than 2 elements returns 0.
     */
    public static int secondSmallest(int[] x) {
        if (x == null || x.length < 2) {
            return 0; // If there are fewer than 2 elements, return 0
        }

        int firstSmallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        // Iterate through the array to find the first and second smallest elements
        for (int num : x) {
            if (num < firstSmallest) {
                secondSmallest = firstSmallest;
                firstSmallest = num;
            } else if (num < secondSmallest && num != firstSmallest) {
                secondSmallest = num;
            }
        }

        return secondSmallest == Integer.MAX_VALUE ? 0 : secondSmallest;
    }

    /**
     * bool doTestsPass()
     * Runs various tests. Returns true if tests pass. Otherwise, returns false.
     */
    public static boolean doTestsPass() {
        // Test cases
        int[] a = {0};
        int[] b = {0, 1};
        int[] c = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] d = {-2, -1, 0, 1, 2};
        int[] e = {5, 5, 5, 5, 5};
        int[] f = {2};

        boolean result = true;
        result &= secondSmallest(a) == 0; // Expected: 0 (array too small)
        result &= secondSmallest(b) == 1; // Expected: 1
        result &= secondSmallest(c) == 2; // Expected: 2
        result &= secondSmallest(d) == -1; // Expected: -1
        result &= secondSmallest(e) == 0; // Expected: 0 (no distinct second smallest)
        result &= secondSmallest(f) == 0; // Expected: 0 (array too small)

        if (result) {
            System.out.println("All tests pass\n");
        } else {
            System.out.println("There are test failures\n");
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
