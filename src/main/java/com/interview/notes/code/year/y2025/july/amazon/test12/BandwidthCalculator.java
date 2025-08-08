package com.interview.notes.code.year.y2025.july.amazon.test12;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BandwidthCalculator {
    // Main method to find maximum bandwidth usage
    public static int findMaxBandwidth(List<ShowSegment> segments) {
        // Create two lists to store time events (start and end)
        List<TimeEvent> events = new ArrayList<>();

        // Process each segment and create start/end events
        for (ShowSegment segment : segments) {
            // Add start event with positive bandwidth
            events.add(new TimeEvent(segment.startTime, segment.bandwidth));
            // Add end event with negative bandwidth (to subtract when show ends)
            events.add(new TimeEvent(segment.endTime, -segment.bandwidth));
        }

        // Sort events by time to process them chronologically
        Collections.sort(events, (a, b) -> a.time.compareTo(b.time));

        int currentBandwidth = 0;
        int maxBandwidth = 0;

        // Process events in time order
        for (TimeEvent event : events) {
            // Update current bandwidth (add for start, subtract for end)
            currentBandwidth += event.bandwidthChange;
            // Keep track of maximum bandwidth seen
            maxBandwidth = Math.max(maxBandwidth, currentBandwidth);
        }

        return maxBandwidth;
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Simple overlap
        List<ShowSegment> test1 = Arrays.asList(
                new ShowSegment(
                        LocalTime.of(8, 0),
                        LocalTime.of(10, 0),
                        1,
                        100
                ),
                new ShowSegment(
                        LocalTime.of(9, 0),
                        LocalTime.of(11, 0),
                        2,
                        150
                )
        );
        int result1 = findMaxBandwidth(test1);
        System.out.println("Test 1 - Expected: 250, Got: " + result1);
        System.out.println("Test 1: " + (result1 == 250 ? "PASS" : "FAIL"));

        // Test Case 2: No overlap
        List<ShowSegment> test2 = Arrays.asList(
                new ShowSegment(
                        LocalTime.of(8, 0),
                        LocalTime.of(9, 0),
                        1,
                        100
                ),
                new ShowSegment(
                        LocalTime.of(10, 0),
                        LocalTime.of(11, 0),
                        2,
                        150
                )
        );
        int result2 = findMaxBandwidth(test2);
        System.out.println("Test 2 - Expected: 150, Got: " + result2);
        System.out.println("Test 2: " + (result2 == 150 ? "PASS" : "FAIL"));

        // Test Case 3: Multiple overlaps
        List<ShowSegment> test3 = Arrays.asList(
                new ShowSegment(
                        LocalTime.of(8, 0),
                        LocalTime.of(11, 0),
                        1,
                        100
                ),
                new ShowSegment(
                        LocalTime.of(9, 0),
                        LocalTime.of(10, 0),
                        2,
                        150
                ),
                new ShowSegment(
                        LocalTime.of(9, 30),
                        LocalTime.of(10, 30),
                        3,
                        200
                )
        );
        int result3 = findMaxBandwidth(test3);
        System.out.println("Test 3 - Expected: 450, Got: " + result3);
        System.out.println("Test 3: " + (result3 == 450 ? "PASS" : "FAIL"));
    }

    // Class to represent a TV show segment with start time, end time and bandwidth
    static class ShowSegment {
        LocalTime startTime;
        LocalTime endTime;
        int channel;
        int bandwidth;

        ShowSegment(LocalTime start, LocalTime end, int channel, int bandwidth) {
            this.startTime = start;
            this.endTime = end;
            this.channel = channel;
            this.bandwidth = bandwidth;
        }
    }

    // Helper class to represent time events (start or end of show)
    static class TimeEvent {
        LocalTime time;
        int bandwidthChange;

        TimeEvent(LocalTime time, int bandwidthChange) {
            this.time = time;
            this.bandwidthChange = bandwidthChange;
        }
    }
}
