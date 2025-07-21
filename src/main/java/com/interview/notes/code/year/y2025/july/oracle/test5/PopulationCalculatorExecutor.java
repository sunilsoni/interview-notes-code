package com.interview.notes.code.year.y2025.july.oracle.test5;

import java.util.*;                        // for List, ArrayList, etc.
import java.util.concurrent.*;             // for ExecutorService, Executors, Future, TimeUnit

public class PopulationCalculatorExecutor {

    // ──────────────────────────────────────────────────────────────────────────────
    // Simple POJO for a State
    // ──────────────────────────────────────────────────────────────────────────────
    public static class State {
        private final String name;             // state name
        private final long population;         // state population

        public State(String name, long population) {
            this.name = name;                 // store the name
            this.population = population;     // store the population
        }

        public long getPopulation() {
            return population;                // expose population
        }

        public String getName() {
            return name;                      // expose name
        }
    }

    // ──────────────────────────────────────────────────────────────────────────────
    // Sum populations using ExecutorService
    // ──────────────────────────────────────────────────────────────────────────────
    /**
     * Sums populations by submitting one Callable per State to an ExecutorService.
     *
     * @param states      list of State objects
     * @param threadCount number of threads in the pool
     * @return total population across all states
     * @throws InterruptedException if interrupted while waiting
     * @throws ExecutionException   if a task throws
     */
    public static long totalPopulationWithExecutor(List<State> states, int threadCount)
            throws InterruptedException, ExecutionException {

        // 1) Create a fixed-size thread pool
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        try {
            // 2) Prepare a list to hold the Future results
            List<Future<Long>> futures = new ArrayList<>();

            // 3) For each state, submit a Callable that returns its population
            for (State s : states) {
                futures.add(
                    executor.submit(() -> {
                        // This runs in another thread:
                        // simply return the state's population
                        return s.getPopulation();
                    })
                );
            }

            // 4) Now collect and sum all the results
            long total = 0L;
            for (Future<Long> f : futures) {
                // Future.get() will block until the individual task completes
                total += f.get();
            }

            // 5) Return the aggregated sum
            return total;

        } finally {
            // 6) Tell the executor we’re done submitting tasks
            executor.shutdown();
            // 7) Wait up to 1 hour for all tasks to finish (adjust as needed)
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                // 8) In the unlikely event they haven't finished, force shutdown
                executor.shutdownNow();
            }
        }
    }

    // ──────────────────────────────────────────────────────────────────────────────
    // main() to test PASS/FAIL
    // ──────────────────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        try {
            // ─── Small test ────────────────────────────────────────────────────────
            List<State> small = List.of(
                new State("A", 10_000),
                new State("B", 20_000),
                new State("C", 30_000)
            );
            long expectedSmall = 60_000;
            long resultSmall = totalPopulationWithExecutor(small, 3);
            System.out.printf("Executor Test 1 (small): expected=%d, got=%d → %s%n",
                expectedSmall, resultSmall,
                resultSmall == expectedSmall ? "PASS" : "FAIL"
            );

            // ─── Large test ────────────────────────────────────────────────────────
            List<State> template = List.of(
                new State("S1", 1_000_000),
                new State("S2", 2_000_000),
                new State("S3", 3_000_000),
                new State("S4", 4_000_000),
                new State("S5", 5_000_000)
            );
            int replicas = 100_000;
            List<State> large = new ArrayList<>(template.size() * replicas);
            for (int i = 0; i < replicas; i++) {
                large.addAll(template);
            }
            long singleSum = totalPopulationWithExecutor(template, 5);
            long expectedLarge = singleSum * replicas;
            long resultLarge = totalPopulationWithExecutor(large, 5);
            System.out.printf("Executor Test 2 (large): templateSum=%d, replicas=%d → expected=%d, got=%d → %s%n",
                singleSum, replicas, expectedLarge, resultLarge,
                resultLarge == expectedLarge ? "PASS" : "FAIL"
            );

        } catch (InterruptedException | ExecutionException e) {
            // If anything goes wrong with threads or tasks, print the stack trace
            e.printStackTrace();
        }
    }
}