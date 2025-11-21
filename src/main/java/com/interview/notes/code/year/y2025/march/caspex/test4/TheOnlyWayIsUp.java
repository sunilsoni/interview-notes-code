package com.interview.notes.code.year.y2025.march.caspex.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
WORKING 100

### **Problem Statement: The Only Way is Up**
A sequence, `ar[]`, is called a **rising sequence** if for each `i > 0`, the condition `ar[i] > ar[i-1]` holds.

Given:
- A sequence `ar[]`
- A positive integer `B`

Your task is to **convert** the sequence into a rising sequence by **adding B** to one or all of its elements any number of times.

#### **Objective**
Find the **total number of times** you need to add `B` to make `ar[]` a rising sequence.

---

### **Input Format**
1. First line → Integer `B` → Value to be added.
2. Second line → Integer `N` → Size of the array.
3. Third line → `N` space-separated integers → Array elements.

---

### **Expected Output**
- A single integer → Total number of times `B` is added.

---

### **Constraints**
- \(2 \leq N \leq 2000\)
- \(1 \leq B \leq 10^6\)
- \(1 \leq ar[i] \leq 10^6\)
- **Optimal solution required** → Brute force \(O(N^2)\) may be too slow.

---

### **Example 1**
#### **Input**
```
2
4
1 3 3 2
```
#### **Output**
```
3
```
#### **Explanation**
1. `a[2] = a[1] = 3`, so add `B = 2` once → `[1, 3, 5, 2]`
2. `a[3] < a[2]`, so add `B = 2` **twice** → `[1, 3, 5, 6]`
3. Final sequence is **rising**.
4. **Total additions = 1 + 2 = 3**.

---

### **Example 2**
#### **Input**
```
1
2
1 1
```
#### **Output**
```
1
```
#### **Explanation**
- `a[1] = a[0]`, so add `B = 1` once → `[1, 2]` (rising sequence).
- **Total additions = 1**.

---
 */

/**
 * Class to solve "The Only Way is Up" problem.
 * The goal is to convert a given sequence into a strictly increasing (rising) sequence by adding B to elements as needed.
 */
public class TheOnlyWayIsUp {

    /**
     * This method calculates the total number of times B must be added to the sequence
     * so that the sequence becomes strictly increasing.
     *
     * @param B  The positive integer value to add.
     * @param ar The list of integers representing the sequence.
     * @return The total number of times B is added.
     */
    public static int solve(int B, List<Integer> ar) {
        int totalAdditions = 0; // Counter to track total additions required

        // Iterate through the array starting from the second element (index 1)
        for (int i = 1; i < ar.size(); i++) {
            // Check if the current element is not strictly greater than the previous element
            if (ar.get(i) <= ar.get(i - 1)) {
                // Calculate the difference needed: (previous element + 1) minus the current element
                int diff = ar.get(i - 1) + 1 - ar.get(i);

                // Compute the number of times to add B using ceiling division:
                // (diff + B - 1) / B gives the smallest integer >= diff/B
                int addTimes = (diff + B - 1) / B;

                // Increase the total additions by the number of times B was added
                totalAdditions += addTimes;

                // Update the current element by adding B addTimes times
                ar.set(i, ar.get(i) + addTimes * B);
            }
        }
        return totalAdditions; // Return the total number of additions needed
    }

    /**
     * Main method to run the solution and test cases.
     */
    public static void main(String[] args) {
        // Execute all test cases to validate the solution.
        runTests();
    }

    /**
     * This method runs multiple test cases (including edge cases and large inputs) to verify the solution.
     */
    private static void runTests() {
        // Create a list to store our test cases using our custom TestCase class
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Provided sample test case.
        // For B = 2 and array [1, 3, 3, 2]:
        // - a[2] is not > a[1] so we add 2 once (3 becomes 5)
        // - a[3] is not > updated a[2]=5 so we add 2 twice (2 becomes 6)
        // Total additions = 1 + 2 = 3.
        testCases.add(new TestCase(2, Arrays.asList(1, 3, 3, 2), 3));

        // Test Case 2: Provided sample test case.
        // For B = 1 and array [1, 1]:
        // - a[1] is not > a[0], so we add 1 once to get [1, 2].
        testCases.add(new TestCase(1, Arrays.asList(1, 1), 1));

        // Test Case 3: Already strictly increasing sequence.
        // For B = 5 and array [1, 2, 3, 4, 5], no additions are needed.
        testCases.add(new TestCase(5, Arrays.asList(1, 2, 3, 4, 5), 0));

        // Test Case 4: Edge case with a single element.
        // With one element, the sequence is trivially rising.
        testCases.add(new TestCase(10, List.of(100), 0));

        // Test Case 5: Large data test case for performance testing.
        // Create a sequence of 2000 elements, all starting with value 1.
        int n = 2000;
        List<Integer> largeTest = new ArrayList<>(Collections.nCopies(n, 1));
        // For B = 1, each element (starting from the second) must be incremented such that:
        // a[1] becomes 2, a[2] becomes 3, ..., a[n-1] becomes n.
        // The total additions will be the sum of 1 to (n - 1) = (n-1)*n/2.
        int expectedLarge = (n - 1) * n / 2;
        testCases.add(new TestCase(1, largeTest, expectedLarge));

        // Iterate over all test cases and check if the solution produces the expected result.
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            // Clone the list to avoid modifying the original test data.
            List<Integer> inputList = new ArrayList<>(tc.ar);
            // Call the solve method with current test case parameters.
            int result = solve(tc.B, inputList);
            // Print pass/fail result along with details if the test fails.
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL. Expected: " + tc.expected + ", Got: " + result);
            }
        }
    }

    /**
     * Inner class to store individual test case details.
     */
    static class TestCase {
        int B;                  // The value to be added.
        List<Integer> ar;       // The sequence (array) of integers.
        int expected;           // The expected total number of additions.

        /**
         * Constructor to initialize the test case.
         *
         * @param B        The addition value.
         * @param ar       The input sequence.
         * @param expected The expected number of additions.
         */
        TestCase(int B, List<Integer> ar, int expected) {
            this.B = B;
            this.ar = ar;
            this.expected = expected;
        }
    }
}