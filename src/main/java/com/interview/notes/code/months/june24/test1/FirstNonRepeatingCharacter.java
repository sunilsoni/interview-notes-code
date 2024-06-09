package com.interview.notes.code.months.june24.test1;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {

    // Method to find the first non-repeating character in a string
    public static Character findFirstNonRepeatingCharacter(String str) {
        // Create a HashMap to store the frequency of each character
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Traverse the string and count the occurrences of each character
        for (char ch : str.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        // Traverse the string again to find the first non-repeating character
        for (char ch : str.toCharArray()) {
            if (charCountMap.get(ch) == 1) {
                return ch;
            }
        }

        // If no non-repeating character is found, return null
        return null;
    }

    public static void main(String[] args) {
        // Example strings
        String str1 = "hello";
        String str2 = "aabccd";
        String str3 = "welcome to java world";

        // Find and print the first non-repeating characters
        System.out.println("First non-repeating character in \"" + str1 + "\": " + findFirstNonRepeatingCharacter(str1));
        System.out.println("First non-repeating character in \"" + str2 + "\": " + findFirstNonRepeatingCharacter(str2));
        System.out.println("First non-repeating character in \"" + str3 + "\": " + findFirstNonRepeatingCharacter(str3));
    }
}
