package com.interview.notes.code.months.june24.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BuildingOccupancy {
    public static void main(String[] args) {
        List<Event> events = Arrays.asList(
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
        TreeMap<Integer, Integer> timestampChanges = new TreeMap<>();

        // Record the net change in occupancy at each timestamp
        for (Event event : events) {
            int change = event.entry ? 1 : -1;
            timestampChanges.put(event.timestamp, timestampChanges.getOrDefault(event.timestamp, 0) + change);
        }

        int maxCount = 0, currentCount = 0, earliestMaxTimestamp = 0;

        // Calculate the occupancy count at each timestamp and find the maximum
        for (Map.Entry<Integer, Integer> entry : timestampChanges.entrySet()) {
            currentCount += entry.getValue();
            if (currentCount > maxCount) {
                maxCount = currentCount;
                earliestMaxTimestamp = entry.getKey();
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
