package com.interview.notes.code.year.y2024.aug24.amz.test16;

public class Solution1 {

    public static String getSpecialString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Start from the rightmost character
        for (int i = n - 1; i >= 0; i--) {
            // Try to increment the current character
            for (char c = (char) (chars[i] + 1); c <= 'z'; c++) {
                // Check if the new character is valid
                if ((i == 0 || c != chars[i - 1]) && (i == n - 1 || c != chars[i + 1])) {
                    chars[i] = c;

                    // Fill the rest with the smallest possible characters
                    for (int j = i + 1; j < n; j++) {
                        for (char nc = 'a'; nc <= 'z'; nc++) {
                            if (nc != chars[j - 1] && (j == n - 1 || nc != chars[j + 1])) {
                                chars[j] = nc;
                                break;
                            }
                        }
                    }

                    return new String(chars);
                }
            }
        }

        // If no solution found
        return "-1";
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getSpecialString("abbd"));  // Expected: abca Actual: abbe
        System.out.println(getSpecialString("abccde"));  // Expected: abcdab Actual: abccdf
        System.out.println(getSpecialString("zzab"));  // Expected: -1 Actual: zzac

        // Additional test cases
        System.out.println(getSpecialString("a"));  // Expected: b  Actual: b
        System.out.println(getSpecialString("z"));  // Expected: -1  Actual: -1
        System.out.println(getSpecialString("az"));  // Expected: ba  Actual: -ba
        System.out.println(getSpecialString("zz"));  // Expected: -1  Actual: -1
        System.out.println(getSpecialString("abcdefghijklmnopqrstuvwxyz"));  // Expected: -1
    }
}
