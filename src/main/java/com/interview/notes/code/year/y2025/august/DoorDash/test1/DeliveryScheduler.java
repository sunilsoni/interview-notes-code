package com.interview.notes.code.year.y2025.august.DoorDash.test1;

import java.util.*;

// Class that contains the solution and test harness
public class DeliveryScheduler {

    /**
     * Method to compute maximum earnings.
     * Uses Weighted Interval Scheduling with DP + binary search.
     */
    public static int maxEarnings(int startTime, int endTime, int[] d_starts, int[] d_ends, int[] d_pays) {
        // Check for null inputs to avoid NullPointerException
        if (d_starts == null || d_ends == null || d_pays == null) return 0;

        // Take min length to avoid ArrayIndexOutOfBounds if arrays differ
        int n = Math.min(d_starts.length, Math.min(d_ends.length, d_pays.length));

        // If no jobs available, earnings = 0
        if (n == 0) return 0;

        // Filter jobs: only include valid ones inside the allowed window and with positive duration
        List<Job> jobs = new ArrayList<>(n); // list to collect valid jobs
        for (int i = 0; i < n; i++) { // loop through each job
            int s = d_starts[i], e = d_ends[i], p = d_pays[i];
            if (s < startTime || e > endTime || e <= s) continue; // skip if invalid
            jobs.add(new Job(s, e, p)); // add valid job to list
        }

        // If no valid jobs remain, return 0
        if (jobs.isEmpty()) return 0;

        // Sort jobs by end time so we can binary search predecessors
        jobs.sort(Comparator.comparingInt(j -> j.e));

        // Prepare arrays of start, end, pay for faster access
        int m = jobs.size();       // number of valid jobs
        int[] starts = new int[m]; // store job start times
        int[] ends = new int[m]; // store job end times
        int[] pays = new int[m]; // store job pays
        for (int i = 0; i < m; i++) { // fill arrays
            Job jb = jobs.get(i);
            starts[i] = jb.s;
            ends[i] = jb.e;
            pays[i] = jb.p;
        }

        // DP array: dp[i] = max earnings considering jobs[0..i]
        long[] dp = new long[m]; // long to avoid overflow

        // Base case: best using first job is either its pay or 0
        dp[0] = Math.max(0, pays[0]);

        // Iterate through jobs to fill dp
        for (int i = 1; i < m; i++) {
            // Option 1: take this job
            long take = pays[i];

            // Use built-in Arrays.binarySearch to find predecessor (job ending <= this start)
            int idx = Arrays.binarySearch(ends, 0, i, starts[i]);

            int pred; // predecessor index
            if (idx >= 0) {
                // Found exact end time == start; move right to last equal
                while (idx + 1 < i && ends[idx + 1] == starts[i]) idx++;
                pred = idx;
            } else {
                // Not found; convert to insertion point
                int insertionPoint = -idx - 1;  // first index with end > start
                pred = insertionPoint - 1;      // predecessor = just before that
            }

            // If valid predecessor exists, add its dp value
            if (pred >= 0) take += dp[pred];

            // Option 2: skip this job → same as dp[i-1]
            long skip = dp[i - 1];

            // Pick max of take vs skip
            dp[i] = Math.max(skip, take);
        }

        // Answer is best value after last job
        long ans = dp[m - 1];

        // Clamp to int range (problem requires int)
        if (ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (ans < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) ans;
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        // Helper class to run one test
        class T {
            void run(String name, int start, int end, int[] s, int[] e, int[] p, int expected) {
                long t0 = System.nanoTime(); // start timer
                int got = maxEarnings(start, end, s, e, p); // run solution
                long t1 = System.nanoTime(); // end timer
                boolean pass = (got == expected); // check correctness
                double ms = (t1 - t0) / 1_000_000.0; // convert to ms
                // Print result
                System.out.printf("%s -> got=%d, expected=%d : %s | time=%.3f ms%n",
                        name, got, expected, pass ? "PASS" : "FAIL", ms);
            }
        }
        T t = new T(); // create helper

        // Test 1: chain of jobs fits → expect 22
        t.run("Test 1 basic chain", 0, 10,
                new int[]{1, 3, 0, 5, 5, 7},
                new int[]{3, 5, 6, 7, 9, 8},
                new int[]{5, 6, 5, 4, 11, 2}, 22);

        // Test 2: restricted window removes some jobs → expect 12
        t.run("Test 2 restricted window", 2, 8,
                new int[]{1, 3, 0, 5, 5, 7},
                new int[]{3, 5, 6, 7, 9, 8},
                new int[]{5, 6, 5, 4, 11, 2}, 12);

        // Test 3: all overlap, pick best paying job → expect 12
        t.run("Test 3 overlapping choose best one", 0, 5,
                new int[]{0, 0, 0},
                new int[]{5, 5, 5},
                new int[]{10, 8, 12}, 12);

        // Test 4: zero-length/out-of-window jobs ignored → expect 13
        t.run("Test 4 ignore zero/out-of-window", 0, 10,
                new int[]{2, 2, 9, 4},
                new int[]{2, 3, 12, 5},
                new int[]{5, 7, 100, 6}, 13);

        // Test 5: empty arrays → expect 0
        t.run("Test 5 empty", 0, 100,
                new int[]{}, new int[]{}, new int[]{}, 0);

        // Test 6: touching intervals allowed → expect 20
        t.run("Test 6 touching intervals allowed", 0, 10,
                new int[]{1, 2, 4},
                new int[]{2, 4, 7},
                new int[]{5, 6, 9}, 20);

        // Test 7: negative times within window → expect 12
        t.run("Test 7 negative times inside window", -5, 5,
                new int[]{-3, -1, 1, -10},
                new int[]{-1, 1, 2, -9},
                new int[]{4, 5, 3, 100}, 12);

        // Run performance test on large dataset
        runLargeDataPerformanceTest();
    }

    // Large dataset test to check performance
    private static void runLargeDataPerformanceTest() {
        int N = 100_000;           // number of jobs
        int globalStart = 0;       // overall schedule window start
        int globalEnd = 2_000_000_000; // large end so all jobs fit
        Random rnd = new Random(42); // fixed seed for repeatability

        int[] s = new int[N]; // starts
        int[] e = new int[N]; // ends
        int[] p = new int[N]; // pays

        // Generate random jobs with positive durations
        for (int i = 0; i < N; i++) {
            int start = rnd.nextInt(1_000_000);
            int dur = 1 + rnd.nextInt(1000);
            int end = start + dur;
            int pay = 1 + rnd.nextInt(1000);
            s[i] = start;
            e[i] = end;
            p[i] = pay;
        }

        long t0 = System.nanoTime(); // start timer
        int got = maxEarnings(globalStart, globalEnd, s, e, p); // run solution
        long t1 = System.nanoTime(); // end timer
        double ms = (t1 - t0) / 1_000_000.0; // convert to ms
        // Print result (no fixed expected, just performance check)
        System.out.printf("LargeData: n=%d -> value=%d | time=%.2f ms | PASS (performance)%n",
                N, got, ms);
    }

    /**
     * @param p start time, end time, pay
     */ // Inner class representing one delivery job
        private record Job(int s, int e, int p) {
        // Constructor: store values for this job
    }
}