package com.interview.notes.code.year.y2025.June.common.test3;

import java.util.*;
import java.util.stream.*;

public class SpecialSum {
    // Java 8 Stream.reduce implementation of the DP
    public static int maxSum(List<Integer> ar) {
        // acc[0] = incl, acc[1] = excl
        int[] acc = ar.stream().reduce(
            new int[]{0, 0},
            (a, x) -> {
                int incl = a[1] + x;
                int excl = Math.max(a[0], a[1]);
                return new int[]{incl, excl};
            },
            (a, b) -> {
                // combiner not used in sequential streams
                int incl = Math.max(a[0], b[0]);
                int excl = Math.max(a[1], b[1]);
                return new int[]{incl, excl};
            }
        );
        return Math.max(acc[0], acc[1]);
    }

    public static void main(String[] args) {
        // List of test cases: each is [inputList, expectedResult]
        List<Map.Entry<List<Integer>, Integer>> tests = Arrays.asList(
            new AbstractMap.SimpleEntry<>(Arrays.asList(5, 5, 10, 1, 100), 115),
            new AbstractMap.SimpleEntry<>(Arrays.asList(1, 2, 2, 1), 3),
            new AbstractMap.SimpleEntry<>(Arrays.asList(4, 3, 6, 11, 8), 18),
            new AbstractMap.SimpleEntry<>(Collections.singletonList(1), 1),
            new AbstractMap.SimpleEntry<>(Arrays.asList(1, 2), 2),
            // Edge: all equal, size 4
            new AbstractMap.SimpleEntry<>(Arrays.asList(5, 5, 5, 5), 10),
            // Large input: 100 elements of 500 ⇒ pick 50 * 500 = 25000
            new AbstractMap.SimpleEntry<>(
                Collections.nCopies(100, 500),
                500 * 50
            )
        );

        for (int i = 0; i < tests.size(); i++) {
            List<Integer> input = tests.get(i).getKey();
            int expected = tests.get(i).getValue();
            int actual = maxSum(input);
            String status = (actual == expected) ? "PASS" : "FAIL";
            System.out.printf(
                "Test %d: expected=%d, actual=%d → %s%n",
                i + 1, expected, actual, status
            );
        }
    }
}