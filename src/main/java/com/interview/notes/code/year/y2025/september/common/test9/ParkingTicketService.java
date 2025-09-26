package com.interview.notes.code.year.y2025.september.common.test9;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ParkingTicketService:
 * Calculates parking charges when entry and exit times are given as HH:mm.
 * Works for same-day parking (exit >= entry).
 */
public class ParkingTicketService {

    // Method to calculate fee with HH:mm input
    public static int calculateFee(String entryTime, String exitTime) {
        // Step 1: Formatter to parse "HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // Step 2: Parse entry and exit as LocalTime
        LocalTime entry = LocalTime.parse(entryTime, formatter);
        LocalTime exit = LocalTime.parse(exitTime, formatter);

        // Step 3: Duration in minutes (works if exit >= entry same day)
        long minutes = Duration.between(entry, exit).toMinutes();

        // Step 4: Convert to hours, round up partial hours
        long hours = (long) Math.ceil(minutes / 60.0);

        // Step 5: Apply fee calculation
        int total = 2; // entry charge
        if (hours > 0) {
            total += 3; // first hour
            if (hours > 1) {
                total += (hours - 1) * 4; // extra hours
            }
        }

        return total;
    }

    // Test method without JUnit, only main
    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase("10:00", "14:30", 21), // 4h30m -> 5h => $21
                new TestCase("10:00", "10:30", 5),  // 30m -> 1h => $5
                new TestCase("10:00", "11:00", 5),  // Exactly 1h => $5
                new TestCase("10:00", "12:05", 11), // 2h5m -> 3h => $11
                new TestCase("10:00", "18:45", 37)  // 8h45m -> 9h => $37
        );

        // Process and print results
        List<String> results = testCases.stream()
                .map(tc -> {
                    int actualFee = calculateFee(tc.entry, tc.exit);
                    String status = (actualFee == tc.expectedFee) ? "PASS" : "FAIL";
                    return "Entry: " + tc.entry + ", Exit: " + tc.exit +
                            ", Expected: $" + tc.expectedFee +
                            ", Got: $" + actualFee +
                            " => " + status;
                })
                .collect(Collectors.toList());

        results.forEach(System.out::println);

        // Large dataset test
        List<TestCase> largeData = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeData.add(new TestCase("08:00", "10:45", 11)); // 2h45m -> 3h => $11
        }

        long start = System.currentTimeMillis();
        long passCount = largeData.stream()
                .filter(tc -> calculateFee(tc.entry, tc.exit) == tc.expectedFee)
                .count();
        long end = System.currentTimeMillis();

        System.out.println("Large data test: " + passCount + "/" + largeData.size() +
                " passed in " + (end - start) + " ms");
    }

    // Helper test case class
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