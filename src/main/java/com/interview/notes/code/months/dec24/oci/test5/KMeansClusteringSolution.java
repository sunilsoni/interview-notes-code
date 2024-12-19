package com.interview.notes.code.months.dec24.oci.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

### Sample Output

**Output:**
3

**Explanation:**
Let the cluster centers be placed at points 3 and 12.

| Current Location | Closest Cluster Center | Distance       |
|------------------|-------------------------|----------------|
| 1                | 3                       | |1 - 3| = 2    |
| 9                | 12                      | |9 - 12| = 3   |
| 3                | 3                       | |3 - 3| = 0    |
| 10               | 12                      | |10 - 12| = 2  |
| 14               | 12                      | |14 - 12| = 2  |

**Hence, the maximum of all distances is equal to 3.**

---

### Returns

**Return Type:**
`int`: the maximum distance between any data point and its nearest cluster center by optimally placing the `k` clusters.

---

### Constraints

1. \( 1 \leq n \leq 10^5 \)
2. \( 1 \leq k \leq n \)
3. \( 1 \leq \text{location[i]} \leq 10^9 \)

---

### Input Format for Custom Testing

**Example Input:**
- `n = 5` (size of location array)
- `location = [1, 9, 3, 10, 14]`
- `k = 2`

**Sample Output:**
3

---

### Example 2

#### Input:
- \( n = 5 \)
- \( \text{location} = [4, 1, 6, 7, 2] \)
- \( k = 2 \)

#### Explanation:
Let the cluster centers be placed at points 3 and 7.

| Current Location | Closest Cluster Center | Distance       |
|------------------|-------------------------|----------------|
| 4                | 3                       | |4 - 3| = 1    |
| 1                | 3                       | |1 - 3| = 2    |
| 6                | 7                       | |6 - 7| = 1    |
| 7                | 7                       | |7 - 7| = 0    |
| 2                | 3                       | |2 - 3| = 1    |

**Hence, the maximum of all distances is 2.**

---

### Function Description

Complete the function `getMaximumDistance` in the editor below.

**Function Signature:**
```python
def getMaximumDistance(location: List[int], k: int) -> int:
```

**Parameters:**
- `int location[n]`: the feature locations of all the data points.
- `int k`: the number of clusters.

---

### K-Means Clustering Description

In a k-means clustering problem, a dataset contains `n` data points, where the \(i^{th}\) data point is represented by the feature vector `location[i]`.

The goal is to create `k` clusters, where the cluster centers (or centroids) can be placed at any point in the feature space. The overall quality of clustering is measured by the maximum distance between any data point and its nearest cluster center.

The best quality is achieved by optimally placing the cluster centers to minimize this maximum distance.

---

**Note:**
The distance between two feature points \(x\) and \(y\) is defined as \(|x - y|\), where \(|x|\) denotes the absolute value of \(x\).

---

