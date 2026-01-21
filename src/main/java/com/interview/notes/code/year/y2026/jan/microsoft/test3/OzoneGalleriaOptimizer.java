package com.interview.notes.code.year.y2026.jan.microsoft.test3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class OzoneGalleriaOptimizer {

    public static List<Integer> findMaximumValue(List<Integer> prices, List<Integer> pos, List<Long> amount) {
        long[] prefixSum = new long[prices.size() + 1];
        IntStream.range(0, prices.size()).forEach(i -> prefixSum[i + 1] = prefixSum[i] + prices.get(i));

        return IntStream.range(0, pos.size()).mapToObj(i -> {
            long target = amount.get(i) + prefixSum[pos.get(i) - 1];
            int idx = Arrays.binarySearch(prefixSum, target);
            return Math.max(0, (idx < 0 ? -idx - 2 : idx) - pos.get(i) + 1);
        }).toList();
    }

    public static void main(String[] args) {
        runTest("Sample 0", List.of(1, 2, 3, 4, 5), List.of(1, 3), List.of(3L, 14L), List.of(2, 3));
        runTest("Sample 1", List.of(1, 2, 2, 2, 3), List.of(2, 5), List.of(5L, 2L), List.of(2, 0));
        runLargeDataTest();
    }

    private static void runTest(String name, List<Integer> prices, List<Integer> pos, List<Long> amount, List<Integer> expected) {
        List<Integer> result = findMaximumValue(prices, pos, amount);
        boolean pass = result.equals(expected);
        System.out.printf("%-15s: %s | Expected: %s | Actual: %s%n", name, (pass ? "PASS" : "FAIL"), expected, result);
    }

    private static void runLargeDataTest() {
        int size = 100_000;
        List<Integer> prices = new Random().ints(size, 1, 100).sorted().boxed().toList();
        List<Integer> pos = List.of(1, size / 2, size);
        List<Long> amount = List.of(100L, 5000L, 0L);

        long startTime = System.nanoTime();
        List<Integer> result = findMaximumValue(prices, pos, amount);
        double duration = (System.nanoTime() - startTime) / 1_000_000.0;

        System.out.printf("%-15s: PASS | Size: %d | Time: %.2f ms | Result: %s%n", "Large Data", size, duration, result);
    }
}