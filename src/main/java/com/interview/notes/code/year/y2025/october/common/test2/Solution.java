package com.interview.notes.code.year.y2025.october.common.test2;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int solve(List<Integer> A, List<Integer> B) {
        int i = 0, j = 0;
        long sumA = 0, sumB = 0, result = 0;

        while (i < A.size() && j < B.size()) {
            if (A.get(i) < B.get(j)) {
                sumA += A.get(i++);
            } else if (A.get(i) > B.get(j)) {
                sumB += B.get(j++);
            } else {
                // Common element found
                result += Math.max(sumA, sumB) + A.get(i);
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
        List<Integer> A1 = Arrays.asList(2, 6, 8, 9);
        List<Integer> B1 = Arrays.asList(1, 4, 5, 6);
        System.out.println(solve(A1, B1)); // ✅ Expected: 33

        List<Integer> A2 = Arrays.asList(8, 7, 6, 5, 2, 1);
        List<Integer> B2 = Arrays.asList(9, 8, 7, 4, 2);
        System.out.println(solve(A2, B2)); // ✅ Expected: 38
    }
}
