package com.interview.notes.code.year.y2025.september.assesment.test2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static List<Integer> solve(int k, List<Integer> ar) {
        if (ar == null || ar.isEmpty()) return Collections.emptyList();
        int n = ar.size();
        int shift = Math.min(Math.max(k, 0), n);
        return IntStream.range(0, n)
                .mapToObj(i -> i < shift ? 0 : ar.get(i - shift))
                .collect(Collectors.toList());
    }

    private static void runTest(String name, int k, List<Integer> input, List<Integer> expected) {
        long t0 = System.nanoTime();
        List<Integer> actual = solve(k, input);
        long t1 = System.nanoTime();
        boolean pass = actual.equals(expected);
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL") + " -> " + actual + " ("
                + ((t1 - t0) / 1_000_000) + "ms)");
    }

    private static void runLargeTest(int n, int k) {
        List<Integer> input = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        List<Integer> expected = IntStream.range(0, n)
                .mapToObj(i -> i < Math.min(k, n) ? 0 : (i - Math.min(k, n) + 1))
                .collect(Collectors.toList());
        long t0 = System.nanoTime();
        List<Integer> actual = solve(k, input);
        long t1 = System.nanoTime();
        boolean pass = actual.equals(expected);
        System.out.println("Large Data Test (" + n + "," + k + "): " + (pass ? "PASS" : "FAIL")
                + " in " + ((t1 - t0) / 1_000_000) + "ms, sample tail="
                + actual.subList(Math.max(0, n - 5), n));
    }

    public static void main(String[] args) {
        runTest("Example 1", 2, Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(0, 0, 1, 2, 3));
        runTest("Example 2", 3, Arrays.asList(1, 2, 3, 4), Arrays.asList(0, 0, 0, 1));
        runTest("Edge k=0", 0, Arrays.asList(1, 2), Arrays.asList(1, 2));
        runTest("Edge k>=n", 7, Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(0, 0, 0, 0, 0));
        runTest("Single element", 1, List.of(9), List.of(0));
        runLargeTest(100_000, 12_345);
    }
}