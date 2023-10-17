package com.interview.notes.code.months.Oct23.test6;

import java.util.HashMap;
import java.util.Map;

public class FindDuplicateCharacters {
    public static void main(String[] args) {
        String inputString = "programming";
        findDuplicates(inputString);
    }

    public static void findDuplicates(String str) {
        // Create a HashMap to store character frequencies
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Convert the string to a character array
        char[] charArray = str.toCharArray();

        // Iterate through the characters and update the frequency count
        for (char c : charArray) {
            if (charCountMap.containsKey(c)) {
                // If the character is already in the map, increment its count
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                // If the character is not in the map, add it with a count of 1
                charCountMap.put(c, 1);
            }
        }

        // Print characters with count greater than 1 (duplicates)
        System.out.println("Duplicate characters in the string:");

        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
