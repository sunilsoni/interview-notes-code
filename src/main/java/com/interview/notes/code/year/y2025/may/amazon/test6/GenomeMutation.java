package com.interview.notes.code.year.y2025.may.amazon.test6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenomeMutation {

    public static int findTime(String genome, char mutation) {
        if (genome == null || genome.isEmpty()) {
            return 0;
        }

        String current = genome;
        int time = 0;

        while (true) {
            // Find all positions where mutations can remove left neighbors
            List<Integer> removalPositions = new ArrayList<>();

            for (int i = 1; i < current.length(); i++) {
                if (current.charAt(i) == mutation) {
                    // This mutation can remove its left neighbor
                    removalPositions.add(i - 1);
                }
            }

            // If no removals possible, we're done
            if (removalPositions.isEmpty()) {
                break;
            }

            // Build new genome after removals
            StringBuilder newGenome = new StringBuilder();
            Set<Integer> toRemove = new HashSet<>(removalPositions);

            for (int i = 0; i < current.length(); i++) {
                if (!toRemove.contains(i)) {
                    newGenome.append(current.charAt(i));
                }
            }

            current = newGenome.toString();
            time++;

            // If genome becomes empty or has only one character, stop
            if (current.length() <= 1) {
                break;
            }
        }

        return time;
    }

    // Helper method to visualize genome evolution (for debugging)
    private static void visualizeEvolution(String genome, char mutation) {
        System.out.println("Initial: " + genome);
        String current = genome;
        int time = 0;

        while (true) {
            List<Integer> removalPositions = new ArrayList<>();

            for (int i = 1; i < current.length(); i++) {
                if (current.charAt(i) == mutation) {
                    removalPositions.add(i - 1);
                }
            }

            if (removalPositions.isEmpty()) {
                break;
            }

            StringBuilder newGenome = new StringBuilder();
            Set<Integer> toRemove = new HashSet<>(removalPositions);

            for (int i = 0; i < current.length(); i++) {
                if (!toRemove.contains(i)) {
                    newGenome.append(current.charAt(i));
                }
            }

            current = newGenome.toString();
            time++;
            System.out.println("Time " + time + ": " + current);

            if (current.length() <= 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample cases from problem
        testCases.add(new TestCase("tamem", 'm', 2, "Sample 0"));
        testCases.add(new TestCase("momoz", 'm', 2, "Sample 1"));

        // Visual example from problem
        testCases.add(new TestCase("luvzliz", 'z', 3, "Visual Example"));

        // Edge cases
        testCases.add(new TestCase("", 'a', 0, "Empty string"));
        testCases.add(new TestCase("a", 'a', 0, "Single character"));
        testCases.add(new TestCase("abc", 'd', 0, "No mutations"));
        testCases.add(new TestCase("aaaa", 'a', 3, "All mutations"));
        testCases.add(new TestCase("abcba", 'a', 1, "Mutations at ends"));
        testCases.add(new TestCase("xmxmxm", 'm', 3, "Alternating pattern"));
        testCases.add(new TestCase("mmmmm", 'm', 4, "All same mutation"));

        // Complex cases
        testCases.add(new TestCase("abcdefghijklmnopqrstuvwxyz", 'z', 1, "Alphabet"));
        testCases.add(new TestCase("zzzzabcdefghijklmnopqrstuvwxyz", 'z', 4, "Mutations at start"));

        // Large data test
        StringBuilder largeGenome = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            largeGenome.append(i % 3 == 0 ? 'm' : (char) ('a' + (i % 26)));
        }
        // For large data, we calculate expected result differently
        testCases.add(new TestCase(largeGenome.toString(), 'm', -1, "Large data (50k)"));

        // Run all test cases
        int passed = 0;
        int total = 0;

        System.out.println("Running genome mutation test cases...\n");

        for (TestCase tc : testCases) {
            if (tc.expected == -1) {
                // For large data, just verify it completes in reasonable time
                long startTime = System.currentTimeMillis();
                int result = findTime(tc.genome, tc.mutation);
                long endTime = System.currentTimeMillis();

                System.out.println("Test: " + tc.name);
                System.out.println("Genome length: " + tc.genome.length());
                System.out.println("Result: " + result);
                System.out.println("Time: " + (endTime - startTime) + " ms");
                System.out.println("Status: " + (endTime - startTime < 5000 ? "PASS ✓" : "TIMEOUT ✗"));
                System.out.println("-------------------");

                if (endTime - startTime < 5000) passed++;
                total++;
                continue;
            }

            total++;
            long startTime = System.currentTimeMillis();
            int result = findTime(tc.genome, tc.mutation);
            long endTime = System.currentTimeMillis();

            boolean pass = result == tc.expected;
            if (pass) passed++;

            System.out.println("Test: " + tc.name);
            System.out.println("Genome: " + (tc.genome.length() > 30 ?
                    tc.genome.substring(0, 30) + "..." : tc.genome));
            System.out.println("Mutation: '" + tc.mutation + "'");
            System.out.println("Expected: " + tc.expected + ", Got: " + result);
            System.out.println("Status: " + (pass ? "PASS ✓" : "FAIL ✗"));
            System.out.println("Time: " + (endTime - startTime) + " ms");

            // Show evolution for failed cases (if genome is small)
            if (!pass && tc.genome.length() <= 20) {
                System.out.println("\nEvolution trace:");
                visualizeEvolution(tc.genome, tc.mutation);
            }

            System.out.println("-------------------");
        }

        System.out.println("\nSummary: " + passed + "/" + total + " tests passed");
        System.out.println(passed == total ? "All tests PASSED! ✓" : "Some tests FAILED! ✗");

        // Demonstrate visual evolution for a few examples
        System.out.println("\n=== Visual Evolution Examples ===");
        System.out.println("\nExample 1: 'tamem' with mutation 'm'");
        visualizeEvolution("tamem", 'm');

        System.out.println("\nExample 2: 'luvzliz' with mutation 'z'");
        visualizeEvolution("luvzliz", 'z');
    }

    static class TestCase {
        String genome;
        char mutation;
        int expected;
        String name;

        TestCase(String genome, char mutation, int expected, String name) {
            this.genome = genome;
            this.mutation = mutation;
            this.expected = expected;
            this.name = name;
        }
    }
}