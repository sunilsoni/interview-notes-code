package com.interview.notes.code.year.y2024.dec24.amazon.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Sample Input 1
        testCases.add(new TestCase(
                Arrays.asList(3, 6, 10, 15, 20),
                Arrays.asList(
                        Arrays.asList(2, 4)
                ),
                Arrays.asList(8L)
        ));

        // Test Case 2: Sample Input 0
        testCases.add(new TestCase(
                Arrays.asList(0, 2, 5, 9, 12, 18),
                Arrays.asList(
                        Arrays.asList(2, 5),
                        Arrays.asList(1, 3)
                ),
                Arrays.asList(12L, 18L)
        ));

        // Test Case 3: All warehouses connected to first hub
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(
                        Arrays.asList(0, 4)
                ),
                Arrays.asList(10L)
        ));

        // Test Case 4: All warehouses connected to second hub
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(
                        Arrays.asList(0, 4)
                ),
                Arrays.asList(10L)
        ));

        // Test Case 5: Edge case with minimum n and q
        testCases.add(new TestCase(
                Arrays.asList(0, 0, 0),
                Arrays.asList(
                        Arrays.asList(0, 2)
                ),
                Arrays.asList(0L)
        ));

        // Test Case 6: Large Input
        int n = 250000;
        List<Integer> largeWarehouse = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            largeWarehouse.add(i);
        }
        List<List<Integer>> largeQueries = new ArrayList<>();
        List<Long> largeExpected = new ArrayList<>();
        // Add a single query
        largeQueries.add(Arrays.asList(0, n - 1));
        // Compute expected for large test case
        long expectedSum = 0;
        for (int i = 0; i < n; i++) {
            expectedSum += Math.abs(largeWarehouse.get(i) - largeWarehouse.get(0));
        }
        largeExpected.add(expectedSum);
        testCases.add(new TestCase(largeWarehouse, largeQueries, largeExpected));

        // Execute test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Long> result = getMinConnectionCost(tc.warehouseCapacity, tc.additionalHubs);
            if (result.equals(tc.expectedOutput)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expectedOutput);
                System.out.println("Got     : " + result);
            }
        }

        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    public static List<Long> getMinConnectionCost(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs) {
        int n = warehouseCapacity.size();
        // Precompute prefix sums
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + warehouseCapacity.get(i);
        }

        List<Long> results = new ArrayList<>();
        for (List<Integer> hub : additionalHubs) {
            int h1 = hub.get(0);
            int h2 = hub.get(1);
            if (h1 > h2) {
                int temp = h1;
                h1 = h2;
                h2 = temp;
            }
            int cH1 = warehouseCapacity.get(h1);
            int cH2 = warehouseCapacity.get(h2);
            // Calculate midpoint
            double m = (cH1 + (double) cH2) / 2.0;
            // Binary search to find the last index where c[i] <= m
            int k = upperBound(warehouseCapacity, m) - 1;
            // Ensure k is within bounds
            k = Math.max(k, -1);
            k = Math.min(k, n - 1);

            // Sum for warehouses <= k: (k+1)*cH1 - prefixSum[k+1]
            long sum1 = ((long) (k + 1) * cH1) - prefixSum[k + 1];
            // Sum for warehouses > k: prefixSum[n] - prefixSum[k+1] - (n - (k+1)) * cH2
            long sum2 = prefixSum[n] - prefixSum[k + 1] - ((long) (n - (k + 1)) * cH2);
            long total = sum1 + sum2;
            results.add(total);
        }

        return results;
    }

    // Helper method to find the first index where warehouseCapacity.get(index) > m
    private static int upperBound(List<Integer> warehouseCapacity, double m) {
        int left = 0;
        int right = warehouseCapacity.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (warehouseCapacity.get(mid) > m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // Test case class
    static class TestCase {
        List<Integer> warehouseCapacity;
        List<List<Integer>> additionalHubs;
        List<Long> expectedOutput;

        TestCase(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs, List<Long> expectedOutput) {
            this.warehouseCapacity = warehouseCapacity;
            this.additionalHubs = additionalHubs;
            this.expectedOutput = expectedOutput;
        }
    }
}
