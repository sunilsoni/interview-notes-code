package com.interview.notes.code.year.y2025.july.common.test11;

public class Solution {
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        
        // Convert to lowercase and remove non-alphanumeric characters
        str = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
