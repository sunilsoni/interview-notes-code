package com.interview.notes.code.months.oct24.test4;

import java.util.*;

public class MaxNonRepeatingChars {
    public static void main(String[] args) {
        List<String> inputStrings = Arrays.asList("hello", "world", "java", "programming", "openai");
        int maxNonRepeating = getMaxNonRepeatingChars(inputStrings);
        System.out.println("Maximum non-repeating characters: " + maxNonRepeating);
    }

    public static int getMaxNonRepeatingChars(List<String> strings) {
        int maxCount = 0;

        for (String str : strings) {
            Set<Character> charSet = new HashSet<>();
            Set<Character> repeatingSet = new HashSet<>();

            for (char c : str.toCharArray()) {
                // If the character is already in charSet, add it to repeatingSet
                if (!charSet.add(c)) {
                    repeatingSet.add(c);
                }
            }

            // Calculate the count of non-repeating characters
            int nonRepeatingCount = 0;
            for (char c : charSet) {
                if (!repeatingSet.contains(c)) {
                    nonRepeatingCount++;
                }
            }

            // Update maxCount if we found a new maximum
            maxCount = Math.max(maxCount, nonRepeatingCount);
        }

        return maxCount;
    }
}
