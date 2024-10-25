package com.interview.notes.code.months.oct24.amz.test11;

import java.util.*;
import java.util.stream.Collectors;

public class MostFrequent3PageSequence {

    public static void main(String[] args) {
        // Run all test cases
        runTests();
    }

    /**
     * Finds the most frequently visited 3-page sequence across all customers.
     *
     * @param logs List of log entries where each entry is a string in the format "time, customerId, pageVisited"
     * @return The most frequent 3-page sequence as a list of strings
     */
    public static List<String> mostVisitedPattern(List<String> logs) {
        // Step 1: Parse logs and group by customerId
        Map<String, List<LogEntry>> customerPageMap = new HashMap<>();
        for (String log : logs) {
            String[] parts = log.split(",\\s*");
            if (parts.length != 3) continue; // Invalid log entry
            String time = parts[0];
            String customerId = parts[1];
            String page = parts[2];
            customerPageMap
                    .computeIfAbsent(customerId, k -> new ArrayList<>())
                    .add(new LogEntry(time, page));
        }

        // Step 2: For each customer, sort their page visits by time
        Map<String, List<String>> sortedCustomerPages = new HashMap<>();
        for (Map.Entry<String, List<LogEntry>> entry : customerPageMap.entrySet()) {
            List<LogEntry> sortedLogs = entry.getValue().stream()
                    .sorted(Comparator.comparing(LogEntry::getTime))
                    .collect(Collectors.toList());
            List<String> pages = sortedLogs.stream()
                    .map(LogEntry::getPage)
                    .collect(Collectors.toList());
            sortedCustomerPages.put(entry.getKey(), pages);
        }

        // Step 3: Generate all possible 3-page sequences per customer and count frequencies
        Map<String, Integer> sequenceCount = new HashMap<>();
        for (List<String> pages : sortedCustomerPages.values()) {
            if (pages.size() < 3) continue;
            Set<String> sequences = new HashSet<>(); // To avoid duplicate sequences per customer
            for (int i = 0; i <= pages.size() - 3; i++) {
                String seq = pages.get(i) + "->" + pages.get(i + 1) + "->" + pages.get(i + 2);
                sequences.add(seq);
            }
            for (String seq : sequences) {
                sequenceCount.put(seq, sequenceCount.getOrDefault(seq, 0) + 1);
            }
        }

        // Step 4: Identify the sequence with the highest frequency
        String mostFrequentSeq = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : sequenceCount.entrySet()) {
            String seq = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount || (count == maxCount && seq.compareTo(mostFrequentSeq) < 0)) {
                maxCount = count;
                mostFrequentSeq = seq;
            }
        }

        // Convert the sequence string back to a list
        return Arrays.asList(mostFrequentSeq.split("->"));
    }

    /**
     * Runs all test cases and prints PASS/FAIL for each.
     */
    public static void runTests() {
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Provided Example
        testCases.add(new TestCase(
                Arrays.asList(
                        "TO, C1, A",
                        "TO, C2, E",
                        "T1, C1, B",
                        "T1, C2, B",
                        "T2, C1, C",
                        "T2, C2, C",
                        "T3, C1, D",
                        "T3, C2, D",
                        "T4, C1, E",
                        "T5, C2, A"
                ),
                Arrays.asList("B", "C", "D")
        ));

        // Test Case 2: Multiple Customers with Overlapping Sequences
        testCases.add(new TestCase(
                Arrays.asList(
                        "T1, C1, A",
                        "T2, C1, B",
                        "T3, C1, C",
                        "T4, C1, D",
                        "T1, C2, A",
                        "T2, C2, B",
                        "T3, C2, C",
                        "T4, C2, D",
                        "T5, C3, A",
                        "T6, C3, B",
                        "T7, C3, C"
                ),
                Arrays.asList("A", "B", "C")
        ));

        // Test Case 3: Single Customer with Multiple Sequences
        testCases.add(new TestCase(
                Arrays.asList(
                        "T1, C1, A",
                        "T2, C1, B",
                        "T3, C1, A",
                        "T4, C1, B",
                        "T5, C1, C"
                ),
                Arrays.asList("A", "B", "C")
        ));

        // Test Case 4: Customers with Less Than 3 Pages
        testCases.add(new TestCase(
                Arrays.asList(
                        "T1, C1, A",
                        "T2, C1, B",
                        "T1, C2, C",
                        "T2, C2, D"
                ),
                Collections.emptyList()
        ));

        // Test Case 5: Tie on Frequency, Lexicographical Order
        testCases.add(new TestCase(
                Arrays.asList(
                        "T1, C1, A",
                        "T2, C1, B",
                        "T3, C1, C",
                        "T1, C2, A",
                        "T2, C2, B",
                        "T3, C2, D"
                ),
                Arrays.asList("A", "B", "C") // "A->B->C" and "A->B->D" both have frequency 1; "A->B->C" is lex smaller
        ));

        // Test Case 6: Large Data Input
        testCases.add(new TestCase(generateLargeTestLogs(100000, 1000), Arrays.asList("Page1", "Page2", "Page3")));

        // Execute all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<String> result = mostVisitedPattern(tc.logs);
            if (result.equals(tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got     : " + result);
            }
        }

        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    /**
     * Generates a large test log with specified number of entries and customers.
     *
     * @param numEntries   Number of log entries to generate
     * @param numCustomers Number of unique customers
     * @return List of log entries
     */
    public static List<String> generateLargeTestLogs(int numEntries, int numCustomers) {
        List<String> logs = new ArrayList<>(numEntries);
        Random rand = new Random(0);
        for (int i = 0; i < numEntries; i++) {
            String time = "T" + i;
            String customer = "C" + (rand.nextInt(numCustomers) + 1);
            String page = "Page" + (rand.nextInt(100) + 1);
            logs.add(time + ", " + customer + ", " + page);
        }
        return logs;
    }

    /**
     * Represents a log entry with time and page visited.
     */
    static class LogEntry {
        private String time;
        private String page;

        public LogEntry(String time, String page) {
            this.time = time;
            this.page = page;
        }

        public String getTime() {
            return time;
        }

        public String getPage() {
            return page;
        }
    }

    /**
     * Represents a test case with input logs and the expected output sequence.
     */
    static class TestCase {
        List<String> logs;
        List<String> expected;

        public TestCase(List<String> logs, List<String> expected) {
            this.logs = logs;
            this.expected = expected;
        }
    }
}