package com.interview.notes.code.months.jan24.test1;

/**
 *

 Letter Candles : Java
 Your friend Alice has a box with N letter candles in it.
 The cost of the box is determined as follows - Find the number of occurrences of each characters in the box and sum up the squares of these numbers.
 Alice wants to reduce the cost of the box by removing some candles from it. However, she is allowed to remove at most M candles from the box.
 Can you help Alice determine the minimum cost of the box?
 Input
 The first line of the input contains the integer
 N, representing the number of letter candles.
 The second line of the input contains the integer
 M, representing the number of candles Alice can remove.
 The third line of the input contains an N-lettered string S, which contains lowercase English letters, representing the letter candles in the box.
 Output
 Print the minimum possible cost of the box.
 Example #1
 Input
 6
 2
 bacaco
 Output
 6

 Example #1
 Input
 6
 2
 bacacc
 Output
 6
 Explanation: There are two As, one B, and three Cs in
 the box. Current cost of the box is 22 + 12 + 32 = 14.
 The best way to minimize the cost of the box is to remove two C-shaped candles from it. The new
 minimal cost will be 22 + 12 + 12 = 6. The answer is 6.
 Example #2
 Input
 15
 3
 XXXXXXXXXXXXXXX
 Output
 144
 Explanation: There are 15 X5. The current cost of the
 box is 152 = 225. The only way to minimize the cost
 is by reducing three X-shaped candles from it. The
 new minimal cost will be 122 = 144. The answer is 144.


 */
public class LetterCandles {
    public static int solve(int N, int M, String S) {
        int[] charCounts = new int[26];
        for (int i = 0; i < S.length(); i++) {
            charCounts[S.charAt(i) - 'a']++;
        }

        java.util.Arrays.sort(charCounts);

        for (int i = 0; i < M; i++) {
            int index = 25; // Start from the end as the array is sorted in ascending order
            while (index >= 0 && charCounts[index] == 0) {
                index--; // Find the first non-zero count
            }
            if (index >= 0) {
                charCounts[index]--; // Remove one candle
                java.util.Arrays.sort(charCounts);
            }
        }

        int minCost = 0;
        for (int count : charCounts) {
            minCost += count * count;
        }

        return minCost;
    }

    public static void main(String[] args) {
        int N1 = 6, M1 = 2;
        String S1 = "bacacc";
        int N2 = 15, M2 = 3;
        String S2 = "xxxxxxxxxxxxxxx";

        System.out.println("The minimum cost for the first example is: " + solve(N1, M1, S1));
        System.out.println("The minimum cost for the second example is: " + solve(N2, M2, S2));
    }

}
