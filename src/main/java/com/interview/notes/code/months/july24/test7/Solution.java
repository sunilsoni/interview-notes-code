package com.interview.notes.code.months.july24.test7;

public class Solution {
    public String solution(String S) {
        char[] chars = S.toCharArray();
        int n = chars.length;

        // Traverse from both ends towards the center
        for (int i = 0; i < n / 2; i++) {
            int j = n - i - 1; // Corresponding character from the end

            if (chars[i] == '?' && chars[j] == '?') {
                // Both are '?', replace both with 'a' (or any letter)
                chars[i] = chars[j] = 'a';
            } else if (chars[i] == '?') {
                // Replace '?' at i with character at j
                chars[i] = chars[j];
            } else if (chars[j] == '?') {
                // Replace '?' at j with character at i
                chars[j] = chars[i];
            } else if (chars[i] != chars[j]) {
                // Characters do not match and none is '?', can't form a palindrome
                return "NO";
            }
        }

        // If the length is odd, handle the middle character
        if (n % 2 == 1 && chars[n / 2] == '?') {
            chars[n / 2] = 'a'; // or any letter
        }

        return new String(chars);
    }
}
