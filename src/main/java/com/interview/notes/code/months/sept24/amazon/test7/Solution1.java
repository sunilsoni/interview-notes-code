package com.interview.notes.code.months.sept24.amazon.test7;

import java.lang.*;

class Solution1 {

    /*
     * Complete the 'getLongestSubstring' function below.
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int getLongestSubstring(String s) {
        if (s.length() < 2) {
            return 0;
        }

        int maxLength = 0;
        int start = 0;
        int end = 1;

        while (end < s.length()) {
            if (s.charAt(start) < s.charAt(end)) {
                maxLength = Math.max(maxLength, end - start + 1);
                end++;
            } else {
                start = end;
                end++;
            }
        }
        return maxLength;
    }


    public static void main(String[] args) {
        // Test cases
        System.out.println(getLongestSubstring("abcd")); // Output: 4
        System.out.println(getLongestSubstring("fghbbadcba")); // Output: 5
        System.out.println(getLongestSubstring("aabbccdd"));  // Output: 0
        System.out.println(getLongestSubstring("zabcdzy"));  // Output: 5
    }
}