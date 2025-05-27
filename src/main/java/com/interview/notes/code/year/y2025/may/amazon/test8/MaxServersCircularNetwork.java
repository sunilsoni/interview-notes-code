package com.interview.notes.code.year.y2025.may.amazon.test8;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class MaxServersCircularNetwork {
    
    public static int getMaxServers(List<Integer> powers) {
        if (powers == null || powers.isEmpty()) {
            return 0;
        }
        
        // Count frequency of each power value using Stream API
        Map<Integer, Long> frequencyMap = powers.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        // For each unique power value k, check how many servers we can use
        // with powers k-1, k, and k+1
        return frequencyMap.keySet().stream()
            .mapToInt(k -> {
                long count = frequencyMap.getOrDefault(k, 0L);
                count += frequencyMap.getOrDefault(k - 1, 0L);
                count += frequencyMap.getOrDefault(k + 1, 0L);
                return (int) Math.min(count, Integer.MAX_VALUE);
            })
            .max()
            .orElse(0);
    }
    
    // Helper method to verify if a list can form a valid circle
    private static boolean canFormValidCircle(List<Integer> values) {
        if (values.size() <= 2) return true;
        
        // Sort to make arrangement easier
        List<Integer> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        
        // Check if all values are within range of 3
        int min = sorted.get(0);
        int max = sorted.get(sorted.size() - 1);
        
        return max - min <= 2;
    }
    
    // Generate all subsequences of given size (for small test verification)
    private static boolean hasValidSubsequence(List<Integer> powers, int targetSize) {
        if (targetSize > powers.size()) return false;
        
        // For small sizes, we can verify by checking if any subsequence of targetSize
        // can form a valid circle
        return generateSubsequences(powers, targetSize).stream()
            .anyMatch(MaxServersCircularNetwork::canFormValidCircle);
    }
    
    private static List<List<Integer>> generateSubsequences(List<Integer> powers, int size) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsequencesHelper(powers, 0, new ArrayList<>(), size, result);
        return result;
    }
    
    private static void generateSubsequencesHelper(List<Integer> powers, int start, 
            List<Integer> current, int targetSize, List<List<Integer>> result) {
        if (current.size() == targetSize) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < powers.size() && current.size() + (powers.size() - i) >= targetSize; i++) {
            current.add(powers.get(i));
            generateSubsequencesHelper(powers, i + 1, current, targetSize, result);
            current.remove(current.size() - 1);
        }
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
        
        // Edge cases
        testCases.add(new TestCase(
            Arrays.asList(5), 
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
            Arrays.asList(1, 2, 3, 4, 5), 
            3, 
            "Sequential powers"
        ));
        
        testCases.add(new TestCase(
            Arrays.asList(10, 11, 12, 11, 10, 11), 
            6, 
            "Three consecutive values"
        ));
        
        testCases.add(new TestCase(
            Arrays.asList(100, 101, 102, 103, 104), 
            3, 
            "Wide range"
        ));
        
        testCases.add(new TestCase(
            new ArrayList<>(), 
            0, 
            "Empty list"
        ));
        
        testCases.add(new TestCase(
            Arrays.asList(1000000000, 999999999, 1000000001), 
            3, 
            "Large values"
        ));
        
        // Pattern tests
        testCases.add(new TestCase(
            IntStream.range(0, 100)
                .map(i -> i % 3)
                .boxed()
                .collect(Collectors.toList()), 
            100, 
            "Repeating pattern 0,1,2"
        ));
        
        // Large data test - random values
        Random rand = new Random(42);
        List<Integer> largeRandom = IntStream.range(0, 100000)
            .map(i -> rand.nextInt(1000))
            .boxed()
            .collect(Collectors.toList());
        testCases.add(new TestCase(largeRandom, -1, "Large random data (100k)"));
        
        // Large data test - clustered values
        List<Integer> largeClustered = IntStream.range(0, 100000)
            .map(i -> 500 + (i % 3))
            .boxed()
            .collect(Collectors.toList());
        testCases.add(new TestCase(largeClustered, 100000, "Large clustered data (100k)"));
        
        // Run all test cases
        int passed = 0;
        int total = testCases.size();
        
        System.out.println("=== Max Servers Circular Network Test Suite ===\n");
        
        for (TestCase tc : testCases) {
            long startTime = System.currentTimeMillis();
            int result = getMaxServers(tc.powers);
            long endTime = System.currentTimeMillis();
            
            boolean pass;
            if (tc.expected == -1) {
                // For large random data, just check performance
                pass = (endTime - startTime) < 1000;
                System.out.println("Test: " + tc.name);
                System.out.println("Input size: " + tc.powers.size());
                System.out.println("Result: " + result);
                System.out.println("Time: " + (endTime - startTime) + " ms");
                System.out.println("Status: " + (pass ? "PASS ✓ (Performance)" : "FAIL ✗ (Timeout)"));
            } else {
                pass = result == tc.expected;
                System.out.println("Test: " + tc.name);
                System.out.println("Input: " + 
                    (tc.powers.size() > 10 ? 
                        tc.powers.subList(0, 10) + "... (size=" + tc.powers.size() + ")" : 
                        tc.powers));
                System.out.println("Expected: " + tc.expected + ", Got: " + result);
                System.out.println("Time: " + (endTime - startTime) + " ms");
                System.out.println("Status: " + (pass ? "PASS ✓" : "FAIL ✗"));
                
                // For small failing cases, verify our logic
                if (!pass && tc.powers.size() <= 8) {
                    System.out.println("Frequency analysis:");
                    Map<Integer, Long> freq = tc.powers.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                    freq.forEach((k, v) -> System.out.println("  Power " + k + ": " + v + " servers"));
                }
            }
            
            if (pass) passed++;
            System.out.println("-------------------");
        }
        
        System.out.println("\nSummary: " + passed + "/" + total + " tests passed");
        System.out.println(passed == total ? "All tests PASSED! ✓✓✓" : "Some tests FAILED! ✗✗✗");
        
        // Demonstrate the algorithm with detailed example
        System.out.println("\n=== Algorithm Demonstration ===");
        List<Integer> demo = Arrays.asList(2, 2, 3, 2, 1, 2, 2);
        System.out.println("Input: " + demo);
        
        Map<Integer, Long> demoFreq = demo.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        System.out.println("\nFrequency Map:");
        demoFreq.forEach((k, v) -> System.out.println("  Power " + k + ": " + v + " servers"));
        
        System.out.println("\nChecking each power value k:");
        demoFreq.keySet().stream()
            .sorted()
            .forEach(k -> {
                long count = demoFreq.getOrDefault(k, 0L) +
                            demoFreq.getOrDefault(k - 1, 0L) +
                            demoFreq.getOrDefault(k + 1, 0L);
                System.out.println("  k=" + k + ": Can use powers {" + (k-1) + "," + k + "," + (k+1) + "} = " + count + " servers");
            });
        
        System.out.println("\nMaximum servers: " + getMaxServers(demo));
        System.out.println("Valid arrangement example: [1,2,2,2,2,2,3] forms a circle where all adjacent differences ≤ 1");
    }
    
    static class TestCase {
        final List<Integer> powers;
        final int expected;
        final String name;
        
        TestCase(List<Integer> powers, int expected, String name) {
            this.powers = powers;
            this.expected = expected;
            this.name = name;
        }
    }
}