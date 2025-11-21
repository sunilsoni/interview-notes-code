package com.interview.notes.code.year.y2025.may.amazon.test9;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaxServersCircularNetwork {

    public static int getMaxServers(List<Integer> powers) {
        if (powers == null || powers.isEmpty()) {
            return 0;
        }

        // Special case: if all values are the same
        Set<Integer> uniqueValues = new HashSet<>(powers);
        if (uniqueValues.size() == 1) {
            return powers.size();
        }

        // Count frequency of each power value
        Map<Integer, Long> frequencyMap = powers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Find the maximum count for any window of 3 consecutive values
        int maxServers = 0;

        // Get all unique power values and sort them
        List<Integer> sortedPowers = frequencyMap.keySet().stream()
                .sorted()
                .collect(Collectors.toList());

        // Check each possible center value k
        for (Integer k : sortedPowers) {
            long count = 0;

            // Count servers with power k-1, k, and k+1
            count += frequencyMap.getOrDefault(k - 1, 0L);
            count += frequencyMap.getOrDefault(k, 0L);
            count += frequencyMap.getOrDefault(k + 1, 0L);

            maxServers = Math.max(maxServers, (int) count);
        }

        // Also check windows starting at each unique value
        for (int i = 0; i < sortedPowers.size(); i++) {
            int start = sortedPowers.get(i);
            long count = frequencyMap.getOrDefault(start, 0L);

            // Add next value if it's exactly start+1
            if (i + 1 < sortedPowers.size() && sortedPowers.get(i + 1) == start + 1) {
                count += frequencyMap.get(sortedPowers.get(i + 1));

                // Add third value if it's exactly start+2
                if (i + 2 < sortedPowers.size() && sortedPowers.get(i + 2) == start + 2) {
                    count += frequencyMap.get(sortedPowers.get(i + 2));
                }
            }

            maxServers = Math.max(maxServers, (int) count);
        }

        return maxServers;
    }

    // Alternative approach using sliding window
    public static int getMaxServersAlternative(List<Integer> powers) {
        if (powers == null || powers.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Count frequencies and find range
        for (Integer power : powers) {
            freq.put(power, freq.getOrDefault(power, 0) + 1);
            min = Math.min(min, power);
            max = Math.max(max, power);
        }

        int maxServers = 0;

        // Slide a window of size 3 across all possible values
        for (int k = min; k <= max; k++) {
            int count = 0;
            count += freq.getOrDefault(k - 1, 0);
            count += freq.getOrDefault(k, 0);
            count += freq.getOrDefault(k + 1, 0);
            maxServers = Math.max(maxServers, count);
        }

        return maxServers;
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample case from problem
        testCases.add(new TestCase(
                Arrays.asList(2, 2, 3, 2, 1, 2, 2),
                7,
                "Sample Case"
        ));

        // The failing test case
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5),
                3,
                "Sequential 1-5"
        ));

        // Edge cases
        testCases.add(new TestCase(
                List.of(5),
                1,
                "Single server"
        ));

        testCases.add(new TestCase(
                Arrays.asList(1, 1, 1, 1),
                4,
                "All same power"
        ));

        testCases.add(new TestCase(
                Arrays.asList(1, 3, 5, 7, 9),
                1,
                "No adjacent values"
        ));

        testCases.add(new TestCase(
                Arrays.asList(10, 11, 12, 11, 10, 11),
                6,
                "Three consecutive values"
        ));

        testCases.add(new TestCase(
                Arrays.asList(100, 101, 102, 103, 104),
                3,
                "Sequential 100-104"
        ));

        testCases.add(new TestCase(
                Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5),
                12,
                "Multiple occurrences"
        ));

        // Test with gaps
        testCases.add(new TestCase(
                Arrays.asList(1, 1, 1, 5, 5, 5, 9, 9, 9),
                3,
                "Groups with gaps"
        ));

        // Large range test
        List<Integer> largeRange = new ArrayList<>();
        for (int i = 500000000; i <= 500000499; i++) {
            largeRange.add(i);
        }
        testCases.add(new TestCase(largeRange, 3, "Large range sequential"));

        // Run all test cases
        int passed = 0;
        int total = testCases.size();

        System.out.println("=== Max Servers Circular Network Test Suite ===\n");

        for (TestCase tc : testCases) {
            long startTime = System.currentTimeMillis();
            int result = getMaxServers(tc.powers);
            int resultAlt = getMaxServersAlternative(tc.powers);
            long endTime = System.currentTimeMillis();

            boolean pass = result == tc.expected;
            boolean passAlt = resultAlt == tc.expected;

            System.out.println("Test: " + tc.name);
            System.out.println("Input: " +
                    (tc.powers.size() > 15 ?
                            tc.powers.subList(0, 10) + "... (size=" + tc.powers.size() + ")" :
                            tc.powers));
            System.out.println("Expected: " + tc.expected);
            System.out.println("Got (main): " + result + " | Got (alt): " + resultAlt);
            System.out.println("Status: " + (pass ? "PASS ✓" : "FAIL ✗") +
                    " | Alt: " + (passAlt ? "PASS ✓" : "FAIL ✗"));
            System.out.println("Time: " + (endTime - startTime) + " ms");

            if (!pass) {
                // Debug info
                Map<Integer, Long> freq = tc.powers.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                System.out.println("Debug - Frequency map (first 10):");
                freq.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .limit(10)
                        .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue()));

                if (freq.size() > 10) {
                    System.out.println("  ... (" + (freq.size() - 10) + " more)");
                }

                // Find the best window
                int bestK = -1;
                int bestCount = 0;
                for (Integer k : freq.keySet()) {
                    int count = freq.getOrDefault(k - 1, 0L).intValue() +
                            freq.getOrDefault(k, 0L).intValue() +
                            freq.getOrDefault(k + 1, 0L).intValue();
                    if (count > bestCount) {
                        bestCount = count;
                        bestK = k;
                    }
                }
                System.out.println("Best window center: " + bestK + " with count: " + bestCount);
            }

            if (pass) passed++;
            System.out.println("-------------------");
        }

        System.out.println("\nSummary: " + passed + "/" + total + " tests passed");

        // Test the specific failing case with detailed output
        System.out.println("\n=== Debugging Specific Case ===");
        List<Integer> debugCase = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Input: " + debugCase);

        Map<Integer, Long> freq = debugCase.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Frequencies: " + freq);
        System.out.println("\nChecking each window:");
        for (int k = 0; k <= 6; k++) {
            long count = freq.getOrDefault(k - 1, 0L) +
                    freq.getOrDefault(k, 0L) +
                    freq.getOrDefault(k + 1, 0L);
            if (count > 0) {
                System.out.println("Window [" + (k - 1) + "," + k + "," + (k + 1) + "]: " + count + " servers");
            }
        }

        System.out.println("\nResult: " + getMaxServers(debugCase));
    }

    record TestCase(List<Integer> powers, int expected, String name) {
    }
}