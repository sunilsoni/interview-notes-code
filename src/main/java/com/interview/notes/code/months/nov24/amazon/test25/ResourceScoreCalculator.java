package com.interview.notes.code.months.nov24.amazon.test25;

import java.util.Arrays;

/*
Assume that I'm building a new container platform that runs within a cluster of nodes.
The first thing that the platform does is find a node to become the orchestrator.
It queries each node for its resource usage and then calculates a resource score.
The node with the highest resource score becomes the orchestrator.
The resource score is the product of the available resources across all the nodes except the current node. Write a function to calculate all of the resource scores.
input - memFreeIngb = [ 2, 4, 1, 10]
output - scores= 140, 20, 80, 8]
 */
public class ResourceScoreCalculator {

    public static long[] calculateResourceScores(int[] memFreeInGb) {
        if (memFreeInGb == null || memFreeInGb.length == 0) {
            return new long[0];
        }

        long[] scores = new long[memFreeInGb.length];

        for (int i = 0; i < memFreeInGb.length; i++) {
            long product = 1;
            for (int j = 0; j < memFreeInGb.length; j++) {
                if (i != j) {
                    product *= memFreeInGb[j];
                }
            }
            scores[i] = product;
        }

        return scores;
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case
        testCase(new int[]{2, 4, 1, 10}, new long[]{40, 20, 80, 8}, "Normal Case");

        // Test Case 2: Single element
        testCase(new int[]{5}, new long[]{1}, "Single Element");

        // Test Case 3: Two elements
        testCase(new int[]{3, 4}, new long[]{4, 3}, "Two Elements");

        // Test Case 4: Zero values
        testCase(new int[]{0, 1, 2}, new long[]{2, 0, 0}, "Zero Values");

        // Test Case 5: Large numbers
        testCase(new int[]{1000, 2000, 3000},
                new long[]{6000000L, 3000000L, 2000000L},
                "Large Numbers");

        // Test Case 6: Empty array
        testCase(new int[]{}, new long[]{}, "Empty Array");
    }

    private static void testCase(int[] input, long[] expected, String testName) {
        System.out.println("\nRunning Test: " + testName);
        System.out.println("Input: " + Arrays.toString(input));

        long[] result = calculateResourceScores(input);

        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Actual: " + Arrays.toString(result));

        boolean passed = Arrays.equals(result, expected);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }
}
