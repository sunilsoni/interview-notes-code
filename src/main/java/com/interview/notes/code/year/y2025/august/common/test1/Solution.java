package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.*;                                      // pull in List, ArrayList, Set, HashSet, Collections, Objects
import java.util.stream.*;                               // pull in IntStream and Collectors for large-data test

public class Solution {
    // simple class to hold one vendor interval (start, end, price)
    static class Interval {
        int startTime;                                   // start of this price interval
        int endTime;                                     // end of this price interval
        int price;                                       // price that applies from startTime to endTime

        // constructor to build a new Interval from three ints
        Interval(int startTime, int endTime, int price) {
            this.startTime = startTime;                  // set the start time field
            this.endTime   = endTime;                    // set the end time field
            this.price     = price;                      // set the price field
        }

        // equality check so we can compare actual vs expected in tests
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;                  // same object reference -> equal
            if (o == null || getClass() != o.getClass()) // null or different class -> not equal
                return false;
            Interval that = (Interval) o;                 // cast to our type
            // equal if all three fields match exactly
            return startTime == that.startTime
                && endTime   == that.endTime
                && price     == that.price;
        }

        // hashCode matches equals, using a standard helper
        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime, price);
        }

        // helpful for debugging or printing results
        @Override
        public String toString() {
            return "Interval(" + startTime + "," + endTime + "," + price + ")";
        }
    }

    // main logic: split the day into sub‐intervals where the minimum price is constant
    private List<Interval> getLowestPrices(List<Interval> vendors) {
        // 1) collect all unique boundary points (every start and every end)
        Set<Integer> boundarySet = new HashSet<>();      // use a set to avoid duplicates
        for (Interval iv : vendors) {                    
            boundarySet.add(iv.startTime);                // add start point
            boundarySet.add(iv.endTime);                  // add end point
        }
        // 2) sort those boundary points in ascending order
        List<Integer> points = new ArrayList<>(boundarySet);
        Collections.sort(points);

        // 3) build a list of small segments [points[i], points[i+1])
        List<Interval> rawSegments = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            int segStart = points.get(i);                 // segment start
            int segEnd   = points.get(i + 1);             // segment end
            int minPrice = Integer.MAX_VALUE;             // track the lowest price that covers this segment

            // check every vendor interval to see if it fully covers [segStart,segEnd)
            for (Interval iv : vendors) {
                if (iv.startTime <= segStart && iv.endTime >= segEnd) {
                    // if vendor covers this slice, update minPrice
                    minPrice = Math.min(minPrice, iv.price);
                }
            }
            // if at least one vendor covered it, record a segment with that minPrice
            if (minPrice != Integer.MAX_VALUE) {
                rawSegments.add(new Interval(segStart, segEnd, minPrice));
            }
        }

        // 4) merge adjacent segments that have the same price
        List<Interval> merged = new ArrayList<>();
        for (Interval seg : rawSegments) {
            if (!merged.isEmpty()) {
                Interval last = merged.get(merged.size() - 1);
                // if this seg is right after last and same price, extend the last
                if (last.price == seg.price && last.endTime == seg.startTime) {
                    merged.set(merged.size() - 1,
                        new Interval(last.startTime, seg.endTime, last.price));
                    continue;                               // go to next raw segment
                }
            }
            // otherwise just add as a new piece
            merged.add(seg);
        }

        return merged;                                    // final list of lowest‐price intervals
    }

    // run tests without any testing framework
    public static void main(String[] args) {
        Solution sol = new Solution();                   // create the solver object

        // --- Test 1: overlapping intervals example ---
        List<Interval> in1 = Arrays.asList(
            new Interval(1, 5, 20),                      // vendor A: [1,5) @20
            new Interval(3, 8, 15),                      // vendor B: [3,8) @15
            new Interval(7,10,  8)                       // vendor C: [7,10)@ 8
        );
        List<Interval> exp1 = Arrays.asList(
            new Interval(1, 3, 20),                      // from 1–3 lowest is 20
            new Interval(3, 7, 15),                      // from 3–7 lowest is 15
            new Interval(7,10,  8)                       // from 7–10 lowest is 8
        );
        boolean r1 = sol.getLowestPrices(in1).equals(exp1); 
        System.out.println("Test 1 " + (r1 ? "PASSED" : "FAILED"));

        // --- Test 2: no vendors => no output ---
        List<Interval> in2  = Collections.emptyList();    // empty input
        List<Interval> exp2 = Collections.emptyList();    // expect empty output
        boolean r2 = sol.getLowestPrices(in2).equals(exp2);
        System.out.println("Test 2 " + (r2 ? "PASSED" : "FAILED"));

        // --- Test 3: single interval returns itself ---
        List<Interval> in3 = Arrays.asList(
            new Interval(5, 10, 100)                     // just one vendor
        );
        List<Interval> exp3 = Arrays.asList(
            new Interval(5, 10, 100)                     // same interval
        );
        boolean r3 = sol.getLowestPrices(in3).equals(exp3);
        System.out.println("Test 3 " + (r3 ? "PASSED" : "FAILED"));

        // --- Test 4: contiguous same‐price intervals should merge ---
        List<Interval> in4 = Arrays.asList(
            new Interval(1, 3, 50),                      // piece 1
            new Interval(3, 5, 50)                       // piece 2 right after, same price
        );
        List<Interval> exp4 = Arrays.asList(
            new Interval(1, 5, 50)                       // merged into one [1,5)@50
        );
        boolean r4 = sol.getLowestPrices(in4).equals(exp4);
        System.out.println("Test 4 " + (r4 ? "PASSED" : "FAILED"));

        // --- Large‐data stress test: 100K identical intervals ---
        int n = 100_000;                                 // number of intervals to generate
        List<Interval> bigIn = IntStream.range(0, n)     // stream 0..n-1
            .mapToObj(i -> new Interval(1, 1000, 100))   // each is [1,1000)@100
            .collect(Collectors.toList());
        List<Interval> bigExp = Arrays.asList(
            new Interval(1, 1000, 100)                   // should collapse to exactly one
        );
        long t0 = System.currentTimeMillis();             // note start time
        List<Interval> bigOut = sol.getLowestPrices(bigIn);
        long dt = System.currentTimeMillis() - t0;        // compute elapsed
        boolean r5 = bigOut.equals(bigExp);
        System.out.println("Large‐data test " 
            + (r5 ? "PASSED" : "FAILED") 
            + " in " + dt + " ms");

        // --- overall summary ---
        if (r1 && r2 && r3 && r4 && r5) {
            System.out.println("All tests PASSED");       // every check was green
        } else {
            System.out.println("Some tests FAILED");      // at least one check was red
        }
    }
}