package com.interview.notes.code.year.y2026.march.amazon.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmartCitiesManagement {

    public static int moveUnits(List<Integer> population, String unit) {
        int ans = 0;
        for (int i = 0, n = population.size(); i < n; i++) {
            if (unit.charAt(i) == '1') {
                int s = i, sum = 0, min = Integer.MAX_VALUE;
                if (s > 0) {
                    sum = population.get(s - 1);
                    min = sum;
                }
                while (i < n && unit.charAt(i) == '1') {
                    int v = population.get(i++);
                    sum += v;
                    min = Math.min(min, v);
                }
                ans += sum - (s > 0 ? min : 0);
                i--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        test(1, Arrays.asList(20, 10, 9, 30, 20, 19), "011011", 80);
        test(2, Arrays.asList(5, 4, 5, 1), "0111", 14);
        test(3, Arrays.asList(10, 5, 8, 9, 6), "01101", 27);
        test(4, Arrays.asList(10, 10, 10), "111", 30);
        test(5, Arrays.asList(10, 10, 10), "000", 0);

        List<Integer> largePop1 = IntStream.range(0, 100000).mapToObj(i -> 10000).collect(Collectors.toList());
        String largeUnit1 = IntStream.range(0, 100000).mapToObj(i -> "1").collect(Collectors.joining(""));
        test(6, largePop1, largeUnit1, 1000000000);

        List<Integer> largePop2 = IntStream.range(0, 100000).mapToObj(i -> 10000).collect(Collectors.toList());
        String largeUnit2 = IntStream.range(0, 100000).mapToObj(i -> i % 2 == 0 ? "0" : "1").collect(Collectors.joining(""));
        test(7, largePop2, largeUnit2, 500000000);
    }

    private static void test(int id, List<Integer> p, String u, int expected) {
        System.out.println("Test " + id + ": " + (moveUnits(p, u) == expected ? "PASS" : "FAIL"));
    }
}