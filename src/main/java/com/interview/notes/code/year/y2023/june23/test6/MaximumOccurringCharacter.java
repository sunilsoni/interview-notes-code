package com.interview.notes.code.year.y2023.june23.test6;

import java.util.HashMap;
import java.util.Map;

public class MaximumOccurringCharacter {

    public static char maximumOccurringCharacter(String text) {
        // Create a map to store the frequency of each character.
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Iterate through the string and update the frequency map.
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Find the character with the maximum frequency.
        int maxFrequency = Integer.MIN_VALUE;
        char maxFrequencyCharacter = ' ';

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                maxFrequencyCharacter = entry.getKey();
            }
        }

        return maxFrequencyCharacter;
    }

    public static void main(String[] args) {
        String text = "helloworld";

        System.out.println("The maximum occurring character in " + text + " is " + maximumOccurringCharacter(text));
    }
}
