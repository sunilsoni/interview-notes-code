package com.interview.notes.code.months.july23.test2;

public class CollectGold {

    static long collectGold(int n, int[] G) {
        long[] dp = new long[n + 2];

        // start from the end
        for (int i = n - 1; i >= 0; i--) {
            // try both 2*i + 1 and 2*i + 2
            dp[i] = Math.max(G[i] + (i * 2 + 1 < n ? dp[i * 2 + 1] : 0),
                    G[i] + (i * 2 + 2 < n ? dp[i * 2 + 2] : 0));
        }

        // return the maximum coins from the 1st house
        return dp[0];
    }

    public static void main(String[] args) {
        int n = 6;
        int[] G = {1, 3, 9, 1, 5, 3};

        //int n = 4;
        //int[] G = {1, 2, 3, 4};
        System.out.println(collectGold(n, G));  // Output: 7
    }
}
