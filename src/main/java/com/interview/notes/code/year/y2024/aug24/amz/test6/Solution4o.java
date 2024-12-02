package com.interview.notes.code.year.y2024.aug24.amz.test6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution4o {

    public static int getMaxNegativePnL(List<Integer> PnL) {
        // Sort the array to consider smaller elements for negation first
        Collections.sort(PnL);

        int cumulativeSum = 0;
        int maxNegatives = 0;

        for (int i = 0; i < PnL.size(); i++) {
            // Negate the smallest element in current context to maximize the number of losses
            int potentialSum = cumulativeSum - PnL.get(i);

            if (potentialSum > 0) {
                maxNegatives++;
                cumulativeSum = potentialSum; // Update cumulativeSum with negated value
            } else {
                cumulativeSum += PnL.get(i); // Maintain original array element as is
            }
        }

        return maxNegatives;
    }

    public static void main(String[] args) {
        testCases();
    }

    private static void testCases() {
        List<Integer> testCase1 = Arrays.asList(1, 1, 1, 1, 1);
        System.out.println(getMaxNegativePnL(testCase1)); // Expected output: 2

        List<Integer> testCase2 = Arrays.asList(5, 2, 3, 5, 2, 3);
        System.out.println(getMaxNegativePnL(testCase2)); // Expected output: 3

        List<Integer> testCase3 = Arrays.asList(5, 3, 1, 2);
        System.out.println(getMaxNegativePnL(testCase3)); // Expected output: 2

        // Additional test case
        List<Integer> testCase4 = Arrays.asList(10, 20, 30);
        System.out.println(getMaxNegativePnL(testCase4)); // Expected output: 1 (if any)

        List<Integer> testCase5 = Arrays.asList(1, 2, 3);
        System.out.println(getMaxNegativePnL(testCase5)); // Expected output: 1+1 = 2
    }
}