package com.interview.notes.code.months.june24.test11;

import java.util.ArrayList;
import java.util.List;

public class Result2 {
    public static List<Integer> frequency(String s) {
        int[] freq = new int[26]; // Array to hold frequencies of each character
        int i = 0;
        while (i < s.length()) {
            int num = 0;
            boolean isHash = false;

            // Decode the number or number with hash
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
                i++;
            }
            if (i < s.length() && s.charAt(i) == '#') {
                isHash = true;
                i++;
            }

            // Adjust for 0-based index of 'a'
            if (isHash) {
                num = num - 10; // For j to z (#)
            } else {
                num = num - 1; // For a to i (without #)
            }

            if (num < 0 || num > 25) {
                throw new IllegalArgumentException("Invalid character encoding: " + num);
            }

            int count = 1; // Default count is 1 unless specified
            if (i < s.length() && s.charAt(i) == '(') {
                i++; // Move past '('
                count = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    count = count * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i++; // Move past ')'
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
        // List of encoded strings from examples
        String[] encodedStrings = {
                "23#(2)24#25#26#23#(3)", // Example from Sample Input 3
                "2#10#(2)",              // Example from Sample Output 2 (Assuming 'b' and 'j')
                "1(2)2(3)",             // Example from Sample Output 1 (Assuming 'a' and 'b')
                "1#26#24#",             // Example from Sample Case 0 (Assuming 'a', 'z', and 'x')
                "1(2)23(3)"             // Example from Sample Case 1 (Assuming 'a' and 'w')
        };

        // Displaying the results
        for (String encoded : encodedStrings) {
            try {
                List<Integer> frequencies = frequency(encoded);
                System.out.println("Frequencies for \"" + encoded + "\": " + frequencies);
            } catch (Exception e) {
                System.out.println("Error processing the encoded string: " + e.getMessage());
            }
        }
    }
}
