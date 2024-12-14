package com.interview.notes.code.months.dec24.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CoinCollectionSolution1 {

    public static int solve(List<Integer> A, List<Integer> B) {
        // Check order and reverse if descending
        if (!A.isEmpty() && !B.isEmpty()) {
            boolean ascending = A.get(0) < A.get(A.size() - 1);
            // If not ascending, then it's descending; reverse both
            if (!ascending) {
                Collections.reverse(A);
                Collections.reverse(B);
            }
        }

        int i = 0, j = 0;
        long sumA = 0, sumB = 0, result = 0;

        while (i < A.size() && j < B.size()) {
            int aVal = A.get(i);
            int bVal = B.get(j);
            if (aVal < bVal) {
                sumA += aVal;
                i++;
            } else if (aVal > bVal) {
                sumB += bVal;
                j++;
            } else {
                // intersection
                sumA += aVal;
                sumB += bVal;
                result += Math.max(sumA, sumB);
                sumA = 0;
                sumB = 0;
                i++;
                j++;
            }
        }

        while (i < A.size()) sumA += A.get(i++);
        while (j < B.size()) sumB += B.get(j++);

        result += Math.max(sumA, sumB);
        return (int) result;
    }

    public static void main(String[] args) {
        // Test #1 (from provided example)
        List<Integer> A1 = Arrays.asList(2, 6, 8, 9);
        List<Integer> B1 = Arrays.asList(1, 4, 5, 6);
        int expected1 = 33;
        int result1 = solve(new ArrayList<>(A1), new ArrayList<>(B1));
        System.out.println("Test #1: " + (result1 == expected1 ? "Passed" : "Failed - got " + result1));

        // Test #2 (from provided example)
        List<Integer> A2 = Arrays.asList(8, 7, 6, 5, 2, 1);
        List<Integer> B2 = Arrays.asList(9, 8, 7, 4, 2);
        int expected2 = 38;
        int result2 = solve(new ArrayList<>(A2), new ArrayList<>(B2));
        System.out.println("Test #2: " + (result2 == expected2 ? "Passed" : "Failed - got " + result2));
    }
}
