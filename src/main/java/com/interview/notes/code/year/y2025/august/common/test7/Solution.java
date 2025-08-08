package com.interview.notes.code.year.y2025.august.common.test7;

class Solution {
    public static void main(String[] args) {
        PangramDetector pd = new PangramDetector();
        boolean success = true;

        success = success && "".equals(pd.findMissingLetters("The quick brown fox jumps over the lazy dog"));
        success = success && "bfgjkvz".equals(pd.findMissingLetters("The slow purple oryx meanders past the quiescent canine"));
        success = success && "cdfjklmopruvwxyz".equals(pd.findMissingLetters("We hates Bagginses!"));
        success = success && "abcdefghijklmnopqrstuvwxyz".equals(pd.findMissingLetters(""));

        if (success) {
            System.out.println("All tests passed.");
        } else {
            System.out.println("At least one of your tests failed.");
        }
    }

    private static class PangramDetector {
        private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

        /**
         * Finds the letters of the alphabet not included in the input string
         *
         * @param sentence a string to examine
         * @return a string made up of the missing letters of the alphabet in sorted order
         */
        public String findMissingLetters(String sentence) {
            // Boolean array to remember which letters are present
            boolean[] present = new boolean[26];

            // Scan the sentence – we only care about ASCII a–z
            for (int i = 0; i < sentence.length(); i++) {
                char c = Character.toLowerCase(sentence.charAt(i));
                if (c >= 'a' && c <= 'z') {
                    present[c - 'a'] = true;
                }
            }

            // Build the result string from the letters that were never seen
            StringBuilder missing = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (!present[i]) {
                    missing.append((char) ('a' + i));
                }
            }
            return missing.toString();
        }
    }
}
