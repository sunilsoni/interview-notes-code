package com.interview.notes.code.year.y2025.november.common.test2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingChar {

    // Method to find first non-repeating character in a given string
    public static Character findFirstUniqueChar(String input) {

        // Defensive check: handle null or empty string inputs
        if (input == null || input.isEmpty())
            return null;

        // Step 1: Convert the string into a stream of characters
        // We use chars() to get IntStream of code points and then cast each to Character
        // Collect into a LinkedHashMap to preserve order of appearance
        Map<Character, Long> frequencyMap = input.chars()
                .mapToObj(ch -> (char) ch) // convert each int code to Character object
                .collect(Collectors.groupingBy(
                        Function.identity(),       // key -> character itself
                        LinkedHashMap::new,        // maintain insertion order
                        Collectors.counting()));   // count frequency of each character

        // Step 2: Iterate through the map entries to find the first with count == 1
        // LinkedHashMap ensures we encounter characters in the same order as original string
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)   // only unique ones
                .map(Map.Entry::getKey)                   // take character
                .findFirst()                              // take first one
                .orElse(null);                            // return null if not found
    }

    // Main method used for manual testing (no JUnit)
    public static void main(String[] args) {

        // Step 3: Prepare test cases
        String[] testInputs = {
                "swiss",          // expected: 'w'
                "success",        // expected: 'u'
                "aabbcc",         // expected: null (no unique char)
                "abacabad",       // expected: 'c'
                "",               // expected: null (empty string)
                "a",              // expected: 'a' (single character)
                generateLargeInput(1_000_000) // large data test
        };

        // Step 4: Expected outputs for first few known cases
        Character[] expectedOutputs = {'w', 'u', null, 'c', null, 'a', null};

        // Step 5: Run each test and show pass/fail result
        for (int i = 0; i < testInputs.length; i++) {
            long startTime = System.currentTimeMillis(); // measure performance
            Character result = findFirstUniqueChar(testInputs[i]);
            long endTime = System.currentTimeMillis();

            System.out.println("Test " + (i + 1) +
                    " | Input: " + summarize(testInputs[i]) +
                    " | Output: " + result +
                    " | Expected: " + expectedOutputs[i] +
                    " | Time: " + (endTime - startTime) + "ms" +
                    " | Result: " + (Objects.equals(result, expectedOutputs[i]) ? "PASS" : "FAIL"));
        }
    }

    // Utility method to summarize long strings during testing
    private static String summarize(String str) {
        if (str == null) return "null";
        if (str.length() > 20) return str.substring(0, 20) + "...(" + str.length() + ")";
        return str;
    }

    // Generates a very large test string for performance testing
    private static String generateLargeInput(int size) {
        // Example: create a long string of repeating 'a's and a single 'z' at the end
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size - 1; i++) sb.append('a');
        sb.append('z'); // only one unique char at end
        return sb.toString();
    }
}
