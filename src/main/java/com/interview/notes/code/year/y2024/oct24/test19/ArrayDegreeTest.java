package com.interview.notes.code.year.y2024.oct24.test19;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayDegreeTest {
    public static void main(String[] args) {
        // Test cases
        test(Arrays.asList(4, 1, 1, 2, 2, 1, 3, 3), Arrays.asList(1, 1, 2, 2, 1));
        test(Arrays.asList(5, 1, 2, 2, 3, 1), Arrays.asList(2, 2));
        test(Arrays.asList(1, 1, 1, 1, 1), List.of(1));
        test(List.of(1), List.of(1));
        test(generateLargeInput(1000000), null); // Performance test
    }

    public static List<Integer> solve(List<Integer> a) {
        if (a.size() == 1) return Collections.singletonList(a.get(0));

        // Store frequency and position data
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, int[]> positions = new HashMap<>();

        // Single pass to collect data
        IntStream.range(0, a.size()).forEach(i -> {
            int num = a.get(i);
            freq.merge(num, 1, Integer::sum);
            positions.computeIfAbsent(num, k -> new int[]{i, i})[1] = i;
        });

        // Handle all same elements case
        if (positions.size() == 1) return Collections.singletonList(a.get(0));

        // Find max frequency
        int maxFreq = freq.values().stream().max(Integer::compareTo).get();

        // Get result with minimum subarray length
        return freq.entrySet().stream()
                .filter(e -> e.getValue() == maxFreq)
                .map(e -> {
                    int[] pos = positions.get(e.getKey());
                    return a.subList(pos[0], pos[1] + 1);
                })
                .min(Comparator.comparing(List::size))
                .get();
    }

    private static void test(List<Integer> input, List<Integer> expected) {
        long start = System.nanoTime();
        List<Integer> result = solve(input);
        long time = (System.nanoTime() - start) / 1_000_000;

        System.out.printf("Input size: %d, Time: %dms%n", input.size(), time);
        if (expected != null) {
            boolean passed = result.equals(expected);
            System.out.printf("Status: %s%n", passed ? "PASS" : "FAIL");
            if (!passed) {
                System.out.printf("Expected: %s%nGot: %s%n", expected, result);
            }
        } else {
            System.out.printf("Performance test - Result size: %d%n", result.size());
        }
        System.out.println();
    }

    private static List<Integer> generateLargeInput(int size) {
        return new Random().ints(size, 1, 100)
                .boxed()
                .collect(Collectors.toList());
    }
}