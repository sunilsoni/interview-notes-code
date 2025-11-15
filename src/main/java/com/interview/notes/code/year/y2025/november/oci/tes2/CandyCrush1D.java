package com.interview.notes.code.year.y2025.november.oci.tes2;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class CandyCrush1D {

    // This method performs the candy crush logic on the input string
    public static String candyCrush(String ipStr) {
        // Repeat crushing until no change happens
        while (true) {
            // Stack to hold character and its count
            Stack<Pair> stack = new Stack<>();
            boolean crushed = false; // Flag to check if any crush happened

            // Loop through each character in the string
            for (char ch : ipStr.toCharArray()) {
                // If stack is empty, push the character with count 1
                if (stack.isEmpty() || stack.peek().ch != ch) {
                    stack.push(new Pair(ch, 1));
                } else {
                    // If same character as top, increase count
                    stack.peek().count++;
                }

                // If count reaches 3, pop it (crush it)
                if (!stack.isEmpty() && stack.peek().count >= 3) {
                    stack.pop();
                    crushed = true; // Mark that we crushed something
                }
            }

            // Build new string from stack
            StringBuilder sb = new StringBuilder();
            for (Pair p : stack) {
                for (int i = 0; i < p.count; i++) {
                    sb.append(p.ch); // Append character 'count' times
                }
            }

            // If no crush happened, return result
            if (!crushed) return sb.toString();

            // Else, repeat with new string
            ipStr = sb.toString();
        }
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // List of test cases: input and expected output
        List<TestCase> tests = Arrays.asList(
            new TestCase("aaabbbacd", "acd"),
            new TestCase("aabbbacd", "aacd"),
            new TestCase("aaabbbccc", ""),
            new TestCase("abcdddcba", "abccba"),
            new TestCase("a", "a"),
            new TestCase("aaa", ""),
            new TestCase("aabbaaa", "aabb"),
            new TestCase("aabbaaacccddd", "aabb"),
            new TestCase(generateLargeInput(), "") // large input test
        );

        // Run each test and print result
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
        return new String(arr); // Expected output is empty string
    }

    // Helper class to store character and its count
    static class Pair {
        char ch;
        int count;
        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    // Helper class to store test case input and expected output
    static class TestCase {
        String input;
        String expected;
        TestCase(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
