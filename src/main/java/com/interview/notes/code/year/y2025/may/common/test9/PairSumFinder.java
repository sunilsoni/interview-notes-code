package com.interview.notes.code.year.y2025.may.common.test9;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PairSumFinder {

    // Finds distinct pairs that add up to target
    public static List<AbstractMap.SimpleEntry<Integer,Integer>> findPairs(int[] arr, int target) {
        // Build a frequency map: value → count
        Map<Integer, Long> freq = Arrays.stream(arr)                   // stream over the int[]
            .boxed()                                                    // convert IntStream to Stream<Integer>
            .collect(Collectors.groupingBy(
                Function.identity(),                                    // group by the number itself
                Collectors.counting()                                   // count occurrences
            ));

        // Stream over each distinct key to find valid pairs
        return freq.keySet().stream()
            .filter(k -> {
                int c = target - k;                                     // complement needed
                if (k < c) {
                    // distinct numbers: check complement exists
                    return freq.containsKey(c);
                } else if (k == c) {
                    // same number twice: need at least two occurrences
                    return freq.get(k) > 1;
                } else {
                    // k > c would duplicate a pair already counted
                    return false;
                }
            })
            .map(k -> {
                int c = target - k;
                // ensure pair is (smaller, larger)
                int small = Math.min(k, c);
                int large = Math.max(k, c);
                return new AbstractMap.SimpleEntry<>(small, large);
            })
            .collect(Collectors.toList());
    }

    // Simple main method to run test cases and print PASS/FAIL
    public static void main(String[] args) {
        // Define test cases: {array, target, expectedPairs}
        List<TestCase> tests = Arrays.asList(
            new TestCase(new int[]{72,8,15,63,17,10,70,1,2,3,8}, 80,
                Arrays.asList(
                    new AbstractMap.SimpleEntry<>(8,72),
                    new AbstractMap.SimpleEntry<>(17,63),
                    new AbstractMap.SimpleEntry<>(10,70)
                )
            ),
            new TestCase(new int[]{5,5,5}, 10,
                Arrays.asList(new AbstractMap.SimpleEntry<>(5,5))
            ),
            new TestCase(new int[]{1,2,3}, 7, Collections.emptyList()),
            new TestCase(new int[]{}, 5, Collections.emptyList()),
            // Large input test: all zeros, target 0 → many zeros collapsed to one pair
            new TestCase(IntStream.range(0,1000000).map(i -> 0).toArray(), 0,
                Arrays.asList(new AbstractMap.SimpleEntry<>(0,0)))
        );

        for (TestCase tc : tests) {
            List<AbstractMap.SimpleEntry<Integer,Integer>> result = findPairs(tc.arr, tc.target);
            // Sort both lists to compare regardless of order
            Comparator<AbstractMap.SimpleEntry<Integer,Integer>> cmp = Comparator
                .<AbstractMap.SimpleEntry<Integer,Integer>, Integer>comparing(Map.Entry::getKey)
                .thenComparing(Map.Entry::getValue);
            result.sort(cmp);
            tc.expected.sort(cmp);

            boolean pass = result.equals(tc.expected);
            System.out.printf("Test target=%d, arraySize=%d: %s%n",
                tc.target, tc.arr.length, pass ? "PASS" : "FAIL");
            if (!pass) {
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Got     : " + result);
            }
        }
    }

    // Helper class to bundle test data
    static class TestCase {
        int[] arr;
        int target;
        List<AbstractMap.SimpleEntry<Integer,Integer>> expected;
        TestCase(int[] arr, int target, List<AbstractMap.SimpleEntry<Integer,Integer>> expected) {
            this.arr = arr;
            this.target = target;
            this.expected = expected;
        }
    }
}