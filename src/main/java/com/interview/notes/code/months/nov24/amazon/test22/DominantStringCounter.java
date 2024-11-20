package com.interview.notes.code.months.nov24.amazon.test22;

import java.util.HashMap;

public class DominantStringCounter {

    /**
     * Calculates the number of dominant substrings in the given string.
     *
     * @param s the input string consisting of lowercase letters 'a' to 'j'
     * @return the number of dominant substrings
     */
    public static long getDominantStringCount(String s) {
        int n = s.length();
        long result = 0;

        // Since the string consists of letters 'a' to 'j', we have only 10 possible characters.
        // We can process each character separately.
        for (char ch = 'a'; ch <= 'j'; ch++) {
            result += countDominantSubstringsForChar(s, ch);
        }

        return result;
    }

    /**
     * Counts the number of dominant substrings where the specified character occurs
     * exactly half the length of the substring.
     *
     * @param s  the input string
     * @param ch the character to consider
     * @return the number of dominant substrings for the character
     */
    private static long countDominantSubstringsForChar(String s, char ch) {
        int n = s.length();
        long count = 0;

        // Map to store the frequency of key values
        HashMap<Integer, Integer> map = new HashMap<>();
        int key = 0;

        // Initialize the map with key 0 occurring once
        map.put(key, 1);

        // Cumulative count of character ch
        int cumulativeCount = 0;

        for (int i = 0; i < n; i++) {
            // Update cumulative count
            if (s.charAt(i) == ch) {
                cumulativeCount++;
            }

            // Calculate key
            key = 2 * cumulativeCount - i;

            // If the key exists in the map, add the frequency to the count
            count += map.getOrDefault(key, 0);

            // Update the map with the current key
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return count;
    }

    /**
     * Main method to test the getDominantStringCount function with various test cases.
     */
    public static void main(String[] args) {
        // Test cases
        String[] testStrings = {
                "idafddfii",
                "aaaaid",
                "abab",
                "aabbccddeeffgghhiijj",
                "abcabcabcabc",
                "a",
                "aa",
                "aaa",
                "abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde"
        };

        // Expected results
        long[] expectedResults = {
                13,
                3,
                4,
                55,
                12,
                0,
                1,
                2,
                25
        };

        boolean allPassed = true;

        for (int i = 0; i < testStrings.length; i++) {
            long result = getDominantStringCount(testStrings[i]);
            boolean passed = result == expectedResults[i];
            System.out.println("Test case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Input:    " + testStrings[i]);
                System.out.println("  Expected: " + expectedResults[i]);
                System.out.println("  Got:      " + result);
                allPassed = false;
            }
        }

        if (allPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }
}
