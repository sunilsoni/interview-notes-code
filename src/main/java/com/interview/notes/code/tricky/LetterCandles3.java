package com.interview.notes.code.tricky;

import java.util.Arrays;
import java.util.Scanner;

public class LetterCandles3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String s = scanner.next();

        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            counts[c - 'a']++;
        }

        int cost = 0;
        for (int i = 0; i < 26; i++) {
            cost += counts[i] * counts[i];
        }

        Arrays.sort(counts);
        for (int i = 25; i >= 0 && m > 0; i--) {
            int candlesToRemove = Math.min(m, counts[i]);
            cost -= candlesToRemove * candlesToRemove;
            m -= candlesToRemove;
        }

        System.out.println(cost);
    }
}
