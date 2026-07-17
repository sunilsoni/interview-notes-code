package com.interview.notes.code.year.y2026.july.common.test5;

public class Solution {

    // This method returns the shortest distance between two word midpoints
    public static double shortestDistance(String document, String word1, String word2) {

        // If document or words are invalid, return -1 because we cannot calculate distance
        if (document == null || word1 == null || word2 == null) return -1;

        // Convert word1 to lowercase because matching should be case-insensitive
        word1 = word1.toLowerCase();

        // Convert word2 to lowercase because matching should be case-insensitive
        word2 = word2.toLowerCase();

        // This regex finds only real words and ignores punctuation like comma or period
        java.util.regex.Matcher matcher =
                java.util.regex.Pattern.compile("[A-Za-z0-9]+").matcher(document);

        // Store latest midpoint location of word1
        double word1Loc = -1;

        // Store latest midpoint location of word2
        double word2Loc = -1;

        // Store best/shortest distance found so far
        double shortest = Double.MAX_VALUE;

        // Go through every word in the document one by one
        while (matcher.find()) {

            // Read current word from document
            String word = matcher.group();

            // Convert current word to lowercase for case-insensitive comparison
            String lowerWord = word.toLowerCase();

            // Calculate midpoint using original character position
            double midPoint = matcher.start() + word.length() / 2.0;

            // If current word matches word1, update latest word1 location
            if (lowerWord.equals(word1)) {

                // Store current midpoint of word1
                word1Loc = midPoint;

                // If word2 was already seen, update shortest distance
                if (word2Loc != -1) {

                    // Use absolute difference because words can appear in any order
                    shortest = Math.min(shortest, Math.abs(word1Loc - word2Loc));
                }
            }

            // If current word matches word2, update latest word2 location
            if (lowerWord.equals(word2)) {

                // Store current midpoint of word2
                word2Loc = midPoint;

                // If word1 was already seen, update shortest distance
                if (word1Loc != -1) {

                    // Use absolute difference because words can appear in any order
                    shortest = Math.min(shortest, Math.abs(word1Loc - word2Loc));
                }
            }
        }

        // If shortest never changed, one or both words were missing
        if (shortest == Double.MAX_VALUE) return -1;

        // Return final shortest distance
        return shortest;
    }

    // Simple helper method to compare double values safely
    private static boolean same(double actual, double expected) {

        // Small difference is allowed because double values can have tiny precision issues
        return Math.abs(actual - expected) < 0.000001;
    }

    // Simple test method without JUnit
    private static void test(String document, String word1, String word2, double expected) {

        // Call shortestDistance method and store actual result
        double actual = shortestDistance(document, word1, word2);

        // Compare actual and expected result
        boolean pass = same(actual, expected);

        // Print PASS or FAIL clearly
        System.out.println((pass ? "PASS" : "FAIL")
                + " | " + word1 + ", " + word2
                + " | expected=" + expected
                + " | actual=" + actual);
    }

    // Main method to run all test cases
    public static void main(String[] args) {

        // Sample document from the question
        String document = "This is a sample document we just made up";

        // Given test: midpoint distance between we and just is 4
        test(document, "we", "just", 4);

        // Given test: midpoint distance between is and a is 2.5
        test(document, "is", "a", 2.5);

        // Given test: if one word is missing, return -1
        test(document, "is", "not", -1);

        // Case-insensitive test
        test(document, "THIS", "sample", 11);

        // Punctuation test
        test("Design, filler text. Layout. It also works.", "Design", "filler", 8);

        // Reverse order test
        test("graphic design and visual graphic", "design", "graphic", 7);

        // Large input test
        String large = "a ".repeat(100000) + "hello " + "x ".repeat(100000) + "world";

        // Large input should still work in one pass
        System.out.println(shortestDistance(large, "hello", "world") > 0 ? "PASS large input" : "FAIL large input");
    }
}