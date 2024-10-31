package com.interview.notes.code.months.oct24.amazon.test2;

import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {
    public static void main(String[] args) {
        String input = "hello world"; // Example input string
        countCharacterOccurrences(input);
    }

    public static void countCharacterOccurrences(String str) {
        // Create a HashMap to store character counts
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Convert the string to a character array
        char[] characters = str.toCharArray();

        // Iterate over each character in the array
        for (char c : characters) {
            // Ignore spaces or consider them if required
            if (c != ' ') {
                // Update the count for the character in the map
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
        }

        // Display the character counts
        System.out.println("Character occurrences:");
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
