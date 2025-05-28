package com.interview.notes.code.year.y2025.may.amazon.test7;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GenomeMutationSolver {

    public static int findTime(String genome, char mutation) {
        String currentGenome = genome;
        int time = 0;

        while (true) {
            time++;
            String nextGenome = processOneTimeUnit(currentGenome, mutation);

            if (nextGenome.equals(currentGenome)) {
                return time;
            }

            currentGenome = nextGenome;
        }
    }

    private static String processOneTimeUnit(String genome, char mutation) {
        List<Integer> mutationPositions = IntStream.range(0, genome.length())
                .filter(i -> genome.charAt(i) == mutation)
                .boxed()
                .collect(Collectors.toList());

        if (mutationPositions.isEmpty()) {
            return genome;
        }

        Set<Integer> toRemove = mutationPositions.stream()
                .flatMap(pos -> IntStream.range(0, pos).boxed())
                .collect(Collectors.toSet());

        return IntStream.range(0, genome.length())
                .filter(i -> !toRemove.contains(i))
                .mapToObj(i -> String.valueOf(genome.charAt(i)))
                .collect(Collectors.joining());
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
        testCase("Complex Pattern", "abmcdmef", 'm', 3);

        // Test Case 9: Large data test
        testLargeData();

        // Test Case 10: Performance test
        testPerformance();

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

        // Test case with 1000 characters
        String largeGenome1 = Stream.generate(() -> "abcdefg")
                .limit(143)
                .collect(Collectors.joining()) + "m";
        testCase("Large Data 1000", largeGenome1, 'm', 2);

        // Test case with alternating pattern
        String largeGenome2 = IntStream.range(0, 500)
                .mapToObj(i -> i % 2 == 0 ? "a" : "m")
                .collect(Collectors.joining());
        testCase("Large Alternating", largeGenome2, 'm', 250);

        // Test case with many mutations
        String largeGenome3 = "a" + "m".repeat(100) + "b";
        testCase("Many Mutations", largeGenome3, 'm', 101);
    }

    private static void testPerformance() {
        System.out.println("\n--- Performance Test ---");

        String performanceGenome = "a".repeat(1000) + "m" + "b".repeat(1000);
        long startTime = System.currentTimeMillis();
        int result = findTime(performanceGenome, 'm');
        long endTime = System.currentTimeMillis();

        System.out.printf("Performance Test    | Size: 2001 chars | Result: %d | Time: %d ms | %s%n",
                result,
                endTime - startTime,
                endTime - startTime < 1000 ? "PASS" : "FAIL");
    }

    // Helper method to simulate and display the mutation process
    private static void simulateProcess(String genome, char mutation) {
        System.out.println("  📊 Simulation Steps:");
        String current = genome;
        int time = 0;

        while (true) {
            time++;
            String next = processOneTimeUnit(current, mutation);
            System.out.printf("     Time %d: %s -> %s%n", time, current, next);

            if (next.equals(current)) {
                System.out.printf("     Final result at time %d%n", time);
                break;
            }

            current = next;
            if (time > 10) { // Prevent infinite loop in display
                System.out.println("     ... (truncated for display)");
                break;
            }
        }
    }
}