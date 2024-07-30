package com.interview.notes.code.months.july24.test9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FirstCharacterFinder {
    public static void main(String[] args) {
        String input = "programming";
        findFirstRepeatedAndNonRepeatedCharacter(input);
    }

    public static void findFirstRepeatedAndNonRepeatedCharacter(String input) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        Character firstRepeated = null;
        Character firstNonRepeated = null;

        // Count the frequency of each character
        for (char c : input.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // Find the first non-repeated character
        for (char c : input.toCharArray()) {
            if (charCountMap.get(c) == 1) {
                firstNonRepeated = c;
                break;
            }
        }

        // To find the first repeated character, we create a set to keep track of seen characters
        Set<Character> seen = new HashSet<>();

        for (char c : input.toCharArray()) {
            // If we have seen it before and it's the first one, we save it as firstRepeated
            if (seen.contains(c)) {
                firstRepeated = c;
                break;
            } else {
                seen.add(c);
            }
        }

        // Print the results
        System.out.println("First repeated character: " + (firstRepeated != null ? firstRepeated : "None"));
        System.out.println("First non-repeated character: " + (firstNonRepeated != null ? firstNonRepeated : "None"));
    }
}
