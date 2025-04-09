package com.interview.notes.code.year.y2025.march.amazon.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING 100%

### **Problem: Get Minimum Connection Cost**

In Amazon’s highly efficient logistics network, minimizing operational overhead and optimizing package routing is crucial to ensure smooth deliveries across various regions.

The network consists of **n warehouses**, numbered from **1 to n**, each strategically positioned at its corresponding index. Each warehouse has a specific storage capacity, given by `warehouseCapacity`, where `warehouseCapacity[i]` represents the capacity of the warehouse located at position `i+1` (1-based indexing).

These warehouses are organized in a **non-decreasing** order of their storage capacities. This means:
- `warehouseCapacity[i] ≤ warehouseCapacity[i+1]` for all valid `i`
- Each warehouse at position `i` can only connect to a distribution hub at a location `j` such that `j ≥ i`

To optimize routing:
- Amazon has placed a central **high-capacity distribution hub** at the **last** warehouse (position `n`)
- This central hub serves as the main connection point if no other distribution hub is available
- The cost of connecting warehouse `i` to hub `j` is calculated as:

  \[
  \text{Cost} = \text{warehouseCapacity}[j] - \text{warehouseCapacity}[i]
  \]

You are also given `q` queries. Each query specifies the positions (1-based) of two **additional** distribution hubs placed at different warehouses (excluding the last one). For each query, your task is to calculate the **minimum total connection cost** for all warehouses, considering the **nearest hub** at or beyond the warehouse’s position.

---

### **Function Signature**
```java
public static List<Long> getMinConnectionCost(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs)
```

---

### **Parameters**
- `warehouseCapacity`: List of `n` integers representing the non-decreasing storage capacities.
- `additionalHubs`: A list of `q` queries, where each query is a list of two integers `[hubA, hubB]`, representing the positions (1-based) where additional hubs are placed.

---

### **Returns**
- A list of `q` long integers where each value represents the minimum total connection cost for the corresponding query.

---

### **Constraints**
- \( 3 \leq n \leq 2.5 \times 10^5 \)
- \( 1 \leq q \leq 2.5 \times 10^5 \)
- \( 0 \leq \text{warehouseCapacity}[i] \leq 10^9 \)
- \( \text{warehouseCapacity}[i] \leq \text{warehouseCapacity}[i+1] \)
- \( 1 \leq \text{hubA} < \text{hubB} < n \)

---

### **Notes**
- 1-based indexing is assumed for additional hubs.
- Each query is **independent**, meaning added hubs do not persist across queries.
- Warehouses always connect to the **nearest available hub** at or beyond their position.

---

### **Example**

#### Input
```plaintext
warehouseCapacity = [3, 6, 10, 15, 20]
additionalHubs = [[2, 4]]
```

#### Output
```plaintext
[8]
```

#### Explanation
- With hubs at positions 2 and 4 (0-based index 1 and 3):
  - 1st warehouse connects to hub at 2 → cost = 6 - 3 = 3
  - 2nd warehouse is hub → cost = 0
  - 3rd warehouse connects to hub at 4 → cost = 15 - 10 = 5
  - 4th & 5th are hubs → cost = 0
  - Total = 3 + 0 + 5 + 0 + 0 = 8

---

### **Sample Case 1**

#### Input
```plaintext
warehouseCapacity = [2, 6, 8, 14]
additionalHubs = [[1, 2]]
```

#### Output
```plaintext
[6]
```

---

### **Sample Case 2**

#### Input
```plaintext
warehouseCapacity = [0, 2, 5, 9, 12, 18]
additionalHubs = [[2, 5], [1, 3]]
```

