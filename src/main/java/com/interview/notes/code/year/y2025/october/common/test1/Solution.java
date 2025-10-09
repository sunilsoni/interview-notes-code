package com.interview.notes.code.year.y2025.october.common.test1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static int solve(List<Integer> A, List<Integer> B) {
        // Ensure both lists are sorted in ascending order
        if (A.size() > 1 && A.get(0) > A.get(A.size() - 1))
            Collections.reverse(A);
        if (B.size() > 1 && B.get(0) > B.get(B.size() - 1))
            Collections.reverse(B);

        int i = 0, j = 0;
        int n = A.size(), m = B.size();
        long sumA = 0, sumB = 0, result = 0;

        while (i < n && j < m) {
            if (A.get(i).equals(B.get(j))) {
                result += Math.max(sumA, sumB) + A.get(i);
                sumA = 0;
                sumB = 0;
                i++;
                j++;
            } else if (A.get(i) < B.get(j)) {
                sumA += A.get(i++);
            } else {
                sumB += B.get(j++);
            }
        }

        while (i < n) sumA += A.get(i++);
        while (j < m) sumB += B.get(j++);
        result += Math.max(sumA, sumB);

        return (int) result;
    }

    public static void main(String[] args) {
        List<Integer> A1 = Arrays.asList(2, 6, 8, 9);
        List<Integer> B1 = Arrays.asList(1, 4, 5, 6);
        System.out.println("Output 1: " + solve(A1, B1)); // ✅ 33

        List<Integer> A2 = Arrays.asList(8, 7, 6, 5, 2, 1);
        List<Integer> B2 = Arrays.asList(9, 8, 7, 4, 2);
        System.out.println("Output 2: " + solve(A2, B2)); // ✅ 38
    }
}