package com.interview.notes.code.year.y2025.october.common.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeRangeChecker {
    public static String checkDateTimeRange(LocalDateTime inputDateTime) {
        // Get current date
        LocalDateTime now = LocalDateTime.now();
        
        // Create yesterday 23:30
        LocalDateTime startDateTime = now.minusDays(1)
                                      .withHour(23)
                                      .withMinute(30)
                                      .withSecond(0);
        
        // Create tomorrow 01:00
        LocalDateTime endDateTime = now.plusDays(1)
                                    .withHour(1)
                                    .withMinute(0)
                                    .withSecond(0);
        
        // Check if input datetime is within range
        if (inputDateTime.isAfter(startDateTime) && inputDateTime.isBefore(endDateTime)) {
            return "ACCEPTED";
        } else {
            return "REJECTED";
        }
    }

    public static void main(String[] args) {
        // Example usage
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        // Test cases
        LocalDateTime test1 = LocalDateTime.now(); // Current time
        LocalDateTime test2 = LocalDateTime.now().minusDays(2); // 2 days ago
        LocalDateTime test3 = LocalDateTime.now().withHour(0).withMinute(30); // Today at 00:30
        
        System.out.println("Current time: " + checkDateTimeRange(test1));
        System.out.println("2 days ago: " + checkDateTimeRange(test2));
        System.out.println("Today at 00:30: " + checkDateTimeRange(test3));
    }
}
