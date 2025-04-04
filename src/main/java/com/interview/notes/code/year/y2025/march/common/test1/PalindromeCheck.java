package com.interview.notes.code.year.y2025.march.common.test1;

public class PalindromeCheck {
    public static boolean isPalindrome(String str) {
        // Remove non-alphanumeric characters and convert to lowercase
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
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

    public static void main(String[] args) {
        String test1 = "A man, a plan, a canal: Panama";
        String test2 = "race a car";
        
        System.out.println(test1 + " is palindrome: " + isPalindrome(test1));
        System.out.println(test2 + " is palindrome: " + isPalindrome(test2));
    }
}
