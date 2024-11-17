package com.interview.notes.code.months.nov24.amazon.test21;

import java.util.*;

public class DominantStrings {

    // Rolling hash base and modulus
    private static final int BASE = 911;
    private static final int MOD = 1_000_000_007;

    public static int getDominantStringCount(String s) {
        int n = s.length();
        int maxLen = Math.min(n, 200); // Limit to a reasonable maximum length
        Set<Long> dominantSubstrings = new HashSet<>();

        // Precompute powers of BASE modulo MOD
        long[] pow = new long[maxLen + 1];
        pow[0] = 1;
        for (int i = 1; i <= maxLen; i++) {
            pow[i] = (pow[i - 1] * BASE) % MOD;
        }

        for (int L = 2; L <= maxLen; L += 2) {
            int[] freq = new int[26];
            long hash = 0;

            // Initialize frequency and hash for the first window
            for (int i = 0; i < L; i++) {
                char c = s.charAt(i);
                freq[c - 'a']++;
                hash = (hash * BASE + c) % MOD;
            }

            if (isDominant(freq, L)) {
                dominantSubstrings.add(hash);
            }

            // Slide the window
            for (int i = L; i < n; i++) {
                // Remove the leftmost character
                char leftChar = s.charAt(i - L);
                freq[leftChar - 'a']--;
                hash = (hash - pow[L - 1] * leftChar % MOD + MOD) % MOD;

                // Add the new rightmost character
                char rightChar = s.charAt(i);
                freq[rightChar - 'a']++;
                hash = (hash * BASE + rightChar) % MOD;

                if (isDominant(freq, L)) {
                    dominantSubstrings.add(hash);
                }
            }
        }

        return dominantSubstrings.size();
    }

    private static boolean isDominant(int[] freq, int L) {
        int half = L / 2;
        for (int f : freq) {
            if (f == half) {
                return true;
            }
        }
        return false;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        String[] testInputs = {
                "aaaaaid",
                "aidfg",
                "dfdffdfi"
        };
        int[] expectedOutputs = {
                3,
                4,
                13
        };

        boolean allTestsPassed = true;

        for (int i = 0; i < testInputs.length; i++) {
            int result = getDominantStringCount(testInputs[i]);
            System.out.println("Test Case " + (i + 1) + ": Input = \"" + testInputs[i] + "\"");
            System.out.println("Expected Output: " + expectedOutputs[i]);
            System.out.println("Actual Output  : " + result);
            if (result == expectedOutputs[i]) {
                System.out.println("Result: PASS\n");
            } else {
                System.out.println("Result: FAIL\n");
                allTestsPassed = false;
            }
        }

        // Additional test case with large input
        StringBuilder sb = new StringBuilder();
        int largeInputSize = 100000;
        for (int i = 0; i < largeInputSize; i++) {
            sb.append('a');
        }
        String largeInput = sb.toString();

        long startTime = System.currentTimeMillis();
        int largeInputResult = getDominantStringCount(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test: Length = " + largeInputSize);
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
        System.out.println("Dominant Substrings Count: " + largeInputResult);

        if (allTestsPassed) {
            System.out.println("\nAll test cases passed.");
        } else {
            System.out.println("\nSome test cases failed.");
        }
    }
}
