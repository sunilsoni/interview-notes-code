package com.interview.notes.code.year.y2025.august.common.test8;

import java.util.*;                         // Import core collections (List, Map, LinkedHashMap, Arrays, etc.)
import java.util.function.Function;         // Import Function.identity() used in groupingBy
import java.util.stream.Collectors;         // Import Collectors for groupingBy and counting
import java.util.stream.IntStream;          // Import IntStream to generate large synthetic datasets efficiently
import java.util.concurrent.ThreadLocalRandom; // Import ThreadLocalRandom for large test data generation

public class EmailCounter {

    /**
     * Counts occurrences of each email.
     * @param emails      input list of email strings (may be large)
     * @param normalize   if true, we trim and lowercase each email to treat variants as the same
     * @return LinkedHashMap<email, count> preserving first-appearance order
     */
    public static LinkedHashMap<String, Long> countEmailOccurrences(List<String> emails, boolean normalize) {
        // Defensive null handling: if list is null, treat as empty to avoid NPEs
        List<String> safe = emails == null ? Collections.emptyList() : emails;

        // Stream the list to process each email declaratively
        return safe.stream()
                // Optionally normalize: trim spaces and lowercase so "  A@B.Com " == "a@b.com" (only if caller asks)
                .map(e -> normalize ? normalizeEmail(e) : e)
                // Group identical strings; use LinkedHashMap to preserve the *first appearance order*
                .collect(Collectors.groupingBy(
                        Function.identity(),                // Key function: the email string itself
                        LinkedHashMap::new,                 // Map supplier: keep insertion order (first occurrence)
                        Collectors.counting()               // Downstream: count items in each group, returns Long
                ));
    }

    /**
     * Normalization rule used when the caller enables it:
     * - null-safe: convert null to empty string
     * - trim surrounding spaces
     * - to lowercase (case-insensitive comparison)
     * Note: We are NOT doing domain-specific email rules (like removing dots for Gmail),
     *       because the problem statement said treat full strings as-is unless specified.
     */
    private static String normalizeEmail(String e) {
        // Replace null with empty so downstream operations don’t NPE
        String x = (e == null) ? "" : e;
        // Remove leading/trailing spaces
        x = x.trim();
        // Convert to lowercase for case-insensitive equality when requested
        x = x.toLowerCase(Locale.ROOT);
        // Return normalized email
        return x;
    }

    /**
     * Helper to pretty-print the results as "email count" per line.
     * The problem asks to print each unique email followed by the count.
     */
    public static void printCounts(Map<String, Long> counts) {
        // Null-safe: if map is null, nothing to print
        if (counts == null) return;
        // For each entry preserving map’s iteration order
        counts.forEach((email, cnt) -> {
            // Print "email count" exactly as required
            System.out.println(email + " " + cnt);
        });
    }

    /**
     * Utility to check equality of expected map and actual map (both email->count).
     * This helps us do PASS/FAIL without JUnit.
     */
    private static boolean mapsEqual(Map<String, Long> expected, Map<String, Long> actual) {
        // Quick checks to avoid NPEs and mismatch
        if (expected == actual) return true;
        if (expected == null || actual == null) return false;
        // Size must match
        if (expected.size() != actual.size()) return false;
        // Each key must exist with same count
        for (Map.Entry<String, Long> e : expected.entrySet()) {
            if (!Objects.equals(e.getValue(), actual.get(e.getKey()))) return false;
        }
        // All good
        return true;
    }

    /**
     * Build a LinkedHashMap easily from varargs (key, value, key, value...) for expected outputs.
     * Example: mapOf("a",1L,"b",2L) -> {a=1, b=2}
     */
    @SafeVarargs
    private static LinkedHashMap<String, Long> mapOf(Object... kv) {
        // Create LinkedHashMap to control insertion order (matching our collector)
        LinkedHashMap<String, Long> m = new LinkedHashMap<>();
        // Expect even number of items: (key,value) pairs
        for (int i = 0; i < kv.length; i += 2) {
            // Cast key to String (input format is controlled in tests)
            String k = (String) kv[i];
            // Cast value to Long
            Long v = (Long) kv[i + 1];
            // Put in order
            m.put(k, v);
        }
        // Return built map
        return m;
    }

    /**
     * Generate a large synthetic dataset of emails like userNNN@domainK.com for performance/memory testing.
     * @param total total number of events to generate
     * @param distinctUsers number of distinct user ids to cycle through
     * @param distinctDomains number of distinct domains to cycle through
     * @return List of generated emails of size 'total'
     */
    private static List<String> generateLargeEmails(int total, int distinctUsers, int distinctDomains) {
        // Use ArrayList with pre-sized capacity for better performance and fewer resizes
        ArrayList<String> list = new ArrayList<>(total);
        // ThreadLocalRandom is efficient and thread-safe per thread; we use it to vary user/domain
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        // Fill list with synthesized emails in O(total)
        for (int i = 0; i < total; i++) {
            // Random user id in [0, distinctUsers)
            int user = rnd.nextInt(distinctUsers);
            // Random domain id in [0, distinctDomains)
            int dom = rnd.nextInt(distinctDomains);
            // Build email like "user123@domain7.com"
            String email = "user" + user + "@domain" + dom + ".com";
            // Add to list
            list.add(email);
        }
        // Return the generated dataset
        return list;
    }

