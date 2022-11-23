package com.interview.notes.code.LeetCode;

class Solution {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abamadam"));
    }

    public static String longestPalindrome(String s) {

        int len = s.length();

        int maxLen = 0;
        String res = "";

        //ConcurrentHashMap map = new ConcurrentHashMap();
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {

                if (s.charAt(left) == s.charAt(right) && isPalin(s, left + 1, right - 1)) {
                    if (maxLen < right - left + 1) {
                        maxLen = right - left + 1;
                        res = s.substring(left, right + 1);
                    }
                }
            }
        }

        return res;
    }


    public static boolean isPalin(String s, int left, int right) {

        if (left >= right) {
            return true;
        }

        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        if (right - left <= 2) {
            return true;
        }

        return isPalin(s, left + 1, right - 1);

    }

}