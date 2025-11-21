package com.interview.notes.code.year.y2025.november.common.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LogExceptionCounter
 * -------------------
 * Reads log lines, extracts Java exception class names via regex, and prints counts per exception type.
 * - Java 8 compliant; uses Stream API and regex.
 * - Fully commented line-by-line to explain each step.
 * - Includes a simple main-based test harness (no JUnit) with PASS/FAIL output and large data generation.
 */
public class LogExceptionCounter {

    // Compile the regex pattern once and reuse: matches simple or fully-qualified class names ending with 'Exception'
    // \b ensures we match a word boundary (avoid partial tokens)
    // ([A-Za-z_]\w*(?:\.[A-Za-z_]\w*)*Exception) allows optional package segments separated by dots
    private static final Pattern EXCEPTION_PATTERN = Pattern.compile(
            "\\b([A-Za-z_]\\w*(?:\\.[A-Za-z_]\\w*)*Exception)\\b"
    );

    /**
     * parseAndCountExceptions
     * -----------------------
     * Core function: given a Reader over log text, find all exception class tokens and return a sorted frequency map.
     * The returned map is sorted by count (desc) then by exception name (asc) for stable output.
     */
    public static LinkedHashMap<String, Long> parseAndCountExceptions(Reader reader) throws IOException {
        // Use BufferedReader to get a Stream<String> of lines when the input isn't a file
        try (BufferedReader br = new BufferedReader(reader)) {

            // We need to stream lines and, for each line, stream all regex matches (not just one).
            // To do that, we map each line to a Stream<String> of matches, then flatMap to a single stream of tokens.
            Map<String, Long> freq = br.lines() // Stream<String> over all lines
                    .flatMap(LogExceptionCounter::findExceptionTokens) // Stream<String> of every exception token on each line
                    .collect(Collectors.groupingBy(                 // Group identical tokens
                            s -> s,                                 // key: exception name string
                            Collectors.counting()                   // value: frequency as Long
                    ));

            // If no matches, return an empty ordered map for graceful handling
            if (freq.isEmpty()) {
                return new LinkedHashMap<>();
            }

            // Now sort entries by count desc, then by name asc, and collect into a LinkedHashMap to preserve order
            return freq.entrySet().stream()
                    .sorted(Comparator
                            .<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue)
                            .reversed()
                            .thenComparing(Map.Entry::getKey))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,            // key mapper
                            Map.Entry::getValue,          // value mapper
                            (a, b) -> a,                  // merge (not used)
                            LinkedHashMap::new            // keep sorted order
                    ));
        }
    }

    /**
     * findExceptionTokens
     * -------------------
     * Given a single log line, find ALL matches of the exception regex and produce them as a Stream<String>.
     * This helper makes it easy to use flatMap in the main pipeline.
     */
    private static Stream<String> findExceptionTokens(String line) {
        // Null guard: if somehow a null line appears (it shouldn't), return empty stream
        if (line == null) return Stream.empty();

        // Create matcher on the line
        Matcher m = EXCEPTION_PATTERN.matcher(line);

        // Collect all matches into a list (Java 8 has no built-in Stream over Matcher.find())
        List<String> matches = new ArrayList<>();
        while (m.find()) {
            // Group 1 is the exception class name we captured
            matches.add(m.group(1));
        }

        // Return a stream over collected matches
        return matches.stream();
    }

    /**
     * printReport
     * -----------
     * Pretty-prints the frequency map as a table plus a minimal ASCII bar for quick visual scanning.
     */
    public static void printReport(LinkedHashMap<String, Long> sortedFreq) {
        // Print header line
        System.out.println("Exception Type                                      | Count | Bar");
        System.out.println("----------------------------------------------------+-------+------------------------------");

        // Determine the max to scale bars; if empty, nothing to print
        long max = sortedFreq.values().stream().mapToLong(Long::longValue).max().orElse(0L);

        // Iterate in order (LinkedHashMap preserves the sorted order)
        sortedFreq.forEach((name, count) -> {
            // Scale bar length between 0 and 30 chars (avoid division by zero)
            int barLen = (max > 0) ? (int) Math.max(1, Math.round(30.0 * count / max)) : 0;

            // Build bar string
            StringBuilder bar = new StringBuilder();
            for (int i = 0; i < barLen; i++) bar.append('#');

            // Left-pad/trim exception name to align columns (max 52 chars shown)
            String left = (name.length() <= 52)
                    ? String.format("%-52s", name)
                    : name.substring(0, 49) + "...";

            // Print row
            System.out.printf("%s | %5d | %s%n", left, count, bar);
        });
    }

    /**
     * main
     * ----
     * Usage:
     * 1) java LogExceptionCounter <path-to-log>
     * 2) cat app.log | java LogExceptionCounter
     * 3) java LogExceptionCounter            (runs built-in tests and demo)
     * <p>
     * Behavior:
     * - If a file path arg is provided: parse that file and print the report.
     * - If no args: run a set of PASS/FAIL tests, then show a small demo.
     */
    public static void main(String[] args) throws Exception {
        // If a file path is provided, parse and print report for that file
        if (args != null && args.length >= 1) {
            // Build Path from first argument
            Path p = Paths.get(args[0]);

            // Use Files.newBufferedReader to ensure UTF-8 and stream safely
            try (BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
                LinkedHashMap<String, Long> result = parseAndCountExceptions(br);
                printReport(result);
            }
            return; // Exit after printing report for the given file
        }

        // No args: run tests and demo to validate logic without JUnit
        runSelfTests();    // Run a set of PASS/FAIL tests on controlled inputs
        runLargeDataTest();// Stress test for performance with generated data
        demoSmallReport(); // Show a small human-readable demo report
    }

    /**
     * runSelfTests
     * ------------
     * A simple PASS/FAIL harness:
     * - Builds small in-memory logs with known expected counts
     * - Compares function output to expected map
     * - Prints PASS or FAIL lines
     */
    private static void runSelfTests() throws IOException {
        System.out.println("=== Self Tests (no JUnit) ===");

        // Test 1: Basic exceptions, fully qualified + simple names + Caused by
        String log1 =
                "2025-11-07 ERROR java.io.IOException: disk full\n" +
                        "Caused by: org.acme.storage.StorageException: write failed\n" +
                        "WARN Retrying after TimeoutException\n";
        LinkedHashMap<String, Long> out1 = parseAndCountExceptions(new StringReader(log1));

        Map<String, Long> expected1 = new HashMap<>();
        expected1.put("java.io.IOException", 1L);
        expected1.put("org.acme.storage.StorageException", 1L);
        expected1.put("TimeoutException", 1L);

        assertPass("Test1 basic matching", mapsEqual(out1, expected1));

        // Test 2: Multiple matches on the same line (both counted)
        String log2 =
                "ERROR WrapperException caused by InnerException and also AnotherInnerException\n";
        LinkedHashMap<String, Long> out2 = parseAndCountExceptions(new StringReader(log2));

        Map<String, Long> expected2 = new HashMap<>();
        expected2.put("WrapperException", 1L);
        expected2.put("InnerException", 1L);
        expected2.put("AnotherInnerException", 1L);

        assertPass("Test2 multi-match single line", mapsEqual(out2, expected2));

        // Test 3: No exceptions found → empty result
        String log3 = "INFO All good. No failures.\nDEBUG Value x=42\n";
        LinkedHashMap<String, Long> out3 = parseAndCountExceptions(new StringReader(log3));
        assertPass("Test3 no matches", out3.isEmpty());

        // Test 4: Long qualified names + underscores/digits after first char
        String log4 =
                "Caused by: com.example.deeply.nested_name.Funky123Exception: boom\n" +
                        "ERROR com.example.deeply.nested_name.Funky123Exception: again\n";
        LinkedHashMap<String, Long> out4 = parseAndCountExceptions(new StringReader(log4));

        Map<String, Long> expected4 = new HashMap<>();
        expected4.put("com.example.deeply.nested_name.Funky123Exception", 2L);

        assertPass("Test4 qualified + underscores + digits", mapsEqual(out4, expected4));

        // Test 5: Ensure we don’t match partial words
        String log5 =
                "INFO NotAnExceptio note: but TimeoutException should count\n";
        LinkedHashMap<String, Long> out5 = parseAndCountExceptions(new StringReader(log5));

        Map<String, Long> expected5 = new HashMap<>();
        expected5.put("TimeoutException", 1L);

        assertPass("Test5 word boundaries", mapsEqual(out5, expected5));

        System.out.println("=== End Self Tests ===\n");
    }

    /**
     * runLargeDataTest
     * ----------------
     * Generates a large synthetic log in-memory to test performance and memory behavior.
     * We don’t print the full report here—only verify counts and print timing.
     */
    private static void runLargeDataTest() throws IOException {
        System.out.println("=== Large Data Test (synthetic) ===");
        // Define a small catalog of exception types to sample from
        String[] pool = {
                "NullPointerException",
                "IllegalArgumentException",
                "java.io.IOException",
                "org.example.CustomException",
                "TimeoutException",
                "javax.net.ssl.SSLException"
        };

        // Choose how many lines to generate. Adjust if needed.
        final int LINES = 200_000; // 200k lines ~ fast but meaningful
        StringBuilder sb = new StringBuilder(LINES * 40);

        // Randomly generate lines where some contain exceptions and some don’t
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        long expectedTotal = 0L;

        for (int i = 0; i < LINES; i++) {
            // ~70% of lines contain an exception, possibly 1-2 mentions
            if (rnd.nextDouble() < 0.7) {
                String e1 = pool[rnd.nextInt(pool.length)];
                sb.append("ERROR ").append(e1).append(": simulated message\n");
                expectedTotal++;
                if (rnd.nextDouble() < 0.2) {
                    String e2 = pool[rnd.nextInt(pool.length)];
                    sb.append("Caused by: ").append(e2).append(": cause message\n");
                    expectedTotal++;
                }
            } else {
                // Non-exception line
                sb.append("INFO heartbeat ok\n");
            }
        }

        long start = System.currentTimeMillis();
        LinkedHashMap<String, Long> out = parseAndCountExceptions(new StringReader(sb.toString()));
        long end = System.currentTimeMillis();

        long sum = out.values().stream().mapToLong(Long::longValue).sum();
        boolean ok = (sum == expectedTotal);

        System.out.println("Lines: " + LINES + ", Matches: " + sum + ", Expected: " + expectedTotal +
                ", Time(ms): " + (end - start));
        assertPass("LargeData total-match-count check", ok);
        System.out.println("=== End Large Data Test ===\n");
    }

    /**
     * demoSmallReport
     * ---------------
     * Prints a tiny human-readable report so you can see the output format quickly.
     */
    private static void demoSmallReport() throws IOException {
        System.out.println("=== Demo Report ===");
        String demo =
                "2025-11-07 10:00:00 ERROR java.io.IOException: disk full\n" +
                        "Caused by: org.acme.storage.StorageException: write failed\n" +
                        "WARN Retrying after TimeoutException\n" +
                        "WARN Another TimeoutException observed\n";
        LinkedHashMap<String, Long> result = parseAndCountExceptions(new StringReader(demo));
        printReport(result);
        System.out.println("=== End Demo ===");
    }

    /**
     * mapsEqual
     * ---------
     * Helper to compare map contents (ignoring order).
     */
    private static boolean mapsEqual(Map<String, Long> a, Map<String, Long> b) {
        if (a.size() != b.size()) return false;
        for (Map.Entry<String, Long> e : a.entrySet()) {
            Long other = b.get(e.getKey());
            if (!Objects.equals(e.getValue(), other)) return false;
        }
        return true;
    }

    /**
     * assertPass
     * ----------
     * Prints PASS/FAIL with a label; does not throw—keeps tests running to show all results.
     */
    private static void assertPass(String label, boolean ok) {
        System.out.println((ok ? "[PASS] " : "[FAIL] ") + label);
    }
}
