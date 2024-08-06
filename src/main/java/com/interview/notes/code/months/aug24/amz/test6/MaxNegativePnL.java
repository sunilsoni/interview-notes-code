package com.interview.notes.code.months.aug24.amz.test6;

import java.util.List;

public class MaxNegativePnL {
    public static int getMaxNegativePnL(List<Integer> pnl) {
        int n = pnl.size();
        int[] cumulativePnL = new int[n];
        cumulativePnL[0] = pnl.get(0);
        for (int i = 1; i < n; i++) {
            cumulativePnL[i] = cumulativePnL[i - 1] + pnl.get(i);
        }

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (cumulativePnL[i] > 2 * pnl.get(i)) {
                count++;
                cumulativePnL[i] -= 2 * pnl.get(i);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Example test cases
        List<Integer> pnl1 = List.of(5, 3, 1, 2);
        List<Integer> pnl2 = List.of(1, 1, 1, 1, 1);
        List<Integer> pnl3 = List.of(5, 2, 3, 5, 2, 3);

        System.out.println(getMaxNegativePnL(pnl1)); // Output: 2
        System.out.println(getMaxNegativePnL(pnl2)); // Output: 2
        System.out.println(getMaxNegativePnL(pnl3)); // Output: 3
    }
}
