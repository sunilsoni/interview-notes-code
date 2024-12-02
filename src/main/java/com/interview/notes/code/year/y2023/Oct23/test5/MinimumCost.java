package com.interview.notes.code.year.y2023.Oct23.test5;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Letter Candles
 * Your friend Alice has a box with N letter candles in it.
 * The cost of the box is determined as follows - And
 * the number of occurrences of each characters in the
 * box and sum up the squares of these numbers.
 * Alice wants to reduce the cost of the box by
 * removing some candles from it However, she is
 * allowed to remove at most Mcand es from the box.
 * Can you help Alice determine the minimum cost of
 * the box?
 * Input
 * The first line of the input contains the integer
 * N, representing the number of letter candles.
 * The second line of the Input contains the integer
 * M representing the number of candles Alice can
 * remove.
 * The third line of the input contains an N-lettered
 * string S which contains lowercase English letters,
 * representing the letter candles in the box.
 * Output
 * Print the minimum possible cost of the box.
 * Example #1
 * input
 * 6
 * 2
 * bacacc
 * Output
 * 6
 * <p>
 * <p>
 * <p>
 * <p>
 * Explanation: There are two As, one B, and three Cs in
 * the box. Current cost of the box is 22 + 12 + 32 = 14.
 * The best way to minimize the cost of the box is to
 * remove two C-shaped candles from It The new
 * minimal cost will be 22 + 12 + 12 • 6. The answer Is 6.
 * Example #2
 * input
 * 15
 * 3
 * xxxxxxxxxxxxxxx
 * Output
 * 144
 * Explanation: There are 15 Xs. The current cost of the
 * box is 152 = 225. The only way to minimize the cost
 * is by reducing three X-shaped candles from it. The
 * new minimal cost will be 122 = 144. The answer is
 * 144.
 */
public class MinimumCost {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        String S = sc.next();
        int[] freq = new int[26];

        // Counting the frequency of each character in the string
        for (int i = 0; i < N; i++) {
            freq[S.charAt(i) - 'a']++;
        }

        // Sorting the frequency array
        Arrays.sort(freq);

        // Removing the maximum occurring characters, M times
        for (int i = 25; i >= 0 && M > 0; i--) {
            while (freq[i] > 0 && M > 0) {
                freq[i]--;
                M--;
            }
        }

        // Calculating the cost
        int cost = 0;
        for (int i = 0; i < 26; i++) {
            cost += freq[i] * freq[i];
        }

        // Printing the cost
        System.out.println(cost);
    }
}
