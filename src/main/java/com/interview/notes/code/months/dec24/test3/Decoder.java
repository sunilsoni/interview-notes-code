package com.interview.notes.code.months.dec24.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Decoder {
    public static void main(String[] args) {
        // Test cases
        String[] inputs = {
            "1226#24#",
            "1(2)23(3)",
            "2110#(2)",
            "23#(2)24#25#26#23#(3)"
        };

        int[][] expectedOutputs = {
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 1, 0}
        };

        boolean allPassed = true;

        for (int i = 0; i < inputs.length; i++) {
            List<Integer> output = frequency(inputs[i]);
            if (Arrays.equals(output.stream().mapToInt(Integer::intValue).toArray(), expectedOutputs[i])) {
                System.out.println("Test case " + (i + 1) + ": PASS");
            } else {
                allPassed = false;
                System.out.println("Test case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + Arrays.toString(expectedOutputs[i]));
                System.out.println("Got     : " + output);
            }
        }

        if (allPassed) {
            System.out.println("All test cases passed.");
        }
    }

    public static List<Integer> frequency(String s) {
        int[] freq = new int[26]; // Indices 0 to 25 correspond to 'a' to 'z'
        int i = 0;
        int n = s.length();

        while (i < n) {
            int num = 0;
            int count = 1; // Default count is 1

            // Check for two-digit number with optional '#'
            if (i + 1 < n && Character.isDigit(s.charAt(i)) && Character.isDigit(s.charAt(i + 1))) {
                num = Integer.parseInt(s.substring(i, i + 2));
                if (num <= 26) {
                    i += 2;
                    // Skip '#' if present
                    if (i < n && s.charAt(i) == '#') {
                        i++;
                    }
                } else {
                    // Not a valid letter, treat as single-digit
                    num = s.charAt(i) - '0';
                    i++;
                }
            } else if (Character.isDigit(s.charAt(i))) {
                // Single-digit number
                num = s.charAt(i) - '0';
                i++;
            } else {
                // Invalid character, skip
                i++;
                continue;
            }

            // Check for count in parentheses
            if (i < n && s.charAt(i) == '(') {
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                count = Integer.parseInt(s.substring(i + 1, j));
                i = j + 1; // Move past ')'
            }

            // Update frequency
            if (num >= 1 && num <= 26) {
                freq[num - 1] += count;
            }
        }

        // Convert frequency array to list
        List<Integer> result = new ArrayList<>();
        for (int f : freq) {
            result.add(f);
        }
        return result;
    }
}
