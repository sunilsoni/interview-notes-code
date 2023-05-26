package com.interview.notes.code.tricky;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class LetterCandles1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of letter candles
        int m = sc.nextInt(); // number of candles Alice can remove
        String s = sc.next(); // string containing the letters of the candles

        // count the occurrences of each letter in the string
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            freq[c - 'a']++;
        }

        // sort the frequencies in decreasing order
        Integer[] sortedFreq = new Integer[26];
        for (int i = 0; i < 26; i++) {
            sortedFreq[i] = freq[i];
        }
        Arrays.sort(sortedFreq, Collections.reverseOrder());

        // calculate the initial cost of the box
        int cost = 0;
        for (int i = 0; i < 26; i++) {
            cost += sortedFreq[i] * sortedFreq[i];
        }

        // try removing each possible combination of up to m candles
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 26; j++) {
                if (sortedFreq[j] == 0) {
                    break;
                }
                sortedFreq[j]--;
                int newCost = 0;
                for (int k = 0; k < 26; k++) {
                    newCost += sortedFreq[k] * sortedFreq[k];
                }
                cost = Math.min(cost, newCost);
            }
        }

        System.out.println(cost);
    }
}
