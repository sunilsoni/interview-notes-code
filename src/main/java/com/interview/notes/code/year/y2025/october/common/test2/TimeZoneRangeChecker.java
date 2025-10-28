package com.interview.notes.code.year.y2025.october.common.test2;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneRangeChecker {
    private static final ZoneId SYDNEY_ZONE = ZoneId.of("Australia/Sydney");
    private static final ZoneId DALLAS_ZONE = ZoneId.of("America/Chicago"); // CST

    public static String checkDateTimeRange(String inputDateTimeStr) {
        try {
            // Parse input CST datetime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDallasTime = LocalDateTime.parse(inputDateTimeStr, formatter);

            // Convert Dallas time to ZonedDateTime
            ZonedDateTime dallasTime = ZonedDateTime.of(localDallasTime, DALLAS_ZONE);

            // Convert to Sydney time
            ZonedDateTime sydneyTime = dallasTime.withZoneSameInstant(SYDNEY_ZONE);

            // Define Sydney time range (23:30 previous day to 01:00 next day)
            LocalDateTime now = LocalDateTime.now(SYDNEY_ZONE);
            ZonedDateTime startRange = ZonedDateTime.of(
                    now.minusDays(1).withHour(23).withMinute(30).withSecond(0),
                    SYDNEY_ZONE
            );
            ZonedDateTime endRange = ZonedDateTime.of(
                    now.plusDays(1).withHour(1).withMinute(0).withSecond(0),
                    SYDNEY_ZONE
            );

            // Check if Sydney time falls within range
            if (sydneyTime.isAfter(startRange) && sydneyTime.isBefore(endRange)) {
                return "ACCEPTED";
            } else {
                return "REJECTED";
            }
        } catch (Exception e) {
            return "ERROR: Invalid date format";
        }
    }

    // Overloaded method for specific date range
    public static String checkSpecificDateTimeRange(String inputDateTimeStr,
                                                    LocalDateTime startSydneyDate,
                                                    LocalDateTime endSydneyDate) {
        try {
            // Parse input CST datetime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDallasTime = LocalDateTime.parse(inputDateTimeStr, formatter);

            // Convert Dallas time to ZonedDateTime
            ZonedDateTime dallasTime = ZonedDateTime.of(localDallasTime, DALLAS_ZONE);

            // Convert to Sydney time
            ZonedDateTime sydneyTime = dallasTime.withZoneSameInstant(SYDNEY_ZONE);

            // Create Sydney range
            ZonedDateTime startRange = ZonedDateTime.of(startSydneyDate, SYDNEY_ZONE);
            ZonedDateTime endRange = ZonedDateTime.of(endSydneyDate, SYDNEY_ZONE);

            // Check if Sydney time falls within range
            if (sydneyTime.isAfter(startRange) && sydneyTime.isBefore(endRange)) {
                return "ACCEPTED";
            } else {
                return "REJECTED";
            }
        } catch (Exception e) {
            return "ERROR: Invalid date format";
        }
    }

    public static void main(String[] args) {
        // Example usage with current time range
        String dallasTime1 = "2024-01-24 14:30"; // Example CST time
        String dallasTime2 = "2024-01-24 20:00"; // Example CST time

        System.out.println("Testing with current time range:");
        System.out.println("Dallas time " + dallasTime1 + " (CST): " + checkDateTimeRange(dallasTime1));
        System.out.println("Dallas time " + dallasTime2 + " (CST): " + checkDateTimeRange(dallasTime2));

        // Example usage with specific date range
        LocalDateTime startSydney = LocalDateTime.of(2024, 1, 23, 23, 30);
        LocalDateTime endSydney = LocalDateTime.of(2024, 1, 25, 1, 0);

        System.out.println("\nTesting with specific date range:");
        System.out.println("Dallas time " + dallasTime1 + " (CST): " +
                checkSpecificDateTimeRange(dallasTime1, startSydney, endSydney));

        // Print time conversion example
        ZonedDateTime dallasZdt = ZonedDateTime.of(
                LocalDateTime.parse(dallasTime1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                DALLAS_ZONE
        );
        ZonedDateTime sydneyZdt = dallasZdt.withZoneSameInstant(SYDNEY_ZONE);

        System.out.println("\nTime conversion example:");
        System.out.println("Dallas time: " + dallasZdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")));
        System.out.println("Sydney time: " + sydneyZdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")));
    }
}
