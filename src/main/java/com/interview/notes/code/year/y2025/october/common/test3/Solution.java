package com.interview.notes.code.year.y2025.october.common.test3;

import java.util.Arrays;
import java.util.List;

public class Solution {

    public static int solve(List<Integer> A, List<Integer> B) {
        if (A == null || B == null) return 0;

        int n = A.size(), m = B.size();
        if (n == 0 && m == 0) return 0;

        // Determine order (both roads are in the same order per problem)
        boolean ascending;
        if (n > 1) ascending = A.get(0) <= A.get(n - 1);
        else if (m > 1) ascending = B.get(0) <= B.get(m - 1);
        else ascending = true; // singletons/empties – doesn't matter

        // Direction-aware pointers (no in-place reverse; works for immutable lists)
        int i = ascending ? 0 : n - 1;
        int j = ascending ? 0 : m - 1;
        int step = ascending ? 1 : -1;

        long sumA = 0, sumB = 0, result = 0;

        while ((i >= 0 && i < n) && (j >= 0 && j < m)) {
            int a = A.get(i);
            int b = B.get(j);

            if (a == b) {
                result += Math.max(sumA, sumB) + a; // take one bucket at the junction
                sumA = 0;
                sumB = 0;
                i += step;
                j += step;
            } else if (a < b) {
                sumA += a;
                i += step;
            } else {
                sumB += b;
                j += step;
            }
        }

        // Add remaining tails
        while (i >= 0 && i < n) {
            sumA += A.get(i);
            i += step;
        }
        while (j >= 0 && j < m) {
            sumB += B.get(j);
            j += step;
        }

        result += Math.max(sumA, sumB);

        // Prevent int overflow wraparound (tests typically fit in int; this keeps it safe)
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) result;
    }

    public static void main(String[] args) {
        // Given examples
        List<Integer> A1 = Arrays.asList(2, 6, 8, 9);
        List<Integer> B1 = Arrays.asList(1, 4, 5, 6);
        System.out.println("Output 1: " + solve(A1, B1)); // 33

        List<Integer> A2 = Arrays.asList(8, 7, 6, 5, 2, 1);
        List<Integer> B2 = Arrays.asList(9, 8, 7, 4, 2);
        System.out.println("Output 2: " + solve(A2, B2)); // 38

        // Extra edge checks
        System.out.println("Descending + immutable: " + solve(List.of(9, 8, 6, 5), List.of(7, 6, 3))); // works w/ List.of
        System.out.println("All equal (different lengths): " + solve(List.of(1, 1, 1), List.of(1, 1))); // 3
        System.out.println("One empty: " + solve(List.of(), List.of(1, 2, 3))); // 6
    }
}