package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question01 {

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {-2, 3, 2, -3},     // Balanced
                {-2, 2, 2, 2},      // Balanced
                {-5, 2, -2},        // Not Balanced
                {},                 // Empty array -> Balanced (trivially)
                {0, 0, 1, -1},      // Balanced (0 counts as its own negative)
                {1, 2, -1, -3}      // Not Balanced
        };

        for (int[] arr : testCases) {
            System.out.println(Arrays.toString(arr) + " -> " + (isBalanced(arr) == 1 ? "PASS" : "FAIL"));
        }

        // Large data test
        int[] large = IntStream.rangeClosed(1, 100000).flatMap(n -> IntStream.of(n, -n)).toArray();
        System.out.println("Large array test -> " + (isBalanced(large) == 1 ? "PASS" : "FAIL"));
    }

    public static int isBalanced(int[] a) {
        // Convert array to a Set for O(1) lookups
        Set<Integer> set = Arrays.stream(a).boxed().collect(Collectors.toSet());

        // Check if for every n, -n exists in the set
        for (int n : a) {
            if (!set.contains(-n)) {
                return 0; // Found imbalance
            }
        }
        return 1; // Balanced
    }
}