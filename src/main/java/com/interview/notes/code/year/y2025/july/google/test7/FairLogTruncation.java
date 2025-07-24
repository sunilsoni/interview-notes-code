package com.interview.notes.code.year.y2025.july.google.test7;

import java.util.*;
import java.util.stream.*;

class LogMessage {
    String sourceFile;
    String message;

    LogMessage(String sourceFile, String message) {
        this.sourceFile = sourceFile;
        this.message = message;
    }
}

public class FairLogTruncation {

    public static List<LogMessage> truncateMessages(List<LogMessage> logList, int maxLogMessages) {
        // Handle edge cases: null or empty input, or zero allowed messages
        if (logList == null || logList.isEmpty() || maxLogMessages == 0)
            return new ArrayList<>();

        // Group messages by source file and preserve order
        Map<String, List<LogMessage>> logsBySource = logList.stream()
                .collect(Collectors.groupingBy(log -> log.sourceFile, LinkedHashMap::new, Collectors.toList()));

        // Binary search to find the maximum X (messages per file)
        int low = 1, high = logList.size(), optimalX = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Mid value to check

            // Calculate total messages if we limit each file to 'mid' messages
            int count = logsBySource.values().stream()
                    .mapToInt(list -> Math.min(list.size(), mid))
                    .sum();

            if (count <= maxLogMessages) {
                optimalX = mid; // Mid is valid, try to find a larger X
                low = mid + 1;
            } else {
                high = mid - 1; // Mid is too high, try lower
            }
        }

        final int X = optimalX; // Final calculated X

        // Collect truncated messages preserving chronological order within each source file
        List<LogMessage> truncatedLogs = logsBySource.values().stream()
                .flatMap(list -> list.stream().limit(X))
                .collect(Collectors.toList());

        return truncatedLogs;
    }

    // Test method with simple main
    public static void main(String[] args) {
        // Test Case 1:
        List<LogMessage> logs = new ArrayList<>();
        for (int i = 0; i < 100; i++) logs.add(new LogMessage("A.java", "Message A" + i));
        for (int i = 0; i < 20; i++) logs.add(new LogMessage("B.java", "Message B" + i));
        for (int i = 0; i < 10; i++) logs.add(new LogMessage("C.java", "Message C" + i));

        List<LogMessage> result1 = truncateMessages(logs, 50);
        System.out.println("Test Case 1: " + (result1.size() == 50 ? "PASS" : "FAIL")); // Expected 50

        // Test Case 2:
        List<LogMessage> result2 = truncateMessages(logs, 30);
        System.out.println("Test Case 2: " + (result2.size() == 30 ? "PASS" : "FAIL")); // Expected 30

        // Edge Test Case 3: maxLogMessages = 0
        List<LogMessage> result3 = truncateMessages(logs, 0);
        System.out.println("Test Case 3: " + (result3.isEmpty() ? "PASS" : "FAIL")); // Expected 0

        // Edge Test Case 4: Empty logList
        List<LogMessage> result4 = truncateMessages(new ArrayList<>(), 10);
        System.out.println("Test Case 4: " + (result4.isEmpty() ? "PASS" : "FAIL")); // Expected 0

        // Large Data Test Case 5:
        List<LogMessage> largeLogs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 100; j++)
                largeLogs.add(new LogMessage("File" + i, "Msg" + j));
        }
        List<LogMessage> result5 = truncateMessages(largeLogs, 50000);
        System.out.println("Test Case 5: " + (result5.size() <= 50000 ? "PASS" : "FAIL")); // Expected <= 50000
    }
}
