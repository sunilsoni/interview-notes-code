package com.interview.notes.code.year.y2025.july.common.test6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DegreeOfArray {

    /**
     * Returns the length of the smallest subarray whose degree
     * equals the degree of the entire input list.
     */
    public static int degreeOfArray(List<Integer> arr) {
        // 1) Count how many times each number appears
        Map<Integer, Long> frequencyMap = arr.stream()
            .collect(
                Collectors.groupingBy(
                    Function.identity(),    // group by the number itself
                    Collectors.counting()   // count how many times each appears
                )
            );

        // 2) Find the highest count (the degree)
        long degree = frequencyMap.values().stream()
            .mapToLong(Long::longValue)    // convert Long to primitive long
            .max()                         // pick the maximum
            .orElse(0L);                   // default to 0 if list is empty

        // 3) Gather all the numbers that reach that degree
        Set<Integer> degreeElements = frequencyMap.entrySet().stream()
            .filter(e -> e.getValue() == degree)  // keep only entries with count == degree
            .map(Map.Entry::getKey)               // extract the number
            .collect(Collectors.toSet());         // put into a set

        // 4) For each of those numbers, record where it first and last appears
        //    We use a map from number -> int[2], where [0]=firstIdx, [1]=lastIdx
        Map<Integer, int[]> positions = new HashMap<>();

        // Walk through the array by index
        IntStream.range(0, arr.size()).forEach(i -> {
            int num = arr.get(i);
            if (degreeElements.contains(num)) {
                // If this is the first time seeing num, initialize both to i
                // Otherwise just update the last index to i
                positions.computeIfAbsent(num, k -> new int[]{i, i})[1] = i;
            }
        });

        // 5) Compute the minimum window size among all degree-elements
        return positions.values().stream()
            .mapToInt(pair -> pair[1] - pair[0] + 1) // lastIdx - firstIdx + 1
            .min()                                   // take the smallest
            .orElse(0);                              // default if no elements
    }

    /**
     * Simple main method to run a few test cases without JUnit.
     * Prints PASS or FAIL for each, and then does a large random test.
     */
    public static void main(String[] args) {
        // Define sample inputs and their expected outputs
        List<List<Integer>> tests = Arrays.asList(
            Arrays.asList(1, 1, 2, 1, 2, 2),  // sample 1 → degree=3 → shortest window=4
            Arrays.asList(1, 2, 2, 3, 1),     // sample 2 → degree=2 → shortest window=2
            Arrays.asList(1, 2, 3, 4, 5),     // all unique → degree=1 → any window=1
            Arrays.asList(2, 2, 2, 2)         // all same   → degree=4 → window=4
        );
        List<Integer> expected = Arrays.asList(4, 2, 1, 4);

        // Run each test and report
        for (int i = 0; i < tests.size(); i++) {
            int result = degreeOfArray(tests.get(i));
            String status = (result == expected.get(i)) ? "PASS" : "FAIL";
            System.out.printf(
                "Test %d: %s → expected=%d, got=%d → %s%n",
                i+1, tests.get(i), expected.get(i), result, status
            );
        }

        // Large random test to show it handles up to ~10^5 quickly
        int n = 100_000;
        Random rnd = new Random();
        List<Integer> large = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            large.add(rnd.nextInt(5000));  // values in [0..4999]
        }
        long start = System.currentTimeMillis();
        int ans = degreeOfArray(large);
        long time = System.currentTimeMillis() - start;
        System.out.printf("Large test (n=%d) → result=%d computed in %dms%n", n, ans, time);
    }
}