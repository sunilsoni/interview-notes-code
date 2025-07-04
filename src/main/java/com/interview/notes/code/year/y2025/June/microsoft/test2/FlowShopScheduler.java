package com.interview.notes.code.year.y2025.June.microsoft.test2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlowShopScheduler {

    /**
     * Applies Johnson's Rule to compute optimal sequence and makespan.
     */
    public static ScheduleResult scheduleTests(List<TestJob> jobs) {
        // Partition into set A (buildTime < runTime) and B (buildTime >= runTime)
        List<TestJob> setA = jobs.stream()
                .filter(j -> j.buildTime < j.runTime)
                .sorted(Comparator.comparingInt(j -> j.buildTime))  // ascending buildTime
                .collect(Collectors.toList());
        List<TestJob> setB = jobs.stream()
                .filter(j -> j.buildTime >= j.runTime)
                .sorted(Comparator.comparingInt((TestJob j) -> j.runTime).reversed()) // descending runTime
                .collect(Collectors.toList());

        // Concatenate A then B to get the optimal sequence
        List<TestJob> sequence = new ArrayList<>();
        sequence.addAll(setA);
        sequence.addAll(setB);

        // Simulate the two-machine pipeline to compute makespan
        int t1 = 0; // time when VM1 (build) becomes free
        int t2 = 0; // time when VM2 (run) becomes free
        for (TestJob job : sequence) {
            t1 += job.buildTime;                         // build this job after previous builds
            int startRun = Math.max(t1, t2);             // run can only start after both build done and VM2 free
            t2 = startRun + job.runTime;                 // update VM2 free time
        }

        // Extract just the list of job IDs for the output order
        List<Integer> order = sequence.stream()
                .map(j -> j.id)
                .collect(Collectors.toList());

        return new ScheduleResult(order, t2);
    }

    /**
     * Simple main method for testing correctness and performance.
     * Prints PASS/FAIL for known tests and shows time for a large random test.
     */
    public static void main(String[] args) {
        // Example from problem statement
        List<TestJob> example = Arrays.asList(
                new TestJob(1, 4, 5),
                new TestJob(2, 2, 3),
                new TestJob(3, 3, 2),
                new TestJob(4, 1, 4)
        );
        ScheduleResult res = scheduleTests(example);
        // Expected order [2,4,3,1], totalTime 14
        boolean pass = res.order.equals(Arrays.asList(2, 4, 3, 1)) && res.totalTime == 14;
        System.out.println("Example Test: " + (pass ? "PASS" : "FAIL"));
        System.out.println("  Order: " + res.order);
        System.out.println("  Total Time: " + res.totalTime);

        // Large random test to ensure performance
        Random rnd = new Random(0);
        int N = 100_000;  // up to 100k jobs
        List<TestJob> large = IntStream.rangeClosed(1, N)
                .mapToObj(i -> new TestJob(i, rnd.nextInt(1000), rnd.nextInt(1000)))
                .collect(Collectors.toList());
        long start = System.currentTimeMillis();
        ScheduleResult largeRes = scheduleTests(large);
        long duration = System.currentTimeMillis() - start;
        System.out.println("Large Test (n=" + N + "): completed in " + duration + " ms");
        // No expected value for makespan, just ensuring it runs fast
    }

    // Simple data holder for each test job
    static class TestJob {
        int id;            // unique identifier for the job
        int buildTime;     // time to build on VM1
        int runTime;       // time to run on VM2

        public TestJob(int id, int buildTime, int runTime) {
            this.id = id;
            this.buildTime = buildTime;
            this.runTime = runTime;
        }
    }

    // Result container: the optimal order and the total makespan
    static class ScheduleResult {
        List<Integer> order;  // job IDs in execution sequence
        int totalTime;        // minimal total time to finish all jobs

        public ScheduleResult(List<Integer> order, int totalTime) {
            this.order = order;
            this.totalTime = totalTime;
        }
    }
}