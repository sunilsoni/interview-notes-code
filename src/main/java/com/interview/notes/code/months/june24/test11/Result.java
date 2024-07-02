package com.interview.notes.code.months.june24.test11;

import java.util.ArrayList;
import java.util.List;

public class Result {

    public static List<Integer> frequency(String s) {
        int[] freq = new int[26]; // Frequency array for 'a' to 'z'
        int n = s.length();
        int i = 0;

        while (i < n) {
            int num = 0;
            boolean isSharp = false;

            // Read the number part
            while (i < n && Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
                i++;
                if (i < n && s.charAt(i) == '#') {
                    isSharp = true;
                    i++;
                    break;
                }
            }

            // Adjust for 1-based index in encoding
            num--;

            // Check if there is a multiplier
            int count = 1;
            if (i < n && s.charAt(i) == '(') {
                i++; // skip '('
                int start = i;
                while (i < n && s.charAt(i) != ')') {
                    i++;
                }
                count = Integer.parseInt(s.substring(start, i));
                i++; // skip ')'
            }

            // Update the frequency array
            freq[num] += count;
        }

        // Convert array to list for the expected return type
        List<Integer> result = new ArrayList<>();
        for (int j : freq) {
            result.add(j);
        }
        return result;
    }

    public static void main(String[] args) {
        // Example usage
        String encoded = "1226#24#";
        List<Integer> frequencies = frequency(encoded);
        System.out.println(frequencies);
    }
}
