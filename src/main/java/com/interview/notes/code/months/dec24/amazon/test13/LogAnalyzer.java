package com.interview.notes.code.months.dec24.amazon.test13;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
/*

You have an application which sends messages, and it has a log file which has the following sample format:
2021-10-23T09:35:29Z sent message

2021-10-23T09:44:01Z transmit error

2021-10-23T09:49:29Z sent message

2021-10-23T10:01:49Z sent message

2021-10-23T10:05:29Z transmit error

2021-10-23T10:06:05Z socket error

2021-10-23T10:07:17Z transmit error

2021-10-23T11:23:24Z sent message

2021-10-23T11:52:28Z sent message

2021-10-23T12:01:13Z connect error

2021-10-23T12:02:13Z connect error

2021-10-23T12:03:13Z transmit error

Given the l0g file, return the top T most frequent error types.

In above example, if T = 2

Result: [transmit error, connect error]

• List‹String› getMostFrequentErrors(File file, int T) {

 */
public class LogAnalyzer {

    /**
     * Finds the top T most frequent error types from the given log file.
     *
     * @param file The log file to analyze.
     * @param T    The number of top error types to return.
     * @return A list of the top T most frequent error types.
     */
    public static List<String> getMostFrequentErrors(File file, int T) {
        Map<String, Integer> errorCountMap = new HashMap<>();

        // Read the file line by line
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Check if the line contains an error
                if (line.contains("error")) {
                    // Extract the error type (e.g., "transmit error")
                    String[] parts = line.split("Z ");
                    if (parts.length > 1) {
                        String message = parts[1].trim();
                        errorCountMap.put(message, errorCountMap.getOrDefault(message, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the log file: " + e.getMessage());
            return Collections.emptyList();
        }

        // Use a priority queue to find the top T errors
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue())
                        ? a.getKey().compareTo(b.getKey())
                        : b.getValue() - a.getValue()
        );

        pq.addAll(errorCountMap.entrySet());

        List<String> topErrors = new ArrayList<>();
        for (int i = 0; i < T && !pq.isEmpty(); i++) {
            topErrors.add(pq.poll().getKey());
        }

        return topErrors;
    }

    /**
     * Helper class to represent a test case.
     */
    static class TestCase {
        String description;
        List<String> logEntries;
        int T;
        List<String> expectedOutput;

        TestCase(String description, List<String> logEntries, int T, List<String> expectedOutput) {
            this.description = description;
            this.logEntries = logEntries;
            this.T = T;
            this.expectedOutput = expectedOutput;
        }
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Sample Case 0
        List<String> logs1 = Arrays.asList(
                "2021-10-23T09:35:29Z sent message",
                "2021-10-23T09:44:01Z transmit error",
                "2021-10-23T09:49:29Z sent message",
                "2021-10-23T10:01:49Z sent message",
                "2021-10-23T10:05:29Z transmit error",
                "2021-10-23T10:06:05Z socket error",
                "2021-10-23T10:07:17Z transmit error",
                "2021-10-23T11:23:24Z sent message",
                "2021-10-23T11:52:28Z sent message",
                "2021-10-23T12:01:13Z connect error",
                "2021-10-23T12:02:13Z connect error",
                "2021-10-23T12:03:13Z transmit error"
        );
        List<String> expected1 = Arrays.asList("transmit error", "connect error");
        testCases.add(new TestCase("Sample Case 0", logs1, 2, expected1));

        // Test Case 2: No Errors
        List<String> logs2 = Arrays.asList(
                "2021-10-23T09:35:29Z sent message",
                "2021-10-23T09:49:29Z sent message",
                "2021-10-23T10:01:49Z sent message"
        );
        List<String> expected2 = Collections.emptyList();
        testCases.add(new TestCase("No Errors", logs2, 3, expected2));

        // Test Case 3: All Errors Same Type
        List<String> logs3 = Arrays.asList(
                "2021-10-23T09:44:01Z transmit error",
                "2021-10-23T10:05:29Z transmit error",
                "2021-10-23T10:07:17Z transmit error",
                "2021-10-23T12:03:13Z transmit error"
        );
        List<String> expected3 = Arrays.asList("transmit error");
        testCases.add(new TestCase("All Errors Same Type", logs3, 1, expected3));

        // Test Case 4: T Greater Than Number of Error Types
        List<String> logs4 = Arrays.asList(
                "2021-10-23T09:44:01Z transmit error",
                "2021-10-23T10:05:29Z socket error",
                "2021-10-23T12:01:13Z connect error"
        );
        List<String> expected4 = Arrays.asList("connect error", "socket error", "transmit error");
        testCases.add(new TestCase("T Greater Than Number of Error Types", logs4, 5, expected4));

        // Test Case 5: Multiple Errors with Same Frequency
        List<String> logs5 = Arrays.asList(
                "2021-10-23T09:44:01Z transmit error",
                "2021-10-23T10:05:29Z socket error",
                "2021-10-23T12:01:13Z connect error",
                "2021-10-23T12:02:13Z connect error",
                "2021-10-23T12:03:13Z transmit error",
                "2021-10-23T12:04:13Z socket error"
        );
        List<String> expected5 = Arrays.asList("transmit error", "connect error");
        testCases.add(new TestCase("Multiple Errors with Same Frequency", logs5, 2, expected5));

        // Test Case 6: Large Data Input
        List<String> logs6 = new ArrayList<>();
        Map<String, Integer> frequencyMap6 = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            String errorType = "errorType" + (i % 100);
            logs6.add("2021-10-23T09:35:" + String.format("%02d", i % 60) + "Z " + errorType);
            frequencyMap6.put(errorType, frequencyMap6.getOrDefault(errorType, 0) + 1);
        }
        // Get top 5 error types
        List<Map.Entry<String, Integer>> sortedErrors6 = frequencyMap6.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .collect(Collectors.toList());
        List<String> expected6 = sortedErrors6.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        testCases.add(new TestCase("Large Data Input", logs6, 5, expected6));

        // Run test cases
        int testNumber = 1;
        for (TestCase testCase : testCases) {
            // Create a temporary file and write log entries
            File tempFile = null;
            try {
                tempFile = File.createTempFile("logTestCase" + testNumber, ".txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                for (String log : testCase.logEntries) {
                    writer.write(log);
                    writer.newLine();
                }
                writer.close();

                // Get the most frequent errors
                List<String> result = getMostFrequentErrors(tempFile, testCase.T);

                // Compare with expected output
                if (result.equals(testCase.expectedOutput)) {
                    System.out.println("Test Case " + testNumber + ": PASS - " + testCase.description);
                } else {
                    System.out.println("Test Case " + testNumber + ": FAIL - " + testCase.description);
                    System.out.println("Expected Output: " + testCase.expectedOutput);
                    System.out.println("Actual Output: " + result);
                }

            } catch (IOException e) {
                System.err.println("Error during Test Case " + testNumber + ": " + e.getMessage());
            } finally {
                // Delete the temporary file
                if (tempFile != null && tempFile.exists()) {
                    tempFile.delete();
                }
            }
            testNumber++;
        }
    }
}