package com.interview.notes.code.year.y2025.july.oracle.test6;// Import the utility package for collections (List, ArrayList, etc.)
import java.util.*;
// Import the Stream API for functional-style operations on collections
import java.util.stream.*;

public class PopulationCalculator {

    // ──────────────────────────────────────────────────────────────────────────────
    // Nested class representing a US State and its population
    // ──────────────────────────────────────────────────────────────────────────────
    public static class State {
        // The name of the state (e.g. "California")
        private final String name;
        // The population of the state as a long integer
        private final long population;

        /**
         * Constructor for State.
         * @param name       the name of the state
         * @param population the population count for the state
         */
        public State(String name, long population) {
            // Assign the provided name to this.name
            this.name = name;
            // Assign the provided population to this.population
            this.population = population;
        }

        /**
         * Getter for population.
         * @return the population of this state
         */
        public long getPopulation() {
            // Return the stored population value
            return population;
        }

        /**
         * Getter for name.
         * @return the name of this state
         */
        public String getName() {
            // Return the stored name value
            return name;
        }
    }

    // ──────────────────────────────────────────────────────────────────────────────
    // Method to compute total population across a list of states, in parallel
    // ──────────────────────────────────────────────────────────────────────────────
    /**
     * Calculates the sum of populations of all states in the given list,
     * using multiple CPU cores via parallel streams.
     *
     * @param states list of State objects
     * @return total population as long
     */
    public static long totalPopulation(List<State> states) {
        // states.parallelStream()
        //   → creates a parallel stream from the list, splitting it across threads
        // .mapToLong(State::getPopulation)
        //   → transforms each State into its primitive long population
        // .sum()
        //   → aggregates all those long values into a single sum
        return states
            .parallelStream()          // 1) start a parallel stream over the list
            .mapToLong(State::getPopulation)  // 2) extract each state's population
            .sum();                    // 3) sum them all into one long result
    }

    // ──────────────────────────────────────────────────────────────────────────────
    // Main method to run example tests and print PASS/FAIL
    // ──────────────────────────────────────────────────────────────────────────────
    /**
     * Entry point of the program. Builds test data, invokes totalPopulation(),
     * and prints whether each test passes or fails.
     */
    public static void main(String[] args) {
        // ─── Test 1: Small dataset ───────────────────────────────────────────────
        // Create an immutable list of three small states for a quick sanity check
        List<State> small = List.of(
            new State("A", 10_000),    // State A with population 10,000
            new State("B", 20_000),    // State B with population 20,000
            new State("C", 30_000)     // State C with population 30,000
        );
        // Expected sum for these three: 10k + 20k + 30k = 60,000
        long expectedSmall = 60_000;
        // Invoke our parallel-sum method
        long resultSmall = totalPopulation(small);
        // Print formatted result and check PASS/FAIL
        System.out.printf(
            "Test 1 (small): expected=%d, got=%d → %s%n",
            expectedSmall,                // placeholder 1
            resultSmall,                  // placeholder 2
            resultSmall == expectedSmall // evaluate to "PASS" or "FAIL"
                ? "PASS"
                : "FAIL"
        );

        // ─── Test 2: Large dataset ───────────────────────────────────────────────
        // Build a small “template” list of 5 states (simulating 50 in real use)
        List<State> template = List.of(
            new State("State1", 1_000_000),
            new State("State2", 2_000_000),
            new State("State3", 3_000_000),
            new State("State4", 4_000_000),
            new State("State5", 5_000_000)
            // … you could add up to 50 real states here
        );
        // Number of times to replicate the template to simulate big data
        int replicas = 100_000;  // 5 states × 100k = 500,000 total entries
        // Pre-size the ArrayList for performance (avoids repeated resizing)
        List<State> large = new ArrayList<>(template.size() * replicas);
        // Fill 'large' by repeating the template list
        for (int i = 0; i < replicas; i++) {
            large.addAll(template);
        }
        // Compute sum of one template copy
        long singleSum = totalPopulation(template);
        // Expected total = singleSum × number of replicas
        long expectedLarge = singleSum * replicas;
        // Compute actual total on the big list
        long resultLarge = totalPopulation(large);
        // Print formatted result and check PASS/FAIL
        System.out.printf(
            "Test 2 (large): templateSum=%d, replicas=%d → expected=%d, got=%d → %s%n",
            singleSum,                   // placeholder 1
            replicas,                    // placeholder 2
            expectedLarge,               // placeholder 3
            resultLarge,                 // placeholder 4
            resultLarge == expectedLarge // evaluate to "PASS" or "FAIL"
                ? "PASS"
                : "FAIL"
        );
    }
}