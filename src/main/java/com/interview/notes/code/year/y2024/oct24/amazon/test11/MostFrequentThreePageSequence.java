package com.interview.notes.code.year.y2024.oct24.amazon.test11;

/*
Given a log of website requests, where each line contains an entry with the following fields (time, customerId, pageVisited), write an algorithm to find the most frequently visited 3-page sequence of page visits for all customers.
Each line represents a request (A-Z) made by customer (C#) at time T to one of the website's pages.
For example, given the following log file containing:
TO, C1, A TO, C2, E T1, C1, B
T1, C2, B
T2, C1, C
T2, C2, C
T3, C1, D
T3, C2, D
T4, C1, E
Т5, С2, А
Sequence of visits for each customer:
C1 = A →> B →> C-> D -> E
C2 = E →> B →> C -> D -> A
Answer: We see that the most common 3-page sequence visited by a customer is: B->C->D|

 */

// Solution Overview:
// The goal is to find the most frequently visited 3-page sequence across all customers based on their browsing history.
// Each customer's browsing history is ordered by time, and we need to consider sequences of 3 consecutive pages.

// Algorithm Steps:
// 1. Read and parse the log data to extract time, customerId, and pageVisited.
// 2. Group the entries by customerId.
// 3. For each customer, sort their entries by time to get the browsing sequence.
// 4. Generate all possible 3-page sequences for each customer.
// 5. Use a HashMap to count the frequency of each 3-page sequence.
// 6. Identify the sequence(s) with the highest frequency.
// 7. Implement test methods to verify correctness, including handling large datasets.

import java.util.*;
import java.util.Map.Entry;

public class MostFrequentThreePageSequence {

    public static void main(String[] args) {
        // Run all test cases
        boolean allTestsPassed = true;
        allTestsPassed &= testExampleCase();
        allTestsPassed &= testEdgeCaseSingleCustomer();
        allTestsPassed &= testEdgeCaseNoSequences();
        allTestsPassed &= testLargeDataSet();

        if (allTestsPassed) {
            System.out.println("All test cases PASSED.");
        } else {
            System.out.println("Some test cases FAILED.");
        }
    }

    // Method to process the log and find the most frequent 3-page sequence
    public static List<String> findMostFrequentSequence(List<LogEntry> logEntries) {
        // Group entries by customerId
        Map<String, List<LogEntry>> customerVisits = new HashMap<>();
        for (LogEntry entry : logEntries) {
            customerVisits.computeIfAbsent(entry.customerId, k -> new ArrayList<>()).add(entry);
        }

        // Map to count frequency of 3-page sequences
        Map<String, Integer> sequenceCount = new HashMap<>();

        // For each customer, process their page visits
        for (List<LogEntry> visits : customerVisits.values()) {
            // Sort visits by time
            visits.sort(Comparator.comparing(entry -> entry.time));

            // Extract page names in order
            List<String> pages = new ArrayList<>();
            for (LogEntry entry : visits) {
                pages.add(entry.pageVisited);
            }

            // Generate 3-page sequences
            for (int i = 0; i < pages.size() - 2; i++) {
                String sequence = pages.get(i) + "->" + pages.get(i + 1) + "->" + pages.get(i + 2);
                sequenceCount.put(sequence, sequenceCount.getOrDefault(sequence, 0) + 1);
            }
        }

        // Find the sequence(s) with the highest frequency
        int maxCount = 0;
        List<String> mostFrequentSequences = new ArrayList<>();
        for (Entry<String, Integer> entry : sequenceCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentSequences.clear();
                mostFrequentSequences.add(entry.getKey());
            } else if (entry.getValue() == maxCount) {
                mostFrequentSequences.add(entry.getKey());
            }
        }

        return mostFrequentSequences;
    }

    // Test case based on the provided example
    public static boolean testExampleCase() {
        List<LogEntry> logEntries = Arrays.asList(
                new LogEntry("T0", "C1", "A"),
                new LogEntry("T0", "C2", "E"),
                new LogEntry("T1", "C1", "B"),
                new LogEntry("T1", "C2", "B"),
                new LogEntry("T2", "C1", "C"),
                new LogEntry("T2", "C2", "C"),
                new LogEntry("T3", "C1", "D"),
                new LogEntry("T3", "C2", "D"),
                new LogEntry("T4", "C1", "E"),
                new LogEntry("T5", "C2", "A")
        );

        List<String> result = findMostFrequentSequence(logEntries);
        boolean testPassed = result.contains("B->C->D") && result.size() == 1;

        System.out.println("Test Example Case: " + (testPassed ? "PASS" : "FAIL"));
        return testPassed;
    }

    // Edge case: Single customer with enough visits
    public static boolean testEdgeCaseSingleCustomer() {
        List<LogEntry> logEntries = Arrays.asList(
                new LogEntry("T1", "C1", "X"),
                new LogEntry("T2", "C1", "Y"),
                new LogEntry("T3", "C1", "Z")
        );

        List<String> result = findMostFrequentSequence(logEntries);
        boolean testPassed = result.contains("X->Y->Z") && result.size() == 1;

        System.out.println("Test Edge Case Single Customer: " + (testPassed ? "PASS" : "FAIL"));
        return testPassed;
    }

    // Edge case: Not enough pages to form a sequence
    public static boolean testEdgeCaseNoSequences() {
        List<LogEntry> logEntries = Arrays.asList(
                new LogEntry("T1", "C1", "X"),
                new LogEntry("T2", "C1", "Y")
        );

        List<String> result = findMostFrequentSequence(logEntries);
        boolean testPassed = result.isEmpty();

        System.out.println("Test Edge Case No Sequences: " + (testPassed ? "PASS" : "FAIL"));
        return testPassed;
    }

    // Test with a large dataset
    public static boolean testLargeDataSet() {
        List<LogEntry> logEntries = new ArrayList<>();
        int numCustomers = 1000;
        int visitsPerCustomer = 100;
        String[] pages = {"A", "B", "C", "D", "E", "F", "G", "H"};

        // Generate synthetic data
        for (int c = 1; c <= numCustomers; c++) {
            String customerId = "C" + c;
            for (int v = 0; v < visitsPerCustomer; v++) {
                String time = "T" + (v + c * visitsPerCustomer);
                String page = pages[v % pages.length];
                logEntries.add(new LogEntry(time, customerId, page));
            }
        }

        // Expected to find the sequence that repeats the most
        List<String> result = findMostFrequentSequence(logEntries);
        boolean testPassed = !result.isEmpty();

        System.out.println("Test Large Data Set: " + (testPassed ? "PASS" : "FAIL"));
        return testPassed;
    }
}

// LogEntry class to represent each log entry
class LogEntry {
    String time;
    String customerId;
    String pageVisited;

    public LogEntry(String time, String customerId, String pageVisited) {
        this.time = time;
        this.customerId = customerId;
        this.pageVisited = pageVisited;
    }
}
