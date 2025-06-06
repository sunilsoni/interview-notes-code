package com.interview.notes.code.year.y2025.may.oci.test3;

import java.util.*;

public class OptimizedArrayCompressor {

    /**
     * Optimized version using single pass and stack-based approach
     */
    public static int getMinLengthOptimized(List<Integer> a, int k) {
        // Handle edge cases
        if (a == null || a.isEmpty()) return 0;
        if (a.size() == 1) return 1;

        // Use Deque as a stack for better performance
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(a.get(0));

        // Single pass through the array
        for (int i = 1; i < a.size(); i++) {
            int current = a.get(i);

            // Try to merge with previous element while possible
            while (!stack.isEmpty()) {
                long product = (long) stack.peek() * current;
                if (product <= k) {
                    stack.pop();  // Remove previous
                    current = (int) product;  // Update current with merged value
                } else {
                    break;
                }
            }
            stack.push(current);
        }

        return stack.size();
    }

    /**
     * Original version for comparison
     */
    public static int getMinLengthOriginal(List<Integer> a, int k) {
        List<Integer> arr = new ArrayList<>(a);
        boolean merged;
        do {
            merged = false;
            for (int i = 0; i < arr.size() - 1; i++) {
                long product = (long) arr.get(i) * arr.get(i + 1);
                if (product <= k) {
                    arr.set(i, (int) product);
                    arr.remove(i + 1);
                    merged = true;
                    break;
                }
            }
        } while (merged);
        return arr.size();
    }

    public static void main(String[] args) {
        // Performance test with large input
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(rand.nextInt(100) + 1);
        }

        // Test cases
        runTests();

        // Performance comparison
        comparePerformance(largeInput);
    }

    private static void runTests() {
        System.out.println("Running functional tests...");

        // Test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase(Arrays.asList(1, 2, 1, 3, 6, 1), 6, 2),
                new TestCase(Arrays.asList(2, 3, 3, 7, 3, 5), 20, 3),
                new TestCase(Arrays.asList(1, 3, 2, 5, 4), 6, 3),
                new TestCase(Arrays.asList(5), 10, 1),
                new TestCase(Arrays.asList(10, 20, 30), 5, 3),
                new TestCase(Arrays.asList(999999999, 999999999), 1000000000, 2)
        );

        for (TestCase test : testCases) {
            int optimizedResult = getMinLengthOptimized(test.input, test.k);
            int originalResult = getMinLengthOriginal(test.input, test.k);

            System.out.printf("Input: %s, k=%d\n", test.input, test.k);
            System.out.printf("Expected: %d, Optimized: %d, Original: %d\n",
                    test.expected, optimizedResult, originalResult);
            System.out.println("Status: " +
                    (optimizedResult == test.expected ? "PASS" : "FAIL"));
            System.out.println("------------------------");
        }
    }

    private static void comparePerformance(List<Integer> largeInput) {
        System.out.println("\nRunning performance comparison...");

        // Test optimized version
        long startTime = System.currentTimeMillis();
        int optimizedResult = getMinLengthOptimized(largeInput, 1000);
        long optimizedTime = System.currentTimeMillis() - startTime;

        // Test original version
        startTime = System.currentTimeMillis();
        int originalResult = getMinLengthOriginal(largeInput, 1000);
        long originalTime = System.currentTimeMillis() - startTime;

        System.out.println("Large input size: " + largeInput.size());
        System.out.println("Optimized version time: " + optimizedTime + "ms");
        System.out.println("Original version time: " + originalTime + "ms");
        System.out.println("Speedup: " +
                String.format("%.2fx", (double) originalTime / optimizedTime));
        System.out.println("Results match: " + (optimizedResult == originalResult));
    }

    static class TestCase {
        List<Integer> input;
        int k;
        int expected;

        TestCase(List<Integer> input, int k, int expected) {
            this.input = input;
            this.k = k;
            this.expected = expected;
        }
    }
}
