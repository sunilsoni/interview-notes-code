package com.interview.notes.code.year.y2025.march.tiktok.test3;

import java.util.*;
import java.util.stream.Collectors;
/*
WORKING 100%


### **Risk-Aware Partition Optimization**
TikTok’s content moderation system needs to efficiently review videos with potential policy violations. Each video has a **risk score**, represented by an array **videoRiskScores**.

To optimize the review process, the videos need to be **partitioned into groups** (subarrays) where:
- Each **subarray's size is at most `k`**.
- The **cost of a group** is determined by the **maximum risk score** in that group.
- The **goal** is to **minimize the total evaluation cost** by dividing the videos optimally.

A **subarray** is defined as any contiguous segment of the array.

---

### **Example**
#### **Input**
```
n = 4
videoRiskScores = [1, 2, 3, 4]
k = 3
```
#### **Explanation**
- The optimal way to partition the array is **[1]** and **[2, 3, 4]**.
- The maximum risk scores in the partitions are:
  - **1** for `[1]`
  - **4** for `[2, 3, 4]`
- The **total cost** is **1 + 4 = 5**.

#### **Output**
```
5
```

---

### **Function Description**
You need to complete the function:

```java
public static int findTotalCost(List<Integer> videoRiskScores, int k)
```

**Parameters:**
- `List<Integer> videoRiskScores`: The list of risk scores of videos.
- `int k`: The maximum possible length of a group.

**Returns:**
- `int`: The minimum total cost for evaluating risks of videos.

---

### **Constraints**
- \( 2 \leq n \leq 10^3 \)
- \( 1 \leq k \leq 10^3 \)
- \( 1 \leq videoRiskScores[i] \leq 10^3 \)

---

### **Sample Cases**
#### **Sample Case 1**
**Input:**
```
n = 6
videoRiskScores = [1, 3, 4, 5, 2, 6]
k = 3
```
**Output:**
```
10
```
**Explanation:**
- Optimal partition: **[1, 3, 4]** and **[5, 2, 6]**.
- The max risk scores:
  - **4** for `[1, 3, 4]`
  - **6** for `[5, 2, 6]`
- Total cost: **4 + 6 = 10**.

---

#### **Sample Case 2**
**Input:**
```
n = 4
videoRiskScores = [1, 2, 3, 4]
k = 1
```
**Output:**
```
10
```
**Explanation:**
- Since `k = 1`, each video forms its own group.
- The sum of all values: **1 + 2 + 3 + 4 = 10**.

---

### **Task**
Implement the function **findTotalCost(videoRiskScores, k)** to determine the **minimum evaluation cost** for the given constraints.

 */
public class RiskAwarePartitionSolution {

    /**
     * findTotalCost function:
     * -----------------------
     * Given a list of video risk scores and an integer k (the maximum group size),
     * this method calculates the minimal total cost of partitioning the videos 
     * such that each partition's size is at most k, and the cost of a partition 
     * is the maximum risk score in that partition.
     *
     * @param videoRiskScores list of integer risk scores
     * @param k               maximum allowed subarray size
     * @return                minimal total partition cost
     */
    public static int findTotalCost(List<Integer> videoRiskScores, int k) {
        // n is the number of videos
        int n = videoRiskScores.size();
        
        // dp[i] will store the minimal cost to partition videos[0..i-1]
        int[] dp = new int[n + 1];

        // Base case: no videos => cost is 0
        dp[0] = 0;

        // Initialize all other dp states to a large number
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // Build dp from left to right
        for (int i = 1; i <= n; i++) {
            int currentMax = Integer.MIN_VALUE;
            
            // Look back up to k positions to find best sub-partition
            // j goes from i-1 down to i-k (but not less than 0)
            for (int j = i - 1; j >= Math.max(0, i - k); j--) {
                // Track the max risk within this subarray [j..i-1]
                currentMax = Math.max(currentMax, videoRiskScores.get(j));
                
                // Update dp[i] if we found a smaller cost partition
                dp[i] = Math.min(dp[i], dp[j] + currentMax);
            }
        }

        // dp[n] is the minimal cost to partition all n videos
        return dp[n];
    }

    /**
     * Main method: 
     * ------------
     * 1. Demonstrates how to run multiple tests.
     * 2. Validates each test by comparing actual result vs expected result.
     * 3. Prints "PASS" or "FAIL" accordingly.
     */
    public static void main(String[] args) {
        // Define some test cases:
        // Each test case has: (input array, k, expectedOutput)
        List<TestCase> testCases = Arrays.asList(
            new TestCase(Arrays.asList(1, 3, 4, 5, 2, 6), 3, 10),
            new TestCase(Arrays.asList(1, 2, 3, 4), 1, 10),
            new TestCase(Arrays.asList(1, 2, 3, 4), 3, 5),
            new TestCase(Arrays.asList(5, 5, 5, 5), 2, 10),      // partition [5,5] + [5,5]
            new TestCase(Arrays.asList(5, 5, 5, 5), 4, 5),       // partition all in one group
            new TestCase(Arrays.asList(10), 1, 10),             // single element
            new TestCase(Arrays.asList(2, 2, 2), 2, 4)          // partition [2,2] + [2] => 2 + 2 = 4
        );

        // Run each test case
        for (TestCase test : testCases) {
            int actual = findTotalCost(test.videoRiskScores, test.k);
            boolean pass = (actual == test.expected);
            System.out.printf(
                "Input: %s, k=%d, Expected: %d, Got: %d => %s%n",
                test.videoRiskScores, test.k, test.expected, actual, pass ? "PASS" : "FAIL"
            );
        }
    }

    // Simple helper class to hold test case data
    static class TestCase {
        List<Integer> videoRiskScores;
        int k;
        int expected;

        TestCase(List<Integer> videoRiskScores, int k, int expected) {
            this.videoRiskScores = videoRiskScores;
            this.k = k;
            this.expected = expected;
        }
    }
}
