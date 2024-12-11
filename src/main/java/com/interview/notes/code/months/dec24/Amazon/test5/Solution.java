package com.interview.notes.code.months.dec24.Amazon.test5;

import java.util.*;
import java.io.*;

/*
WORKING


### Function Description
Complete the function `getMinConnectionCost` in the editor below.

**Function Signature:**
```java
public static List<Long> getMinConnectionCost(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs);
```

**Parameters:**
- `int warehouseCapacity[n]`: A non-decreasing array of integers representing the storage capacities of the warehouses.
- `int additionalHubs[q][2]`: A 2D array where each element denotes the positions of two additional distribution hubs installed for a query.

**Returns:**
- `long_int[q]`: The answers for each query.

---

### Constraints
- \( 3 \leq n \leq 2.5 \times 10^5 \)
- \( 1 \leq q \leq 2.5 \times 10^5 \)
- \( 0 \leq warehouseCapacity[i] \leq 10^9 \)
- \( warehouseCapacity[i] \leq warehouseCapacity[i+1] \) for all \( 0 \leq i < n - 1 \)
- \( 1 \leq additionalHubs[i][0] < additionalHubs[i][1] < n \)

---

### Input Format for Custom Testing
1. The first line contains an integer \( n \), the number of elements in `warehouseCapacity`.
2. Each of the next \( n \) lines contains an integer, `warehouseCapacity[i]`.
3. The next line contains an integer \( q \), the number of rows in `additionalHubs`.
4. The next line contains an integer \( 2 \), the number of columns in `additionalHubs`.
5. Each line \( i \) of the \( q \) subsequent lines (where \( 0 \leq i < q \)) contains 2 space-separated integers, `additionalHubs[i][0]` and `additionalHubs[i][1]`.

---

### Example
**Input:**
```
5
3
6
10
15
20
1
2
2 4
```

**Output:**
```
8
```

**Explanation:**
In this case, there is \( q = 1 \) query with two additional high-performance distribution hubs at positions \( hubA = 2 \) and \( hubB = 4 \).

#### Before Query
The total connection cost is calculated as:
- \( |20 - 10| = 10 \)
- \( |20 - 6| = 14 \)
- \( |20 - 3| = 17 \)
- \( |20 - 15| = 5 \)
Thus, total connection cost = \( 10 + 14 + 17 + 5 = 46 \).

#### After Query
With distribution hubs at positions \( hubA = 2 \) and \( hubB = 4 \):
- \( |6 - 3| = 3 \)
- \( |15 - 10| = 5 \)
- Cost from all others is \( 0 \).

Total connection cost = \( 3 + 5 + 0 + 0 + 0 = 8 \).

Thus, the answer = \( 8 \).

---

### Sample Input 0
**Input:**
```
6
0
2
5
9
12
18
2
2
2 5
1 3
```

**Output:**
```
12
18
```

**Explanation:**
In this case, \( n = 6 \), `warehouseCapacity = [0, 2, 5, 9, 12, 18]`, \( q = 2 \), `additionalHubs = [[2, 5], [1, 3]]`.

#### Query 1:
Two additional distribution hubs are at positions \( 2 \) and \( 5 \):
- \( |2 - 0| = 2 \)
- \( |12 - 9| = 3 \)
- Total = \( 2 + 0 + 7 + 3 + 0 + 0 = 12 \).

#### Query 2:
Two additional distribution hubs are at positions \( 1 \) and \( 3 \):
- \( |5 - 2| = 3 \)
- Total = \( 0 + 3 + 0 + 0 + 0 + 0 = 18 \).

---

### Implementation
Complete the function in the provided code editor:

 */
public class Solution {
    
    // Method to compute minimal connection costs for multiple queries
    public static List<Long> getMinConnectionCost(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs) {
        int n = warehouseCapacity.size();
        // Precompute prefix sums
        long[] prefixSum = new long[n+1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i-1] + warehouseCapacity.get(i-1);
        }
        
        long cN = warehouseCapacity.get(n-1); // Capacity at position n
        List<Long> result = new ArrayList<>(additionalHubs.size());
        
        for (List<Integer> hubs : additionalHubs) {
            int hubA = hubs.get(0);
            int hubB = hubs.get(1);
            
            long cA = warehouseCapacity.get(hubA-1);
            long cB = warehouseCapacity.get(hubB-1);
            
            // Segment 1
            long seg1 = hubA * cA - prefixSum[hubA];
            
            // Segment 2
            long seg2 = (hubB - hubA) * cB - (prefixSum[hubB] - prefixSum[hubA]);
            
            // Segment 3
            long seg3 = (n - hubB) * cN - (prefixSum[n] - prefixSum[hubB]);
            
            long totalCost = seg1 + seg2 + seg3;
            result.add(totalCost);
        }
        
        return result;
    }
    
    // Simple main method for testing without JUnit
    public static void main(String[] args) {
        // Test 1: Example from the prompt
        // Input:
        // n=5, capacities=[3,6,10,15,20]
        // q=1, additionalHubs=[[2,4]]
        // Expected Output: 8
        List<Integer> capacities1 = Arrays.asList(3,6,10,15,20);
        List<List<Integer>> queries1 = new ArrayList<>();
        queries1.add(Arrays.asList(2,4));
        List<Long> result1 = getMinConnectionCost(capacities1, queries1);
        System.out.println("Test 1: " + (result1.get(0) == 8 ? "PASS" : "FAIL") + " (Expected 8, Got " + result1.get(0) + ")");
        
        // Test 2: Sample Input 0 from prompt
        // n=6, capacities=[0,2,5,9,12,18]
        // q=2, queries=[[2,5],[1,3]]
        // Expected Output: 12,18
        List<Integer> capacities2 = Arrays.asList(0,2,5,9,12,18);
        List<List<Integer>> queries2 = new ArrayList<>();
        queries2.add(Arrays.asList(2,5));
        queries2.add(Arrays.asList(1,3));
        List<Long> result2 = getMinConnectionCost(capacities2, queries2);
        boolean passQ1 = (result2.get(0) == 12);
        boolean passQ2 = (result2.get(1) == 18);
        System.out.println("Test 2 Query 1: " + (passQ1 ? "PASS" : "FAIL") + " (Expected 12, Got " + result2.get(0) + ")");
        System.out.println("Test 2 Query 2: " + (passQ2 ? "PASS" : "FAIL") + " (Expected 18, Got " + result2.get(1) + ")");
        
        // Additional tests can be added here:
        // Edge Cases, Large Inputs, etc.
        
        // If all tests print PASS, the solution likely works correctly.
    }
}
