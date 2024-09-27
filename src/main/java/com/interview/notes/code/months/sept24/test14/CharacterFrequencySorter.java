package com.interview.notes.code.months.sept24.test14;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterFrequencySorter {

    public static void main(String[] args) {
        // Test cases
        test("tree", "eert", "eetr");
        test("Aabb", "bbAa", "bbaA");
        test("ppprrr", "ppprr", "rrrpp");
        test("", ""); // Edge case: empty string
        test("a", "a"); // Edge case: single character
    }

    // Method to sort characters based on frequency and alphabetical order using streams
    public static String frequencySort(String s) {
        // Step 1: Count the frequency of each character
        Map<Character, Long> frequencyMap = s.chars()
            .mapToObj(c -> (char) c) // Convert int stream to character stream
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        // Step 2: Sort the characters by frequency and then alphabetically
        return frequencyMap.entrySet().stream()
            .sorted((e1, e2) -> {
                int freqCompare = e2.getValue().compareTo(e1.getValue()); // Compare by frequency (descending)
                if (freqCompare == 0) {
                    return Character.compare(e1.getKey(), e2.getKey()); // Compare alphabetically if frequencies are equal
                }
                return freqCompare;
            })
            // Step 3: Build the result string by repeating each character according to its frequency
            .map(entry -> String.valueOf(entry.getKey()).repeat(entry.getValue().intValue()))
            .collect(Collectors.joining());
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
