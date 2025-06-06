package com.interview.notes.code.year.y2025.may.common.test14;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Finds the k most frequent words using Java 8 Streams.
 */
public class MostFrequentWords {

    /**
     * MAIN METHOD – runs simple tests, no JUnit needed.
     */
    public static void main(String[] args) {
        /* ------------- Define test cases -------------- */
        // Example from the prompt
        String[] words1 = {"java", "kafka", "spring", "java", "aws", "spring", "kafka", "java"};
        int k1 = 2;
        List<String> expected1 = Arrays.asList("java", "kafka");

        // All words same frequency -> lexicographic tie-breaker
        String[] words2 = {"c", "b", "a"};
        int k2 = 2;
        List<String> expected2 = Arrays.asList("a", "b");

        // k larger than unique words
        String[] words3 = {"a", "a", "b"};
        int k3 = 5;
        List<String> expected3 = Arrays.asList("a", "b");

        // Empty input
        String[] words4 = {};
        int k4 = 3;
        List<String> expected4 = Collections.emptyList();

        // Large data test (100 000 “x”, 100 000 “y”, 50 000 “z”)
        String[] words5 = buildLargeInput();
        int k5 = 3;
        List<String> expected5 = Arrays.asList("x", "y", "z"); // by freq then alpha

        /* ------------- Run tests -------------- */
        runTest(words1, k1, expected1, "Provided example");
        runTest(words2, k2, expected2, "Tie-breaker example");
        runTest(words3, k3, expected3, "k > unique words");
        runTest(words4, k4, expected4, "Empty input");
        runTest(words5, k5, expected5, "Large data (~250 k words)");

        /* ------------- Done -------------- */
    }

    /**
     * Core function returning the k most frequent words.
     *
     * @param words input array of words
     * @param k     number of top frequent words requested
     * @return list of words satisfying the criteria
     */
    public static List<String> topKFrequent(String[] words, int k) {
        // Handle edge cases first – if k <= 0 or no words, return empty list.
        if (k <= 0 || words == null || words.length == 0) {
            return Collections.emptyList();
        }

        // STEP 1: Build a frequency map: word -> count
        Map<String, Long> freqMap = Arrays.stream(words)          // convert array to Stream<String>
                .collect(Collectors.groupingBy(                   // group identical words
                        w -> w,                                   // classifier = the word itself
                        Collectors.counting()));                  // downstream collector = counting

        // STEP 2: Sort entries by (1) descending count, (2) ascending word
        List<String> result = freqMap.entrySet().stream()         // stream over Map.Entry<String,Long>
                .sorted(Comparator                                 // custom comparator
                        .<Map.Entry<String, Long>>comparingLong(e -> -e.getValue()) // higher count first
                        .thenComparing(Map.Entry::getKey))        // then alphabetical
                .limit(k)                                         // keep only k elements
                .map(Map.Entry::getKey)                           // convert Entry to just the word
                .collect(Collectors.toList());                    // collect to List<String>

        // STEP 3: Return the final list
        return result;
    }

    /* ------------- Helper functions -------------- */

    /**
     * Runs one test case and prints PASS / FAIL.
     */
    private static void runTest(String[] words, int k, List<String> expected, String label) {
        List<String> actual = topKFrequent(words, k);             // call the solution
        if (actual.equals(expected)) {                            // List equality checks order & content
            System.out.println(label + " : PASS");
        } else {
            System.out.println(label + " : FAIL");
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual  : " + actual);
        }
    }

    /**
     * Builds a large input array for the performance test.
     * 100k x, 100k y, 50k z -> total 250k words.
     */
    private static String[] buildLargeInput() {
        int xCount = 100_000;
        int yCount = 100_000;
        int zCount = 50_000;
        String[] arr = new String[xCount + yCount + zCount];
        int idx = 0;

        // Fill with 'x'
        for (int i = 0; i < xCount; i++) {
            arr[idx++] = "x";
        }

        // Fill with 'y'
        for (int i = 0; i < yCount; i++) {
            arr[idx++] = "y";
        }

        // Fill with 'z'
        for (int i = 0; i < zCount; i++) {
            arr[idx++] = "z";
        }

        return arr;                                               // return the big array
    }
}
