package com.interview.notes.code.year.y2025.march.caspex.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static List<Integer> solve(List<Integer> nums) {
        List<Integer> nonZero = nums.stream()
                .filter(n -> n != 0)
                .collect(Collectors.toList());
        long zerosCount = nums.size() - nonZero.size();
        nonZero.addAll(Collections.nCopies((int) zerosCount, 0));
        return nonZero;
    }

    public static void main(String[] args) {
        test(Arrays.asList(0, 1, 0, 3, 12), Arrays.asList(1, 3, 12, 0, 0));
        test(List.of(0), List.of(0));
        test(Arrays.asList(1, 2, 3, 0, 4), Arrays.asList(1, 2, 3, 4, 0));
        test(largeInputTest(), largeOutputTest());
    }

    private static void test(List<Integer> input, List<Integer> expected) {
        List<Integer> result = solve(input);
        System.out.println(result.equals(expected) ? "PASS" : "FAIL");
    }

    private static List<Integer> largeInputTest() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            list.add(i % 10 == 0 ? 0 : i);
        }
        return list;
    }

    private static List<Integer> largeOutputTest() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            if (i % 10 != 0) list.add(i);
        }
        for (int i = 0; i < 5000; i++) {
            list.add(0);
        }
        return list;
    }
}
