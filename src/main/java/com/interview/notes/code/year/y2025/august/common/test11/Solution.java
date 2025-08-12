package com.interview.notes.code.year.y2025.august.common.test11;

import java.util.Set;
import java.util.stream.Collectors;


class Solution {
    private static class PangramDetector {
        // Constant alphabet string for reference
        private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

        /*
         * Finds the letters of the alphabet not included in the input string
         *
         * @param sentence a string to examine
         * @return a string made up of the missing letters of the alphabet in sorted order
         */
        public String findMissingLetters(String sentence) {
            // Step 1: Create a Set to store letters found in the input
            Set<Character> foundLetters = sentence
                    .toLowerCase()                // Normalize to lowercase
                    .chars()                       // Get IntStream of character codes
                    .filter(c -> c >= 'a' && c <= 'z') // Keep only 'a' to 'z'
                    .mapToObj(c -> (char) c)        // Convert int to char
                    .collect(Collectors.toSet());  // Store unique letters in a Set

            // Step 2: Stream over ALPHABET and filter letters not in foundLetters
            return ALPHABET.chars()                   // Stream over 'a' to 'z'
                    .mapToObj(c -> String.valueOf((char) c)) // Convert to String
                    .filter(ch -> !foundLetters.contains(ch.charAt(0))) // Keep missing letters
                    .collect(Collectors.joining());   // Combine into one string
        }
    }

    // Main method for testing without JUnit
    public static void main(String[] args) {
        PangramDetector pd = new PangramDetector();
        boolean success = true;

        // Provided test cases
        success = success && "".equals(pd.findMissingLetters("The quick brown fox jumps over the lazy dog"));
        success = success && "bfgjkvz".equals(pd.findMissingLetters("The slow purple oryx meanders past the quiescent canine"));
        success = success && "cdfjklmopruvwxyz".equals(pd.findMissingLetters("We hates Bagginses!"));
        success = success && "abcdefghijklmnopqrstuvwxyz".equals(pd.findMissingLetters(""));

        // Additional edge test cases
        success = success && "".equals(pd.findMissingLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));
        success = success && "abcdefghijklmnopqrstuvwxyz".equals(pd.findMissingLetters("1234567890!@#$%^&*()"));
        success = success && "".equals(pd.findMissingLetters(PangramDetector.ALPHABET + PangramDetector.ALPHABET.toUpperCase()));

        // Large input performance test
        String largeInput = new String(new char[1_000_000]).replace("\0", "abcxyz");
        success = success && "defghijklmnopqrstuvw".equals(pd.findMissingLetters(largeInput));

        // Final output
        if (success) {
            System.out.println("All tests passed.");
        } else {
            System.out.println("At least one test failed.");
        }
    }
}