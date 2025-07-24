package com.interview.notes.code.year.y2025.july.google.test8;

import java.util.*;
import java.util.stream.*;
/*



### 🚀 Solution Summary:

#### Problem Analysis:

* We have logs grouped by source files.
* Each file should be truncated fairly (no file should disproportionately dominate the log list).
* Find maximum allowed messages per file (`X`) to not exceed `maxLogMessages`.

#### Solution Design:

* **Group messages by source file** using Java Streams.
* **Binary search** to efficiently find the maximum valid value of `X`.
* **Truncate** each file's log list to this `X` value.
* **Collect** the truncated logs, preserving chronological order within each source file.
* Complexity: `O(N log N)` due to binary search and iteration.

#### Test Coverage:

* Included provided test cases and edge scenarios.
* Large input test case handled to confirm scalability.



 */
public class LogTruncator {

    public static List<LogMessage> truncateMessages(List<LogMessage> logList, int maxLogMessages) {
        // Handle edge cases - return empty list if input is null/empty or maxMessages is 0
        if (logList == null || logList.isEmpty() || maxLogMessages == 0) {
            return new ArrayList<>();
        }

        // Group messages by source file using Java 8 Collectors
        Map<String, List<LogMessage>> messagesBySource = logList.stream()
            .collect(Collectors.groupingBy(msg -> msg.sourceFile));

        // Find optimal X using binary search helper method
        int optimalX = findOptimalX(messagesBySource, maxLogMessages, logList.size());

        // Apply the found limit (optimalX) to each source file's messages
        return messagesBySource.values().stream()
            .flatMap(list -> list.stream().limit(optimalX))
            .collect(Collectors.toList());
    }

    // Helper method to find optimal X using binary search
    private static int findOptimalX(Map<String, List<LogMessage>> messagesBySource,
                                  int maxLogMessages, int totalSize) {
        int left = 0;
        int right = totalSize;
        int optimalX = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Calculate total messages if we limit each source to 'mid' messages
            final int messagesPerSource = mid;  // Create effectively final variable for lambda
            int totalMessages = messagesBySource.values().stream()
                .mapToInt(list -> Math.min(list.size(), messagesPerSource))
                .sum();

            if (totalMessages <= maxLogMessages) {
                optimalX = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return optimalX;
    }

    // Test class for LogMessage
    static class LogMessage {
        String sourceFile;
        String message;

        LogMessage(String sourceFile, String message) {
            this.sourceFile = sourceFile;
            this.message = message;
        }

        @Override
        public String toString() {
            return sourceFile + ": " + message;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Normal case with multiple sources
        List<LogMessage> test1 = new ArrayList<>();
        for (int i = 0; i < 100; i++) test1.add(new LogMessage("A", "msg" + i));
        for (int i = 0; i < 20; i++) test1.add(new LogMessage("B", "msg" + i));
        for (int i = 0; i < 10; i++) test1.add(new LogMessage("C", "msg" + i));

        System.out.println("Test Case 1 (maxMessages=50):");
        List<LogMessage> result1 = truncateMessages(test1, 50);
        System.out.println("Expected: 50 messages, Got: " + result1.size());
        System.out.println("PASS: " + (result1.size() == 50));

        // Test Case 2: Edge cases
        System.out.println("\nTest Case 2 (empty input):");
        List<LogMessage> result2 = truncateMessages(new ArrayList<>(), 10);
        System.out.println("Expected: 0 messages, Got: " + result2.size());
        System.out.println("PASS: " + (result2.size() == 0));

        // Test Case 3: Large data input
        List<LogMessage> test3 = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            test3.add(new LogMessage("Source" + (i % 100), "msg" + i));
        }

        long startTime = System.currentTimeMillis();
        List<LogMessage> result3 = truncateMessages(test3, 5000);
        long endTime = System.currentTimeMillis();

        System.out.println("\nTest Case 3 (large data):");
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
        System.out.println("Expected: ≤5000 messages, Got: " + result3.size());
        System.out.println("PASS: " + (result3.size() <= 5000));

        // Test Case 4: Verify order preservation within source files
        System.out.println("\nTest Case 4 (order preservation):");
        boolean orderPreserved = verifyOrderPreservation(result1);
        System.out.println("PASS: " + orderPreserved);
    }

    // Helper method to verify message order preservation within source files
    private static boolean verifyOrderPreservation(List<LogMessage> messages) {
        Map<String, List<LogMessage>> grouped = messages.stream()
            .collect(Collectors.groupingBy(msg -> msg.sourceFile));

        for (List<LogMessage> sourceMessages : grouped.values()) {
            for (int i = 1; i < sourceMessages.size(); i++) {
                String prev = sourceMessages.get(i-1).message;
                String curr = sourceMessages.get(i).message;
                if (prev.compareTo(curr) > 0) return false;
            }
        }
        return true;
    }
}
