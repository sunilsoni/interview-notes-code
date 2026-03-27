package com.interview.notes.code.year.y2026.march.common.test9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OddSumPairCounter {

    public static long solve(List<Integer> A) {
        long evenCount = A.stream().filter(n -> n % 2 == 0).count();
        return evenCount * (A.size() - evenCount);
    }

    public static void main(String[] args) {
        test(List.of(1, 2, 3, 4), 4L, "Test Case 1");
        test(List.of(4, 7, 2), 2L, "Test Case 2");
        test(List.of(2, 4, 6), 0L, "Test Case 3 (All Even)");
        test(List.of(1, 3, 5), 0L, "Test Case 4 (All Odd)");
        
        List<Integer> largeList = new ArrayList<>(Collections.nCopies(50000, 2));
        largeList.addAll(Collections.nCopies(50000, 1));
        test(largeList, 2500000000L, "Test Case 5 (Large Data)");
    }

    private static void test(List<Integer> input, long expected, String name) {
        long result = solve(input);
        System.out.println(name + ": " + (result == expected ? "PASS" : "FAIL"));
    }
}