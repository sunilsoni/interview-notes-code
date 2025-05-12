package com.interview.notes.code.year.y2025.may.amazon.test5;

import java.util.*;

//14/15 working

public class AmazonAcademyScholarship {

    public static long findMinimumDays(List<Integer> pages, int k, int p) {
        int n = pages.size();
        long low = 1, high = (long)1e12;

        while (low < high) {
            long mid = (low + high) / 2;
            if (isPossible(pages, k, p, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static boolean isPossible(List<Integer> pages, int k, int p, long days) {
        int n = pages.size();
        long[] diff = new long[n + 1];
        long sum = 0, needed = 0;

        for (int i = 0; i < n; i++) {
            sum += diff[i];
            long remaining = pages.get(i) - sum;
            if (remaining > 0) {
                long cnt = (remaining + p - 1) / p;
                needed += cnt;
                if (needed > days) return false;
                sum += cnt * p;
                if (i + k < n) {
                    diff[i + k] -= cnt * p;
                }
            }
        }

        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        test(Arrays.asList(3, 4), 1, 2, 4);  // Expected output: 4
        test(Arrays.asList(5, 1, 2), 3, 3, 2);  // Expected output: 2
        test(Arrays.asList(3, 1, 4), 2, 2, 4);  // Expected output: 4

        // Large test case
        List<Integer> largeTest = new ArrayList<>(Collections.nCopies(100000, 1000000000));
        test(largeTest, 100000, 1000000000, 1); // Expected output: 1
    }

    private static void test(List<Integer> pages, int k, int p, long expected) {
        long result = findMinimumDays(pages, k, p);
        if (result == expected) {
            System.out.println("PASS: " + result);
        } else {
            System.out.println("FAIL: Expected " + expected + " but got " + result);
        }
    }
}