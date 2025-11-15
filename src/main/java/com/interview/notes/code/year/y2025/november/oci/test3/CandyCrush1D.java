package com.interview.notes.code.year.y2025.november.oci.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CandyCrush1D {

    // Function to repeatedly crush sequences of 3 or more same consecutive characters
    public static String candyCrush(String ipStr) {
        // Continue until no more crushes can happen
        while (true) {
            StringBuilder sb = new StringBuilder(); // new string after crushing
            int i = 0;
            boolean crushed = false; // track if any crush happened this round

            // Traverse characters one by one
            while (i < ipStr.length()) {
                int j = i;

                // Move j forward while characters are the same
                while (j < ipStr.length() && ipStr.charAt(j) == ipStr.charAt(i)) {
                    j++;
                }

                int count = j - i; // length of current group

                // If group size >= 3, crush (skip adding)
                if (count >= 3) {
                    crushed = true; // mark that a crush occurred
                } else {
                    // Keep smaller groups (<3 same chars)
                    sb.append(ipStr, i, j);
                }

                // Move to the next group
                i = j;
            }

            // If no crush occurred, stop and return the result
            if (!crushed) {
                return sb.toString();
            }

            // Otherwise, repeat with the crushed result
            ipStr = sb.toString();
        }
    }

    // Test the candyCrush method with multiple cases
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                new TestCase("aaabbbacd", "acd"),
                new TestCase("aabbbacd", "aacd"),
                new TestCase("aaabbbccc", ""),
                new TestCase("abcdddcba", "abccba"),
                new TestCase("a", "a"),
                new TestCase("aaa", ""),
                new TestCase("aabbaaa", "aabb"),
                new TestCase("aabbaaacccddd", "aabb"),
                new TestCase(generateLargeInput(), "") // performance test
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

    // Generates large input with 100000 'a's for performance check
    static String generateLargeInput() {
        char[] arr = new char[100000];
        Arrays.fill(arr, 'a');
        return new String(arr); // expected: ""
    }

    // Helper inner class for test cases
    static class TestCase {
        String input;
        String expected;

        TestCase(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
