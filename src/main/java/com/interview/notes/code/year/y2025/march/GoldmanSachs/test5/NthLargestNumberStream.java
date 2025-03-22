package com.interview.notes.code.year.y2025.march.GoldmanSachs.test5;

import java.util.PriorityQueue;

/*
Here's a well-structured and interview-ready question based on the scenario you described:

---

### 🧠 **System Design + Data Structure Question: Nth Largest Number in a Stream**

**Question Prompt:**

You are designing a real-time game or system where a user selects a number `N` at the beginning, and then an **infinite stream of integers** starts arriving one by one. At any given point, you are required to **output the Nth largest number seen so far**.

#### Example:
If `N = 3` and the input stream is:
```
Input: 5 → 4 → 2 → 1 → 7
Output: null → null → 2 → 2 → 4
```
**Explanation:**
- After receiving [5], [5, 4] → not enough elements → output `null`
- After [5, 4, 2] → 3 elements → 3rd largest is 2 → output `2`
- After [5, 4, 2, 1] → top 3 are [5, 4, 2] → 3rd largest is `2`
- After [5, 4, 2, 1, 7] → top 3 are [7, 5, 4] → 3rd largest is `4`

---

### 🎯 Your Tasks:

1. **Design an efficient algorithm** that can process this unbounded stream in real-time and output the Nth largest number seen so far.
2. **Describe the data structure** you would use and **why**.
3. **What optimizations** would you propose if:
   - `N` is very large (e.g., 10^6)
   - The stream is truly infinite and high-frequency
4. **What are the time and space complexities** of your approach?

---

### ⚙️ Constraints:
- `1 <= N <= 10^6`
- Stream is **infinite**
- You must return results in **real-time**

---

### 💡 Follow-up:
- How would you modify your design if the stream allows deletion of old elements?
- Can you handle duplicate numbers in the stream?

---
 */
public class NthLargestNumberStream {

    private PriorityQueue<Integer> minHeap;
    private int n;

    public NthLargestNumberStream(int n) {
        this.n = n;
        minHeap = new PriorityQueue<>();
    }

    public static void main(String[] args) {
        testSolution();
    }

    private static void testSolution() {
        boolean passed = true;

        // Provided example test
        int[] stream = {5, 4, 2, 1, 7};
        Integer[] expected = {null, null, 2, 2, 4};
        passed &= runTest(3, stream, expected, "Provided Test Case");

        // Edge case test: N=1, always output max seen so far
        int[] stream2 = {3, 6, 1, 10, 8};
        Integer[] expected2 = {3, 6, 6, 10, 10};
        passed &= runTest(1, stream2, expected2, "Edge Test Case (N=1)");

        // Large data input test
        int largeN = 10000;
        int largeStreamSize = 100000;
        int[] largeStream = new int[largeStreamSize];
        for (int i = 0; i < largeStreamSize; i++) {
            largeStream[i] = i + 1; // ascending numbers
        }
        // After seeing numbers from 1 to 10000, the Nth largest (10000th largest) is 1, then 2, then 3, and so on.
        Integer[] expectedLarge = new Integer[largeStreamSize];
        for (int i = 0; i < largeStreamSize; i++) {
            expectedLarge[i] = (i + 1 >= largeN) ? (i - largeN + 2) : null;
        }
        passed &= runTest(largeN, largeStream, expectedLarge, "Large Data Test Case");

        System.out.println("\nAll tests " + (passed ? "PASSED" : "FAILED"));
    }

    private static boolean runTest(int n, int[] stream, Integer[] expected, String testName) {
        NthLargestNumberStream nthLargest = new NthLargestNumberStream(n);
        boolean testPassed = true;

        for (int i = 0; i < stream.length; i++) {
            Integer result = nthLargest.add(stream[i]);
            if ((expected[i] == null && result != null) ||
                    (expected[i] != null && !expected[i].equals(result))) {
                testPassed = false;
                System.out.printf("Test [%s] failed at input index %d: Expected: %s, Got: %s%n",
                        testName, i, expected[i], result);
                break;
            }
        }

        if (testPassed) {
            System.out.println("Test [" + testName + "] PASSED");
        }

        return testPassed;
    }

    public Integer add(int num) {
        minHeap.offer(num);
        if (minHeap.size() > n) {
            minHeap.poll();
        }
        return (minHeap.size() < n) ? null : minHeap.peek();
    }
}