#### Output
```plaintext
[12, 18]
 */
public class GetMinConnectionCost {
    public static List<Long> getMinConnectionCost(List<Integer> capacity, List<List<Integer>> queries) {
        int n = capacity.size();
        List<Long> result = new ArrayList<>();

        // Precompute prefix sums
        long[] prefixCapSum = new long[n];
        long[] prefixBaseDiff = new long[n]; // diff from central hub
        int centralCap = capacity.get(n - 1);

        prefixCapSum[0] = capacity.get(0);
        prefixBaseDiff[0] = centralCap - capacity.get(0);
        for (int i = 1; i < n; i++) {
            prefixCapSum[i] = prefixCapSum[i - 1] + capacity.get(i);
            prefixBaseDiff[i] = prefixBaseDiff[i - 1] + (centralCap - capacity.get(i));
        }

        long baseCost = prefixBaseDiff[n - 2]; // exclude last warehouse

        for (List<Integer> q : queries) {
            int h1 = q.get(0) - 1;
            int h2 = q.get(1) - 1;

            long totalCost = baseCost;

            // Range [0..h1-1] → connect to h1
            if (h1 > 0) {
                long original = prefixBaseDiff[h1 - 1];
                long newCost = (long) h1 * (long) capacity.get(h1) - prefixCapSum[h1 - 1];
                totalCost += newCost - original;
            }

            // Range [h1+1 .. h2-1] → connect to h2
            if (h2 > h1 + 1) {
                long original = prefixBaseDiff[h2 - 1] - prefixBaseDiff[h1];
                long newCost = (long) (h2 - h1 - 1) * (long) capacity.get(h2) - (prefixCapSum[h2 - 1] - prefixCapSum[h1]);
                totalCost += newCost - original;
            }

            // Subtract central cost for h1 and h2
            if (h1 != n - 1) totalCost -= (centralCap - capacity.get(h1));
            if (h2 != n - 1) totalCost -= (centralCap - capacity.get(h2));

            result.add(totalCost);
        }

        return result;
    }

    public static List<Long> getMinConnectionCost1(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs) {
        int n = warehouseCapacity.size();
        List<Long> results = new ArrayList<>();

        // Precompute total cost if all warehouses connect to the last warehouse (central hub)
        long baseCost = 0;
        for (int i = 0; i < n - 1; i++) {
            baseCost += warehouseCapacity.get(n - 1) - warehouseCapacity.get(i);
        }

        for (List<Integer> query : additionalHubs) {
            // Convert 1-based to 0-based indexing
            int hub1 = query.get(0) - 1;
            int hub2 = query.get(1) - 1;

            // Start with base cost (all warehouses connected to central hub)
            long totalCost = baseCost;

            // Calculate savings for warehouses connecting to hub1 instead of central hub
            for (int i = 0; i < hub1; i++) {
                // Save by connecting to hub1 instead of central hub
                totalCost -= (warehouseCapacity.get(n - 1) - warehouseCapacity.get(i));
                totalCost += (warehouseCapacity.get(hub1) - warehouseCapacity.get(i));
            }

            // Calculate savings for warehouses connecting to hub2 instead of central hub
            for (int i = hub1 + 1; i < hub2; i++) {
                // Save by connecting to hub2 instead of central hub
                totalCost -= (warehouseCapacity.get(n - 1) - warehouseCapacity.get(i));
                totalCost += (warehouseCapacity.get(hub2) - warehouseCapacity.get(i));
            }

            // Subtract the cost for hub1 and hub2 (they have zero cost)
            totalCost -= (warehouseCapacity.get(n - 1) - warehouseCapacity.get(hub1));
            totalCost -= (warehouseCapacity.get(n - 1) - warehouseCapacity.get(hub2));

            results.add(totalCost);
        }

        return results;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> warehouses1 = Arrays.asList(3, 6, 10, 15, 20);
        List<List<Integer>> queries1 = new ArrayList<>();
        queries1.add(Arrays.asList(2, 4));
        List<Long> expected1 = Arrays.asList(8L);
        List<Long> result1 = getMinConnectionCost(warehouses1, queries1);
        System.out.println("Test Case 1: " + (expected1.equals(result1) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);

        // Test Case 2
        List<Integer> warehouses2 = Arrays.asList(2, 6, 8, 14);
        List<List<Integer>> queries2 = new ArrayList<>();
        queries2.add(Arrays.asList(1, 2));
        List<Long> expected2 = Arrays.asList(6L);
        List<Long> result2 = getMinConnectionCost(warehouses2, queries2);
        System.out.println("Test Case 2: " + (expected2.equals(result2) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);

        // Test Case 3
        List<Integer> warehouses3 = Arrays.asList(0, 2, 5, 9, 12, 18);
        List<List<Integer>> queries3 = new ArrayList<>();
        queries3.add(Arrays.asList(2, 5));
        queries3.add(Arrays.asList(1, 3));
        List<Long> expected3 = Arrays.asList(12L, 18L);
        List<Long> result3 = getMinConnectionCost(warehouses3, queries3);
        System.out.println("Test Case 3: " + (expected3.equals(result3) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected3);
        System.out.println("Result: " + result3);

        // Test for large data
        testLargeData();
    }

    private static void testLargeData() {
        // Create large test data
        int n = 200000;
        List<Integer> largeWarehouses = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            largeWarehouses.add(i);
        }

        List<List<Integer>> largeQueries = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeQueries.add(Arrays.asList(n / 4, 3 * n / 4));
        }

        long startTime = System.currentTimeMillis();
        getMinConnectionCost(largeWarehouses, largeQueries);
        long endTime = System.currentTimeMillis();

        System.out.println("Large data test with n=" + n + " and q=100");
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
}
