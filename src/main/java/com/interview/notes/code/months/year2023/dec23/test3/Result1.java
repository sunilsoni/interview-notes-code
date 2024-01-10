package com.interview.notes.code.months.year2023.dec23.test3;

class Result1 {

    public static int maxGameScore(int[] cell) {
        int n = cell.length;
        int[] dp = new int[n]; // dp[i] stores the maximum score at cell i
        dp[0] = cell[0]; // score at starting cell is its value

        // iterate through each cell (except the last)
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], 0); // always take one step if better

            // check for prime jumps ending in 3
            for (int jump = 3; jump <= i; jump++) {
                if (isPrime(jump) && jump % 10 == 3) {
                    int prevCell = i - jump;
                    if (prevCell >= 0) {
                        dp[i] = Math.max(dp[i], dp[prevCell] + cell[i]);
                    }
                }
            }
        }

        return dp[n - 1]; // return score at the last cell
    }

    // helper function to check if a number is prime
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
