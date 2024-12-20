package com.interview.notes.code.months.dec24.amazon.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
WORKING:



### Problem Description
Developers at Amazon have deployed an application with a distributed database. It is stored on `total_servers` different servers numbered from 1 to `total_servers` that are connected in a circular fashion, i.e., 1 is connected to 2, 2 is connected to 3, and so on until `total_servers` connects back to 1.

There is a subset of servers represented by an array `servers` of `n` integers. They need to transfer the data to each other to be synchronized. Data transfer from one server to one it is directly connected to takes 1 unit of time. Starting from any server, find the minimum amount of time to transfer the data to all the other servers.

---

### Function Description
Complete the function `getMinTime` in the editor below.

**Function Signature:**
```java
public static int getMinTime(int total_servers, List<Integer> servers);
```

**Parameters:**
- `int total_servers`: The number of servers in the system.
- `List<Integer> servers`: The servers to share the data with.

**Returns:**
- `int`: The minimum time required to transfer the data on all the servers.

---

### Constraints
- \( 1 \leq total\_servers \leq 10^9 \)
- \( 1 \leq n \leq 10^5 \)
- \( 1 \leq servers[i] \leq n \)

---

### Input Format for Custom Testing
1. The first line contains an integer `total_servers`.
2. The next line contains an integer `n`, the number of elements in `servers`.
3. Each of the next `n` lines contains an integer, `servers[i]`.

---

### Example

#### Sample Input 1
```
10
4
4
6
2
9
```

**Output:**
```
7
```

**Explanation:**
An optimal strategy is to start from server 2 and go to 4, then 6, then 9. It takes \( 2 + 2 + 3 = 7 \) units of time.

---

#### Sample Input 2
```
5
2
1
5
```

**Output:**
```
1
```

**Explanation:**
The two servers are directly connected, so it will take only 1 unit of time to share the data.

---

### Diagram Explanation
Suppose `total_servers = 8`, `n = 3`, `servers = [2, 6, 8]`.

- Two possible paths are shown:
  - One path goes from 2 to 6 to 8, taking 6 units of time.
  - The other path goes from 2 to 8 to 6, taking 4 units of time.

Return the shorter path length, which is 4.

---

 */
public class ServerDataTransferFinal {
    /**
     * Problem Analysis:
     * We have total_servers arranged in a circle (1 connected to 2, 2 to 3, ..., total_servers to 1).
     * We are given a list of servers that must all share data with each other. We can start the data transfer from any one
     * of these chosen servers and must ensure that data is eventually on all chosen servers.
     * Data transfer between directly connected servers takes 1 unit of time.
     * We must find the minimum time required to achieve this synchronization among the chosen servers.
     * <p>
     * Key Insight:
     * In essence, we need to find the minimal continuous circular arc that covers all chosen servers. Since we can start from any chosen server,
     * the problem reduces to finding the smallest arc on the circle that contains all chosen servers. The length of that arc is the answer.
     * <p>
     * Approach:
     * 1. Sort the chosen servers in ascending order.
     * 2. Compute the circular gaps between consecutive chosen servers:
     * - For i in [0..n-2], gap_i = servers[i+1] - servers[i]
     * - Also consider the wrap-around gap: gap_wrap = total_servers - (servers[n-1] - servers[0])
     * 3. Identify the largest gap. By "skipping" the largest gap, we form the smallest arc that covers all chosen servers.
     * The minimal travel time = total_servers - largest_gap.
     * 4. Special case: If there is only one chosen server, no travel is needed, so answer = 0.
     * <p>
     * Complexity:
     * - Sorting takes O(n log n).
     * - Finding largest gap takes O(n).
     * This is efficient for n up to 10^5.
     * <p>
     * Edge Cases:
     * - n=1: Only one chosen server, answer=0.
     * - All servers close together and one far away creates a large gap, ensure correctness.
     */
    public static int getMinTime(int total_servers, List<Integer> servers) {
        int n = servers.size();
        if (n == 1) {
            // Only one server, no travel needed.
            return 0;
        }

        // Sort the server positions
        Collections.sort(servers);

        // Find the largest gap in the circular arrangement
        int largestGap = 0;
        for (int i = 0; i < n - 1; i++) {
            int gap = servers.get(i + 1) - servers.get(i);
            if (gap > largestGap) {
                largestGap = gap;
            }
        }
        // Consider the wrap-around gap
        int wrapGap = total_servers - (servers.get(n - 1) - servers.get(0));
        if (wrapGap > largestGap) {
            largestGap = wrapGap;
        }

        // The minimal route is total_servers - largest_gap
        return total_servers - largestGap;
    }

    /**
     * A simple main method to test the solution without JUnit.
     * We'll print PASS/FAIL for the given test cases and can add more tests as needed.
     */
    public static void main(String[] args) {
        // Test Case 1 (From the provided examples):
        // total_servers=10, servers=[4,6,2,9] => expected 7
        {
            int total = 10;
            List<Integer> s = Arrays.asList(4, 6, 2, 9);
            int result = getMinTime(total, s);
            System.out.println("Test 1: " + (result == 7 ? "PASS" : "FAIL") + " (Expected 7, Got " + result + ")");
        }

        // Test Case 2 (From the provided examples):
        // total_servers=5, servers=[1,5] => expected 1
        {
            int total = 5;
            List<Integer> s = Arrays.asList(1, 5);
            int result = getMinTime(total, s);
            System.out.println("Test 2: " + (result == 1 ? "PASS" : "FAIL") + " (Expected 1, Got " + result + ")");
        }

        // Additional Test based on the explanation (from problem statement):
        // total_servers=8, servers=[2,6,8]
        // The example mentions a minimal route of 4. Let's confirm:
        {
            int total = 8;
            List<Integer> s = Arrays.asList(2, 6, 8);
            // Sort: [2,6,8]
            // Gaps: (6-2)=4, (8-6)=2, wrapGap=8-(8-2)=8-6=2
            // largestGap=4, answer=8-4=4
            int result = getMinTime(total, s);
            System.out.println("Test 3: " + (result == 4 ? "PASS" : "FAIL") + " (Expected 4, Got " + result + ")");
        }

        // Edge Case: Single server
        {
            int total = 100;
            List<Integer> s = Arrays.asList(50);
            // Only one server, no travel needed, result=0
            int result = getMinTime(total, s);
            System.out.println("Test Single Server: " + (result == 0 ? "PASS" : "FAIL") + " (Expected 0, Got " + result + ")");
        }

        // Large test scenario (not checked explicitly here due to complexity):
        // We can just ensure the function runs in reasonable time for large input.
        // For a quick sanity check, we won't print PASS/FAIL, just ensure no exception:
        {
            int total = 1000000000; // large total_servers
            List<Integer> s = new ArrayList<>();
            // Add some servers randomly
            s.add(1);
            s.add(500000000);
            s.add(999999999);
            // Just run it, ensure no error
            int result = getMinTime(total, s);
            System.out.println("Large Input Test (No expected value, just runs): " + result);
        }
    }
}
