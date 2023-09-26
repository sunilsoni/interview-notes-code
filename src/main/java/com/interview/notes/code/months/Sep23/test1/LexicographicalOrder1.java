package com.interview.notes.code.months.Sep23.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LexicographicalOrder1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();  // Consume the leftover newline
        String s = scanner.nextLine();

        List<String> substrings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                substrings.add(s.substring(i, j));
            }
        }

        Collections.sort(substrings);

        StringBuilder concatenated = new StringBuilder();
        for (String str : substrings) {
            concatenated.append(str);
        }

        System.out.println(concatenated.charAt(k - 1));
    }
}
