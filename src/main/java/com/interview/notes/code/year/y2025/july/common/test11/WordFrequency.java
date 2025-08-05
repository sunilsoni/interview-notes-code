package com.interview.notes.code.year.y2025.july.common.test11;

import java.util.*;                                    // for Map, LinkedHashMap, Arrays
import java.util.function.Function;                    // for Function.identity()
import java.util.stream.Collectors;                    // for Collectors

public class WordFrequency {                            // Class to hold our methods

    public static void main(String[] args) {            // Main method to run tests

        // Test 1: Example from the prompt
        String input1 = "java is a powerful language springboot is a java framework";  // sample input
        Map<String, Long> result1 = countWordFrequency(input1);                       // run frequency count
        Map<String, Long> expected1 = new LinkedHashMap<>();                          // prepare expected map
        expected1.put("java", 2L);                                                     // expected "java" count
        expected1.put("is", 2L);                                                       // expected "is" count
        expected1.put("a", 2L);                                                        // expected "a" count
        expected1.put("powerful", 1L);                                                 // expected "powerful" count
        expected1.put("language", 1L);                                                // expected "language" count
        expected1.put("springboot", 1L);                                               // expected "springboot" count
        expected1.put("framework", 1L);                                                // expected "framework" count

        if (result1.equals(expected1)) {                                              // compare actual vs expected
            System.out.println("Test 1: PASS");                                       // print PASS if same
        } else {
            System.out.println("Test 1: FAIL");                                       // print FAIL otherwise
        }

        // Test 2: Edge case with empty input
        String input2 = "";                                                            // empty string
        Map<String, Long> result2 = countWordFrequency(input2);                       // run frequency count
        if (result2.isEmpty()) {                                                       // empty map is expected
            System.out.println("Test 2: PASS");                                       // PASS for empty input
        } else {
            System.out.println("Test 2: FAIL");                                       // FAIL otherwise
        }

        // Test 3: Large data input to check performance and correctness
        StringBuilder sb = new StringBuilder();                                        // builder for large input
        for (int i = 0; i < 100_000; i++) {                                            // repeat many times
            sb.append("test test freq ");                                              // three words per iteration
        }
        String input3 = sb.toString();                                                 // convert builder to string
        Map<String, Long> result3 = countWordFrequency(input3);                       // run frequency count
        long testCount = result3.getOrDefault("test", 0L);                             // get count for "test"
        long freqCount = result3.getOrDefault("freq", 0L);                             // get count for "freq"

        if (testCount == 200_000 && freqCount == 100_000) {                            // expected counts
            System.out.println("Test 3 (large): PASS");                                // PASS if correct
        } else {
            System.out.println("Test 3 (large): FAIL");                                // FAIL otherwise
        }
    }

    /**
     * Counts how many times each word appears in the given text.
     *
     * @param text the input string (one or two lines)
     * @return a LinkedHashMap mapping each word to its frequency,
     *         in the order words first appear
     */
    public static Map<String, Long> countWordFrequency(String text) {                 // method to do counting
        return Arrays.stream(text                                              // turn the text into a stream
                .toLowerCase()                                                  // make lowercase for uniformity
                .split("\\s+"))                                                 // split on any whitespace
            .filter(word -> !word.isEmpty())                                     // skip empty strings
            .collect(Collectors.groupingBy(                                      // group words
                Function.identity(),                                             // word itself is the key
                LinkedHashMap::new,                                              // preserve insertion order
                Collectors.counting()                                            // count occurrences
            ));
    }
}