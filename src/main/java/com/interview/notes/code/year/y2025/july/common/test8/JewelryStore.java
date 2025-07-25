package com.interview.notes.code.year.y2025.july.common.test8;

import java.util.*;
import java.util.stream.Collectors;

public class JewelryStore {
    public static int solve(int N, List<Integer> K) {
        Map<Integer, Long> freq = K.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        int total = 0;
        for (int i = 1; i <= N; i++) {
            long count = freq.getOrDefault(i, 0L);
            total += Math.max(count, 1);
        }
        return total;
    }

    public static void main(String[] args) {
        // Test case 1 (from example)
        int N1 = 5;
        List<Integer> K1 = Arrays.asList(4, 11);
        int expected1 = 2;
        System.out.println("Test1: " + (solve(N1, K1) == expected1 ? "PASS" : "FAIL"));

        // Test case 2 (from example)
        int N2 = 5;
        List<Integer> K2 = Arrays.asList(4, 11, 4);
        int expected2 = 4;
        System.out.println("Test2: " + (solve(N2, K2) == expected2 ? "PASS" : "FAIL"));

        // Edge Case: All jewels same type, N=3, K=[2,2,2,2]
        int N3 = 3;
        List<Integer> K3 = Arrays.asList(2, 2, 2, 2);
        int expected3 = 6; // Each type not seen must have at least 1
        System.out.println("Test3: " + (solve(N3, K3) == expected3 ? "PASS" : "FAIL"));

        // Large Input: R=100000, N=2000, all jewels of type 1
        int N4 = 2000;
        List<Integer> K4 = new ArrayList<>(Collections.nCopies(100000, 1));
        int expected4 = 100000 + 1999; // All others must have at least 1
        System.out.println("Test4: " + (solve(N4, K4) == expected4 ? "PASS" : "FAIL"));

        // Edge: Each type appears once, N=4, K=[1,2,3,4]
        int N5 = 4;
        List<Integer> K5 = Arrays.asList(1, 2, 3, 4);
        int expected5 = 4;
        System.out.println("Test5: " + (solve(N5, K5) == expected5 ? "PASS" : "FAIL"));
    }
}
