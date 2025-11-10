package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharFrequency {

    // This method takes a string and returns a map of character frequencies
    public static Map<Character, Long> getCharFrequency(String input) {
        // Convert string to character stream, group by character, and count occurrences
        return input.chars() // Convert string to IntStream of character codes
                .mapToObj(c -> (char) c) // Convert each int to character
                .collect(Collectors.groupingBy( // Group characters
                        Function.identity(), // Use character itself as key
                        Collectors.counting() // Count how many times each character appears
                ));
    }

    // This method compares expected and actual frequency maps and prints PASS/FAIL
    public static void testFrequency(String testName, String input, Map<Character, Long> expected) {
        Map<Character, Long> actual = getCharFrequency(input); // Get actual result from method

        // Compare expected and actual maps
        if (actual.equals(expected)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Actual  : " + actual);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Simple string "hello"
        Map<Character, Long> expected1 = new HashMap<>();
        expected1.put('h', 1L);
        expected1.put('e', 1L);
        expected1.put('l', 2L);
        expected1.put('o', 1L);
        testFrequency("Test Case 1 - hello", "hello", expected1);

        // Test Case 2: Empty string
        Map<Character, Long> expected2 = new HashMap<>();
        testFrequency("Test Case 2 - empty", "", expected2);

        // Test Case 3: Repeated characters
        Map<Character, Long> expected3 = new HashMap<>();
        expected3.put('a', 5L);
        testFrequency("Test Case 3 - aaaaa", "aaaaa", expected3);

        // Test Case 4: Mixed characters
        Map<Character, Long> expected4 = new HashMap<>();
        expected4.put('a', 2L);
        expected4.put('b', 1L);
        expected4.put('c', 1L);
        expected4.put('1', 1L);
        expected4.put('2', 1L);
        expected4.put('!', 1L);
        testFrequency("Test Case 4 - mixed", "abca12!", expected4);

        // Test Case 5: Large input string
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("abc");
        }
        Map<Character, Long> expected5 = new HashMap<>();
        expected5.put('a', 100000L);
        expected5.put('b', 100000L);
        expected5.put('c', 100000L);
        testFrequency("Test Case 5 - large input", largeInput.toString(), expected5);
    }
}
