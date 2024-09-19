package com.interview.notes.code.months.sept24.test7;

import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueCharacterFinder {

    public static String findFirstUniqueCharacter(String input) {
        if (input == null || input.isEmpty()) {
            return "0";
        }

        // Use a LinkedHashMap to preserve the insertion order
        Map<Character, Integer> charCountMap = new LinkedHashMap<>();
        Map<Character, Integer> charIndexMap = new LinkedHashMap<>();

        // Convert input to lowercase to handle case insensitivity
        String lowerCaseInput = input.toLowerCase();

        // Populate the maps
        for (int i = 0; i < lowerCaseInput.length(); i++) {
            char c = lowerCaseInput.charAt(i);
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            charIndexMap.putIfAbsent(c, i + 1);  // Store 1-based index
        }

        // Find the first non-repeating character
        for (char c : charCountMap.keySet()) {
            if (charCountMap.get(c) == 1) {
                int index = charIndexMap.get(c);
                return index + ", " + input.charAt(index - 1);
            }
        }

        return "0";
    }

    public static void main(String[] args) {
        String input1 = "HarryPotterHasWand";
        String input2 = "HarryPotter";

        System.out.println(findFirstUniqueCharacter(input1)); // Output: "5, y"
        System.out.println(findFirstUniqueCharacter(input2)); // Output: "1, H"
    }
}
