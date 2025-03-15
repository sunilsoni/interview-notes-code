package com.interview.notes.code.year.y2025.march.common.test15;


import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*

**Problem Name: "Unique Tuples"**
(Please do not remove this line)

### Instructions to Candidate:
1. Run this code in the REPL to observe its behavior. The execution entry point is `main()`.
2. Consider adding some additional tests in `doTestsPass()`.
3. Implement `uniqueTuples()` correctly.
4. If time permits, try to improve your implementation.

---

### Given Task:
Implement a method `uniqueTuples(String input, int len)` that extracts all unique substrings (tuples) of the given size from the input string.

 */
public class Solution {

    /**
     * Extracts all unique substrings (tuples) of the given length from the input string.
     * Uses Java 8 streams for concise processing.
     *
     * @param input The input string from which to extract substrings.
     * @param len   The length of each tuple (substring) to extract.
     * @return A HashSet containing all unique substrings of length 'len'.
     */
    public static HashSet<String> uniqueTuples(String input, int len) {
        // Create a HashSet to hold the unique substrings.
        HashSet<String> result = new HashSet<>();

        // Handle edge cases:
        // 1. If the input string is null.
        // 2. If len is less than or equal to 0.
        // 3. If len is greater than the input length (cannot form any tuple).
        if (input == null || len <= 0 || len > input.length()) {
            return result; // Returns an empty set in these cases.
        }

        // Use IntStream to iterate over valid starting positions in the string.
        // The range is from 0 to (input.length() - len) inclusive.
        result = IntStream.range(0, input.length() - len + 1)
                // For each index, extract the substring of length 'len'.
                .mapToObj(i -> input.substring(i, i + len))
                // Collect the substrings into a HashSet to ensure uniqueness.
                .collect(Collectors.toCollection(HashSet::new));

        return result;
    }

    /**
     * Tests the uniqueTuples() method with multiple test cases.
     * Each test prints pass/fail status.
     *
     * @return true if all tests pass; false otherwise.
     */
    public static boolean doTestsPass() {
        // Test case 1: Basic test with overlapping tuples.
        // For "aab" with len = 2, expected unique tuples are "aa" and "ab".
        if (!uniqueTuples("aab", 2).equals(new HashSet<String>() {{
            add("aa");
            add("ab");
        }})) {
            System.out.println("Test 1 failed.");
            return false;
        }

        // Test case 2: Input with all same characters.
        // For "aaaa" with len = 2, only one unique tuple "aa" is expected.
        if (!uniqueTuples("aaaa", 2).equals(new HashSet<String>() {{
            add("aa");
        }})) {
            System.out.println("Test 2 failed.");
            return false;
        }

        // Test case 3: Input with distinct characters.
        // For "abcdef" with len = 3, expected tuples are "abc", "bcd", "cde", and "def".
        if (!uniqueTuples("abcdef", 3).equals(new HashSet<String>() {{
            add("abc");
            add("bcd");
            add("cde");
            add("def");
        }})) {
            System.out.println("Test 3 failed.");
            return false;
        }

        // Test case 4: When tuple length is greater than the string length.
        // For "abc" with len = 4, no tuple can be formed so the result should be empty.
        if (!uniqueTuples("abc", 4).equals(new HashSet<String>())) {
            System.out.println("Test 4 failed.");
            return false;
        }

        // Test case 5: When tuple length is zero (invalid scenario).
        // Expected output is an empty set.
        if (!uniqueTuples("abc", 0).equals(new HashSet<String>())) {
            System.out.println("Test 5 failed.");
            return false;
        }

        // Test case 6: Handling large input.
        // Build a large string of 10,000 "a" characters.
        // Since all characters are the same, for len = 5 the only unique tuple is "aaaaa".
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        if (!uniqueTuples(sb.toString(), 5).equals(new HashSet<String>() {{
            add("aaaaa");
        }})) {
            System.out.println("Test 6 failed.");
            return false;
        }

        System.out.println("All tests passed.");
        return true;
    }

    /**
     * Main method: the program entry point.
     * It runs the test cases.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        doTestsPass();
    }
}