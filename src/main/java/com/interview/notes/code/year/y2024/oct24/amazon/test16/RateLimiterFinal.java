package com.interview.notes.code.year.y2024.oct24.amazon.test16;

/*
FINAL: WORKING


**Problem Description:**

Some developers at Amazon are building a prototype for a simple rate-limiting algorithm. There are `n` requests to be processed by the server, represented by a string `requests`, where the i-th character represents the region of the i-th client. Each request takes 1 unit of time to process. There must be a minimum time gap of `minGap` units between any two requests from the same region.

The requests can be sent in any order, and there can be gaps in transmission for testing purposes. You need to find the minimum amount of time required to process all the requests such that no request is denied.

**Input Format:**

- The first line contains an integer `n`, the number of requests.
- The second line contains a string `requests`, which indicates the regions of the requests to be processed (each character represents a region).
- The third line contains an integer `minGap`, the minimum gap between two requests from the same region.

**Function Signature:**

```java
public static int getMinTime(int n, String requests, int minGap)
```

**Constraints:**

- \( 1 \leq \text{length of requests} \leq 10^5 \)
- \( 0 \leq \text{minGap} \leq 100 \)
- It is guaranteed that `requests` contains only lowercase English characters.

**Sample Case 0:**

**Input:**

```
12
abacadaeafag
2
```

**Output:**

```
16
```

**Explanation:**

One optimal strategy is `"ab_ad_afgae_ac_a"`. The total time taken is 16.

---

**Sample Case 1:**

**Input:**

```
6
aaabbb
0
```

**Output:**

```
6
```

**Explanation:**

Since `minGap` is 0, the requests can be processed in any order without any gaps. The total time is 6.

 */
public class RateLimiterFinal {

    /**
     * Computes the minimum time required to process all requests
     * while satisfying the minimum gap constraint between requests
     * from the same region.
     *
     * @param n        The number of requests.
     * @param requests A string where each character represents a region.
     * @param minGap   The minimum time gap required between two requests from the same region.
     * @return The minimum total time to process all requests.
     */
    public static int getMinTime(int n, String requests, int minGap) {
        // Frequency array to count occurrences of each region
        int[] freq = new int[26];
        for (char c : requests.toCharArray()) {
            freq[c - 'a']++;
        }

        // Find the maximum frequency and the number of regions with this frequency
        int maxFreq = 0;
        int maxCount = 0;
        for (int count : freq) {
            if (count > maxFreq) {
                maxFreq = count;
                maxCount = 1;
            } else if (count == maxFreq) {
                maxCount++;
            }
        }

        // Calculate the minimum time using the formula
        int minTime = (maxFreq - 1) * (minGap + 1) + maxCount;

        // The total time is the maximum of the calculated minimum time and the number of requests
        return Math.max(minTime, n);
    }

    public static void main(String[] args) {
        // Provided test cases
        testGetMinTime(12, "abacadaeafag", 2, 16);
        testGetMinTime(6, "aaabbb", 0, 6);

        // Additional test cases
        testGetMinTime(4, "aaaa", 2, 10);   // Edge case: All requests from the same region
        testGetMinTime(1, "a", 0, 1);       // Edge case: Single request
        testGetMinTime(2, "ab", 1, 2);      // No gaps needed
        testGetMinTime(5, "abcde", 3, 5);   // All regions are different

        // Large input test case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append('a');
        }
        testGetMinTime(100000, sb.toString(), 0, 100000); // Large input with minGap = 0
    }

    /**
     * Helper method to test getMinTime function.
     *
     * @param n        Number of requests.
     * @param requests String representing regions.
     * @param minGap   Minimum gap between same region requests.
     * @param expected Expected result.
     */
    private static void testGetMinTime(int n, String requests, int minGap, int expected) {
        int result = getMinTime(n, requests, minGap);
        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL: Expected " + expected + ", got " + result);
        }
    }
}
