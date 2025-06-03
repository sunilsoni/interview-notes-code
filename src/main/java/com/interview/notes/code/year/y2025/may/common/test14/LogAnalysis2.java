package com.interview.notes.code.year.y2025.may.common.test14;

import java.util.*;
import java.util.stream.*;

public class LogAnalysis2 {

    /**
     * Data structure to hold a single log entry (serverId, time).
     * We sort by `time` ascending.
     */
    private static class LogEntry {
        int serverId; 
        int time;
        LogEntry(int serverId, int time) {
            this.serverId = serverId;
            this.time = time;
        }
    }

    /**
     * Data structure to hold a single query (queryTime, originalIndex).
     * We sort by `queryTime` ascending, but we remember where it was originally
     * so that we can put the answer back in the correct index.
     */
    private static class QueryEntry {
        int queryTime;
        int originalIdx;
        QueryEntry(int queryTime, int originalIdx) {
            this.queryTime = queryTime;
            this.originalIdx = originalIdx;
        }
    }

    /**
     * getStaleServerCount:
     *
     * n        = total number of servers (1..n)
     * log_data = List of pairs [serverId, time], length = m
     * query    = List of query times, length = q
     * x        = window size
     *
     * Returns an array (List<Integer>) of length q, where
     *   answer[i] = number of servers that did NOT get any request in [query[i] - x, query[i]].
     *
     * We implement the “two-pointer sliding window” approach:
     *   1. Sort logs by time.
     *   2. Sort queries by time, keeping original indices.
     *   3. Maintain a sliding window [windowStart, windowEnd] over the logs,
     *      where windowEnd moves forward to include logs ≤ currentQueryTime,
     *      and windowStart moves forward to exclude logs < (currentQueryTime - x).
     *   4. Keep an array countPerServer[1..n] that tracks how many logs of each
     *      server are currently inside the window; and an integer activeServers
     *      = number of distinct servers with countPerServer[srv] > 0.
     *   5. For each query, stale = n - activeServers.
     */
    public static List<Integer> getStaleServerCount(
            int n,
            List<List<Integer>> log_data,
            List<Integer> query,
            int x
    ) {
        int m = log_data.size();
        int q = query.size();

        // 1) Convert log_data (List<List<Integer>>) → LogEntry[]
        LogEntry[] logs = new LogEntry[m];
        for (int i = 0; i < m; i++) {
            // Each log_data.get(i) is a List<Integer> of size 2: [serverId, time]
            int srv = log_data.get(i).get(0);
            int t   = log_data.get(i).get(1);
            logs[i] = new LogEntry(srv, t);
        }

        // 2) Sort logs[] by `time` ascending
        Arrays.sort(logs, Comparator.comparingInt(e -> e.time));

        // 3) Build an array of QueryEntry {queryTime, originalIdx}
        QueryEntry[] queries = new QueryEntry[q];
        for (int i = 0; i < q; i++) {
            queries[i] = new QueryEntry(query.get(i), i);
        }

        // 4) Sort queries[] by queryTime ascending
        Arrays.sort(queries, Comparator.comparingInt(e -> e.queryTime));

        // 5) Prepare result array; we'll fill it in the loop
        Integer[] answer = new Integer[q];

        // 6) Prepare `countPerServer[1..n]`, all zero initially.
        //    We'll use a 1-based index for convenience (ignore index 0).
        int[] countPerServer = new int[n + 1];
        Arrays.fill(countPerServer, 0);

        // 7) `activeServers` = how many servers currently have countPerServer[srv] > 0
        int activeServers = 0;

        // 8) Two pointers for the sliding window over `logs[]`
        int leftPtr  = 0; // will move forward to drop logs from window
        int rightPtr = 0; // will move forward to add logs into window

        // 9) Now iterate all queries in ascending order:
        for (QueryEntry qe : queries) {
            int currentTime = qe.queryTime;

            // 9a) Extend the window’s right end: include all logs with time <= currentTime
            while (rightPtr < m && logs[rightPtr].time <= currentTime) {
                int srv = logs[rightPtr].serverId;
                // If this server had no logs in the window before, increment activeServers
                if (countPerServer[srv] == 0) {
                    activeServers++;
                }
                // Count this log for the server
                countPerServer[srv]++;
                rightPtr++;
            }

            // 9b) Compute the window start time (inclusive)
            int windowStart = currentTime - x;
            if (windowStart < 1) {
                // times < 1 are irrelevant; clamp to 1
                windowStart = 1;
            }

            // Remove logs with time < windowStart
            while (leftPtr < m && logs[leftPtr].time < windowStart) {
                int srv = logs[leftPtr].serverId;
                // Decrement that server’s count
                countPerServer[srv]--;
                // If it dropped to zero, that server becomes “inactive”
                if (countPerServer[srv] == 0) {
                    activeServers--;
                }
                leftPtr++;
            }

            // 9c) Now activeServers = number of distinct servers in [windowStart, currentTime]
            int staleCount = n - activeServers; 
            answer[qe.originalIdx] = staleCount;
        }

        // 10) Convert Integer[] → List<Integer> and return
        return Arrays.asList(answer);
    }

