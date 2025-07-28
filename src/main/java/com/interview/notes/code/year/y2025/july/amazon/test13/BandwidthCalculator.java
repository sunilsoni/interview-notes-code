package com.interview.notes.code.year.y2025.july.amazon.test13;

import java.util.*;                       // Import utility classes (List, Comparator, etc.)
import java.util.stream.*;               // Import Stream API
/*
 Problem Statement

You have a local cable company with digital cable running to all your subscribers' houses. This cable carries all of the channels simultaneously, and the subscribers have a box in their house that peels off the channel they want to see.

You receive a feed from your upstream satellite provider with segments that include:

* **Start time**
* **End time**
* **Channel**
* **Bandwidth required for that segment**

Your goal is to **find the maximum total bandwidth usage at any point during the day**, so that you can ensure you do not exceed your capacity.

 */
// Class to hold one TV segment's data
class Segment {  
    private final long start;            // Start time (e.g., epoch seconds)
    private final long end;              // End time (must be ≥ start)
    private final long bandwidth;        // Bandwidth required during this segment

    // Constructor to initialize all fields
    public Segment(long start, long end, long bandwidth) {  
        this.start = start;              // Set start time
        this.end = end;                  // Set end time
        this.bandwidth = bandwidth;      // Set bandwidth requirement
    }

    // Getter for start time
    public long getStart() {  
        return start;                    // Return stored start time
    }

    // Getter for end time
    public long getEnd() {  
        return end;                      // Return stored end time
    }

    // Getter for bandwidth
    public long getBandwidth() {  
        return bandwidth;                // Return stored bandwidth
    }
}

// Class to represent a sweep‐line event (start or end of a segment)
class Event {
    public final long time;               // Timestamp of this event
    public final long delta;              // +bandwidth for start, –bandwidth for end

    // Constructor sets time and bandwidth change
    public Event(long time, long delta) {  
        this.time = time;                 // Set event time
        this.delta = delta;               // Set how much to add/subtract
    }
}

public class BandwidthCalculator {

    // Method to compute the maximum concurrent bandwidth
    public static long computeMaxBandwidth(List<Segment> segments) {
        // Convert each segment to two events, then sort all events by time
        List<Event> events = segments.stream()  
            .flatMap(s -> Stream.of(  
                new Event(s.getStart(), s.getBandwidth()),    // Start event: add bandwidth
                new Event(s.getEnd(),   -s.getBandwidth())     // End event: subtract bandwidth
            ))                                                 // Flatten into one stream of Event
            .sorted(Comparator.comparingLong(e -> e.time))    // Sort by event time ascending
            .collect(Collectors.toList());                    // Gather into a List

        long current = 0;   // Tracks bandwidth in effect at current sweep position
        long maximum = 0;   // Tracks maximum seen so far

        // Process all events in time order
        for (Event e : events) {
            current += e.delta;                // Update running bandwidth
            if (current > maximum) {           // If we have a new peak
                maximum = current;             // Record it as the maximum
            }
        }
        return maximum;                       // Return the highest bandwidth observed
    }

    // Main method to run test cases without JUnit, printing PASS/FAIL
    public static void main(String[] args) {
        // List of test cases: each is a pair (segmentsList, expectedMax)
        List<Map.Entry<List<Segment>, Long>> tests = Arrays.asList(
            // Test 1: no segments → max = 0
            new AbstractMap.SimpleEntry<>(Collections.emptyList(), 0L),

            // Test 2: one segment → bandwidth itself
            new AbstractMap.SimpleEntry<>(
                Arrays.asList(new Segment(10, 20, 5)), 5L
            ),

            // Test 3: non-overlapping segments → max is the largest single
            new AbstractMap.SimpleEntry<>(
                Arrays.asList(
                    new Segment(0, 5,  3),
                    new Segment(6, 10, 7)
                ), 7L
            ),

            // Test 4: overlapping segments → sums during overlap
            new AbstractMap.SimpleEntry<>(
                Arrays.asList(
                    new Segment(0, 10, 4),
                    new Segment(5,  15, 6),
                    new Segment(10, 20, 3)
                ), 10L  // overlap of first two from t=5..10 is 4+6=10
            ),

            // Test 5: fully nested segments
            new AbstractMap.SimpleEntry<>(
                Arrays.asList(
                    new Segment(0, 100, 2),
                    new Segment(20, 80, 5),
                    new Segment(40, 60, 7)
                ), 14L  // all three overlap 40..60: 2+5+7=14
            )
        );

        // Run each test and print result
        for (int i = 0; i < tests.size(); i++) {
            // Extract test data and expected result
            List<Segment> segs = tests.get(i).getKey();  
            long expected = tests.get(i).getValue();      

            // Compute actual maximum bandwidth
            long actual = computeMaxBandwidth(segs);      

            // Print PASS or FAIL
            System.out.printf(
                "Test %d: expected=%d, actual=%d → %s%n",
                i + 1,
                expected,
                actual,
                (expected == actual ? "PASS" : "FAIL")
            );
        }

        // (Optional) You can test with very large random data here to ensure performance.
        // For example, generate 1_000_000 random segments and call computeMaxBandwidth(...)
        // to verify it finishes quickly on your machine.
    }
}