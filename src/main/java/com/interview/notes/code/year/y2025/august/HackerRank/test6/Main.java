package com.interview.notes.code.year.y2025.august.HackerRank.test6;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static int getMinCores(List<Integer> start, List<Integer> end) {
        int n = start.size();
        int[] s = start.stream().mapToInt(Integer::intValue).sorted().toArray();
        int[] e = end.stream().mapToInt(Integer::intValue).sorted().toArray();
        int i = 0, j = 0, count = 0, max = 0;
        while (i < n) {
            if (s[i] <= e[j]) {
                count++;
                max = Math.max(max, count);
                i++;
            } else {
                count--;
                j++;
            }
        }
        return max;
    }

    public static void runTest(String name, List<Integer> s, List<Integer> e, int expected) {
        int result = getMinCores(s, e);
        String status = result == expected ? "PASS" : "FAIL";
        System.out.println(name + ": expected=" + expected + ", got=" + result + " => " + status);
    }

    public static void main(String[] args) {
        runTest("Sample Case 0",
            Arrays.asList(1, 2, 3),
            Arrays.asList(3, 3, 5),
            3);

        runTest("Sample Case 1",
            Arrays.asList(1, 4, 7),
            Arrays.asList(2, 4, 10),
            1);

        int n = 100_000;
        List<Integer> allOverlapStart = IntStream.range(0, n).map(i -> 1).boxed().collect(Collectors.toList());
        List<Integer> allOverlapEnd = IntStream.range(0, n).map(i -> 2).boxed().collect(Collectors.toList());
        runTest("All Overlap Large",
            allOverlapStart,
            allOverlapEnd,
            n);

        List<Integer> noOverlapStart = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        List<Integer> noOverlapEnd = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        runTest("No Overlap Large",
            noOverlapStart,
            noOverlapEnd,
            1);
    }
}