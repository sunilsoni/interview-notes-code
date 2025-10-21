package com.interview.notes.code.year.y2025.october.common.test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateWordsCounter { // define a public class to hold logic and tests

    // normalize each word to make comparison fair and case-insensitive
    private static String normalize(String s) { // helper method to clean one word
        if (s == null) return ""; // if the word is null, treat as empty so it will be filtered out later
        String t = s.trim(); // remove leading/trailing spaces to avoid counting " apple " separately
        if (t.isEmpty()) return ""; // if empty after trim, return empty (will be filtered)
        return t.toLowerCase(Locale.ROOT); // make case-insensitive by converting to lower case safely
    }

    // count all words (including singles) after normalization
    public static Map<String, Long> countAll(List<String> words) { // returns frequency map of all normalized words
        if (words == null) return Collections.emptyMap(); // null list -> empty map; avoids NPE
        // stream over the list to process elements
        return words.stream() // start a stream on the input list
                .filter(Objects::nonNull) // skip null entries early
                .map(DuplicateWordsCounter::normalize) // trim + lower case each word
                .filter(s -> !s.isEmpty()) // skip empty results after normalization
                // group equal strings together and count them, preserve insertion order with LinkedHashMap
                .collect(Collectors.groupingBy(
                        Function.identity(), // key is the word itself
                        LinkedHashMap::new,  // keep deterministic iteration order if needed
                        Collectors.counting()// value is the number of occurrences (Long)
                ));
    }

    // find only duplicates (count >= 2) and sort them by count desc, then word asc
    public static Map<String, Long> findDuplicates(List<String> words) { // expose main API to get duplicate counts
        Map<String, Long> counts = countAll(words); // first get full frequency map for all words
        // filter to keep only duplicates and sort as needed
        return counts.entrySet().stream() // stream over entries of the frequency map
                .filter(e -> e.getValue() >= 2L) // keep entries where count is at least 2
                // sort by count descending, then by word ascending for stable readable output
                .sorted(
                        Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                // collect back into a LinkedHashMap to keep the sorted order
                .collect(Collectors.toMap(
                        Map.Entry::getKey,   // map key is the word
                        Map.Entry::getValue, // map value is the count
                        (a, b) -> a,         // merge function (not used here)
                        LinkedHashMap::new   // use LinkedHashMap to preserve sort order
                ));
    }

    // pretty print a map "word -> count" on a single line for debugging
    private static String mapToString(Map<String, Long> map) { // helper to convert map to printable string
        return map.entrySet().stream() // stream entries
                .map(e -> e.getKey() + "=" + e.getValue()) // convert each entry to "word=count"
                .collect(Collectors.joining(", ", "{", "}")); // join with commas and braces
    }

    // generic test runner that compares expected vs actual and prints PASS/FAIL
    private static void runTest(String name, List<String> input, Map<String, Long> expected) { // test helper
        Map<String, Long> actual = findDuplicates(input); // compute duplicates for the given input
        boolean pass = Objects.equals(expected, actual); // compare maps for exact equality
        System.out.println((pass ? "PASS" : "FAIL") + " - " + name); // print status and test name
        if (!pass) { // if failed, show expected vs actual to help debugging
            System.out.println("  Expected: " + mapToString(expected)); // print expected map
            System.out.println("  Actual  : " + mapToString(actual));   // print actual map
        }
    }

    // build a LinkedHashMap easily with varargs key-count pairs (k1, v1, k2, v2, ...)
    private static Map<String, Long> dupMap(Object... kv) { // convenience to write expected maps in tests
        LinkedHashMap<String, Long> m = new LinkedHashMap<>(); // preserve insertion order (matches our sort)
        for (int i = 0; i < kv.length; i += 2) { // walk pairs
            String k = (String) kv[i]; // key is a string
            Long v = ((Number) kv[i + 1]).longValue(); // value cast to long
            m.put(k, v); // put the pair into the map
        }
        return m; // return the built map
    }

    // compute expected frequency for a generated word "wK" when we produce N items cycling over vocabSize
    private static long expectedFreqForKey(int k, int totalN, int vocabSize) { // math for large-data test
        int base = totalN / vocabSize; // each key appears at least this many times
        int extra = (k < (totalN % vocabSize)) ? 1 : 0; // the first (N % vocabSize) keys get +1
        return base + extra; // total appearances of key k
    }

    public static void main(String[] args) { // entry point: runs all tests, no JUnit used
        // Test 1: basic duplicates with case-insensitive matching
        List<String> t1 = Arrays.asList("apple", "banana", "Apple", "orange", "banana", "apple"); // sample input
        // apple occurs 3 times (apple, Apple, apple), banana occurs 2 times, orange occurs once (ignored)
        runTest("Basic case-insensitive",
                t1,
                dupMap("apple", 3L, "banana", 2L)); // expected duplicates sorted by count desc

        // Test 2: no duplicates
        List<String> t2 = Arrays.asList("a", "b", "c"); // all unique
        runTest("No duplicates", t2, dupMap()); // expected empty map

        // Test 3: spaces, nulls, and mixed case
        List<String> t3 = Arrays.asList("  pear ", "PEAR", "", " ", null, "peach", "peach", "PEACH "); // messy input
        // pear -> 2, peach -> 3 after trimming and lowercasing; empties and null ignored
        runTest("Spaces/nulls/mixed case",
                t3,
                dupMap("peach", 3L, "pear", 2L)); // sorted by count desc then word

        // Test 4: punctuation kept as-is (design choice: we did NOT strip punctuation)
        List<String> t4 = Arrays.asList("car", "car,", "car"); // "car," is different from "car"
        // only "car" repeats -> 2; "car," appears once -> ignored
        runTest("Punctuation kept",
                t4,
                dupMap("car", 2L));

        // Test 5: null list input
        runTest("Null list", null, dupMap()); // expected empty

        // Large Data Test: performance and correctness spot-check
        final int N = 200_000; // total words to generate (tune up if you wish)
        final int VOCAB = 5_000; // how many distinct base words "w0"..."w4999"
        ArrayList<String> big = new ArrayList<>(N); // pre-size to avoid re-allocations
        for (int i = 0; i < N; i++) { // fill list with patterned words
            big.add("w" + (i % VOCAB)); // cycle through the vocabulary to guarantee many duplicates
        }
        long startNs = System.nanoTime(); // start timing
        Map<String, Long> bigDup = findDuplicates(big); // run algorithm on large input
        long tookMs = (System.nanoTime() - startNs) / 1_000_000; // compute elapsed ms

        // Spot-check a few keys deterministically so we are not “testing with the same code”
        String k0 = "w0"; // pick a few keys to verify exact counts
        String k1 = "w1";
        String kLast = "w" + (VOCAB - 1);
        boolean ok0 = bigDup.get(k0).equals(expectedFreqForKey(0, N, VOCAB)); // compare actual vs expected for w0
        boolean ok1 = bigDup.get(k1).equals(expectedFreqForKey(1, N, VOCAB)); // compare actual vs expected for w1
        boolean okL = bigDup.get(kLast).equals(expectedFreqForKey(VOCAB - 1, N, VOCAB)); // check last key too
        boolean passLarge = ok0 && ok1 && okL; // large test passes if all three checks match

        System.out.println((passLarge ? "PASS" : "FAIL") + " - Large Data Test " +
                "(N=" + N + ", vocab=" + VOCAB + ", time=" + tookMs + "ms, duplicates=" + bigDup.size() + ")"); // print summary
    }
}