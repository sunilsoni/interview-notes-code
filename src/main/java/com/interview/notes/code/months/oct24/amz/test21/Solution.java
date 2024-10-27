package com.interview.notes.code.months.oct24.amz.test21;

public class Solution {
    public static int getMaxNegativePnL(int[] pnl) {
        if (pnl == null || pnl.length == 0) {
            return 0;
        }
        if (pnl.length == 1) {
            return 0;
        }

        // Sort the array in descending order to handle larger numbers first
        Integer[] sortedIndices = new Integer[pnl.length];
        for (int i = 0; i < pnl.length; i++) {
            sortedIndices[i] = i;
        }
        java.util.Arrays.sort(sortedIndices, (a, b) -> Integer.compare(pnl[b], pnl[a]));

        int maxNegatives = 0;
        for (int negCount = 1; negCount <= pnl.length; negCount++) {
            // Try making 'negCount' numbers negative
            boolean[] isNegative = new boolean[pnl.length];
            
            // Mark the largest numbers as negative first
            for (int i = 0; i < negCount; i++) {
                isNegative[sortedIndices[i]] = true;
            }

            if (isValidConfiguration(pnl, isNegative)) {
                maxNegatives = negCount;
            } else {
                break;
            }
        }

        return maxNegatives;
    }

    private static boolean isValidConfiguration(int[] pnl, boolean[] isNegative) {
        long cumulativeSum = 0;
        
        // Calculate cumulative sum and check if it remains positive
        for (int i = 0; i < pnl.length; i++) {
            cumulativeSum += isNegative[i] ? -pnl[i] : pnl[i];
            if (cumulativeSum <= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{1, 1, 1, 1, 1}, 2, "Sample Case 0");
        runTest(new int[]{5, 2, 3, 5, 2, 3}, 3, "Sample Case 1");
        
        // Edge cases
        runTest(new int[]{1}, 0, "Single Element");
        runTest(new int[]{1000000000, 1000000000}, 1, "Large Numbers");
        runTest(new int[]{1, 1}, 1, "Two Elements");
        
        // Additional test cases
        runTest(new int[]{5, 4, 3, 2, 1}, 2, "Descending Order");
        runTest(new int[]{1, 2, 3, 4, 5}, 2, "Ascending Order");
        runTest(new int[]{2, 3, 1, 4}, 2, "Mixed Values");
        runTest(new int[]{5, 5, 5, 5}, 2, "Equal Values");
        
        // Additional verification cases
        runTest(new int[]{3, 2, 1}, 1, "Three Elements");
        runTest(new int[]{4, 3, 2, 1}, 2, "Four Elements");
    }

    private static void runTest(int[] pnl, int expected, String testName) {
        long startTime = System.nanoTime();
        int result = getMaxNegativePnL(pnl);
        long endTime = System.nanoTime();
        
        boolean passed = result == expected;
        System.out.printf("Test Case: %s - %s%n", testName, passed ? "PASSED" : "FAILED");
        System.out.printf("Input Size: %d, Expected: %d, Got: %d%n", pnl.length, expected, result);
        System.out.printf("Execution Time: %.2f ms%n%n", (endTime - startTime) / 1_000_000.0);
    }
}