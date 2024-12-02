package com.interview.notes.code.year.y2024.sept24.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * Calculates the minimum number of operations required to make all computational times odd.
     *
     * @param computationalTime List of computational times for each layer
     * @return Minimum number of operations required
     */
    public static int getMinOperations(List<Integer> computationalTime) {
        int maxPower = 0;

        for (int time : computationalTime) {
            int power = 0;
            while (time % 2 == 0) {
                time /= 2;
                power++;
            }
            maxPower = Math.max(maxPower, power);
        }

        return maxPower;
    }

    public static void main(String[] args) {
        // Test cases
        testCase(Arrays.asList(6, 2, 5, 1, 6, 2, 5), 2);
        testCase(Arrays.asList(2, 4, 8, 16), 4);
        testCase(Arrays.asList(3, 24), 3);
        testCase(Arrays.asList(1, 9, 5), 0);
        testCase(Arrays.asList(2, 2, 2, 2), 1);
        testCase(Arrays.asList(1, 2, 4, 8, 16, 32), 5);
        testCase(Arrays.asList(1_000_000_000, 1_000_000_000), 9);

        // Large input test case
        List<Integer> largeInput = new ArrayList<>(200000);
        for (int i = 0; i < 200000; i++) {
            largeInput.add((int) Math.pow(2, Math.min(i % 31, 30)));
        }
        testCase(largeInput, 30);
    }

    private static void testCase(List<Integer> input, int expected) {
        long startTime = System.nanoTime();
        int result = getMinOperations(input);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1e6; // Convert to milliseconds

        System.out.println("Input: " + (input.size() > 10 ? input.subList(0, 10) + "... (size: " + input.size() + ")" : input));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + duration + " ms");
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println();
    }
}