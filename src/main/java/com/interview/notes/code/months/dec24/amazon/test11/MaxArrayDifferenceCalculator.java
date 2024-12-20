package com.interview.notes.code.months.dec24.amazon.test11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxArrayDifferenceCalculator {

    public static int getMaxSumarr(List<Integer> item_weights) {
        int n = item_weights.size();
        int elementsToRemove = n / 3;
        int remainingSize = n - elementsToRemove;
        int maxDiff = Integer.MIN_VALUE;

        // Try all possible combinations of removing elements
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) == elementsToRemove) {
                List<Integer> newArr = new ArrayList<>();

                // Create new array based on current combination
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) == 0) {
                        newArr.add(item_weights.get(i));
                    }
                }

                // Calculate sum difference
                int firstHalfSum = 0;
                int secondHalfSum = 0;
                int halfSize = remainingSize / 2;

                for (int i = 0; i < halfSize; i++) {
                    firstHalfSum += newArr.get(i);
                }
                for (int i = halfSize; i < remainingSize; i++) {
                    secondHalfSum += newArr.get(i);
                }

                maxDiff = Math.max(maxDiff, firstHalfSum - secondHalfSum);
            }
        }

        return maxDiff;
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase(1, Arrays.asList(1, 3, 4, 7, 5, 2), 4);
        runTestCase(2, Arrays.asList(-3, -2, -1), -1);
        runTestCase(3, Arrays.asList(1, 2, 3, 4, 5, 6), 1);

        // Edge cases
        runTestCase(4, Arrays.asList(-1, -1, -1), 0);
        runTestCase(5, Arrays.asList(0, 0, 0), 0);

        // Large input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 99; i++) {
            largeInput.add(i % 10);
        }
        runTestCase(6, largeInput, 15);
    }

    private static void runTestCase(int testNumber, List<Integer> input, int expected) {
        long startTime = System.nanoTime();
        int result = getMaxSumarr(input);
        long endTime = System.nanoTime();

        boolean passed = result == expected;
        System.out.printf("Test Case %d: %s\n", testNumber, passed ? "PASS" : "FAIL");
        System.out.printf("Input size: %d, Expected: %d, Got: %d\n", input.size(), expected, result);
        System.out.printf("Execution time: %.2f ms\n", (endTime - startTime) / 1_000_000.0);
        System.out.println("--------------------");
    }
}
