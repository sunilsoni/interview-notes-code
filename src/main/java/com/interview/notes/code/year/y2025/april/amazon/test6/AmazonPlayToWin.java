package com.interview.notes.code.year.y2025.april.amazon.test6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AmazonPlayToWin {

    public static int getMaximumCount(List<Integer> arr, int k) {
        int n = arr.size();
        int existingK = (int) arr.stream().filter(num -> num == k).count();

        Map<Integer, Integer> freq = new HashMap<>();
        int maxAdditional = 0;

        for (int i = 0; i < n; i++) {
            int diff = k - arr.get(i);
            if (diff != 0) {
                freq.put(diff, freq.getOrDefault(diff, 0) + 1);
            }
        }

        maxAdditional = freq.values().stream().max(Integer::compareTo).orElse(0);

        return existingK + maxAdditional;
    }

    public static void main(String[] args) {
        // Test cases
        test(Arrays.asList(2, 5, 2, 5, 2), 2, 4);
        test(Arrays.asList(6, 4, 4, 6, 4, 4), 6, 5);

        // Large Test Case
        List<Integer> largeTest = IntStream.range(0, 200000)
                .map(i -> i % 5 + 1)
                .boxed().collect(Collectors.toList());
        test(largeTest, 3, 80000); // large test validation
    }

    private static void test(List<Integer> arr, int k, int expected) {
        int result = getMaximumCount(arr, k);
        System.out.println(result == expected ? "PASS" : "FAIL (Expected: " + expected + ", Got: " + result + ")");
    }
}
