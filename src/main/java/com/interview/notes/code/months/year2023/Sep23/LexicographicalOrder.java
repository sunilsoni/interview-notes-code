package com.interview.notes.code.months.year2023.Sep23;

import java.util.Arrays;
import java.util.Scanner;

public class LexicographicalOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();  // Consume the leftover newline
        String s = scanner.nextLine();

        char[] sortedChars = s.toCharArray();
        Arrays.sort(sortedChars);

        for (char ch : sortedChars) {
            int count = 0;
            for (int j = s.indexOf(ch); j < n && s.charAt(j) == ch; j++) {
                count += (n - j);
            }
            if (k <= count) {
                // Find exact substring and the kth character within it
                for (int j = s.indexOf(ch); j < n; j++) {
                    if (k <= (n - j)) {
                        System.out.println(s.charAt(j + k - 1));
                        return;
                    } else {
                        k -= (n - j);
                    }
                }
            } else {
                k -= count;
                s = s.replace(Character.toString(ch), ""); // Remove this character for next iterations
            }
        }
    }
}
