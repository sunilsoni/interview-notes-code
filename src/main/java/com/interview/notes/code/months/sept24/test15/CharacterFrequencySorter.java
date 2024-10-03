package com.interview.notes.code.months.sept24.test15;

import java.util.*;

public class CharacterFrequencySorter {

    public static void main(String[] args) {
        // Test cases
        test("tree", "eert", "eetr");
        test("Aabb", "bbAa", "bbaA");
        test("ppprrr", "ppprr", "rrrpp");
        test("", ""); // Edge case: empty string
        test("a", "a"); // Edge case: single character
    }

    // Method to sort characters based on frequency and alphabetical order
    public static String frequencySort(String s) {
        // Map to store character frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each character
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Create a list of characters from the map keys
        List<Character> characters = new ArrayList<>(frequencyMap.keySet());

        // Sort the list based on frequency and then alphabetically
        characters.sort((a, b) -> {
            int freqCompare = frequencyMap.get(b) - frequencyMap.get(a); // Sort by frequency (descending)
            if (freqCompare == 0) {
                return Character.compare(a, b); // Sort alphabetically if frequencies are the same
            }
            return freqCompare;
        });

        // Build the result string
        StringBuilder result = new StringBuilder();
        for (char c : characters) {
            int freq = frequencyMap.get(c);
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    // Method to test the implementation with expected results
    public static void test(String input, String... expectedOutputs) {
        String result = frequencySort(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + result);
        boolean isValid = Arrays.asList(expectedOutputs).contains(result);
        System.out.println("Test " + (isValid ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