    /** 
     * A simple test harness (no JUnit!).  
     * We hardcode multiple test cases. For each:
     *   1) Build n, log_data, query, x
     *   2) call getStaleServerCount(...)
     *   3) Compare with the expected output
     *   4) Print PASS/FAIL
     */
    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();

        // ---- TestCase 1: Sample from the prompt ----
        {
            int n = 6;
            List<List<Integer>> logData = Arrays.asList(
                Arrays.asList(3, 2),
                Arrays.asList(4, 3),
                Arrays.asList(2, 6),
                Arrays.asList(6, 3)
            );
            List<Integer> queries = Arrays.asList(3, 2, 6);
            int x = 2;
            // Explanation (from prompt):
            // query=3  → interval [1,3]: logs by servers {3 @ t=2, 4 @ t=3, 6 @ t=3} → active servers={3,4,6} → stale = 6-3 = 3
            // query=2  → interval [0,2] but clamp to [1,2]: logs by servers {3 @ t=2} → stale = 6-1 = 5
            // query=6  → interval [4,6]: logs by servers {2 @ t=6} → stale = 6-1 = 5
            // So expected = [3,5,5]
            List<Integer> expected = Arrays.asList(3, 5, 5);

            tests.add(new TestCase(n, logData, queries, x, expected, "SampleCase"));
        }

        // ---- TestCase 2: No logs at all ----
        {
            int n = 5;
            List<List<Integer>> logData = Collections.emptyList();
            List<Integer> queries = Arrays.asList(1, 10, 100);
            int x = 5;
            // With no logs, every server is stale for every query
            List<Integer> expected = Arrays.asList(5, 5, 5);
            tests.add(new TestCase(n, logData, queries, x, expected, "NoLogs"));
        }

        // ---- TestCase 3: All servers have logs at every time, so active always = n ----
        {
            int n = 3;
            // Suppose logs: server1 @ t=1, server2 @ t=1, server3 @ t=1, server1 @ t=2, server2 @ t=2, server3 @ t=2, ...
            List<List<Integer>> logData = new ArrayList<>();
            for (int t = 1; t <= 3; t++) {
                for (int srv = 1; srv <= 3; srv++) {
                    logData.add(Arrays.asList(srv, t));
                }
            }
            List<Integer> queries = Arrays.asList(1, 2, 3, 4);
            int x = 2;
            // Evaluate:
            // query=1 → interval [−1,1] → clamp to [1,1] → active={1,2,3} → stale=0
            // query=2 → interval [0,2] → clamp to [1,2] → active={1,2,3} → stale=0
            // query=3 → interval [1,3] → active={1,2,3} → stale=0
            // query=4 → interval [2,4] → active={1,2,3} (all have logs at t=2 or t=3) → stale=0
            List<Integer> expected = Arrays.asList(0, 0, 0, 0);
            tests.add(new TestCase(n, logData, queries, x, expected, "AllActive"));
        }

