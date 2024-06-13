package com.interview.notes.code.months.june24.test3;

import java.util.HashMap;
import java.util.Map;

public class DuplicateCharactersString {
    public static void main(String[] args) {
        String s = "rarKKbc";

        Map<Character, Integer> charCountMap = new HashMap<>();

        // Count occurrences of each character in the string
        s.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1));

        // Print duplicate characters
        System.out.println("Duplicate characters in the string '" + s + "':");
        charCountMap.forEach((key, value) -> {
            if (value > 1) {
                System.out.println(key + " - " + value + " occurrences");
            }
        });
    }
}