```java
/*
 * Complete the 'getMaximumDistance' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER_ARRAY location
 *  2. INTEGER k

 */
public class KMeansClusteringSolution {

    /**
     * Finds the minimum possible maximum distance between any point and its nearest cluster center.
     *
     * @param location List of integer locations of data points.
     * @param k        Number of clusters.
     * @return The minimized maximum distance.
     */
    public static long getMaximumDistance(List<Integer> location, int k) {
        // Validate input: Ensure the location list is not null or empty
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location list cannot be null or empty.");
        }

        // Validate input: Ensure all location values are non-negative
        for (int loc : location) {
            if (loc < 0) {
                throw new IllegalArgumentException("Location values must be non-negative.");
            }
        }

        // Sort the locations in ascending order to facilitate clustering
        Collections.sort(location);

        int n = location.size();

        // Edge Case: If the number of clusters is greater than or equal to the number of points,
        // each point can be its own cluster, resulting in a maximum distance of 0
        if (k >= n) {
            return 0;
        }

        // Determine the minimum and maximum locations to establish the search range
        long minLoc = location.get(0);
        long maxLoc = location.get(n - 1);

        // Initialize binary search boundaries
        long low = 0; // Minimum possible distance
        long high = maxLoc - minLoc; // Maximum possible distance

        // Binary Search Loop: Continuously narrow down the search range until low meets high
        while (low < high) {
            // Calculate the midpoint to test as the potential maximum distance
            long mid = low + (high - low) / 2;

            // Check if it's possible to cover all points with k clusters using the current mid as the maximum distance
            if (canCoverWithKClusters(location, k, mid)) {
                // If feasible, attempt to find a smaller maximum distance by adjusting the high boundary
                high = mid;
            } else {
                // If not feasible, increase the minimum boundary to search for a larger maximum distance
                low = mid + 1;
            }
        }

        // After the loop, low (or high) holds the smallest feasible maximum distance
        return low;
    }

    /**
     * Checks if all points can be covered with k clusters where each cluster covers points within 2R distance.
     *
     * @param location Sorted list of integer locations.
     * @param k        Number of clusters.
     * @param R        Current maximum allowed distance.
     * @return True if possible to cover with k clusters, else False.
     */
    private static boolean canCoverWithKClusters(List<Integer> location, int k, long R) {
        int count = 0; // Counter for the number of clusters used
        int i = 0; // Index to iterate through the location list
        int n = location.size();

        // Iterate through all points to determine cluster placements
        while (i < n) {
            // Start a new cluster with the current point as the starting point
            long start = location.get(i);
            // Define the coverage end for this cluster; it can cover points up to start + 2R
            long coverEnd = start + 2 * R;

            // Move the index forward to include all points covered by this cluster
            while (i < n && location.get(i) <= coverEnd) {
                i++;
            }

            // Increment the cluster count as one cluster has been placed
            count++;

            // Early termination: If the number of clusters used exceeds k, it's not feasible
            if (count > k) {
                return false;
            }
        }

        // If all points are covered within k clusters, return true
        return count <= k;
    }

    public static void main(String[] args) {
        // Initialize a list to hold all test cases
        List<TestCase> testCases = new ArrayList<>();

        /*
         * Adding Test Cases:
         * Each TestCase consists of:
         * - A list of integer locations
         * - An integer k representing the number of clusters
         * - The expected output (minimum possible maximum distance)
         */

        // Test Case 1: Provided Example
        // Input: location = [1, 9, 3, 10, 14], k = 2
        // Expected Output: 3
        testCases.add(new TestCase(Arrays.asList(1, 9, 3, 10, 14), 2, 3));

        // Test Case 2: Another Provided Example
        // Input: location = [4, 1, 6, 7, 2], k = 2
        // Expected Output: 2
        testCases.add(new TestCase(Arrays.asList(4, 1, 6, 7, 2), 2, 2));

        // Test Case 3: Edge Case - k equals the number of points
        // Input: location = [1, 2, 3, 4], k = 4
        // Expected Output: 0
        testCases.add(new TestCase(Arrays.asList(1, 2, 3, 4), 4, 0));

        // Test Case 4: Edge Case - All points are the same
        // Input: location = [5, 5, 5, 5], k = 1
        // Expected Output: 0
        testCases.add(new TestCase(Arrays.asList(5, 5, 5, 5), 1, 0));

        // Test Case 5: Large Range with k=1
        // Input: location = [1, 1000000000], k = 1
        // Expected Output: 499999999
        testCases.add(new TestCase(Arrays.asList(1, 1000000000), 1, 499999999));

        // Test Case 6: Additional Test - k equals the number of points
        // Input: location = [10, 20, 30, 40, 50], k = 5
        // Expected Output: 0
        testCases.add(new TestCase(Arrays.asList(10, 20, 30, 40, 50), 5, 0));

        // Test Case 7: Additional Test - Different k
        // Input: location = [10, 20, 30, 40, 50], k = 2
        // Expected Output: 10
        testCases.add(new TestCase(Arrays.asList(10, 20, 30, 40, 50), 2, 10));

        // Test Case 8: Large Data Input
        // Generating a large list of locations from 0 to 99,999
        List<Integer> largeLocation = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeLocation.add(i);
        }
        // Input: location = [0, 1, 2, ..., 99999], k = 1
        // Expected Output: 99999
        testCases.add(new TestCase(largeLocation, 1, 99999));

        // Execute all test cases
        int testNumber = 1; // Counter for test case numbering
        for (TestCase tc : testCases) {
            try {
                // Record the start time to measure performance
                long startTime = System.currentTimeMillis();

                // Invoke the getMaximumDistance method with current test case inputs
                long result = getMaximumDistance(tc.location, tc.k);

                // Record the end time after method execution
                long endTime = System.currentTimeMillis();

                // Determine if the test case passed by comparing the result with the expected output
                boolean pass = result == tc.expected;

                // Output the result of the test case along with execution time
                System.out.println("Test " + testNumber + " (expected " + tc.expected + "): " + result
                        + (pass ? " PASS" : " FAIL") + " | Time: " + (endTime - startTime) + "ms");
            } catch (Exception e) {
                // If an exception occurs during the test case execution, catch it and mark the test as failed
                System.out.println("Test " + testNumber + " (expected " + tc.expected + "): Exception occurred - "
                        + e.getMessage() + " FAIL");
            }
            testNumber++; // Increment the test case number for the next iteration
        }
    }

    /**
     * Helper class to represent a test case.
     */
    static class TestCase {
        List<Integer> location; // List of integer locations
        int k;                  // Number of clusters
        long expected;          // Expected maximum distance

        /**
         * Constructor to initialize a test case with its inputs and expected output.
         *
         * @param location List of integer locations.
         * @param k        Number of clusters.
         * @param expected Expected maximum distance.
         */
        TestCase(List<Integer> location, int k, long expected) {
            this.location = location;
            this.k = k;
            this.expected = expected;
        }
    }
}
