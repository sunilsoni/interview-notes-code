package com.interview.notes.code.year.y2024.aug24.amz.test6;

import java.util.List;

public class MaxNegativePnL1 {

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        int[] cumulativePnL = new int[n];
        cumulativePnL[0] = PnL.get(0);
        for (int i = 1; i < n; i++) {
            cumulativePnL[i] = cumulativePnL[i - 1] + PnL.get(i);
        }

        int minCumulativePnL = cumulativePnL[0];
        int countNegatives = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (PnL.get(i) > minCumulativePnL) {
                countNegatives++;
                minCumulativePnL = Math.min(minCumulativePnL, cumulativePnL[i] - PnL.get(i));
            }
        }
        return countNegatives;
    }

    public static void main(String[] args) {
        // Example test cases
        List<Integer> PnL1 = List.of(5, 3, 1, 2);
        List<Integer> PnL2 = List.of(1, 1, 1, 1, 1);
        List<Integer> PnL3 = List.of(5, 2, 3, 5, 2, 3);

        System.out.println(getMaxNegativePnL(PnL1)); // Output: 2
        System.out.println(getMaxNegativePnL(PnL2)); // Output: 2
        System.out.println(getMaxNegativePnL(PnL3)); // Output: 3
    }
}