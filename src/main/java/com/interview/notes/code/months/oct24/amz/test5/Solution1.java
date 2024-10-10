package com.interview.notes.code.months.oct24.amz.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution1 {

    public static int getMaxNegativePnl(List<Integer> PnL) {
        // Step 1: Sort the PnL in descending order
        Collections.sort(PnL, Collections.reverseOrder());

        // Step 2: Calculate the initial cumulative sum (all positive PnLs)
        long cumulativeSum = 0;
        for (int pnl : PnL) {
            cumulativeSum += pnl;
        }

        // Step 3: Try flipping elements one by one and check if cumulative sum stays positive
        int flipCount = 0;
        for (int pnl : PnL) {
            // If we flip this pnl, the new cumulative sum will be reduced by 2 * pnl
            long newCumulativeSum = cumulativeSum - 2 * pnl;

            // If the new cumulative sum remains positive, we flip this pnl
            if (newCumulativeSum > 0) {
                flipCount++;
                cumulativeSum = newCumulativeSum;  // Update the cumulative sum after the flip
            } else {
                // If flipping this pnl would make the cumulative sum non-positive, stop
                break;
            }
        }

        // Step 4: Return the maximum number of flips (negative PnLs)
        return flipCount;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> PnL1 = Arrays.asList(1, 1, 1, 1, 2);
        System.out.println(getMaxNegativePnl(PnL1));  // Expected output: 2

        // Test Case 2
        List<Integer> PnL2 = Arrays.asList(5, 2, 3, 5, 2, 3);
        System.out.println(getMaxNegativePnl(PnL2));  // Expected output: 3

        // Additional test cases can be added here for further testing
    }
}
