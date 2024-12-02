package com.interview.notes.code.year.y2024.july24.test10;

import java.util.HashMap;
import java.util.Map;

public class CharacterCounter {
    public static void main(String[] args) {
        String input = "programming";
        printRepeatedAndNonRepeatedCharacters(input);
    }

    public static void printRepeatedAndNonRepeatedCharacters(String input) {
        // HashMap to store the frequency of each character
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Fill the HashMap with character counts
        for (char c : input.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // StringBuilder to help format the output
        StringBuilder repeated = new StringBuilder("Repeated characters: ");
        StringBuilder nonRepeated = new StringBuilder("Non-repeated characters: ");

        // Iterate over the string to maintain order
        for (char c : input.toCharArray()) {
            if (charCountMap.get(c) > 1) {
                // If character is repeated and not already in the StringBuilder
                if (repeated.indexOf(String.valueOf(c)) == -1) {
                    repeated.append(c).append(" ");
                }
            } else {
                nonRepeated.append(c).append(" ");
            }
        }

        // Print the results
        System.out.println(repeated.toString().trim());
        System.out.println(nonRepeated.toString().trim());
    }
}
