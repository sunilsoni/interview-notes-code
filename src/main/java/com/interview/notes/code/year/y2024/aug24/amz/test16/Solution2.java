package com.interview.notes.code.year.y2024.aug24.amz.test16;

public class Solution2 {

    public static String getSpecialString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Start from the rightmost character
        for (int i = n - 1; i >= 0; i--) {
            char original = chars[i];
            // Try to increment the current character
            for (char c = (char) (original + 1); c <= 'z'; c++) {
                // Check if the new character is valid
                if ((i == 0 || c != chars[i - 1]) && (i == n - 1 || c != chars[i + 1])) {
                    chars[i] = c;

                    // Fill the rest with the smallest possible characters
                    boolean valid = true;
                    for (int j = i + 1; j < n; j++) {
                        char smallest = findSmallestValid(chars, j);
                        if (smallest == 0) {
                            valid = false;
                            break;
                        }
                        chars[j] = smallest;
                    }

                    if (valid) {
                        return new String(chars);
                    }
                }
            }
            // Restore original character if no valid solution found
            chars[i] = original;
        }

        // If no solution found
        return "-1";
    }

    private static char findSmallestValid(char[] chars, int index) {
        for (char c = 'a'; c <= 'z'; c++) {
            if ((index == 0 || c != chars[index - 1]) &&
                    (index == chars.length - 1 || c != chars[index + 1])) {
                return c;
            }
        }
        return 0; // No valid character found
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getSpecialString("abbd"));  // Expected: abca
        System.out.println(getSpecialString("abccde"));  // Expected: abcdab
        System.out.println(getSpecialString("zzab"));  // Expected: -1

        // Additional test cases
        System.out.println(getSpecialString("a"));  // Expected: b
        System.out.println(getSpecialString("z"));  // Expected: -1
        System.out.println(getSpecialString("az"));  // Expected: ba
        System.out.println(getSpecialString("zz"));  // Expected: -1
        System.out.println(getSpecialString("abcdefghijklmnopqrstuvwxyz"));  // Expected: -1
    }
}
