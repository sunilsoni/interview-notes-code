package com.interview.notes.code.year.y2025.march.amazon.test11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinConnectionCost {

    // Precompute prefix sum for warehouseCapacity array (1-indexed based)
    private static long[] buildPrefixSum(List<Integer> warehouseCapacity) {
        int n = warehouseCapacity.size();
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + warehouseCapacity.get(i - 1);
        }
        return prefixSum;
    }

    public static List<Long> getMinConnectionCost(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs) {
        int n = warehouseCapacity.size();
        long[] prefixSum = buildPrefixSum(warehouseCapacity);
        List<Long> results = new ArrayList<>();

        for (List<Integer> query : additionalHubs) {
            // Get the additional hubs (1-indexed). They are guaranteed: 1 <= hubA < hubB < n.
            int hubA = query.get(0);
            int hubB = query.get(1);

            // Create sorted list of hub positions including the central hub (n)
            List<Integer> hubs = Arrays.asList(hubA, hubB, n);
            Collections.sort(hubs);

            long totalCost = 0;
            int prevHub = 0; // last hub index; 0 means no hub before, use 1-indexing.
            for (int hub : hubs) {
                // Warehouses from (prevHub+1) to hub-1 (both inclusive) will connect to this hub.
                if (hub - 1 >= prevHub + 1) {
                    int l = prevHub + 1;
                    int r = hub - 1;
                    int count = r - l + 1;
                    long sumSegment = prefixSum[r] - prefixSum[l - 1];
                    long cost = (long) count * warehouseCapacity.get(hub - 1) - sumSegment;
                    totalCost += cost;
                }
                // Current hub cost is 0 as it's a hub.
                prevHub = hub;
            }
            results.add(totalCost);
        }
        return results;
    }

    // Simple main method for testing the solution
    public static void main(String[] args) {
        // Define test cases as a list of objects where each test case is a map of input and expected output.
        class TestCase {
            List<Integer> warehouseCapacity;
            List<List<Integer>> additionalHubs;
            List<Long> expected;
            String name;

            TestCase(String name, List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs, List<Long> expected) {
                this.name = name;
                this.warehouseCapacity = warehouseCapacity;
                this.additionalHubs = additionalHubs;
                this.expected = expected;
            }
        }

        List<TestCase> testCases = new ArrayList<>();

        // Provided example test cases
        testCases.add(new TestCase("Example 1",
                Arrays.asList(3, 6, 10, 15, 20),
                Arrays.asList(Arrays.asList(2, 4)),
                Arrays.asList(8L)));

        testCases.add(new TestCase("Sample Case 1",
                Arrays.asList(2, 6, 8, 14),
                Arrays.asList(Arrays.asList(1, 2)),
                Arrays.asList(6L)));

        testCases.add(new TestCase("Sample Case 2 - Query 1",
                Arrays.asList(0, 2, 5, 9, 12, 18),
                Arrays.asList(Arrays.asList(2, 5)),
                Arrays.asList(12L)));

        testCases.add(new TestCase("Sample Case 2 - Query 2",
                Arrays.asList(0, 2, 5, 9, 12, 18),
                Arrays.asList(Arrays.asList(1, 3)),
                Arrays.asList(18L)));

        // Combined multi-query test case for Sample Case 2
        testCases.add(new TestCase("Sample Case 2 - Combined",
                Arrays.asList(0, 2, 5, 9, 12, 18),
                Arrays.asList(Arrays.asList(2, 5), Arrays.asList(1, 3)),
                Arrays.asList(12L, 18L)));

        // Edge test: All warehouses are hubs (first two are hubs, and last is always hub)
        testCases.add(new TestCase("Edge: All Warehouses Hubs",
                Arrays.asList(5, 5, 5, 5, 5),
                Arrays.asList(Arrays.asList(1, 3)),
                Arrays.asList(0L)));

        // Large input test
        int n = 250000;
        List<Integer> largeWarehouse = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            largeWarehouse.add(i); // non-decreasing capacities from 0 to n-1
        }
        // Choose hubs: one near the beginning and one in the middle
        List<List<Integer>> largeQuery = Arrays.asList(Arrays.asList(2, n / 2));
        // To calculate expected cost for large test:
        // Hubs: at positions 2, n/2, n.
        // Segment1: [1, 1] cost = (1)*warehouseCapacity[1] - warehouseCapacity[0] = 1*1 - 0 = 1.
        // Segment2: from 3 to (n/2 - 1)
        //    count = n/2 - 3 + 1 = n/2 - 2.
        //    cost = count * warehouseCapacity[n/2 - 1] - (sum of capacities from index 3 to n/2 - 1)
        // Segment3: from (n/2 + 1) to n-1.
        // For simplicity, we will not pre-calc expected value; just ensure our code runs within time.
        testCases.add(new TestCase("Large Input Test",
                largeWarehouse,
                largeQuery,
                null));

        boolean allPass = true;
        for (TestCase tc : testCases) {
            List<Long> result = getMinConnectionCost(tc.warehouseCapacity, tc.additionalHubs);
            if (tc.expected != null) {
                if (!result.equals(tc.expected)) {
                    System.out.println("Test " + tc.name + " FAILED. Expected: " + tc.expected + ", Got: " + result);
                    allPass = false;
                } else {
                    System.out.println("Test " + tc.name + " PASSED.");
                }
            } else {
                // For large test case, just output the computed result size.
                System.out.println("Test " + tc.name + " executed. Result: " + result);
            }
        }
        if (allPass) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
