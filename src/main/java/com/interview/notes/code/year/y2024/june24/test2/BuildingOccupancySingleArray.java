package com.interview.notes.code.year.y2024.june24.test2;

import java.util.List;

public class BuildingOccupancySingleArray {
    public static void main(String[] args) {
        List<Event> events = List.of(
                new Event(true, 1, "1"),
                new Event(true, 3, "2"),
                new Event(false, 10, "1"),
                new Event(true, 15, "3"),
                new Event(true, 20, "4"),
                new Event(false, 25, "4"),
                new Event(false, 30, "3")
        );

        System.out.println("Earliest timestamp with maximum occupancy: " + findMaxOccupancyTimestamp(events));
    }

    private static int findMaxOccupancyTimestamp(List<Event> events) {
        int[] occupancyChanges = new int[97];  // Using 97 to include index 96

        // Populate the changes array
        for (Event event : events) {
            if (event.entry) {
                occupancyChanges[event.timestamp]++;
            } else {
                occupancyChanges[event.timestamp]--;
            }
        }

        int maxCount = 0, currentCount = 0, earliestMaxTimestamp = 0;

        // Calculate the cumulative occupancy and determine the maximum
        for (int i = 1; i < occupancyChanges.length; i++) {
            currentCount += occupancyChanges[i];
            if (currentCount > maxCount) {
                maxCount = currentCount;
                earliestMaxTimestamp = i;
            }
        }

        return earliestMaxTimestamp;
    }

    static class Event {
        boolean entry;
        int timestamp;
        String employeeId;

        public Event(boolean entry, int timestamp, String employeeId) {
            this.entry = entry;
            this.timestamp = timestamp;
            this.employeeId = employeeId;
        }
    }
}
