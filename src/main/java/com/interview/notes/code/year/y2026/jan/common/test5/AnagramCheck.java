package com.interview.notes.code.year.y2026.jan.common.test5;

public class AnagramCheck {

    public static boolean isAnagram(String str1, String str2) {
        // 1. Remove whitespace and convert to lowercase for consistent comparison
        String s1 = str1.replaceAll("\\s", "").toLowerCase();
        String s2 = str2.replaceAll("\\s", "").toLowerCase();

        // 2. If lengths differ, they cannot be anagrams
        if (s1.length() != s2.length()) {
            return false;
        }

        // 3. Create a frequency array for 26 English lowercase letters
        // Using an array is faster than a HashMap for simple character sets
        int[] charCounts = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            // Increment for char from s1
            charCounts[s1.charAt(i) - 'a']++;
            // Decrement for char from s2
            charCounts[s2.charAt(i) - 'a']--;
        }

        // 4. Check if all counts are zero
        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String word1 = "Listen";
        String word2 = "Silent";
        
        System.out.println("Are '" + word1 + "' and '" + word2 + "' anagrams? " 
                           + isAnagram(word1, word2)); // Output: true

        String word3 = "Hello";
        String word4 = "World";
        
        System.out.println("Are '" + word3 + "' and '" + word4 + "' anagrams? " 
                           + isAnagram(word3, word4)); // Output: false
    }
}