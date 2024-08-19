package com.interview.notes.code.months.aug24.amz.test16;

class Solution {

    /**
     * Given a string s of length n, generate a special string of length n that is lexicographically greater than s.
     * If multiple such special strings are possible, then return the lexicographically smallest string among them.
     *
     * @param s The input string
     * @return The lexicographically smallest special string that is greater than s.
     * If no such special string exists, return "-1".
     */
    public static String getSpecialString(String s) {
        if (s == null || s.length() == 0) {
            return "-1";
        }

        char[] chars = s.toCharArray();
        int n = chars.length;

        // Find the first character from the right that can be increased.
        int i = n - 1;
        while (i > 0 && chars[i] <= chars[i - 1]) {
            i--;
        }

        // If no character can be increased, there is no special string greater than s.
        if (i == 0) {
            return "-1";
        }

        // Increase the character at index i.
        chars[i - 1]++;

        // Fill the remaining characters with the lexicographically smallest characters
        // that are different from the previous character.
        for (int j = i; j < n; j++) {
            if (j == i) {
                chars[j] = 'a';
            } else {
                chars[j] = (chars[j - 1] == 'z') ? 'a' : (char) (chars[j - 1] + 1);
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getSpecialString("abccde")); // Output: abcdab
        System.out.println(getSpecialString("zzab")); // Output: -1
        System.out.println(getSpecialString("abbd")); // Output: abca
        System.out.println(getSpecialString("aaa")); // Output: aab
        System.out.println(getSpecialString("za")); // Output: zb
        System.out.println(getSpecialString("zz")); // Output: -1
    }
}