package com.interview.notes.code.year.y2025.july.common.test9;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RepeatedCharsFinder {

    public static void main(String[] args) {
        // Test case 1: example from prompt
        runTest("abcdefaaccdd", "acd", "Test1");
        // Test case 2: no repeats should give empty string
        runTest("abcdef", "", "Test2");
        // Test case 3: all same characters
        runTest("aaaaa", "a", "Test3");
        // Test case 4: mixed repeats
        runTest("ababa", "ab", "Test4");

        // Large data test: one million 'x' characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            sb.append('x');
        }
        runTest(sb.toString(), "x", "LargeTest");
    }

    // Helper to run a single test and print PASS/FAIL
    private static void runTest(String input, String expected, String testName) {
        // Find repeated chars using our method
        String result = findRepeatedChars(input);
        // Check result against expected and print
        if (result.equals(expected)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL (expected=" 
                               + expected + ", got=" + result + ")");
        }
    }

    // Method to find repeated characters in order, using Java 8 Streams
    public static String findRepeatedChars(String input) {
        // 1. Count how many times each character appears
        Map<Character, Long> freqMap = input
            .chars()                                           // turn String into IntStream of char codes
            .mapToObj(c -> (char) c)                           // box each int to Character
            .collect(Collectors.groupingBy(
                Function.identity(),                          // group by the character itself
                Collectors.counting()                         // count occurrences
            ));

        // 2. Stream again, keep only first occurrence of each
        //    and filter those whose count > 1, then join into a String
        return input
            .chars()                                           // stream character codes again
            .mapToObj(c -> (char) c)                           // box to Character
            .distinct()                                        // only let each character pass once
            .filter(ch -> freqMap.get(ch) > 1)                 // keep only repeated chars
            .map(String::valueOf)                              // convert Character to String
            .collect(Collectors.joining());                   // concatenate into one String
    }
}