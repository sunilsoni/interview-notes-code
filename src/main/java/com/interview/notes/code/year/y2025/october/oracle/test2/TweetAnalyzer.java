package com.interview.notes.code.year.y2025.october.oracle.test2;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TweetAnalyzer {

    // ---------- Core API ----------

    // Analyze text and return tokens (lowercased alphabetic words) in original order
    public static List<String> tokenize(String text) {                     // Method returns the list of words
        String lower = text == null ? "" : text.toLowerCase();             // Make everything lowercase; handle null safely
        Pattern word = Pattern.compile("[a-z]+");                          // Regex: only alphabet letters form a word
        Matcher m = word.matcher(lower);                                   // Matcher to find words
        List<String> out = new ArrayList<>();                              // Holds all words in order
        while (m.find()) {                                                 // Scan the string to find each word match
            out.add(m.group());                                            // Add each found word to the list
        }
        return out;                                                        // Return the full token list
    }

    // Unique words in order of first appearance
    public static List<String> uniqueWords(List<String> tokens) {          // Accept pre-tokenized words
        return tokens.stream()                                             // Stream over all tokens
                .distinct()                                           // Keep only first occurrence of each word
                .collect(Collectors.toList());                        // Gather into a list preserving order
    }

    // Frequency map of each word, preserving first-seen key order
    public static LinkedHashMap<String, Long> frequency(List<String> tokens) { // Return LinkedHashMap for stable iteration
        return tokens.stream()                                             // Stream over tokens
                .collect(Collectors.groupingBy(                             // Group tokens by the token itself
                        Function.identity(),                                // Key is the word string
                        LinkedHashMap::new,                                 // Use LinkedHashMap to keep encounter order
                        Collectors.counting()                               // Count how many times each word appears
                ));
    }

    // Helper: first index of each word (used to break ties by earliest appearance)
    public static Map<String, Integer> firstIndexMap(List<String> tokens) { // Build first-index map
        Map<String, Integer> first = new HashMap<>();                      // Fast lookups; order not needed here
        for (int i = 0; i < tokens.size(); i++) {                          // Walk through tokens by index
            final int idx = i;                                             // Capture index for lambda below if needed
            first.putIfAbsent(tokens.get(i), idx);                         // Record only the first time we see the word
        }
        return first;                                                      // Return the index map
    }

    // Most frequent single word, tie -> earliest first appearance
    public static Optional<String> mostFrequentWord(List<String> tokens) { // Use Optional in case there are no words
        if (tokens.isEmpty()) return Optional.empty();                     // Empty input -> no result
        LinkedHashMap<String, Long> freq = frequency(tokens);              // Build frequency map
        Map<String, Integer> first = firstIndexMap(tokens);                // Build first-index map
        Comparator<Map.Entry<String, Long>> byCountThenFirst =             // Comparator for (count desc, first-index asc)
                Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getValue()).reversed()
                        .thenComparingInt(e -> first.get(e.getKey()));
        return freq.entrySet().stream()                                    // Stream entries of frequency map
                .max(byCountThenFirst)                                  // Pick the maximum by our comparator
                .map(Map.Entry::getKey);                                // Return only the word
    }

    // Top N frequent words (tie -> earliest first appearance)
    public static List<String> topNFrequentWords(List<String> tokens, int n) { // Return list of top N words
        if (n <= 0 || tokens.isEmpty()) return Collections.emptyList();    // If N ≤ 0 or no words -> empty result
        LinkedHashMap<String, Long> freq = frequency(tokens);              // Count frequencies
        Map<String, Integer> first = firstIndexMap(tokens);                // First-appearance indices
        Comparator<Map.Entry<String, Long>> byCountThenFirst =             // Sorting rule: high count first, early index first
                Comparator.<Map.Entry<String, Long>>comparingLong(e -> e.getValue()).reversed()
                        .thenComparingInt(e -> first.get(e.getKey()));
        return freq.entrySet().stream()                                    // Stream frequency entries
                .sorted(byCountThenFirst)                               // Sort by our rule
                .limit(n)                                               // Take only top N
                .map(Map.Entry::getKey)                                 // Pull out the words
                .collect(Collectors.toList());                          // Collect into a list
    }

    // ---------- Pretty printers (for debug and human-friendly results) ----------

    public static String joinList(List<String> items) {                    // Join list into comma-separated string
        return items.stream().collect(Collectors.joining(", "));           // Simple join for output
    }

    public static String freqToString(Map<String, Long> freq) {            // Stable string for frequency map
        return freq.entrySet().stream()                                    // Stream over entries
                .map(e -> e.getKey() + ":" + e.getValue())              // Format "word:count"
                .collect(Collectors.joining(", "));                     // Join with commas
    }

    // ---------- Simple test helpers (no JUnit) ----------

    private static void assertEquals(String name, Object got, Object expected) { // Compare two objects using equals()
        boolean ok = Objects.equals(got, expected);                       // Use Objects.equals for null-safety
        System.out.println((ok ? "PASS " : "FAIL ") + name +              // Print PASS/FAIL + test name
                " | expected=" + expected + " | got=" + got);             // Show both expected and actual
    }

    private static void assertListEquals(String name, List<String> got, List<String> expected) { // Special list compare
        boolean ok = Objects.equals(got, expected);                       // Lists have value-based equals with order
        System.out.println((ok ? "PASS " : "FAIL ") + name +              // Print outcome
                " | expected=[" + joinList(expected) + "] | got=[" + joinList(got) + "]"); // Show both lists
    }

    private static void assertStringEquals(String name, String got, String expected) { // Convenience for strings
        boolean ok = Objects.equals(got, expected);                       // Compare strings
        System.out.println((ok ? "PASS " : "FAIL ") + name +              // Outcome line
                " | expected=" + expected + " | got=" + got);             // Show values
    }

    // ---------- Demo + Tests ----------

    public static void main(String[] args) {
        // --- Base sample from the prompt image ---
        String sample = "this is a sample, sample is pretty easy. Sometimes easy is not pretty."; // Input text

        List<String> tokens = tokenize(sample);                             // 1) Tokenize the text
        List<String> uniques = uniqueWords(tokens);                         // 2) Unique words in order
        LinkedHashMap<String, Long> freq = frequency(tokens);               // 3) Word → count
        String most = mostFrequentWord(tokens).orElse("");                  // 4) Most frequent word (or empty if none)
        List<String> top2 = topNFrequentWords(tokens, 2);                   // 5) Top 2 frequent words

        // Expected results for the sample (computed by hand following rules)
        List<String> expectedUnique = Arrays.asList(                        // Unique words in first-seen order
                "this", "is", "a", "sample", "pretty", "easy", "sometimes", "not"
        );
        LinkedHashMap<String, Long> expectedFreq = new LinkedHashMap<>();  // Expected frequency map
        expectedFreq.put("this", 1L);
        expectedFreq.put("is", 3L);            // Fill expected counts
        expectedFreq.put("a", 1L);
        expectedFreq.put("sample", 2L);
        expectedFreq.put("pretty", 2L);
        expectedFreq.put("easy", 2L);
        expectedFreq.put("sometimes", 1L);
        expectedFreq.put("not", 1L);
        String expectedMost = "is";                                        // "is" has the highest frequency (3)
        List<String> expectedTop2 = Arrays.asList("is", "sample");          // Tie at 2 goes to earliest first-appearance

        // Assertions for the sample
        assertListEquals("Unique(sample)", uniques, expectedUnique);       // Check unique words
        assertEquals("Freq(sample)", freqToString(freq),                   // Compare via stable string form
                freqToString(expectedFreq));
        assertStringEquals("Most(sample)", most, expectedMost);            // Check most frequent word
        assertListEquals("Top2(sample)", top2, expectedTop2);              // Check top 2 words

        // --- Edge: empty string ---
        String empty = "";                                                 // Empty input
        List<String> tEmpty = tokenize(empty);                             // Tokenize empty
        assertListEquals("Tokens(empty)", tEmpty, Collections.emptyList()); // Should be empty
        assertListEquals("Unique(empty)", uniqueWords(tEmpty), Collections.emptyList()); // Still empty
        assertEquals("Freq(empty)", freqToString(frequency(tEmpty)), "");  // No entries → empty string
        assertStringEquals("Most(empty)", mostFrequentWord(tEmpty).orElse(""), ""); // No most-frequent
        assertListEquals("TopN(empty)", topNFrequentWords(tEmpty, 5), Collections.emptyList()); // No top-N

        // --- Edge: punctuation and case mix ---
        String punct = "Hello, HELLO!! HeLlo? world... WORLD";             // Mixed case and punctuation
        List<String> tPunct = tokenize(punct);                             // Tokenize with regex
        assertListEquals("Tokens(punct)", tPunct,                          // Expected normalized tokens
                Arrays.asList("hello", "hello", "hello", "world", "world"));
        assertListEquals("Unique(punct)", uniqueWords(tPunct),             // Unique order should be hello, world
                Arrays.asList("hello", "world"));
        assertStringEquals("Most(punct)",                                  // Most frequent is "hello" (3 vs 2)
                mostFrequentWord(tPunct).orElse(""), "hello");
        assertListEquals("Top1(punct)", topNFrequentWords(tPunct, 1),      // Top1 should be just "hello"
                List.of("hello"));

        // --- Edge: N larger than unique count ---
        String shortTxt = "one two two";                                   // Small input
        List<String> tShort = tokenize(shortTxt);                          // Tokens: [one, two, two]
        assertListEquals("Top5(short)", topNFrequentWords(tShort, 5),      // Ask for more than exists
                Arrays.asList("two", "one"));                               // Should return only existing words

        // --- Edge: N <= 0 ---
        assertListEquals("Top0(short)", topNFrequentWords(tShort, 0),      // N=0 should be empty
                Collections.emptyList());

        // --- Large data test (performance sanity) ---
        // Build a large text with controlled frequencies to verify correctness and avoid timeouts.
        // Pattern: 200k tokens where: w0 occurs 50k times, w1 occurs 80k times, w2 occurs 70k times, others 0.. small.
        int nWords = 1000;                                                 // We will use 1000 distinct labels (w0..w999)
        StringBuilder sb = new StringBuilder(2_000_000);                   // Pre-size buffer to cut allocations
        for (int i = 0; i < 50_000; i++) sb.append("w0 ");                 // Add w0 many times
        for (int i = 0; i < 80_000; i++) sb.append("w1 ");                 // Add w1 many times (should be top1)
        for (int i = 0; i < 70_000; i++) sb.append("w2 ");                 // Add w2 many times (should be top2)
        for (int i = 3; i < nWords; i++) sb.append("w").append(i).append(' '); // Add one of each extra word

        String big = sb.toString();                                        // Build the big input
        List<String> tBig = tokenize(big);                                 // Tokenize big text
        List<String> top3Big = topNFrequentWords(tBig, 3);                 // Ask for top 3
        assertListEquals("Top3(large)", top3Big,                           // We expect w1, w2, w0 (tie rule irrelevant here)
                Arrays.asList("w1", "w2", "w0"));                            // w1=80k, w2=70k, w0=50k

        // --- Human-friendly demo print for the sample ---
        System.out.println("\n--- Demo Output (Sample) ---");              // Section header
        System.out.println("Tokens: " + tokens);                           // Show token list
        System.out.println("Unique: " + uniques);                          // Show unique words
        System.out.println("Freq: " + freqToString(freq));                 // Show frequencies
        System.out.println("Most: " + most);                               // Show most frequent word
        System.out.println("Top2: " + top2);                               // Show top 2 words
    }
}
