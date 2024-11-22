package com.interview.notes.code.months.nov24.test17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnagramDifferenceTester {

    /*
     * Complete the 'getMinimumDifference' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY a
     *  2. STRING_ARRAY b
     */

    public static List<Integer> getMinimumDifference(List<String> a, List<String> b) {
        List<Integer> result = new ArrayList<>();
        int n = a.size();
        for (int i = 0; i < n; i++) {
            String strA = a.get(i);
            String strB = b.get(i);
            if (strA.length() != strB.length()) {
                result.add(-1);
                continue;
            }

            int[] freqA = new int[26];
            int[] freqB = new int[26];

            for (char c : strA.toCharArray()) {
                freqA[c - 'a']++;
            }

            for (char c : strB.toCharArray()) {
                freqB[c - 'a']++;
            }

            int modifications = 0;
            for (int j = 0; j < 26; j++) {
                modifications += Math.abs(freqA[j] - freqB[j]);
            }

            // Since each modification affects one character in one string,
            // the total modifications required is half the total difference.
            // However, as per the problem statement, it's the number of characters to be modified in either string,
            // so we can take modifications / 2.
            // But according to the examples, it's taking the total difference.
            // To align with the examples, we'll use modifications / 2.
            modifications /= 2;
            result.add(modifications);
        }
        return result;
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                Arrays.asList("tea", "tea", "act"),
                Arrays.asList("ate", "toe", "acts"),
                Arrays.asList(0, 1, -1)
        ));

        // Example 2 (Custom)
        testCases.add(new TestCase(
                Arrays.asList("a", "jk", "abb", "mn", "abc"),
                Arrays.asList("bb", "kj", "bbc", "op", "def"),
                Arrays.asList(-1, 0, 1, 2, 3)
        ));

        // Edge Case 1: Both strings empty
        testCases.add(new TestCase(
                Arrays.asList(""),
                Arrays.asList(""),
                Arrays.asList(0)
        ));

        // Edge Case 2: One string empty, one non-empty
        testCases.add(new TestCase(
                Arrays.asList(""),
                Arrays.asList("a"),
                Arrays.asList(-1)
        ));

        // Edge Case 3: Strings with all identical characters
        testCases.add(new TestCase(
                Arrays.asList("aaaa", "aaaa"),
                Arrays.asList("aaaa", "aaaa"),
                Arrays.asList(0, 0)
        ));

        // Edge Case 4: Strings with no overlapping characters
        testCases.add(new TestCase(
                Arrays.asList("abc", "def"),
                Arrays.asList("xyz", "uvw"),
                Arrays.asList(3, 3)
        ));

        // Large Input Case
        String largeA = generateString('a', 10000);
        String largeB = generateString('a', 10000);
        testCases.add(new TestCase(
                Arrays.asList(largeA),
                Arrays.asList(largeB),
                Arrays.asList(0)
        ));

        // Large Input Case with modifications
        String largeC = generateString('a', 9999) + "b";
        String largeD = generateString('a', 10000);
        testCases.add(new TestCase(
                Arrays.asList(largeC),
                Arrays.asList(largeD),
                Arrays.asList(1)
        ));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Integer> output = getMinimumDifference(tc.a, tc.b);
            if (output.equals(tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got     : " + output);
            }
        }
        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    // Helper method to generate a string with a specific character repeated 'count' times
    private static String generateString(char c, int count) {
        char[] chars = new char[count];
        Arrays.fill(chars, c);
        return new String(chars);
    }

    // Inner class to represent a test case
    static class TestCase {
        List<String> a;
        List<String> b;
        List<Integer> expected;

        TestCase(List<String> a, List<String> b, List<Integer> expected) {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }
    }
}
