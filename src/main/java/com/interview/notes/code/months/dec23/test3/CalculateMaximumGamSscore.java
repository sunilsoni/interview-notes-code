package com.interview.notes.code.months.dec23.test3;

import java.util.List;

/**
 *

 There is a game that is played as follows:
 • There is a pawn located at cell 0, and its score is 0.
 • There is a row of n cells numbered from 0 to n-1 from left to right.
 • Each cell has a value, and the first cell always has a value of 0.
 • In a single move, the pawn can move either:
 • one cell to the right, or
 • some number p cells to the right where p is a prime number ending in 3, e.g., 3 and 13
 • The pawn cannot move beyond the last cell.
 • When the pawn enters a cell, its value is added to the score.
 • The game ends after the pawn lands on the last cell at n-1.
 Determine the maximum possible score.
 Example
 cell = [0, -10, -20, -30, 50]
 There are n = 5 cells in the array, with cells numbered from 0 to 4.
 The pawn starts at cell 0 and can reach cells 1 and 3.
 • The pawn can always move 1 cell right until reaching cell n - 1.
 • 3 is a prime number with the least significant digit of 3.


 There are three ways to reach cell 4:
 1. Jump 3 then 1, sum[-30, 50] = 20.
 2. Jump 1 then 3, sum[-10, 50] = 40
 3. Jump 1 always, sum[-10, -20, -30, 50] = -10
 The best score possible is 40.
 Function Description
 Complete the function maxGameScore in the editor below.
 maxGameScore has the following parameter(s):
 int cell[n]: the cell values
 Returns
 int: the maximum possible score
 Constraints
 • 15n≤ 104
 • -104 ≤ celli] ≤ 104
 • cell[0] = 0

 • Sample Case 0
 Sample Input For Custom Testing
 STDIN
 ーー
 4
 Function
 cell[] size n = 4
 cell = [0, -10, 100, -20]
 -10
 100
 -20
 Sample Output
 70
 Explanation
 Possible scores are:
 1. Jump 3, sum[-20] = -20
 2. Jump 1 always, sum[-10, 100, -20] = 70
 • Sample Case 1
 Sample Input For Custom Testing
 STDIN
 Function
 6
 cell[] size n = 6
 cell = [0, -100, -100,
 -100
 -100
 -1
 -1

 -1, 0, -11
 Sample Output
 -2
 */
public class CalculateMaximumGamSscore {

    // Helper method to check if a number is prime
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;

        if (number % 2 == 0 || number % 3 == 0) return false;

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }

    // Function to calculate the maximum game score
    public static int maxGameScore(List<Integer> cell) {
        int n = cell.size();
        int[] dp = new int[n];
        dp[0] = cell.get(0); // Start with the value at cell 0

        // Fill the dp array with minimum values initially
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < n - 1; i++) {
            // Move 1 step to the right
            if (i + 1 < n && dp[i] != Integer.MIN_VALUE) {
                dp[i + 1] = Math.max(dp[i + 1], dp[i] + cell.get(i + 1));
            }
            
            // Move by a prime number of cells to the right
            for (int j = i + 2; j < n; j++) {
                if (isPrime(j - i) && (j - i) % 10 == 3 && dp[i] != Integer.MIN_VALUE) {
                    dp[j] = Math.max(dp[j], dp[i] + cell.get(j));
                }
            }
        }

        // The maximum score will be at the last cell
        return dp[n - 1];
    }

    public static void main(String[] args) {
        List<Integer> example = List.of(0, -10, 100, -20);
        System.out.println(maxGameScore(example));  // Expected output: 70
    }
}
