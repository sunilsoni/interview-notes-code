package com.interview.notes.code.year.y2025.september.common.test8;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ParkingTicketService:
 * This class calculates parking charges based on entry/exit times.
 */
public class ParkingTicketService {

    // Method to calculate fee for a single vehicle
    public static int calculateFee(LocalDateTime entryTime, LocalDateTime exitTime) {
        // Step 1: Add mandatory entry charge = $2
        int total = 2;

        // Step 2: Calculate duration between entry and exit in minutes
        long minutes = Duration.between(entryTime, exitTime).toMinutes();

        // Step 3: Convert minutes to hours, rounding UP for partial hours
        long hours = (long) Math.ceil(minutes / 60.0);

        if (hours > 0) {
            // Step 4: Add first hour charge ($3)
            total += 3;
            // Step 5: If parked more than 1 hour, add $4 for each additional hour
            if (hours > 1) {
                total += (hours - 1) * 4;
            }
        }
        // Return total fee
        return total;
    }

    // Testing all cases without JUnit, just main method
    public static void main(String[] args) {
        // Formatter to parse human-readable times
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Prepare test cases: list of [entry, exit, expectedFee]
        List<TestCase> testCases = Arrays.asList(
                new TestCase("2025-09-25 10:00", "2025-09-25 14:30", 21), // 4h30m -> 5h => $21
                new TestCase("2025-09-25 10:00", "2025-09-25 10:30", 5),  // 30m -> 1h => $5
                new TestCase("2025-09-25 10:00", "2025-09-25 11:00", 5),  // Exactly 1h => $5
                new TestCase("2025-09-25 10:00", "2025-09-25 12:05", 11), // 2h5m -> 3h => $11
                new TestCase("2025-09-25 10:00", "2025-09-25 18:45", 37)  // 8h45m -> 9h => $37
        );

        // Process all test cases using Streams
        List<String> results = testCases.stream()
                .map(tc -> {
                    // Parse times
                    LocalDateTime entry = LocalDateTime.parse(tc.entry, formatter);
                    LocalDateTime exit = LocalDateTime.parse(tc.exit, formatter);

                    // Calculate actual fee
                    int actualFee = calculateFee(entry, exit);

                    // Compare with expected
                    String status = (actualFee == tc.expectedFee) ? "PASS" : "FAIL";

                    // Build readable result string
                    return "Entry: " + tc.entry + ", Exit: " + tc.exit +
                            ", Expected: $" + tc.expectedFee +
                            ", Got: $" + actualFee +
                            " => " + status;
                })
                .collect(Collectors.toList());

        // Print results
        results.forEach(System.out::println);

        // Large data test: simulate 100,000 vehicles
        List<TestCase> largeData = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeData.add(new TestCase("2025-09-25 08:00", "2025-09-25 10:45", 11)); // 2h45m -> 3h => $11
        }

        // Measure performance on large dataset
        long start = System.currentTimeMillis();
        long passCount = largeData.stream()
                .filter(tc -> calculateFee(LocalDateTime.parse(tc.entry, formatter),
                        LocalDateTime.parse(tc.exit, formatter)) == tc.expectedFee)
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