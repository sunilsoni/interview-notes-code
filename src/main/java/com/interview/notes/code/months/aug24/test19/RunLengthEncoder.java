package com.interview.notes.code.months.aug24.test19;

/*
Implement a simple Run Length Encoder, shortening sequences of the same characters.
For example, ten As would be encoded as A10.
Input aaabbecca
Output.
a3b2c3a1
 */
public class RunLengthEncoder {

    public static String encode(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        char currentChar = input.charAt(0);
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                count++;
            } else {
                result.append(currentChar).append(count);
                currentChar = input.charAt(i);
                count = 1;
            }
        }

        result.append(currentChar).append(count);
        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "aaabbecca",
                "aabbbcccc",
                "a",
                "",
                "aaaaaaaaaa",
                "abcdefg",
                "aabbccaabbcc"
        };

        for (String testCase : testCases) {
            System.out.println("Input: " + testCase);
            System.out.println("Output: " + encode(testCase));
            System.out.println();
        }
    }
}
