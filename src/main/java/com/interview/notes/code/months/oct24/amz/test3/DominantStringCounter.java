package com.interview.notes.code.months.oct24.amz.test3;

import java.util.*;
public class DominantStringCounter {

    public static void main(String[] args) {
        // List of test cases and their expected results
        List<String> testCases = Arrays.asList(
                "aaaaaid",    // Sample Input 0
                "aidfg",      // Sample Input 1
                "dfdffdfi"    // Sample Input 2
                // You can add more test cases here
        );

        List<Integer> expectedResults = Arrays.asList(
                3,  // Expected Output 0
                4,  // Expected Output 1
                13  // Expected Output 2
                // Add expected results for additional test cases
        );

        // Process each test case
        for (int i = 0; i < testCases.size(); i++) {
            String s = testCases.get(i);
            int expected = expectedResults.get(i);
            int result = getDominantStringCount(s);

            // Output pass/fail for each test case
            if (result == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected: " + expected + ", Got: " + result + ")");
            }
        }
    }

    /**
     * Computes the number of distinct dominant substrings in the given string.
     *
     * @param s The input string to analyze.
     * @return The count of distinct dominant substrings.
     */
    public static int getDominantStringCount(String s) {
        int n = s.length();
        int maxLength = Math.min(n, 100); // Limit the maximum length to 100 for efficiency

        Set<String> dominantSubstrings = new HashSet<>();

        // Loop over all even lengths from 2 to maxLength
        for (int L = 2; L <= maxLength; L += 2) {
            int[] counts = new int[26]; // Counts of characters in the current window

            // Initialize counts for the first window of length L
            for (int i = 0; i < L; i++) {
                counts[s.charAt(i) - 'a']++;
            }

            // Slide the window through the string
            for (int i = 0; i <= n - L; i++) {
                // Check if any character has frequency equal to L / 2
                if (hasDominantCharacter(counts, L / 2)) {
                    String substring = s.substring(i, i + L);
                    dominantSubstrings.add(substring);
                }

                // Move the window forward if not at the end
                if (i + L < n) {
                    counts[s.charAt(i) - 'a']--;          // Remove the leftmost character
                    counts[s.charAt(i + L) - 'a']++;      // Add the new rightmost character
                }
            }
        }
        return dominantSubstrings.size();
    }

    /**
     * Checks if any character in the counts array has the specified frequency.
     *
     * @param counts The array of character counts.
     * @param frequency The frequency to check for.
     * @return True if any character has the specified frequency, false otherwise.
     */
    private static boolean hasDominantCharacter(int[] counts, int frequency) {
        for (int count : counts) {
            if (count == frequency) {
                return true;
            }
        }
        return false;
    }
}
