package com.interview.notes.code.months.june24.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BuildingOccupancyAlternative1 {
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
        List<Event> timeline = new ArrayList<>();

        // Convert each event into +1 or -1 change at the respective timestamp
        for (Event event : events) {
            int change = event.entry ? 1 : -1;
            timeline.add(new Event(change > 0, event.timestamp, event.employeeId));
        }

        // Sort events by timestamp
        timeline.sort(Comparator.comparingInt(e -> e.timestamp));

        int maxCount = 0, currentCount = 0, earliestMaxTimestamp = 0;

        // Process sorted events to find the maximum occupancy
        for (Event e : timeline) {
            if (e.entry) {
                currentCount++;
            } else {
                currentCount--;
            }

            if (currentCount > maxCount) {
                maxCount = currentCount;
                earliestMaxTimestamp = e.timestamp;
            }
        }

        return earliestMaxTimestamp;
    }

    static class Event {
        boolean entry; // true for entry, false for exit
        int timestamp;
        String employeeId;

        public Event(boolean entry, int timestamp, String employeeId) {
            this.entry = entry;
            this.timestamp = timestamp;
            this.employeeId = employeeId;
        }
    }
}
