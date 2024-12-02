package com.interview.notes.code.year.y2024.nov24.test7;

import java.util.*;

public class ATMQueueSolution {
    public static List<Integer> getFinalOrder(int k, List<Integer> amount) {
        Queue<Pair> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        // Initialize queue with person index (1-based) and amount
        for (int i = 0; i < amount.size(); i++) {
            queue.offer(new Pair(i + 1, amount.get(i)));
        }

        // Process queue until empty
        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            if (current.amount <= k) {
                // Person can withdraw all remaining amount
                result.add(current.index);
            } else {
                // Person withdraws k and goes back to queue
                current.amount -= k;
                queue.offer(current);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase(1, "Basic Test Case",
                2, Arrays.asList(1, 3, 2),
                Arrays.asList(1, 3, 2));

        runTestCase(2, "Sample Test Case",
                2, Arrays.asList(6, 1, 2, 3, 2, 7),
                Arrays.asList(2, 3, 5, 4, 1, 6));

        runTestCase(3, "Edge Case - Single Person",
                5, Arrays.asList(3),
                Arrays.asList(1));

        runTestCase(4, "Large Amounts Test",
                1000000, Arrays.asList(999999999, 999999998, 999999997),
                Arrays.asList(3, 2, 1));

        // Large data test case
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(1000000);
        }
        List<Integer> expectedLargeOutput = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            expectedLargeOutput.add(i);
        }
        runTestCase(5, "Large Input Test (100000 elements)",
                1000000, largeInput,
                expectedLargeOutput);
    }

    private static void runTestCase(int testNumber, String description,
                                    int k, List<Integer> amount,
                                    List<Integer> expected) {
        try {
            long startTime = System.currentTimeMillis();
            List<Integer> result = getFinalOrder(k, amount);
            long endTime = System.currentTimeMillis();

            boolean passed = result.equals(expected);

            System.out.println("Test Case " + testNumber + ": " + description);
            System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("Expected: " + expected);
                System.out.println("Got: " + result);
            }
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            System.out.println("--------------------");
        } catch (Exception e) {
            System.out.println("Test Case " + testNumber + " failed with exception:");
            e.printStackTrace();
            System.out.println("--------------------");
        }
    }

    // Helper class to store person index and remaining amount
    static class Pair {
        int index;
        int amount;

        Pair(int index, int amount) {
            this.index = index;
            this.amount = amount;
        }
    }
}