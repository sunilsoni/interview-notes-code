package com.interview.notes.code.year.y2025.feb25.Amazon.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DebugSolution {

    /**
     * maximizeGroups:
     * Given a list of product counts (each representing how many items are available for a product),
     * we want to form batches such that the 1st batch uses 1 item (from some product),
     * the 2nd uses 2 items (each from a distinct product), etc.
     * <p>
     * A candidate m (number of batches) is feasible if we can “pay” for batches 1,2,…,m,
     * meaning that the total available contribution (where each product i can contribute at most m items)
     * is at least m(m+1)/2. That is:
     * <p>
     * sum( min(products[i], m) for all i ) >= m*(m+1)/2.
     * <p>
     * We use binary search over m (with an upper bound of roughly 2*n, where n is the number of product types).
     */
    public static int maximizeGroups(List<Integer> products) {
        int n = products.size();
        // Sort the products so that we can later compute contributions quickly.
        Collections.sort(products);

        // Build a prefix sum array for the sorted list.
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + products.get(i);
        }

        int left = 0;
        int right = 2 * n; // safe upper bound for m
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long totalContribution = computeContribution(products, prefix, mid);
            long required = ((long) mid * (mid + 1)) / 2;

            // Debug logging: show candidate m, totalContribution, and required.
            // (Comment out these lines if you do not want logging.)
            System.out.println("Candidate m = " + mid +
                    ", totalContribution = " + totalContribution +
                    ", required = " + required);

            if (totalContribution >= required) {
                answer = mid;
                left = mid + 1;  // try to see if we can form more groups
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    /**
     * computeContribution:
     * Given the sorted products list and its prefix sum, compute:
     * sum( min(products[i], m) for all i )
     */
    private static long computeContribution(List<Integer> products, long[] prefix, int m) {
        int n = products.size();
        // Find the first index at which products[i] > m.
        int idx = upperBound(products, m);
        // For indices i < idx, products[i] <= m, so their full value is counted.
        long sum = prefix[idx];
        // For indices i >= idx, each product contributes exactly m.
        sum += (long) (n - idx) * m;
        return sum;
    }

    /**
     * upperBound:
     * Returns the first index in the sorted list where the element is greater than m.
     */
    private static int upperBound(List<Integer> list, int m) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid) <= m) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /**
     * runTest:
     * Runs a single test case and prints whether it passed (if the result equals the expected value).
     */
    public static void runTest(int testId, List<Integer> products, int expected) {
        int result = maximizeGroups(products);
        if (result == expected) {
            System.out.println("Test " + testId + " PASSED. Expected and Got: " + result);
        } else {
            System.out.println("Test " + testId + " FAILED. Expected: " + expected + ", Got: " + result);
        }
    }

    /**
     * main:
     * Runs sample test cases as well as the long test cases.
     * (For the long lists, please confirm what the expected output should be.)
     */
    public static void main(String[] args) {
        // Sample Case 0: Expected 3
        List<Integer> test0 = Arrays.asList(1, 2, 7);
        runTest(0, test0, 3);

        // Sample Case 1: Expected 4
        List<Integer> test1 = Arrays.asList(1, 2, 8, 9);
        runTest(1, test1, 4);

        // Test Case A: [1, 1, 8, 11, 11, 12, 12]
        // Many solutions (and our binary search) yield 9 for this test.
        runTest(2, Arrays.asList(1, 1, 8, 11, 11, 12, 12), 9);

        // Test Case B (a long list – only a prefix is shown here)
        List<Integer> testB = Arrays.asList(
                46, 250, 77, 488, 212, 28, 379, 50, 332, 526308251, 51, 187563989, 32, 10, 217,
                445, 13, 69, 24, 647819135, 110, 32, 247259207, 34, 83, 137, 16, 476, 893922480,
                129933738, 184, 12, 230, 39, 43, 343, 91, 446, 62, 41, 418, 395741531, 157, 336,
                649211546, 38, 57, 80, 15703084, 221663445, 196105046, 789300684, 426686168, 39,
                63, 27, 63, 744605921, 66, 209, 83, 246, 50, 151949631, 398, 465, 365, 86, 483,
                216, 899536810, 33, 202, 46, 63, 336, 255, 338, 213, 597758591, 45, 7285260, 9,
                716279149, 27, 444, 13, 416, 246314883, 88, 525321870, 144, 893362512, 83, 303, 41
                // ... (the actual test list is much longer)
        );
        // For this test case, please let us know what the expected answer is.
        System.out.println("Test 3 (long list B) computed result: " + maximizeGroups(testB));

        // Test Case C (another long list; only a prefix is shown)
        List<Integer> testC = Arrays.asList(
                825034897, 4678, 66007761, 2752, 20958099, 235, 232, 1871, 2505, 405, 677813320, 1336,
                528136025, 193, 1908, 282878987, 661734976, 1057, 571537554, 38, 4586, 4667, 61, 248, 144386994,
                350, 352, 614045834, 502797634, 6, 228, 2700, 4863, 463658498, 256121622, 2127, 507725013, 232,
                1036, 345, 486, 4697, 499605437, 69, 115, 3966, 272, 2752, 3128, 257, 138, 311, 179, 431, 876,
                992686950, 176, 143308701, 382, 37, 49, 46, 21, 773725809, 746442901, 2752, 204590451, 543, 311,
                49, 4103, 401, 350, 596, 1077, 435, 134412636, 301, 131, 159, 29, 276, 179, 140250814, 146, 1529
                // ... (the actual test list is much longer)
        );
        System.out.println("Test 4 (long list C) computed result: " + maximizeGroups(testC));
    }
}