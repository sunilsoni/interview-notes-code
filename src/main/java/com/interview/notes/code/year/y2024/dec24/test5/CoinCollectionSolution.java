package com.interview.notes.code.year.y2024.dec24.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//WORKING  BUT 2 Test caes failing..
public class CoinCollectionSolution {

    public static int solve(List<Integer> A, List<Integer> B) {
        // Check order (assume both sorted in same order)
        if (!A.isEmpty()) {
            boolean ascending = A.get(0) < A.get(A.size() - 1);
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
                // intersection found
                sumA += aVal;
                sumB += bVal;
                result += Math.max(sumA, sumB);
                sumA = 0;
                sumB = 0;
                i++;
                j++;
            }
        }

        // Add remaining elements
        while (i < A.size()) sumA += A.get(i++);
        while (j < B.size()) sumB += B.get(j++);

        result += Math.max(sumA, sumB);
        return (int) result;
    }

    public static void main(String[] args) {
        // Test #1
        List<Integer> A1 = Arrays.asList(2, 6, 8, 9);
        List<Integer> B1 = Arrays.asList(1, 4, 5, 6);
        int expected1 = 33;
        int result1 = solve(new ArrayList<>(A1), new ArrayList<>(B1));
        System.out.println("Test #1: " + (result1 == expected1 ? "Passed" : "Failed - got " + result1));

        // Test #2
        // Given arrays in descending order; problem states they're the same order.
        // We'll reverse them internally.
        List<Integer> A2 = Arrays.asList(8, 7, 6, 5, 2, 1);
        List<Integer> B2 = Arrays.asList(9, 8, 7, 4, 2);
        int expected2 = 38;
        int result2 = solve(new ArrayList<>(A2), new ArrayList<>(B2));
        System.out.println("Test #2: " + (result2 == expected2 ? "Passed" : "Failed - got " + result2));

        // Additional edge test: empty array
        List<Integer> A3 = Collections.emptyList();
        List<Integer> B3 = Arrays.asList(1, 2, 3);
        int expected3 = 6;
        int result3 = solve(new ArrayList<>(A3), new ArrayList<>(B3));
        System.out.println("Test #3 (empty A): " + (result3 == expected3 ? "Passed" : "Failed - got " + result3));

        // No intersection
        List<Integer> A4 = Arrays.asList(1, 2, 3);
        List<Integer> B4 = Arrays.asList(4, 5, 6);
        // max of sum A=6 or sum B=15 is 15
        int expected4 = 15;
        int result4 = solve(new ArrayList<>(A4), new ArrayList<>(B4));
        System.out.println("Test #4 (no intersection): " + (result4 == expected4 ? "Passed" : "Failed - got " + result4));
    }
}
