package com.interview.notes.code.months.Aug23.test2;

import java.util.HashSet;
import java.util.Set;


//Write a Java function that takes a string as input and returns the length of the longest substring without repeating characters. For example, if the input is "abcabcbb", the output should be 3 ("abc").
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abccc"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(); // Length of the input string
        Set<Character> charSet = new HashSet<>(); // HashSet to ensure unique characters
        int ans = 0, i = 0, j = 0; // Result and pointers

        while (i < n && j < n) { // O(n) loop, where n is the string length
            if (!charSet.contains(s.charAt(j))) { // O(1) contains check
                charSet.add(s.charAt(j++)); // O(1) addition to HashSet
                ans = Math.max(ans, j - i); // O(1) max calculation
            } else {
                charSet.remove(s.charAt(i++)); // O(1) removal from HashSet
            }
        }

        return ans; // Return the result
    }

}
