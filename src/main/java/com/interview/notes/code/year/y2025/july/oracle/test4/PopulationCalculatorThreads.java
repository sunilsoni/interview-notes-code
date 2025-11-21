package com.interview.notes.code.year.y2025.july.oracle.test4;// Import List and ArrayList for working with collections

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Demonstrates summing state populations by manually creating Threads.
 */
public class PopulationCalculatorThreads {

    /**
     * Launches one Thread per State, each adding its population to a shared AtomicLong.
     *
     * @param states list of State objects to sum
     * @return the total population across all states
     * @throws InterruptedException if the main thread is interrupted while waiting
     */
    public static long totalPopulationWithThreads(List<State> states) throws InterruptedException {
        // AtomicLong provides atomic add operations without explicit synchronization
        AtomicLong total = new AtomicLong(0L);
        // We'll keep references to all Threads so we can join() them later
        List<Thread> threads = new ArrayList<>();

        // For each state, create and start a new Thread
        for (State s : states) {
            // Thread will run the lambda, adding this state's population to 'total'
            Thread t = new Thread(() -> {
                total.addAndGet(s.population());  // atomically add population
            });
            threads.add(t);   // remember the thread
            t.start();        // start the thread immediately
        }

        // Now wait for every thread to finish
        for (Thread t : threads) {
            t.join();        // block until this thread completes
        }

        // Once all threads have joined, retrieve the accumulated total
        return total.get();
    }

    // ──────────────────────────────────────────────────────────────────────────────
    // Method: sum populations using raw Thread objects
    // ──────────────────────────────────────────────────────────────────────────────

    // ──────────────────────────────────────────────────────────────────────────────
    // Main method: run two tests and print PASS/FAIL
    // ──────────────────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        try {
            // ─── Test 1: Small dataset ───────────────────────────────────────────────
            List<State> small = List.of(
                    new State("A", 10_000),    // State A population 10,000
                    new State("B", 20_000),    // State B population 20,000
                    new State("C", 30_000)     // State C population 30,000
            );
            long expectedSmall = 60_000L; // known sum: 10k + 20k + 30k
            long resultSmall = totalPopulationWithThreads(small);
            System.out.printf(
                    "Thread Test 1 (small): expected=%d, got=%d → %s%n",
                    expectedSmall,                   // expected placeholder
                    resultSmall,                     // actual result placeholder
                    resultSmall == expectedSmall     // check PASS/FAIL
                            ? "PASS"
                            : "FAIL"
            );

            // ─── Test 2: Large dataset ───────────────────────────────────────────────
            // Build a 5-state template to simulate “50 states”
            List<State> template = List.of(
                    new State("S1", 1_000_000),  // 1 million
                    new State("S2", 2_000_000),  // 2 million
                    new State("S3", 3_000_000),  // 3 million
                    new State("S4", 4_000_000),  // 4 million
                    new State("S5", 5_000_000)   // 5 million
            );
            int replicas = 100_000;          // repeat template 100k times → 500k entries
            // Pre-size the list for performance
            List<State> large = new ArrayList<>(template.size() * replicas);
            for (int i = 0; i < replicas; i++) {
                large.addAll(template);      // add all 5 each iteration
            }
            // Compute sum of one template copy
            long singleSum = totalPopulationWithThreads(template);
            long expectedLarge = singleSum * replicas;
            long resultLarge = totalPopulationWithThreads(large);
            System.out.printf(
                    "Thread Test 2 (large): templateSum=%d, replicas=%d → expected=%d, got=%d → %s%n",
                    singleSum,                      // sum of template
                    replicas,                       // how many times we repeated
                    expectedLarge,                  // expected total
                    resultLarge,                    // actual total
                    resultLarge == expectedLarge    // PASS/FAIL
                            ? "PASS"
                            : "FAIL"
            );

        } catch (InterruptedException e) {
            // If any join() is interrupted, print stack trace
            e.printStackTrace();
        }
    }

    /**
     * @param name       the state's name (e.g. "California")
     * @param population the state's population
     */ // ──────────────────────────────────────────────────────────────────────────────
        // Simple POJO representing a US state and its population
        // ──────────────────────────────────────────────────────────────────────────────
        public record State(String name, long population) {
        /**
         * Constructor to initialize the state.
         *
         * @param name       the name of the state
         * @param population the population of the state
         */
        public State {
            // store the provided name
            // store the provided population
        }

            /**
             * Getter for the state's population.
             *
             * @return the population value
             */
            @Override
            public long population() {
                return population;             // return the stored population
            }

            /**
             * Getter for the state's name.
             *
             * @return the name value
             */
            @Override
            public String name() {
                return name;                   // return the stored name
            }
        }
}