        // ---- TestCase 4: Queries before any log time and after all log times ----
        {
            int n = 4;
            List<List<Integer>> logData = Arrays.asList(
                Arrays.asList(1, 10),
                Arrays.asList(2, 20),
                Arrays.asList(4, 30)
            );
            List<Integer> queries = Arrays.asList(5, 15, 25, 35);
            int x = 5;
            // q=5  → interval [0,5] → clamp to [1,5] → no logs → stale=4
            // q=15 → interval [10,15] → logs at time=10 ⇒ active={1} → stale=3
            // q=25 → interval [20,25] → logs at time=20 ⇒ active={2} → stale=3
            // q=35 → interval [30,35] → logs at time=30 ⇒ active={4} → stale=3
            List<Integer> expected = Arrays.asList(4, 3, 3, 3);
            tests.add(new TestCase(n, logData, queries, x, expected, "BeforeAfterLogs"));
        }

        // ---- TestCase 5: Large‐scale random log to test performance ----
        {
            int n = 100_000;
            int m = 100_000; 
            int q = 100_000;
            int x = 50_000;

            Random rnd = new Random(0);

            // Generate m random logs: serverId ∈ [1,n], time ∈ [1,100_000]
            List<List<Integer>> logData = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                int srv = 1 + rnd.nextInt(n);
                int t   = 1 + rnd.nextInt(100_000);
                logData.add(Arrays.asList(srv, t));
            }

            // Generate q random queries: time ∈ [1,100_000]
            List<Integer> queries = new ArrayList<>(q);
            for (int i = 0; i < q; i++) {
                queries.add(1 + rnd.nextInt(100_000));
            }

            // We do not know the “expected” exactly in advance, so for this large test we simply run:
            //   answer = getStaleServerCount(...) 
            //   and verify that it runs “quickly” (< 2s). Since we do not have a ground‐truth array,
            //   we will just check that it returns an array of length q with values in [0..n].
            List<Integer> expected = null; // indicate “no exact expected; just performance check”

            tests.add(new TestCase(n, logData, queries, x, expected, "LargeRandom"));
        }

        // ---- Run all test cases ----
        for (TestCase tc : tests) {
            System.out.println("==== Running Test: " + tc.name + " ====");
            long startTime = System.currentTimeMillis();

            List<Integer> actual = getStaleServerCount(tc.n, tc.logData, tc.queries, tc.x);

            long elapsed = System.currentTimeMillis() - startTime;

            if (tc.expected != null) {
                // We have an exact expected array → compare element‐by‐element
                boolean pass = true;
                if (actual.size() != tc.expected.size()) {
                    pass = false;
                } else {
                    for (int i = 0; i < actual.size(); i++) {
                        if (!actual.get(i).equals(tc.expected.get(i))) {
                            pass = false;
                            break;
                        }
                    }
                }
                if (pass) {
                    System.out.println("RESULT: PASS    (Time = " + elapsed + " ms)");
                } else {
                    System.out.println("RESULT: FAIL    (Time = " + elapsed + " ms)");
                    System.out.println("  Expected: " + tc.expected);
                    System.out.println("  Actual:   " + actual);
                }
            } else {
                // No exact expected → just check size and bounds
                boolean pass = true;
                if (actual.size() != tc.queries.size()) {
                    pass = false;
                    System.out.println("RESULT: FAIL → returned array length " + actual.size() +
                                       " but expected " + tc.queries.size());
                } else {
                    // Check each answer is in [0..n]
                    for (Integer ans : actual) {
                        if (ans < 0 || ans > tc.n) {
                            pass = false;
                            System.out.println("RESULT: FAIL → found out-of-range answer: " + ans);
                            break;
                        }
                    }
                    if (pass) {
                        System.out.println("RESULT: PASS (Large test completed in " + elapsed + " ms)");
                    }
                }
            }
            System.out.println();
        }
    }

    /** 
     * Helper class to bundle each test’s data together.
     */
    private static class TestCase {
        int n;
        List<List<Integer>> logData;
        List<Integer> queries;
        int x;
        List<Integer> expected; // null if we only want performance check
        String name;

        TestCase(int n, List<List<Integer>> logData, List<Integer> queries, int x,
                 List<Integer> expected, String name) {
            this.n = n;
            this.logData = logData;
            this.queries = queries;
            this.x = x;
            this.expected = expected;
            this.name = name;
        }
    }
}