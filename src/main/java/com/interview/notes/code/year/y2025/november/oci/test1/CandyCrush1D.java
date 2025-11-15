package com.interview.notes.code.year.y2025.november.oci.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CandyCrush1D {

    // Crushes groups of 3 or more same characters repeatedly
    public static String candyCrush(String ipStr) {
        while (true) {
            StringBuilder sb = new StringBuilder(); // To build the new string
            int i = 0;
            boolean crushed = false; // Flag to track if any crush happened

            // Traverse the string
            while (i < ipStr.length()) {
                int j = i;
                // Move j forward while characters match
                while (j < ipStr.length() && ipStr.charAt(j) == ipStr.charAt(i)) {
                    j++;
                }
                int count = j - i; // Number of same characters

                if (count >= 3) {
                    crushed = true; // Mark crush
                    // Skip adding these characters
                } else {
                    // Add non-crushed characters to result
                    sb.append(ipStr, i, j);
                }

                i = j; // Move to next group
            }

            // If no crush happened, return result
            if (!crushed) return sb.toString();

            // Else repeat with new string
            ipStr = sb.toString();
        }
    }

    // Main method to test all cases
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
            new TestCase("aaabbbacd", "acd"),
            new TestCase("aabbbacd", "aacd"), // ✅ fixed
            new TestCase("aaabbbccc", ""),
            new TestCase("abcdddcba", "abccba"),
            new TestCase("a", "a"),
            new TestCase("aaa", ""),
            new TestCase("aabbaaa", "aabb"),
            new TestCase("aabbaaacccddd", "aabb"),
            new TestCase(generateLargeInput(), "") // large input test
        );

        IntStream.range(0, tests.size()).forEach(i -> {
            TestCase test = tests.get(i);
            String result = candyCrush(test.input);
            boolean pass = result.equals(test.expected);
            System.out.println("Test " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  Input:    " + test.input);
                System.out.println("  Expected: " + test.expected);
                System.out.println("  Got:      " + result);
            }
        });
    }

    // Generates a large input string with 100000 'a's
    static String generateLargeInput() {
        char[] arr = new char[100000];
        Arrays.fill(arr, 'a');
        return new String(arr); // Expected output is ""
    }

    // Helper class for test cases
    static class TestCase {
        String input;
        String expected;
        TestCase(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
