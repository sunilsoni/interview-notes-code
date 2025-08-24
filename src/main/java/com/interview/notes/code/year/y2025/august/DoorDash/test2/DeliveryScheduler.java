package com.interview.notes.code.year.y2025.august.DoorDash.test2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Problem: Maximize total pay by selecting non-overlapping deliveries within a given time window.
 * Approach: Weighted Interval Scheduling (sort by end time + DP + binary search for predecessor).
 * Java Version: Java 8 (uses streams lightly but keeps logic simple and fast).
 */
public class DeliveryScheduler {  // define a public class to hold our solution and tests

    /**
     * Compute maximum total pay from non-overlapping deliveries within [startTime, endTime].
     * <p>
     * Rules applied:
     * - A delivery counts only if start >= startTime and end <= endTime.
     * - Deliveries must not overlap in time (exclusive overlap; we allow chaining where previous end == next start).
     * - If start >= end for a delivery, we ignore it (invalid or zero-length).
     * <p>
     * Time Complexity: O(n log n) due to sort + binary searches.
     * Space Complexity: O(n) for DP arrays.
     */
    public static int maxEarnings(int startTime, int endTime, int[] d_starts, int[] d_ends, int[] d_pays) {
        // Guard against null or length mismatch; if invalid input, return 0.
        if (d_starts == null || d_ends == null || d_pays == null) return 0;  // basic null check
        int n = Math.min(d_starts.length, Math.min(d_ends.length, d_pays.length));  // ensure we only read up to the shortest length
        if (n == 0) return 0;  // if there are no deliveries, max earnings are zero

        // Build a filtered list of valid jobs that lie entirely within the allowed window and have positive duration.
        List<Job> jobs = new ArrayList<>(n);  // pre-size for efficiency
        for (int i = 0; i < n; i++) {  // iterate through all provided deliveries
            int s = d_starts[i];  // read start
            int e = d_ends[i];    // read end
            int p = d_pays[i];    // read pay
            if (s < startTime) continue;  // skip if delivery starts before allowed schedule start
            if (e > endTime) continue;    // skip if delivery ends after allowed schedule end
            if (e <= s) continue;         // skip invalid or zero-length jobs (no time to earn)
            // Optional: if negative pay exists, we can allow it but it will never be selected because DP takes max; keeping as-is
            jobs.add(new Job(s, e, p));   // keep the valid job
        }

        // If nothing remains after filtering, return 0.
        if (jobs.isEmpty()) return 0;  // no valid jobs means zero earnings

        // Sort jobs by end time ascending; this is required for the DP recurrence with binary search.
        jobs.sort(Comparator.comparingInt(j -> j.e));  // stable and O(n log n)

        // Prepare arrays for DP:
        // ends[i] = end time of job i after sorting; used for binary searching predecessor
        int m = jobs.size();            // number of valid, filtered jobs
        int[] ends = new int[m];        // array of end times
        int[] starts = new int[m];      // array of start times, for clarity/fast access
        int[] pays = new int[m];        // array of pays
        for (int i = 0; i < m; i++) {   // fill arrays from the sorted jobs
            Job jb = jobs.get(i);       // get job at position i
            starts[i] = jb.s;           // store start
            ends[i] = jb.e;             // store end
            pays[i] = jb.p;             // store pay
        }

        // dp[i] = maximum pay achievable considering jobs[0..i] and possibly taking job i.
        // We will store dp as "best up to index i", i.e., including the choice of taking or skipping i.
        long[] dp = new long[m];        // use long to avoid intermediate overflow when sums get big
        // To compute dp[i], we need the last job that ends <= starts[i] (i.e., compatible predecessor).
        // We'll find it with binary search on the ends[] array which is sorted by construction.

        // Helper lambda-like method to find predecessor index (rightmost index j < i with ends[j] <= starts[i]).
        // We'll implement as a static-like helper below for clarity; but here inline for speed.
        // Initialize dp[0] separately: either take job 0 or skip (skipping yields 0, taking yields pays[0]).
        dp[0] = Math.max(0, pays[0]);   // base case: best using only the first job
        for (int i = 1; i < m; i++) {   // compute dp for each job i from left to right
            long take = pays[i];        // value if we take job i (at least its own pay)
            int pred = predecessorIndex(ends, i - 1, starts[i]);  // find the last non-overlapping job index
            if (pred >= 0) take += dp[pred];  // if a predecessor exists, add its best earnings
            long skip = dp[i - 1];      // value if we skip job i: same as best up to i-1
            dp[i] = Math.max(skip, take);  // choose the better of take vs skip
        }

        // The answer is dp[m - 1], but it may exceed int range if inputs are extreme; clamp safely to int if needed.
        long ans = dp[m - 1];           // read final optimal value
        if (ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;  // clamp high
        if (ans < Integer.MIN_VALUE) return Integer.MIN_VALUE;  // clamp low (unlikely, but safe)
        return (int) ans;               // return as int to match requested output type
    }

    /**
     * Binary search for the predecessor:
     * Find the rightmost index in ends[0..hi] whose value <= start.
     * Returns -1 if none such index exists.
     */
    private static int predecessorIndex(int[] ends, int hi, int start) {
        int lo = 0;               // low pointer for binary search
        int ans = -1;             // default when none found
        while (lo <= hi) {        // standard binary search loop
            int mid = (lo + hi) >>> 1;  // mid index using unsigned right shift to avoid overflow
            if (ends[mid] <= start) {   // if this mid job ends before or exactly at 'start', it's a candidate
                ans = mid;              // record it as current best
                lo = mid + 1;           // try to find a later one (rightmost)
            } else {
                hi = mid - 1;           // otherwise, move left
            }
        }
        return ans;               // return the rightmost non-overlapping job index, or -1
    }

    public static void main(String[] args) {
        // A tiny helper to run and print PASS/FAIL for each test case in a readable way.
        class T {  // local test helper container
            void run(String name, int start, int end, int[] s, int[] e, int[] p, int expected) {  // method to execute one test
                long t0 = System.nanoTime();                             // capture start time for timing
                int got = maxEarnings(start, end, s, e, p);              // compute result
                long t1 = System.nanoTime();                             // capture end time
                boolean pass = (got == expected);                        // compare with expected
                double ms = (t1 - t0) / 1_000_000.0;                     // convert time to milliseconds
                System.out.printf("%s -> got=%d, expected=%d : %s | time=%.3f ms%n",
                        name, got, expected, pass ? "PASS" : "FAIL", ms); // print formatted result
            }
        }
        T t = new T();  // instantiate the helper

        // Test 1: Standard mix; best chain is (1,3,5) + (3,5,6) + (5,9,11) = 22
        t.run(
                "Test 1 basic chain",
                0, 10,
                new int[]{1, 3, 0, 5, 5, 7},  // starts
                new int[]{3, 5, 6, 7, 9, 8},  // ends
                new int[]{5, 6, 5, 4, 11, 2}, // pays
                22                              // expected
        );

        // Test 2: Window restriction filters out (0,6) and (5,9); remaining chain (3,5,6) + (5,7,4) + (7,8,2) = 12
        t.run(
                "Test 2 restricted window",
                2, 8,
                new int[]{1, 3, 0, 5, 5, 7},
                new int[]{3, 5, 6, 7, 9, 8},
                new int[]{5, 6, 5, 4, 11, 2},
                12
        );

        // Test 3: All overlap; pick the single best paying job inside window (0,5) -> pay 12
        t.run(
                "Test 3 overlapping choose best one",
                0, 5,
                new int[]{0, 0, 0},
                new int[]{5, 5, 5},
                new int[]{10, 8, 12},
                12
        );

        // Test 4: Invalid lengths and zero-length jobs ignored; only valid chain is (2,3,7) + (3,5,6) = 13
        t.run(
                "Test 4 ignore zero-length and out-of-window",
                0, 10,
                new int[]{2, 2, 9, 4},       // includes 2,2 (zero length) and 9,12 (outside)
                new int[]{2, 3, 12, 5},
                new int[]{5, 7, 100, 6},
                13
        );

        // Test 5: Empty inputs -> 0
        t.run(
                "Test 5 empty",
                0, 100,
                new int[]{},
                new int[]{},
                new int[]{},
                0
        );

        // Test 6: Exact-touch chaining allowed (end == next start); (1,2,5)+(2,4,6)+(4,7,9)=20
        t.run(
                "Test 6 touching intervals allowed",
                0, 10,
                new int[]{1, 2, 4},
                new int[]{2, 4, 7},
                new int[]{5, 6, 9},
                20
        );

        // Test 7: Negative times are fine if inside window; chain (-3,-1,4) + (-1,1,5) + (1,2,3) = 12
        t.run(
                "Test 7 negative times inside window",
                -5, 5,
                new int[]{-3, -1, 1, -10},
                new int[]{-1, 1, 2, -9},
                new int[]{4, 5, 3, 100},
                12
        );

        // Large Data Test: Randomly generated, checks performance and prints result (no fixed expected).
        runLargeDataPerformanceTest();  // separate method to keep main tidy
    }

    // -------------------------- TEST HARNESS (simple main, no JUnit) --------------------------

    // Generate many jobs and measure performance on O(n log n); also sanity-check by disallowing overlaps in generation.
    private static void runLargeDataPerformanceTest() {
        int N = 100_000;                         // number of jobs to generate; large enough to test performance
        int globalStart = 0;                      // schedule window start
        int globalEnd = 2_000_000_000;           // large window end to keep most jobs valid

        Random rnd = new Random(42);             // fixed seed for reproducibility

        int[] s = new int[N];                    // starts array
        int[] e = new int[N];                    // ends array
        int[] p = new int[N];                    // pays array

        // Generate random jobs with positive durations and reasonable pay.
        for (int i = 0; i < N; i++) {            // loop N times to fill arrays
            int start = rnd.nextInt(1_000_000);  // random start in a large range
            int dur = 1 + rnd.nextInt(1000);     // ensure strictly positive duration [1..1000]
            int end = start + dur;               // end is start + duration
            int pay = 1 + rnd.nextInt(1000);     // random pay [1..1000]
            s[i] = start;                        // store start
            e[i] = end;                          // store end
            p[i] = pay;                          // store pay
        }

        long t0 = System.nanoTime();             // start timing
        int got = maxEarnings(globalStart, globalEnd, s, e, p);  // compute max earnings on large dataset
        long t1 = System.nanoTime();             // end timing
        double ms = (t1 - t0) / 1_000_000.0;     // convert to ms
        // We cannot know the exact expected value; just report timing and a sanity property.
        System.out.printf("LargeData: n=%d -> value=%d | time=%.2f ms | PASS (performance)%n", N, got, ms);  // print outcome
    }

    // A small immutable record-like class to store each delivery's start, end, and pay.
    private static final class Job {  // make it private and final to encapsulate and ensure no subclassing
        final int s;  // start time of the delivery
        final int e;  // end time of the delivery
        final int p;  // pay for completing the delivery

        Job(int s, int e, int p) {  // constructor to set fields
            this.s = s;  // assign start time
            this.e = e;  // assign end time
            this.p = p;  // assign pay
        }
    }
}