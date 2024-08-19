package com.interview.notes.code.months.aug24.amz.test15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

    public static String sortPermutation(List<Integer> p, List<Integer> moves) {
        StringBuilder result = new StringBuilder();
        int n = p.size();

        for (int move : moves) {
            if (canSort(new ArrayList<>(p), move)) {
                result.append("1");
            } else {
                result.append("0");
            }
        }

        return result.toString();
    }

    private static boolean canSort(List<Integer> p, int moves) {
        int inversions = countInversions(p);
        return inversions <= moves && (inversions % 2 == moves % 2);
    }

    private static int countInversions(List<Integer> p) {
        int inversions = 0;
        int n = p.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (p.get(i) > p.get(j)) {
                    inversions++;
                }
            }
        }
        return inversions;
    }

    public static void main(String[] args) {
        // Test Case 0
        List<Integer> p0 = Arrays.asList(4, 5, 1, 3, 2);
        List<Integer> moves0 = Arrays.asList(1, 2, 3);
        System.out.println(sortPermutation(p0, moves0));  // Expected: 001 but actual is 000

        // Test Case 1
        List<Integer> p1 = Arrays.asList(3, 1, 2);
        List<Integer> moves1 = Arrays.asList(2, 4);
        System.out.println(sortPermutation(p1, moves1));  // Expected: 11

        // Additional Test Case
        List<Integer> p2 = Arrays.asList(2, 3, 1, 4);
        List<Integer> moves2 = Arrays.asList(2, 3);
        System.out.println(sortPermutation(p2, moves2));  // Expected: 10
    }
}
