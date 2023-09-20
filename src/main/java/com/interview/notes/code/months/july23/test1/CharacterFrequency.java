package com.interview.notes.code.months.july23.test1;

import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {
    public static void main(String[] args) {
        String str = "Test";
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) { // Check if the character is an alphabet
                char lowercaseChar = Character.toLowerCase(c); // Convert to lowercase to ignore case
                charFrequency.put(lowercaseChar, charFrequency.getOrDefault(lowercaseChar, 0) + 1);
            }
        }

        // Print the character frequencies
        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            System.out.println("Character: " + entry.getKey() + ", Occurrences: " + entry.getValue());
        }
    }
}
