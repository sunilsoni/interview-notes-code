package com.interview.notes.code.months.nov24.test16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnagramDifference {

    /*
     * Complete the 'getMinimumDifference' function below.
     *
     * The function is expected to return a List<Integer>.
     * The function accepts following parameters:
     *  1. List<String> a
     *  2. List<String> b
     */

    public static List<Integer> getMinimumDifference(List<String> a, List<String> b) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            String first = a.get(i);
            String second = b.get(i);

            // If lengths are not equal, cannot form anagrams
            if (first.length() != second.length()) {
                result.add(-1);
                continue;
            }

            int[] freq = new int[26];

            // Count frequency of characters in first string
            for (char c : first.toCharArray()) {
                freq[c - 'a']++;
            }

            // Subtract frequency based on second string
            for (char c : second.toCharArray()) {
                freq[c - 'a']--;
            }

            // Sum the positive differences
            int modifications = 0;
            for (int count : freq) {
                if (count > 0) {
                    modifications += count;
                }
            }

            result.add(modifications);
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Input 1
        testCases.add(new TestCase(
                Arrays.asList("tea", "tea", "act"),
                Arrays.asList("ate", "toe", "acts"),
                Arrays.asList(0, 1, -1)
        ));

        // Sample Input 2 (Custom)
        testCases.add(new TestCase(
                Arrays.asList("a", "jk", "abb", "mn", "abc"),
                Arrays.asList("bb", "kj", "bbc", "op", "def"),
                Arrays.asList(-1, 0, 1, 2, 3)
        ));

        // Additional test cases for edge scenarios and large data
        // Edge Case: Empty Strings
        testCases.add(new TestCase(
                Arrays.asList("", ""),
                Arrays.asList("", ""),
                Arrays.asList(0, 0)
        ));

        // Large Input Case
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb1.append('a');
            sb2.append('b');
        }
        testCases.add(new TestCase(
                Arrays.asList(sb1.toString()),
                Arrays.asList(sb2.toString()),
                Arrays.asList(10000)
        ));

        // Run test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Integer> output = getMinimumDifference(tc.a, tc.b);
            if (output.equals(tc.expectedOutput)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected Output: " + tc.expectedOutput);
                System.out.println("Actual Output:   " + output);
            }
        }

        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }
}

class TestCase {
    List<String> a;
    List<String> b;
    List<Integer> expectedOutput;

    public TestCase(List<String> a, List<String> b, List<Integer> expectedOutput) {
        this.a = a;
        this.b = b;
        this.expectedOutput = expectedOutput;
    }
}
