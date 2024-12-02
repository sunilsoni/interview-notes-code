package com.interview.notes.code.year.y2024.may24.test9;

import java.util.Arrays;

class Solution5 {
    /**
     * 898
     * 00900
     * 0000
     * 5
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        System.out.println(sol.solution("39878"));  // Should return "898"
        System.out.println(sol.solution("00900"));  // Should return "9"
        System.out.println(sol.solution("0000"));   // Should return "0"
        System.out.println(sol.solution("54321"));  // Should return "5"
    }

    public String solution(String S) {
        char[] digits = S.toCharArray();
        Arrays.sort(digits);
        StringBuilder palindrome = new StringBuilder();
        int i = 0;
        if (digits[0] == '0') {
            while (i < digits.length && digits[i] == '0') {
                i++;
            }
            if (i == digits.length) {
                return "0";
            }
            palindrome.append(digits[i]);
            i++;
        }
        while (i < digits.length) {
            palindrome.insert(0, digits[i]);
            palindrome.append(digits[i]);
            i++;
        }
        if (palindrome.charAt(0) == '0') {
            palindrome.deleteCharAt(0);
        }
        return palindrome.toString();
    }
}
