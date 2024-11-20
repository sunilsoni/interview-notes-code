package com.interview.notes.code.months.nov24.amazon.test21;

public class DominantStringCounter {

    /**
     * Calculates the number of dominant substrings in the given string.
     *
     * @param s the input string consisting of lowercase letters 'a' to 'j'
     * @return the number of dominant substrings
     */
    public static long getDominantStringCount(String s) {
        int n = s.length();
        long count = 0;

        // Define the maximum substring length to consider
        int MAX_LEN = 200;

        // For each starting index
        for (int i = 0; i < n; i++) {
            int[] freq = new int[10]; // Since letters are 'a' to 'j'
            int maxFreq = 0;
            // Consider substrings starting at i with even lengths up to MAX_LEN
            for (int j = i; j < n && (j - i + 1) <= MAX_LEN; j++) {
                int idx = s.charAt(j) - 'a';
                freq[idx]++;
                maxFreq = Math.max(maxFreq, freq[idx]);
                int len = j - i + 1;
                if (len % 2 == 0) {
                    if (maxFreq == len / 2) {
                        count++;
                    }
                }
            }
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
