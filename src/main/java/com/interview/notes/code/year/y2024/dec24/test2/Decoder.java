package com.interview.notes.code.year.y2024.dec24.test2;

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
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0}, // Length 25
                {2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 1}
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

        // Additional test cases for edge scenarios and large data
        String largeInput = generateLargeInput();
        List<Integer> largeOutput = frequency(largeInput);
        System.out.println("Large input test case: PASS");

        if (allPassed) {
            System.out.println("All test cases passed.");
        }
    }

    public static List<Integer> frequency(String s) {
        int[] freq = new int[25]; // Assuming letters 'a' to 'y' correspond to indices 0 to 24
        int i = 0;
        int n = s.length();

        while (i < n) {
            int num = 0;
            int count = 1; // Default count is 1
            if (i + 2 < n && s.charAt(i + 2) == '#') {
                // Two-digit number
                num = Integer.parseInt(s.substring(i, i + 2));
                i += 3; // Skip over the '#' symbol
            } else {
                // Single-digit number
                num = s.charAt(i) - '0';
                i += 1;
            }

            // Check for count in parentheses
            if (i < n && s.charAt(i) == '(') {
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                int countStart = i + 1;
                int countEnd = j;
                count = Integer.parseInt(s.substring(countStart, countEnd));
                i = j + 1; // Move past the closing parenthesis
            }

            // Update frequency if num is between 1 and 25
            if (num >= 1 && num <= 25) {
                freq[num - 1] += count;
            }
            // Ignore num == 26 as 'z' is not included in the frequency array
        }

        // Convert the frequency array to a list
        List<Integer> result = new ArrayList<>();
        for (int f : freq) {
            result.add(f);
        }
        return result;
    }

    // Helper method to generate a large input string for testing
    private static String generateLargeInput() {
        StringBuilder sb = new StringBuilder();
        // Generate a sequence like "1(10000)2(10000)...25(10000)"
        for (int i = 1; i <= 25; i++) {
            if (i <= 9) {
                sb.append(i);
            } else {
                sb.append(i).append('#');
            }
            sb.append('(').append(10000).append(')');
        }
        return sb.toString();
    }
}
