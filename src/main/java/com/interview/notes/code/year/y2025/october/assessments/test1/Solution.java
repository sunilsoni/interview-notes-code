package com.interview.notes.code.year.y2025.october.assessments.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static int solve(List<Integer> A, List<Integer> B) {
        int i = 0, j = 0, n = A.size(), m = B.size();
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
        System.out.println(solve(A1, B1)); // Expected 33

        List<Integer> A2 = Arrays.asList(8, 7, 6, 5, 2, 1);
        List<Integer> B2 = Arrays.asList(9, 8, 7, 4, 2);
        System.out.println(solve(A2, B2)); // Expected 38

        List<Integer> A3 = Arrays.asList(1, 2, 3);
        List<Integer> B3 = Arrays.asList(3, 4, 5);
        System.out.println(solve(A3, B3)); // Expected 12

        List<Integer> A4 = Arrays.asList(1, 2, 3, 4);
        List<Integer> B4 = Arrays.asList(10, 11, 12);
        System.out.println(solve(A4, B4)); // Expected 37

        List<Integer> A5 = new ArrayList<>();
        List<Integer> B5 = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            A5.add(i);
            B5.add(i);
        }
        System.out.println(solve(A5, B5)); // Large data test
    }
}
