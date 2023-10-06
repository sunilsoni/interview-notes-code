package com.interview.notes.code.months.Sep23.test2;

import java.util.Arrays;
import java.util.Scanner;

public class MinCost {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        String S = scanner.next();

        int[] counts = new int[26];

        for (char c : S.toCharArray()) {
            counts[c - 'a']++;
        }

        Arrays.sort(counts);

        for (int i = 25; i >= 0 && M > 0; i--) {
            if (counts[i] > M) {
                counts[i] -= M;
                M = 0;
            } else {
                M -= counts[i];
                counts[i] = 0;
            }
        }

        int result = 0;
        for (int count : counts) {
            result += count * count;
        }

        System.out.println(result);
    }
}
