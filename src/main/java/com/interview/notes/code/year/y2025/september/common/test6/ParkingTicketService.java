package com.interview.notes.code.year.y2025.september.common.test6;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ParkingTicketService:
 * Calculates parking charges when entry and exit times are given as String.
 */
public class ParkingTicketService {

    // Method now accepts String entry and exit times
    public static int calculateFee(String entryTime, String exitTime) {
        // Formatter to parse the input strings (format: yyyy-MM-dd HH:mm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Parse entry and exit times into LocalDateTime objects
        LocalDateTime entry = LocalDateTime.parse(entryTime, formatter);
        LocalDateTime exit = LocalDateTime.parse(exitTime, formatter);

        // Step 1: Add flat entry charge = $2
        int total = 2;

        // Step 2: Find total duration in minutes
        long minutes = Duration.between(entry, exit).toMinutes();

        // Step 3: Convert minutes to hours, round UP if there are extra minutes
        long hours = (long) Math.ceil(minutes / 60.0);

        // Step 4: If vehicle stayed at least 1 hour, add charges
        if (hours > 0) {
            // First hour charge = $3
            total += 3;
            // Extra hours (beyond the first) = $4 each
            if (hours > 1) {
                total += (hours - 1) * 4;
            }
        }

        // Return total fee
        return total;
    }

    // Simple test harness without JUnit, only main method
    public static void main(String[] args) {
        // Prepare test cases: entry, exit, expected fee
        List<TestCase> testCases = Arrays.asList(
                new TestCase("2025-09-25 10:00", "2025-09-25 14:30", 21), // 4h30m -> 5h => $21
                new TestCase("2025-09-25 10:00", "2025-09-25 10:30", 5),  // 30m -> 1h => $5
                new TestCase("2025-09-25 10:00", "2025-09-25 11:00", 5),  // Exactly 1h => $5
                new TestCase("2025-09-25 10:00", "2025-09-25 12:05", 11), // 2h5m -> 3h => $11
                new TestCase("2025-09-25 10:00", "2025-09-25 18:45", 37)  // 8h45m -> 9h => $37
        );

        // Run test cases using streams
        List<String> results = testCases.stream()
                .map(tc -> {
                    // Call calculateFee with String input
                    int actualFee = calculateFee(tc.entry, tc.exit);

                    // Compare with expected
                    String status = (actualFee == tc.expectedFee) ? "PASS" : "FAIL";

                    // Build readable output
                    return "Entry: " + tc.entry + ", Exit: " + tc.exit +
                            ", Expected: $" + tc.expectedFee +
                            ", Got: $" + actualFee +
                            " => " + status;
                })
                .collect(Collectors.toList());

        // Print results
        results.forEach(System.out::println);

        // Large data test for performance
        List<TestCase> largeData = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeData.add(new TestCase("2025-09-25 08:00", "2025-09-25 10:45", 11)); // 2h45m -> 3h => $11
        }

        // Measure performance
        long start = System.currentTimeMillis();
        long passCount = largeData.stream()
                .filter(tc -> calculateFee(tc.entry, tc.exit) == tc.expectedFee)
                .count();
        long end = System.currentTimeMillis();

        System.out.println("Large data test: " + passCount + "/" + largeData.size() +
                " passed in " + (end - start) + " ms");
    }

    // Helper class to hold test case data
    static class TestCase {
        String entry;
        String exit;
        int expectedFee;

        TestCase(String entry, String exit, int expectedFee) {
            this.entry = entry;
            this.exit = exit;
            this.expectedFee = expectedFee;
        }
    }
}