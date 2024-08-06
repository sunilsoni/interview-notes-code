package com.interview.notes.code.months.aug24.amz.test6;

import java.util.List;

public class AmazonStocks {

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        int maxNegatives = 0;
        int currentNegatives = 0;
        long cumulativeSum = 0;

        for (int i = 0; i < n; i++) {
            cumulativeSum += PnL.get(i);

            // If the cumulative sum is negative, it's invalid, so reset the count
            if (cumulativeSum < 0) {
                currentNegatives = 0;
                cumulativeSum = 0;
            } else {
                // If the current PnL is negative, increment the count
                if (PnL.get(i) < 0) {
                    currentNegatives++;
                }
                // Update the maximum negatives count
                maxNegatives = Math.max(maxNegatives, currentNegatives);
            }
        }

        return maxNegatives;
    }

    public static void main(String[] args) {
        // Sample Case 0
        List<Integer> PnL1 = List.of(1, 1, 1, 1, 1);
        System.out.println("Max Negatives for Case 0: " + getMaxNegativePnL(PnL1)); // Output: 2

        // Sample Case 1
        List<Integer> PnL2 = List.of(5, 2, 3, 5, 2, 3);
        System.out.println("Max Negatives for Case 1: " + getMaxNegativePnL(PnL2)); // Output: 3

        // Additional Test Cases
        List<Integer> PnL3 = List.of(5, 3, 1, 2);
        System.out.println("Max Negatives for Case 3: " + getMaxNegativePnL(PnL3)); // Output: 2

        List<Integer> PnL4 = List.of(10, 5, 2, 8, 4, 1);
        System.out.println("Max Negatives for Case 4: " + getMaxNegativePnL(PnL4)); // Output: 3

        List<Integer> PnL5 = List.of(1, 2, 3, 4, 5, 6);
        System.out.println("Max Negatives for Case 5: " + getMaxNegativePnL(PnL5)); // Output: 0
    }
}