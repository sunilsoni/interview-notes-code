package com.interview.notes.code.year.y2024.april24.test9;


//Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
public class FirstUniqChar {
    public int firstUniqChar(String s) {
        // Create an array to store the frequency of each character
        int[] charCount = new int[26]; // Assuming lowercase English letters

        // First pass: Count the frequency of each character
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Second pass: Find the first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (charCount[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        // If no non-repeating character is found, return -1
        return -1;
    }
}
