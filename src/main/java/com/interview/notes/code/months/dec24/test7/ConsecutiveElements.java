package com.interview.notes.code.months.dec24.test7;

import java.util.*;

public class ConsecutiveElements {
    public static int solve(List<Integer> ar) {
        if (ar == null || ar.size() <= 2) {
            return 0;
        }
        
        int n = ar.size();
        // dp[i] represents minimum removals needed up to index i
        int[] dp = new int[n];
        Arrays.fill(dp, 0);
        
        for (int i = 2; i < n; i++) {
            // Start with previous minimum removals
            dp[i] = dp[i-1];
            
            // Check if current three elements form increasing or decreasing sequence
            if (isMonotonic(ar, i-2, i-1, i)) {
                // Need to remove at least one element from the last three
                // Try all possibilities and take minimum
                int minRemovals = 1 + Math.min(
                    dp[i-3 >= 0 ? i-3 : 0], // remove current
                    Math.min(
                        dp[i-2], // remove i-1
                        dp[i-2]  // remove i-2
                    )
                );
                dp[i] = minRemovals;
            }
        }
        
        return dp[n-1];
    }
    
    private static boolean isMonotonic(List<Integer> ar, int i, int j, int k) {
        return (ar.get(i) < ar.get(j) && ar.get(j) < ar.get(k)) || 
               (ar.get(i) > ar.get(j) && ar.get(j) > ar.get(k));
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();
        
        // Test case 1: Example from problem
        testCases.add(new TestCase(
            Arrays.asList(1, 2, 4, 1, 2),
            1,
            "Basic example from problem"
        ));
        
        // Test case 2: Another example from problem
        testCases.add(new TestCase(
            Arrays.asList(1, 2, 3, 5),
            2,
            "Second example from problem"
        ));
        
        // Test case 3: Empty array
        testCases.add(new TestCase(
            new ArrayList<>(),
            0,
            "Empty array"
        ));
        
        // Test case 4: Array with two elements
        testCases.add(new TestCase(
            Arrays.asList(1, 2),
            0,
            "Two elements"
        ));
        
        // Test case 5: Strictly decreasing
        testCases.add(new TestCase(
            Arrays.asList(5, 4, 3, 2, 1),
            2,
            "Strictly decreasing sequence"
        ));
        
        // Test case 6: Large input
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < 1000; i++) {
            largeInput.add(rand.nextInt(10000));
        }
        testCases.add(new TestCase(
            largeInput,
            333,
            "Large random input"
        ));

        // Additional test cases
        testCases.add(new TestCase(
            Arrays.asList(1, 3, 2, 4),
            1,
            "Alternating sequence"
        ));
        
        testCases.add(new TestCase(
            Arrays.asList(1, 2, 3, 4, 5),
            2,
            "Strictly increasing sequence"
        ));

        // Run tests
        int passedTests = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long startTime = System.nanoTime();
            int result = solve(tc.input);
            long endTime = System.nanoTime();
            boolean passed = result == tc.expected;
            
            System.out.printf("Test Case %d (%s): %s\n", 
                i + 1, 
                tc.description, 
                passed ? "PASS" : "FAIL"
            );
            
            if (!passed) {
                System.out.println("Input: " + (tc.input.size() > 10 ? 
                    tc.input.subList(0, 10) + "..." : tc.input));
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got: " + result);
            }
            
            if (tc.input.size() > 100) {
                System.out.printf("Execution time: %.3f ms\n", 
                    (endTime - startTime) / 1_000_000.0);
            }
            
            if (passed) passedTests++;
        }
        
        System.out.printf("\nTotal: %d/%d tests passed\n", 
            passedTests, testCases.size());
    }

    static class TestCase {
        List<Integer> input;
        int expected;
        String description;
        
        TestCase(List<Integer> input, int expected, String description) {
            this.input = input;
            this.expected = expected;
            this.description = description;
        }
    }
}
