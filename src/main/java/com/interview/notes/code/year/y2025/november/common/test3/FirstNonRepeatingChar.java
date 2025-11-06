package com.interview.notes.code.year.y2025.november.common.test3;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingChar {

    // Find first non-repeating character using Java 8 streams.
    // Returns Optional.empty() if no such character exists or input is null/empty.
    public static Optional<Character> firstNonRepeatingChar(String s) {
        // If input is null or empty, return empty Optional right away.
        if (s == null || s.isEmpty()) return Optional.empty(); // cheap guard

        // Convert the string into a stream of Character objects, group by the character,
        // count occurrences, and collect into a LinkedHashMap so the order of keys
        // is the same as the order they first appeared in the string.
        Map<Character, Long> frequencyMap = s.chars()                                     // create an IntStream of UTF-16 code units
            .mapToObj(c -> (char) c)                                                      // convert each int to Character
            .collect(Collectors.groupingBy(                                              // group identical characters together
                Function.identity(),                                                     // grouping key is the character itself
                LinkedHashMap::new,                                                       // use LinkedHashMap to preserve insertion order
                Collectors.counting()                                                    // count occurrences of each character
            ));

        // Stream the entries of the map (in insertion order) and find the first entry whose count == 1.
        return frequencyMap.entrySet().stream()                                          // stream map entries in insertion order
            .filter(e -> e.getValue() == 1L)                                             // keep only characters that appear exactly once
            .map(Map.Entry::getKey)                                                      // convert entry to its character key
            .findFirst();                                                                // take first such character (if any)
    }

    // Simple test runner in main (no JUnit). Prints PASS/FAIL for each test case.
    public static void main(String[] args) {
        // Define test inputs. Each test has a corresponding expected Character (null == no result).
        String[] inputs = new String[] {
            "leetcode",             // expected 'l' (first non-repeating)
            "loveleetcode",         // expected 'v'
            "aabb",                 // expected null (no non-repeating)
            "",                     // expected null (empty input)
            "swiss",                // expected 'w'
            "stress",               // expected 't'
            null                    // expected null (null input)
        };

        // Expected results aligned with inputs above. Use Character objects or null.
        Character[] expected = new Character[] {
            'l', 'v', null, null, 'w', 't', null
        };

        // Run the normal test cases above.
        System.out.println("Running standard test cases:");
        for (int i = 0; i < inputs.length; i++) {
            String input = inputs[i];                                           // test input
            Character exp = expected[i];                                        // expected output for this test
            Optional<Character> got = firstNonRepeatingChar(input);             // call the method under test

            // Determine PASS/FAIL
            boolean pass = (got.isPresent() && exp != null && got.get().equals(exp))
                        || (!got.isPresent() && exp == null);

            // Print a simple PASS/FAIL line with details
            System.out.printf("Test %d: %s | Input: %s | Expected: %s | Got: %s%n",
                i + 1,
                pass ? "PASS" : "FAIL",
                input == null ? "null" : "\"" + input + "\"",                  // show null explicitly
                exp == null ? "null" : "'" + exp + "'",                         // expected as char or null
                got.isPresent() ? ("'" + got.get() + "'") : "null"             // got as char or null
            );
        }

        // Large input stress test to ensure algorithm handles big strings efficiently.
        System.out.println("\nRunning large input test (performance check):");
        int repeat = 200_000;                                                   // repeat count (large)
        StringBuilder sb = new StringBuilder(repeat * 2 + 1);                    // allocate roughly enough capacity
        for (int i = 0; i < repeat; i++) sb.append('a');                        // append 'a' many times (repeated char)
        for (int i = 0; i < repeat; i++) sb.append('b');                        // append 'b' many times (repeated char)
        sb.append('c');                                                         // append a unique char at the end

        String largeInput = sb.toString();                                      // convert to String for testing
        long start = System.currentTimeMillis();                                // start timer to show performance
        Optional<Character> largeResult = firstNonRepeatingChar(largeInput);     // run the algorithm on big input
        long duration = System.currentTimeMillis() - start;                      // measure elapsed time

        // Expect 'c' as the first non-repeating character for this constructed input.
        boolean largePass = largeResult.isPresent() && largeResult.get().equals('c');

        // Print result summary for the large test case (PASS/FAIL and time taken).
        System.out.printf("Large test: %s | Expected: 'c' | Got: %s | Time: %d ms%n",
            largePass ? "PASS" : "FAIL",
            largeResult.isPresent() ? ("'" + largeResult.get() + "'") : "null",
            duration
        );

        // Additional edge-case checks demonstration (optional)
        System.out.println("\nSome extra quick checks:");
        System.out.println("Input: \"x\" -> " + firstNonRepeatingChar("x").orElse(null)); // single char -> itself
        System.out.println("Input: \"xx\" -> " + firstNonRepeatingChar("xx").orElse(null)); // double same -> null
        System.out.println("Input: \"ab\" -> " + firstNonRepeatingChar("ab").orElse(null)); // 'a' is first non-repeating
    }
}
