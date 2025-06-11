package com.interview.notes.code.year.y2025.may.paypal.test3;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        // Test cases
        runTests();
        
        // Additional large data test
        testLargeInput();
    }

    public static String find(String[] words, String note) {
        // For each word in the dictionary, check if it can be formed from the note
        for (String word : words) {
            if (canFormWord(word, note)) {
                return word;
            }
        }
        return "-";
    }

    private static boolean canFormWord(String word, String note) {
        // Create frequency maps for both word and note
        Map<Character, Integer> wordFreq = new HashMap<>();
        Map<Character, Integer> noteFreq = new HashMap<>();

        // Count character frequencies in the word
        for (char c : word.toCharArray()) {
            wordFreq.merge(c, 1, Integer::sum);
        }

        // Count character frequencies in the note
        for (char c : note.toCharArray()) {
            noteFreq.merge(c, 1, Integer::sum);
        }

        // Check if note has enough characters to form the word
        for (Map.Entry<Character, Integer> entry : wordFreq.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            if (!noteFreq.containsKey(c) || noteFreq.get(c) < freq) {
                return false;
            }
        }
        return true;
    }

    private static void runTests() {
        String[] words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
        
        // Test cases with expected results
        TestCase[] testCases = {
            new TestCase("ctay", "cat"),
            new TestCase("bcanihjsrrrferet", "cat"),
            new TestCase("tbaykkjlga", "-"),
            new TestCase("bbbbklkjbaby", "baby"),
            new TestCase("dad", "-"),
            new TestCase("breadmaking", "bird"),
            new TestCase("dadaa", "dada")
        };

        // Run each test case and verify results
        for (int i = 0; i < testCases.length; i++) {
            String result = find(words, testCases[i].input);
            boolean passed = result.equals(testCases[i].expected);
            System.out.printf("Test Case %d: %s (Expected: %s, Got: %s)%n", 
                i + 1, 
                passed ? "PASS" : "FAIL",
                testCases[i].expected,
                result);
        }
    }

    private static void testLargeInput() {
        // Create large input data
        String[] largeWords = new String[1000];
        Arrays.fill(largeWords, "testword");
        largeWords[999] = "needle";
        
        // Create a large note with the needle word hidden inside
        StringBuilder largeNote = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeNote.append("abcdefghijklmnopqrstuvwxyz");
        }
        largeNote.append("needle");

        // Test performance with large input
        long startTime = System.currentTimeMillis();
        String result = find(largeWords, largeNote.toString());
        long endTime = System.currentTimeMillis();

        System.out.println("\nLarge Input Test:");
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    // Helper class for test cases
    static class TestCase {
        String input;
        String expected;

        TestCase(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