    /**
     * Print PASS/FAIL label with a message to make test outcomes obvious.
     */
    private static void assertPass(String testName, boolean condition) {
        // Print PASS if condition is true, else FAIL; keep it short and visible
        System.out.println((condition ? "PASS: " : "FAIL: ") + testName);
    }

    /**
     * MAIN METHOD (no JUnit). Runs multiple tests and a large-data test.
     * Also prints example output to the console.
     */
    public static void main(String[] args) {
        // ------------- Test 1: Example from prompt (treat strings as-is; no normalization enabled) -------------
        List<String> input1 = Arrays.asList(
                "pooja.mani@lseg.com",
                "abc@lseg.com",
                "xyz@lseg.com",
                "pooja.mani@lseg.com"
        );
        // Count occurrences without normalization (as specified)
        LinkedHashMap<String, Long> out1 = countEmailOccurrences(input1, false);

        // Expected map in first-appearance order
        LinkedHashMap<String, Long> exp1 = mapOf(
                "pooja.mani@lseg.com", 2L,
                "abc@lseg.com",        1L,
                "xyz@lseg.com",        1L
        );

        // Validate and print PASS/FAIL
        assertPass("Example input (as-is)", mapsEqual(exp1, out1));

        // For visibility, print the actual results
        System.out.println("-- Printed Results (Example) --");
        printCounts(out1);

        // ------------- Test 2: Empty input -------------
        List<String> input2 = Collections.emptyList();
        LinkedHashMap<String, Long> out2 = countEmailOccurrences(input2, false);
        LinkedHashMap<String, Long> exp2 = new LinkedHashMap<>();
        assertPass("Empty input", mapsEqual(exp2, out2));

        // ------------- Test 3: Single email repeated -------------
        List<String> input3 = Arrays.asList("a@x.com", "a@x.com", "a@x.com");
        LinkedHashMap<String, Long> out3 = countEmailOccurrences(input3, false);
        LinkedHashMap<String, Long> exp3 = mapOf("a@x.com", 3L);
        assertPass("Single email repeated", mapsEqual(exp3, out3));

        // ------------- Test 4: Case sensitivity (as-is) -------------
        List<String> input4 = Arrays.asList("A@X.COM", "a@x.com", "A@X.COM");
        LinkedHashMap<String, Long> out4 = countEmailOccurrences(input4, false);
        LinkedHashMap<String, Long> exp4 = mapOf(
                "A@X.COM", 2L,    // counted separately because no normalization
                "a@x.com", 1L
        );
        assertPass("Case sensitive (as-is)", mapsEqual(exp4, out4));

        // ------------- Test 5: With normalization (trim + lowercase) -------------
        List<String> input5 = Arrays.asList("  A@X.COM ", "a@x.com", "A@X.COM");
        LinkedHashMap<String, Long> out5 = countEmailOccurrences(input5, true);
        LinkedHashMap<String, Long> exp5 = mapOf("a@x.com", 3L); // all collapse to lowercase
        assertPass("Case-insensitive with trim (normalize=true)", mapsEqual(exp5, out5));

        // ------------- Test 6: First-appearance order preserved -------------
        List<String> input6 = Arrays.asList("b@k.com", "a@k.com", "b@k.com", "c@k.com");
        LinkedHashMap<String, Long> out6 = countEmailOccurrences(input6, false);
        LinkedHashMap<String, Long> exp6 = mapOf(
                "b@k.com", 2L,  // appears first, stays first
                "a@k.com", 1L,
                "c@k.com", 1L
        );
        assertPass("First appearance order", mapsEqual(exp6, out6));

        // ------------- Test 7: Large data performance test -------------
        // Generate 1,000,000 events (adjust down if memory-limited)
        int total = 1_000_000;
        int distinctUsers = 100_000;    // up to 100k unique users
        int distinctDomains = 10;       // 10 domain buckets
        List<String> big = generateLargeEmails(total, distinctUsers, distinctDomains);
        long t0 = System.currentTimeMillis();
        LinkedHashMap<String, Long> bigOut = countEmailOccurrences(big, false);
        long t1 = System.currentTimeMillis();
        // Basic sanity: total counts must sum back to 'total'
        long summed = bigOut.values().stream().mapToLong(Long::longValue).sum();
        boolean okTotal = (summed == total);
        System.out.println("Large data total events: " + total + ", distinct keys: " + bigOut.size() + ", time ms: " + (t1 - t0));
        assertPass("Large data sum check", okTotal);

        // ------------- Optional: print a small sample of large-data counts -------------
        System.out.println("-- Sample from large data (first 5) --");
        bigOut.entrySet().stream().limit(5).forEach(e ->
                System.out.println(e.getKey() + " " + e.getValue())
        );

        // ------------- Done -------------
    }
}