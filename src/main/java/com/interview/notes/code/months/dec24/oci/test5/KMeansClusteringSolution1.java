package com.interview.notes.code.months.dec24.oci.test5;

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
public class KMeansClusteringSolution1 {

    // Function to get the maximum minimized distance using binary search
    public static int getMaximumDistance(List<Integer> location, int k) {
        Collections.sort(location);

        int n = location.size();
        if (k >= n) {
            // If we have at least one cluster per point, max distance = 0
            return 0;
        }

        int minLoc = location.get(0);
        int maxLoc = location.get(n - 1);

        int low = 0;
        int high = maxLoc - minLoc;

        while (low < high) {
            int mid = (low + high) / 2;
            if (canCoverWithKClusters(location, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Check if we can cover all points using k clusters with maximum distance R
    private static boolean canCoverWithKClusters(List<Integer> location, int k, int R) {
        int count = 0;
        int i = 0;
        int n = location.size();

        while (i < n) {
            // Start covering from this point
            int start = location.get(i);
            // Place cluster center at start + R, covers from start to start + 2R
            int coverEnd = start + 2 * R;

            // Move i forward while points are covered by this cluster
            while (i < n && location.get(i) <= coverEnd) {
                i++;
            }
            count++; // used one cluster

            if (count > k) {
                return false; // used too many clusters
            }
        }

        return count <= k;
    }

    public static void main(String[] args) {
        // Testing the solution using simple print-based checks

        // 1. Provided Example
        // Input: n = 5, location = [1,9,3,10,14], k = 2
        // Expected output = 3
        List<Integer> loc1 = Arrays.asList(1, 9, 3, 10, 14);
        int k1 = 2;
        int result1 = getMaximumDistance(loc1, k1);
        System.out.println("Test 1 (expected 3): " + result1 + (result1 == 3 ? " PASS" : " FAIL"));

        // 2. Another Provided Example
        // Input: n = 5, location = [4,1,6,7,2], k = 2
        // Expected = 2
        List<Integer> loc2 = Arrays.asList(4, 1, 6, 7, 2);
        int k2 = 2;
        int result2 = getMaximumDistance(loc2, k2);
        System.out.println("Test 2 (expected 2): " + result2 + (result2 == 2 ? " PASS" : " FAIL"));

        // 3. Edge Case: k = n
        // location = [1, 2, 3, 4], k = 4
        // Each point can be its own cluster, max distance = 0
        List<Integer> loc3 = Arrays.asList(1, 2, 3, 4);
        int k3 = 4;
        int result3 = getMaximumDistance(loc3, k3);
        System.out.println("Test 3 (expected 0): " + result3 + (result3 == 0 ? " PASS" : " FAIL"));

        // 4. Edge Case: All points same
        // location = [5,5,5,5], k = 1
        // max distance = 0
        List<Integer> loc4 = Arrays.asList(5, 5, 5, 5);
        int k4 = 1;
        int result4 = getMaximumDistance(loc4, k4);
        System.out.println("Test 4 (expected 0): " + result4 + (result4 == 0 ? " PASS" : " FAIL"));

        // 5. Large Range with k=1
        // location = [1, 1000000000], k=1
        // Must cover entire range. Place center at midpoint (500000000.5 approx)
        // Max distance = (1000000000 - 1)/2 = approx 499999999
        List<Integer> loc5 = Arrays.asList(1, 1000000000);
        int k5 = 1;
        int result5 = getMaximumDistance(loc5, k5);
        // Just to confirm correctness:
        // The distance = (1000000000 - 1)/2 = 499999999 (integer division)
        int expected5 = (1000000000 - 1) / 2;
        System.out.println("Test 5 (expected " + expected5 + "): " + result5 + (result5 == expected5 ? " PASS" : " FAIL"));

        // Additional random test
        List<Integer> loc6 = Arrays.asList(10, 20, 30, 40, 50);
        // k=5 -> max distance=0
        int k6 = 5;
        int result6 = getMaximumDistance(loc6, k6);
        System.out.println("Test 6 (expected 0): " + result6 + (result6 == 0 ? " PASS" : " FAIL"));

        // Additional test for different k
        // location = [10,20,30,40,50], k=2
        // Let's think: 
        // If R=10:
        //   Cluster1: center at (10+10)=20 covers [10..30]
        //   covers 10,20,30
        //   Next uncovered: 40
        //   Cluster2: center at (40+10)=50 covers [40..60]
        //   covers 40,50
        // So R=10 works. Can we do smaller?
        // R=9:
        //   Cluster1: center at 19 covers [10..28]
        //   covers 10,20 but NOT 30 (30 > 28)
        //   Next cluster starts at 30: center at (30+9)=39 covers [30..48]
        //   covers 30,40 but NOT 50 (50 > 48)
        //   Need another cluster for 50 -> total 3 clusters needed. Not feasible.
        // So minimal R ~ 10
        int k7 = 2;
        int result7 = getMaximumDistance(loc6, k7);
        System.out.println("Test 7 (expected around 10): " + result7 + ((result7 == 10) ? " PASS" : " FAIL"));
    }
}
