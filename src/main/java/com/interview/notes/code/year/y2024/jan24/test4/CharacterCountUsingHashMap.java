package com.interview.notes.code.year.y2024.jan24.test4;

import java.util.HashMap;
import java.util.Map;

public class CharacterCountUsingHashMap {
    public static void main(String[] args) {
        String input = "This is a sample string with some words. It's a simple example.";

        // Count characters using a HashMap
        Map<Character, Integer> characterCountMap = countCharacters(input);

        // Display character counts
        for (Map.Entry<Character, Integer> entry : characterCountMap.entrySet()) {
            System.out.println("Character '" + entry.getKey() + "' appears " + entry.getValue() + " times.");
        }
    }

    public static Map<Character, Integer> countCharacters(String text) {
        Map<Character, Integer> characterCountMap = new HashMap<>();

        if (text != null) {
            for (char c : text.toCharArray()) {
                if (Character.isLetterOrDigit(c)) { // Count only letters and digits, ignore spaces and punctuation
                    characterCountMap.put(c, characterCountMap.getOrDefault(c, 0) + 1);
                }
            }
        }

        return characterCountMap;
    }
}
