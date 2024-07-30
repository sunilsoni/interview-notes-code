package com.interview.notes.code.months.july24.test9;

import java.util.HashMap;
import java.util.Map;

public class CharacterFinder {

    public static void main(String[] args) {
        String input = "abracadabra";
        char[] result = findCharacters(input);
        System.out.println("First Non-Repeated Character: " + result[0]);
        System.out.println("First Repeated Character: " + result[1]);
    }

    public static char[] findCharacters(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        char firstNonRepeated = '\0'; // Null character as default if no non-repeated character is found
        char firstRepeated = '\0'; // Null character as default if no repeated character is found

        // Step 1: Count each character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Step 2: Find the first non-repeated character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charCount.get(c) == 1) {
                firstNonRepeated = c;
                break;
            }
        }

        // Step 3: Find the first repeated character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charCount.get(c) > 1) {
                firstRepeated = c;
                break;
            }
        }

        return new char[]{firstNonRepeated, firstRepeated};
    }
}
