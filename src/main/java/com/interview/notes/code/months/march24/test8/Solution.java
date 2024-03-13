package com.interview.notes.code.months.march24.test8;


//NO
public class Solution {
    public static String getSmallestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] indices = new int[n];
        int count = 0;
        
        // Find the indices of '?' characters
        for (int i = 0; i < n; i++) {
            if (chars[i] == '?') {
                indices[count++] = i;
            }
        }
        
        // Try all possible replacements
        for (int mask = 0; mask < (1 << count); mask++) {
            char[] candidate = chars.clone();
            
            // Replace '?' with 'a' and check for palindrome
            for (int i = 0; i < count; i++) {
                if ((mask & (1 << i)) == 0) {
                    candidate[indices[i]] = 'a';
                } else {
                    candidate[indices[i]] = 'b';
                }
            }
            
            if (isPalindrome(candidate)) {
                return new String(candidate);
            }
        }
        
        return "-1";
    }
    
    private static boolean isPalindrome(char[] chars) {
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String input = "a?rt???";
        System.out.println(getSmallestPalindrome(input));

        String input1 = "ai??a?u";
        System.out.println(getSmallestPalindrome(input1));
    }

}