package com.interview.notes.code.months.june24.test11;

import java.util.ArrayList;
import java.util.List;

public class Result1 {

    public static List<Integer> frequency(String s) {
        // Initialize an array to store frequencies of 26 letters
        int[] freq = new int[26];
        int i = 0;

        while (i < s.length()) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                // For characters j to z (10# to 26#)
                int num = Integer.parseInt(s.substring(i, i + 2));
                i += 3;  // Move past the number and #

                int count = 1;  // Default count is 1
                if (i < s.length() && s.charAt(i) == '(') {
                    // If the next character is '(', parse the number inside parentheses
                    int start = i + 1;
                    int end = s.indexOf(')', start);
                    count = Integer.parseInt(s.substring(start, end));
                    i = end + 1;  // Move past ')'
                }

                freq[num - 1] += count;  // Increment the frequency
            } else {
                // For characters a to i (1 to 9)
                int num = s.charAt(i) - '0';
                i += 1;

                int count = 1;  // Default count is 1
                if (i < s.length() && s.charAt(i) == '(') {
                    // If the next character is '(', parse the number inside parentheses
                    int start = i + 1;
                    int end = s.indexOf(')', start);
                    count = Integer.parseInt(s.substring(start, end));
                    i = end + 1;  // Move past ')'
                }

                freq[num - 1] += count;  // Increment the frequency
            }
        }

        // Convert the array to a list and return
        List<Integer> result = new ArrayList<>();
        for (int f : freq) {
            result.add(f);
        }

        return result;
    }

    public static void main(String[] args) {
        String input = "23#(2)24#25#26#23#(3)";
        List<Integer> output = frequency(input);
        System.out.println(output);
    }
}
