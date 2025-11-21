package com.interview.notes.code.year.y2025.august.oracle.test1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Prepare a map of test cases → expected result (in insertion order)
        Map<List<String>, String> testCases = new LinkedHashMap<>();

        // 1) Sample from problem statement; second most frequent is "orange"
        testCases.put(
                Arrays.asList(
                        "apple", "banana", "orange", "apple", "orange", "banana",
                        "banana", "apple", "grape", "orange", "apple", "orange", "apple"
                ),
                "orange"
        );

        // 2) Simple tie-break: "a" and "b" both appear twice → sorted lexicographically may decide,
        //    but our Streams sort is stable by insertion order of the map, so second is "b"
        testCases.put(Arrays.asList("a", "b", "a", "b", "c"), "b");

        // 3) Exactly two words both twice → skip("a"), pick "b"
        testCases.put(Arrays.asList("x", "x", "y", "y"), "y");

        // 4) Only one word → no second most frequent → expected null
        testCases.put(Collections.singletonList("single"), null);

        // 5) Large data: 1,000,000 entries cycling through "word0"… "word9"
        //    Each appears 100,000 times → second most frequent is "word1"
        List<String> large = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            large.add("word" + (i % 10));                   // populate in round-robin
        }
        testCases.put(large, "word1");

        // Execute each test case
        for (Map.Entry<List<String>, String> entry : testCases.entrySet()) {
            List<String> words = entry.getKey();            // the input list
            String expected = entry.getValue();             // expected second most frequent
            String actual = findSecondMostFrequent(words);  // compute
            // Compare and print PASS or FAIL
            String status = Objects.equals(actual, expected) ? "PASS" : "FAIL";
            System.out.printf(
                    "Test [%d words]: expected = %-8s | actual = %-8s → %s%n",
                    words.size(), expected, actual, status
            );
        }
    }

    /**
     * Finds the second most frequent word in the given list.
     *
     * @param words list of strings; may be null or empty
     * @return the second most frequent word, or null if none exists
     */
    public static String findSecondMostFrequent(List<String> words) {
        // Edge cases: null or fewer than 2 total entries → cannot have a second most frequent
        if (words == null || words.size() < 2) {
            return null;
        }

        // 1) Build a frequency map: word → count
        Map<String, Long> frequencyMap = words.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),               // group by the word itself
                                Collectors.counting()              // count occurrences
                        )
                );

        // 2) Stream the entries, sort by count descending, skip the top one, take the next key
        return frequencyMap.entrySet().stream()
                .sorted(
                        Map.Entry.comparingByValue(Comparator.reverseOrder())
                )                                          // highest count first
                .skip(1)                                   // drop the single most frequent
                .map(Map.Entry::getKey)                    // extract the word
                .findFirst()                               // Optional<String>
                .orElse(null);                             // return null if no second element
    }
}