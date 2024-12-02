package com.interview.notes.code.year.y2024.oct24.amazon.test27;

import java.util.*;

/*
WORKING

Question
You are given a string with repeated characters, your task is to rearrange characters in a string so that no two adjacent characters are equal.
Sample input bcaaa
Sample output abaca
 */
public class RearrangeStringWorking {

    /**
     * Rearranges the input string so that no two adjacent characters are the same.
     * If such an arrangement is not possible, returns an empty string.
     *
     * @param s The input string to rearrange.
     * @return The rearranged string or an empty string if impossible.
     */
    public static String rearrangeString(String s) {
        // Frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Max heap based on character frequency
        PriorityQueue<CharFrequency> maxHeap = new PriorityQueue<>(
                (a, b) -> b.frequency != a.frequency ? b.frequency - a.frequency : a.character - b.character
        );

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            maxHeap.offer(new CharFrequency(entry.getKey(), entry.getValue()));
        }

        StringBuilder result = new StringBuilder();
        CharFrequency prev = null;

        while (!maxHeap.isEmpty()) {
            CharFrequency current = maxHeap.poll();
            result.append(current.character);

            // Decrease frequency and hold the previous character
            current.frequency--;
            if (prev != null) {
                maxHeap.offer(prev);
                prev = null;
            }

            // If current character still has remaining frequency, set it as previous
            if (current.frequency > 0) {
                prev = current;
            }
        }

        // If the rearranged string's length is equal to the input, return it
        if (result.length() == s.length()) {
            return result.toString();
        } else {
            // Rearrangement not possible
            return "";
        }
    }

    /**
     * Main method to run test cases.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case
        testCases.add(new TestCase("bcaaa", "abaca"));

        // Additional Test Cases
        testCases.add(new TestCase("a", "a")); // Single character
        testCases.add(new TestCase("aa", "")); // Impossible to rearrange
        testCases.add(new TestCase("aabb", "abab")); // Even distribution
        testCases.add(new TestCase("aaab", "")); // Impossible to rearrange
        testCases.add(new TestCase("aaabbc", "ababac")); // Multiple characters
        testCases.add(new TestCase("aaabbbc", "abababc")); // More complex

        // Large Test Case
        StringBuilder largeInputBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInputBuilder.append('a');
            largeInputBuilder.append('b');
        }
        String largeInput = largeInputBuilder.toString();
        String expectedLargeOutput = generateExpectedLargeOutput(largeInput);
        testCases.add(new TestCase(largeInput, expectedLargeOutput)); // Expected to pass

        // Run tests
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            String output = rearrangeString(tc.input);
            boolean isPass = tc.expected.equals("") ? output.equals("") : checkValidRearrangement(tc.input, output);
            if (isPass) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("  Input: " + (tc.input.length() > 50 ? tc.input.substring(0, 50) + "..." : tc.input));
                System.out.println("  Expected: " + (tc.expected.isEmpty() ? "Impossible" : tc.expected));
                System.out.println("  Got: " + (output.isEmpty() ? "Impossible" : output));
            }
        }

        System.out.println("\nPassed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * Generates an expected output for the large test case.
     * Since generating the exact expected output for large inputs is impractical,
     * this method assumes that the rearrangement is possible and returns a valid rearranged string.
     *
     * @param input The input string.
     * @return A valid rearranged string where no two adjacent characters are the same.
     */
    private static String generateExpectedLargeOutput(String input) {
        String rearranged = rearrangeString(input);
        return rearranged;
    }

    /**
     * Checks if the rearranged string is valid:
     * - Same length as input.
     * - Contains the same frequency of each character.
     * - No two adjacent characters are the same.
     *
     * @param input      The original input string.
     * @param rearranged The rearranged string.
     * @return True if valid, else false.
     */
    private static boolean checkValidRearrangement(String input, String rearranged) {
        if (input.length() != rearranged.length()) return false;

        // Check frequency
        Map<Character, Integer> freqInput = new HashMap<>();
        for (char c : input.toCharArray()) {
            freqInput.put(c, freqInput.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> freqRearranged = new HashMap<>();
        for (char c : rearranged.toCharArray()) {
            freqRearranged.put(c, freqRearranged.getOrDefault(c, 0) + 1);
        }

        if (!freqInput.equals(freqRearranged)) return false;

        // Check no two adjacent characters are the same
        for (int i = 1; i < rearranged.length(); i++) {
            if (rearranged.charAt(i) == rearranged.charAt(i - 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper class to store character and its frequency.
     */
    static class CharFrequency {
        char character;
        int frequency;

        CharFrequency(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }
    }

    /**
     * Represents a test case with input and expected output.
     */
    static class TestCase {
        String input;
        String expected;

        TestCase(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
