package com.interview.notes.code.months.march24.test11;

class Result1 {
    public static String getSmallestPalindrome(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] indices = new int[n];
        int count = 0;
        
        // Find the indices of '?' characters
        for (int i = 0; i < n; i++) {
            if (chars[i] == '?') {
                indices[count++] = i;
            }
        }
        
        // Try to form a palindrome by replacing '?' with 'a'
        return tryPalindrome(chars, indices, count, 0, new char[]{'a'});
    }
    
    private static String tryPalindrome(char[] chars, int[] indices, int count, int start, char[] replacement) {
        if (start == count) {
            // Replace '?' with the current replacement
            for (int i = 0; i < count; i++) {
                chars[indices[i]] = replacement[0];
            }
            
            // Check if the string is a palindrome
            if (isPalindrome(chars)) {
                return new String(chars);
            }
            
            return "-1";
        }
        
        // Try replacing the current '?' with 'a'
        replacement[0] = 'a';
        String result = tryPalindrome(chars, indices, count, start + 1, replacement);
        if (!result.equals("-1")) {
            return result;
        }
        
        // If 'a' doesn't work, try 'b', 'c', ..., 'z'
        for (char c = 'b'; c <= 'z'; c++) {
            replacement[0] = c;
            result = tryPalindrome(chars, indices, count, start + 1, replacement);
            if (!result.equals("-1")) {
                return result;
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
        String input = "axxb??";
        System.out.println(getSmallestPalindrome(input));


        String input1 = "a?rt?????";
        System.out.println(getSmallestPalindrome(input1));
    }
}