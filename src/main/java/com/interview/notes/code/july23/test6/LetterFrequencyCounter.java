package com.interview.notes.code.july23.test6;

import java.util.HashMap;
import java.util.Map;

public class LetterFrequencyCounter {
    public static void main(String[] args) {
        String text = "MISSISSIPPI";
        Map<Character, Integer> letterCountMap = countLetterFrequency(text);

        // Display letter frequencies
        System.out.println("Letter frequencies:");
        for (Map.Entry<Character, Integer> entry : letterCountMap.entrySet()) {
            System.out.println(entry.getKey() + " - Count: " + entry.getValue());
        }
    }

    public static Map<Character, Integer> countLetterFrequency(String text) {
        // Convert the text to uppercase to ignore case sensitivity
        text = text.toUpperCase();

        // Create a HashMap to store the count of each letter
        Map<Character, Integer> letterCountMap = new HashMap<>();

        // Iterate through each character in the text
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);

            // Check if the character is a letter (ignoring non-letter characters)
            if (Character.isLetter(letter)) {
                // Update the count of the letter in the HashMap
                letterCountMap.put(letter, letterCountMap.getOrDefault(letter, 0) + 1);
            }
        }

        return letterCountMap;
    }
}
