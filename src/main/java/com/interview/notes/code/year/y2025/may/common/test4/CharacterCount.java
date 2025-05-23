package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.HashMap;
import java.util.Map;

public class CharacterCount {
    public static void main(String[] args) {
        String s = "Code to challenge";

        // Convert string to lowercase
        s = s.toLowerCase();

        // Create HashMap to store character counts
        Map<Character, Integer> charCount = new HashMap<>();

        // Count occurrences of each character
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }

        // Print the count of each character
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
