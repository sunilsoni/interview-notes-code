package com.interview.notes.code.year.y2025.august.common.test3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
    /**
     * main() builds and runs all test cases (including large-data tests)
     * and prints PASS/FAIL for each.
     */
    public static void main(String[] args) {
        Solution sol = new Solution();  // our solution instance

        // list of named test cases for clarity
        Map<String, List<Interval>> testInputs = new LinkedHashMap<>();
        Map<String, List<Interval>> testExpected = new LinkedHashMap<>();

        // --- provided example ---
        testInputs.put("Example1", List.of(
                new Interval(1, 5, 20),
                new Interval(3, 8, 15),
                new Interval(7, 10, 8)
        ));
        testExpected.put("Example1", List.of(
                new Interval(1, 3, 20),
                new Interval(3, 7, 15),
                new Interval(7, 10, 8)
        ));

        // --- single vendor only ---
        testInputs.put("SingleVendor", List.of(
                new Interval(0, 100, 50)
        ));
        testExpected.put("SingleVendor", List.of(
                new Interval(0, 100, 50)
        ));

        // --- disjoint intervals ---
        testInputs.put("Disjoint", List.of(
                new Interval(1, 2, 30),
                new Interval(3, 4, 20)
        ));
        testExpected.put("Disjoint", List.of(
                new Interval(1, 2, 30),
                new Interval(3, 4, 20)
        ));

        // --- nested with same price (should merge) ---
        testInputs.put("MergeSamePrice", List.of(
                new Interval(1, 5, 10),
                new Interval(5, 8, 10)
        ));
        testExpected.put("MergeSamePrice", List.of(
                new Interval(1, 8, 10)
        ));

        // --- empty input ---
        testInputs.put("Empty", Collections.emptyList());
        testExpected.put("Empty", Collections.emptyList());

        // --- large-data test #1: repeated pangram of intervals to force many events
        List<Interval> big1 = new ArrayList<>();
        for (int i = 0; i < 50_000; i++) {
            big1.add(new Interval(i * 2, i * 2 + 5, i % 100 + 1));
        }
        testInputs.put("Large1", big1);
        // we won’t hard-code expected for “Large1”; we just check that it runs without crash
        testExpected.put("Large1", null);

        // --- large-data test #2: one million intervals all identical ---
        List<Interval> big2 = IntStream.range(0, 1_000_000)
                .mapToObj(i -> new Interval(0, 1000, i % 100 + 1))
                .collect(Collectors.toList());
        testInputs.put("Large2", big2);
        testExpected.put("Large2", null);

        // run them all
        for (String name : testInputs.keySet()) {
            List<Interval> input = testInputs.get(name);
            List<Interval> expect = testExpected.get(name);
            long startTime = System.currentTimeMillis();          // time it
            List<Interval> actual = sol.getLowestPrices(input);   // compute
            long duration = System.currentTimeMillis() - startTime;

            boolean pass;
            if (expect == null) {
                // for large tests we only require “no exception” and non-null result
                pass = actual != null;
            } else {
                pass = expect.equals(actual);
            }

            // print PASS/FAIL with timing
            System.out.printf(
                    "%-12s : %s (%,d ms)%n",
                    name,
                    (pass ? "PASS" : "FAIL"),
                    duration
            );
            if (!pass && expect != null) {
                // on failure, show details
                System.out.println("  Expected: " + expect);
                System.out.println("  Actual:   " + actual);
            }
        }
    }

    /**
     * Returns a sorted list of disjoint intervals where each interval carries
     * the lowest price available during that span.
     */
    private List<Interval> getLowestPrices(List<Interval> vendors) {
        // if no vendor input given, nothing to do → empty result
        if (vendors == null || vendors.isEmpty()) {
            return Collections.emptyList();
        }

        // 1) Build all start/end events from the vendor intervals
        List<Event> events = vendors.stream()
                // for each vendor interval, produce two events
                .flatMap(iv -> Stream.of(
                        new Event(iv.startTime(), true, iv.price()),   // start event
                        new Event(iv.endTime(), false, iv.price())   // end   event
                ))
                // collect into a list
                .collect(Collectors.toList());

        // 2) Sort events by time ascending; if same time, start-events first
        events.sort(Comparator
                .comparingInt((Event e) -> e.time)           // by event time
                .thenComparing(e -> !e.start)                // start (true) before end (false)
        );

        // 3) Prepare data structures for sweep
        TreeMap<Integer, Integer> priceCounts = new TreeMap<>(); // multiset of active prices
        List<Interval> result = new ArrayList<>();               // final intervals

        Integer prevTime = null;      // where the current segment began
        Integer prevMinPrice = null;  // what its price was

        // 4) Process events grouped by the same time
        int i = 0;
        while (i < events.size()) {
            int curTime = events.get(i).time;  // current event time

            // process all events at curTime
            while (i < events.size() && events.get(i).time == curTime) {
                Event ev = events.get(i);
                if (ev.start) {
                    // add this price to the multiset
                    priceCounts.merge(ev.price, 1, Integer::sum);
                } else {
                    // remove one occurrence of this price
                    priceCounts.computeIfPresent(ev.price, (p, cnt) -> cnt == 1 ? null : cnt - 1);
                }
                i++;
            }

            // what's the current lowest price now?
            Integer curMinPrice = priceCounts.isEmpty() ? null : priceCounts.firstKey();

            // if we have a previous segment running...
            if (prevMinPrice != null) {
                // and if price changed or no more active prices
                if (!Objects.equals(prevMinPrice, curMinPrice)) {
                    // finish the previous interval [prevTime, curTime)
                    result.add(new Interval(prevTime, curTime, prevMinPrice));
                    prevTime = curTime;      // start a new one here
                    prevMinPrice = curMinPrice; // its price (may be null if nothing active)
                }
                // else price stayed the same → continue the same segment
            } else {
                // no previous segment yet, but now we have a price → start it
                if (curMinPrice != null) {
                    prevTime = curTime;
                    prevMinPrice = curMinPrice;
                }
            }
        }

        // no trailing segment to close because end events close exactly at last time

        // return the computed list (already sorted by start time)
        return result;
    }

    // record to hold a vendor interval with start, end, and price
    private record Interval(int startTime, int endTime, int price) {
    }

    // record to represent an event in the sweep: time, isStart, and price
    private static class Event {
        final int time;       // the point in time of this event
        final boolean start;  // true=start of an interval, false=end
        final int price;      // the vendor price

        // constructor
        Event(int time, boolean start, int price) {
            this.time = time;
            this.start = start;
            this.price = price;
        }
    }
}
