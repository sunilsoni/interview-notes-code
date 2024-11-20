package com.interview.notes.code.months.nov24.amazon.test26; /**
 * ResourceScoreCalculator calculates resource scores for a container platform cluster.
 * The resource score for each node is calculated as the product of available resources
 * across all nodes except the current node.
 * <p>
 * Assumptions:
 * - memFreeIngb contains non-negative integers.
 * - Zero values in memFreeIngb are handled as per the problem's requirement.
 * <p>
 * Approach:
 * - Use BigInteger to handle potential integer overflow with large data inputs.
 * - Count the number of zeros in memFreeIngb.
 * - If more than one zero, all scores are zero.
 * - If exactly one zero, scores for non-zero elements are zero; score for zero element is product of non-zero elements.
 * - If no zeros, calculate total product and divide by memFreeIngb[i] for each element.
 * - Provide test cases, including edge cases, to validate the solution.
 */

import java.math.BigInteger;
import java.util.Arrays;

public class ResourceScoreCalculator {
    public static void main(String[] args) {
        // Test Case 1: Given example
        int[] memFreeIngb1 = {2, 4, 1, 10};
        int[] expectedScores1 = {40, 20, 80, 8};
        testCalculateResourceScores(memFreeIngb1, expectedScores1, "Test Case 1");

        // Test Case 2: Contains zero
        int[] memFreeIngb2 = {0, 1, 2};
        int[] expectedScores2 = {2, 0, 0};
        testCalculateResourceScores(memFreeIngb2, expectedScores2, "Test Case 2");

        // Test Case 3: All zeros
        int[] memFreeIngb3 = {0, 0, 0};
        int[] expectedScores3 = {0, 0, 0};
        testCalculateResourceScores(memFreeIngb3, expectedScores3, "Test Case 3");

        // Test Case 4: Large numbers
        int[] memFreeIngb4 = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] expectedScores4 = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        testCalculateResourceScores(memFreeIngb4, expectedScores4, "Test Case 4");

        // Test Case 5: Single element
        int[] memFreeIngb5 = {5};
        int[] expectedScores5 = {1};
        testCalculateResourceScores(memFreeIngb5, expectedScores5, "Test Case 5");

        // Test Case 6: Negative numbers (if allowed)
        int[] memFreeIngb6 = {-2, 3};
        int[] expectedScores6 = {3, -2};
        testCalculateResourceScores(memFreeIngb6, expectedScores6, "Test Case 6");

        // Test Case 7: Empty array
        int[] memFreeIngb7 = {};
        int[] expectedScores7 = {};
        testCalculateResourceScores(memFreeIngb7, expectedScores7, "Test Case 7");
    }

    /**
     * Calculates resource scores for each node.
     *
     * @param memFreeIngb Array of available resources for each node.
     * @return Array of resource scores.
     */
    public static int[] calculateResourceScores(int[] memFreeIngb) {
        int n = memFreeIngb.length;
        int[] scores = new int[n];

        if (n == 0) {
            return scores; // Return empty array if input is empty
        }

        int zeroCount = 0;
        BigInteger totalProduct = BigInteger.ONE;
        for (int mem : memFreeIngb) {
            if (mem == 0) {
                zeroCount++;
            } else {
                totalProduct = totalProduct.multiply(BigInteger.valueOf(mem));
            }
        }

        if (zeroCount > 1) {
            // If more than one zero, all scores are zero
            Arrays.fill(scores, 0);
        } else if (zeroCount == 1) {
            // If exactly one zero, scores for non-zero elements are zero
            // Score for zero element is product of non-zero elements
            for (int i = 0; i < n; i++) {
                if (memFreeIngb[i] == 0) {
                    scores[i] = totalProduct.intValue();
                } else {
                    scores[i] = 0;
                }
            }
        } else {
            // No zeros, calculate scores normally
            for (int i = 0; i < n; i++) {
                BigInteger score = totalProduct.divide(BigInteger.valueOf(memFreeIngb[i]));
                scores[i] = score.intValue();
            }
        }

        return scores;
    }

    /**
     * Tests the calculateResourceScores method with given inputs and expected outputs.
     *
     * @param memFreeIngb   Input array of available resources.
     * @param expectedScores Expected array of resource scores.
     * @param testCaseName   Name of the test case.
     */
    public static void testCalculateResourceScores(int[] memFreeIngb, int[] expectedScores, String testCaseName) {
        int[] calculatedScores = calculateResourceScores(memFreeIngb);

        boolean passed = Arrays.equals(calculatedScores, expectedScores);
        System.out.println(testCaseName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expectedScores));
            System.out.println("Got     : " + Arrays.toString(calculatedScores));
        }
    }
}
