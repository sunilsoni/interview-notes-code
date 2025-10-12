package com.interview.notes.code.year.y2025.october.common.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestSameWeight {

    // Method to find next number with same bit weight
    public static long closestSameWeight(long x) {
        // Find lowest differing adjacent bits
        for (int i = 0; i < 63; i++) {
            if (((x >> i) & 1) != ((x >> (i + 1)) & 1)) {
                // Flip bits at positions i and i+1
                x ^= (1L << i) | (1L << (i + 1));
                return x;
            }
        }
        throw new IllegalArgumentException("All bits are 0 or 1");
    }

    // Main method for testing
    public static void main(String[] args) {
        List<Long> testInputs = Arrays.asList(92L, 6L, 9L, 28L, 21L);
        List<Integer> expectedWeights = testInputs.stream()
                .map(x -> Long.bitCount(x))
                .collect(Collectors.toList());

        boolean allPass = true;

        for (int i = 0; i < testInputs.size(); i++) {
            long x = testInputs.get(i);
            long y = closestSameWeight(x);
            int w1 = Long.bitCount(x);
            int w2 = Long.bitCount(y);

            boolean pass = (w1 == w2) && (x != y);
            System.out.printf("x=%d (%s), y=%d (%s), sameWeight=%b, diff=%d%n",
                    x, Long.toBinaryString(x), y, Long.toBinaryString(y), pass, Math.abs(y - x));

            if (!pass) allPass = false;
        }

        // Large data input test
        long large = (1L << 40) + (1L << 20) + 3;
        long largeResult = closestSameWeight(large);
        boolean largeTest = Long.bitCount(large) == Long.bitCount(largeResult);
        System.out.println("Large input test passed: " + largeTest);

        System.out.println(allPass ? "PASS" : "FAIL");
    }
}