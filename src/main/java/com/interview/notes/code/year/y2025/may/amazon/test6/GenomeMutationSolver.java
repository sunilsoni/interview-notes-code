package com.interview.notes.code.year.y2025.may.amazon.test6;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenomeMutationSolver {

    public static int findTime(String genome, char mutation) {
        StringBuilder current = new StringBuilder(genome);
        int time = 0;

        while (true) {
            time++;
            boolean removed = processOneTimeUnit(current, mutation);

            if (!removed) {
                return time;
            }
        }
    }

    private static boolean processOneTimeUnit(StringBuilder genome, char mutation) {
        // Find the rightmost mutation that has something to its left
        for (int i = genome.length() - 1; i >= 0; i--) {
            if (genome.charAt(i) == mutation && i > 0) {
                // Remove one nucleotide to the left
                genome.deleteCharAt(i - 1);
                return true;
            }
        }
        return false;
    }

    // Alternative approach - remove from left to right
    private static boolean processOneTimeUnitLeftToRight(StringBuilder genome, char mutation) {
        // Find the leftmost mutation that has something to its left
        for (int i = 1; i < genome.length(); i++) {
            if (genome.charAt(i) == mutation) {
                // Remove one nucleotide to the left
                genome.deleteCharAt(i - 1);
                return true;
            }
        }
        return false;
    }

    // Test method with comprehensive test cases
    public static void main(String[] args) {
        System.out.println("=== Genome Mutation Solver Test Cases ===\n");

        // Test Case 1: Sample Case 1
        testCase("Sample Case 1", "momoz", 'm', 2);

        // Test Case 2: Sample Case 0  
        testCase("Sample Case 0", "tamem", 'm', 2);

        // Test Case 3: Edge case - single character
        testCase("Single Character", "a", 'a', 1);

        // Test Case 4: No mutation present
        testCase("No Mutation", "abcde", 'x', 1);

        // Test Case 5: All mutations
        testCase("All Mutations", "mmmmm", 'm', 5);

        // Test Case 6: Mutation at start
        testCase("Mutation at Start", "mabc", 'm', 1);

        // Test Case 7: Mutation at end
        testCase("Mutation at End", "abcm", 'm', 2);

        // Test Case 8: Complex pattern
        testCase("Complex Pattern", "abmcdmef", 'm', 6);

        // Test Case 9: Simple alternating
        testCase("Simple Alternating", "amam", 'm', 3);

        // Test Case 10: Multiple mutations
        testCase("Multiple Mutations", "abmcmde", 'm', 4);

        // Test Case 11: Large data test
        testLargeData();

        System.out.println("\n=== All Test Cases Completed ===");
    }

    private static void testCase(String testName, String genome, char mutation, int expected) {
        try {
            long startTime = System.nanoTime();
            int result = findTime(genome, mutation);
            long endTime = System.nanoTime();

            boolean passed = result == expected;
            String status = passed ? "PASS" : "FAIL";

            System.out.printf("%-20s | Input: %-15s | Mutation: %c | Expected: %d | Got: %d | %s | Time: %.3f ms%n",
                    testName,
                    genome.length() > 12 ? genome.substring(0, 12) + "..." : genome,
                    mutation,
                    expected,
                    result,
                    status,
                    (endTime - startTime) / 1_000_000.0);

            if (!passed) {
                System.out.println("  ❌ FAILURE DETAILS:");
                System.out.println("     Input genome: " + genome);
                System.out.println("     Mutation: " + mutation);
                System.out.println("     Expected: " + expected + ", Got: " + result);
                simulateProcess(genome, mutation);
            }

        } catch (Exception e) {
            System.out.printf("%-20s | ERROR: %s%n", testName, e.getMessage());
        }
    }

    private static void testLargeData() {
        System.out.println("\n--- Large Data Test Cases ---");

        // Test case with pattern that requires many steps
        String largeGenome1 = "a".repeat(100) + "m";
        testCase("Large Sequential", largeGenome1, 'm', 101);

        // Test case with alternating pattern  
        String largeGenome2 = IntStream.range(0, 20)
                .mapToObj(i -> "am")
                .collect(Collectors.joining());
        testCase("Alternating 40", largeGenome2, 'm', 30);

        // Performance test
        String performanceGenome = "a".repeat(500) + "m";
        long startTime = System.currentTimeMillis();
        int result = findTime(performanceGenome, 'm');
        long endTime = System.currentTimeMillis();

        System.out.printf("Performance Test    | Size: 501 chars | Result: %d | Time: %d ms | %s%n",
                result,
                endTime - startTime,
                endTime - startTime < 1000 ? "PASS" : "FAIL");
    }

    // Helper method to simulate and display the mutation process
    private static void simulateProcess(String genome, char mutation) {
        System.out.println("  📊 Simulation Steps:");
        StringBuilder current = new StringBuilder(genome);
        int time = 0;

        while (time < 15) { // Limit to prevent excessive output
            time++;
            boolean removed = processOneTimeUnit(current, mutation);
            System.out.printf("     Time %d: %s%n", time, current.toString());

            if (!removed) {
                System.out.printf("     No removal at time %d - Final result%n", time);
                break;
            }
        }

        if (time >= 15) {
            System.out.println("     ... (simulation truncated for display)");
        }
    }
}