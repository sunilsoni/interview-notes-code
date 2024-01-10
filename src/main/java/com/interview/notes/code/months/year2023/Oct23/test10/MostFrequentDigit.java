package com.interview.notes.code.months.year2023.Oct23.test10;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MostFrequentDigit {
    public static char findMostFrequentDigit(String input) {
        // Step 1: Initialize a LinkedHashMap to store digit counts while preserving order.
        Map<Character, Integer> digitCount = new LinkedHashMap<>();

        // Step 2: Iterate through the input sequence of digits.
        for (char digit : input.toCharArray()) {
            // Step 3: Update the count for the current digit.
            digitCount.put(digit, digitCount.getOrDefault(digit, 0) + 1);
        }

        char mostFrequentDigit = 0; // Initialize with a default value.
        int maxCount = 0; // Initialize with a default value.

        // Step 4: Find the digit with the highest count while preserving order.
        for (Entry<Character, Integer> entry : digitCount.entrySet()) {
            char digit = entry.getKey();
            int count = entry.getValue();

            // Update if the current digit has a higher count.
            if (count > maxCount) {
                mostFrequentDigit = digit;
                maxCount = count;
            }
        }

        // Step 5: Return the digit that appears the most in its original order.
        return mostFrequentDigit;
    }

    public static void main(String[] args) {
        String input = "95667366399";
        char mostFrequent = findMostFrequentDigit(input);
        System.out.println(mostFrequent + " appears the most.");
    }
}
