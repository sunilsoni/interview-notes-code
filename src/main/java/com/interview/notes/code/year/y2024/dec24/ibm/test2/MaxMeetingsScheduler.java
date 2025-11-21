package com.interview.notes.code.year.y2024.dec24.ibm.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
WORKING


## Problem Statement
A product manager has to organize `n` meetings with different people. Meeting with each person results in an **increase** or **decrease** in the effectiveness index of the manager. The manager wants to organize the meetings such that the index **remains positive** for as many meetings as possible. Find the **maximum number of meetings** for which the effectiveness index is positive. The index at the beginning is `0`.

### **Note**:
After the meetings begin, the index must remain **above 0** to be positive.

---

### **Example**
Input:
```
n = 4
effectiveness = [1, -20, 3, -2]
```
Output:
```
3
```
**Explanation:**
One optimal meeting order is `[3, -2, 1, -20]`. The index is positive for the first three meetings, after which it becomes `3 - 2 + 1 = 1`. So, the answer is **3**.
There is no way to have 4 meetings with a positive index.

---

## **Function Description**
Complete the function `maxMeetings` in the editor.

**maxMeetings** has the following parameter:
- `int effectiveness[n]`: the increase or decrease in effectiveness for each meeting.

**Returns:**
- `int`: the maximum possible number of meetings while maintaining a positive index.

---

## Constraints:
- \( 1 \leq n \leq 10^5 \)
- \( -10^9 \leq \text{effectiveness}[i] \leq 10^9 \)

---

## Input Format For Custom Testing:
1. The first line contains an integer, `n`, the number of elements in `effectiveness`.
2. Each line `i` of the `n` subsequent lines (where \( 0 \leq i < n \)) contains an integer, `effectiveness[i]`.

---

## Sample Cases

### **Sample Case 0**
**Input:**
```
4
-3
0
2
1
```
**Output:**
```
3
```
**Explanation:**
One optimal rearrangement is `[2, 0, 1, -3]`.

---

### **Sample Case 1**
**Input:**
```
3
-3
0
-2
```
**Output:**
```
0
```
**Explanation:**
There is no arrangement that produces a positive index.

---

## Function Signature
```java
/*
 * Complete the 'maxMeetings' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY effectiveness as parameter.
 */


public class MaxMeetingsScheduler {

    /*
     * Complete the 'maxMeetings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY effectiveness as parameter.
     */
    public static int maxMeetings(List<Integer> effectiveness) {
        // Separate meetings into positive, zero, and negative
        List<Integer> positives = new ArrayList<>();
        List<Integer> zeros = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();

        for (int eff : effectiveness) {
            if (eff > 0) {
                positives.add(eff);
            } else if (eff == 0) {
                zeros.add(eff);
            } else {
                negatives.add(eff);
            }
        }

        // Sort negative meetings in descending order (less negative first)
        Collections.sort(negatives, Collections.reverseOrder());

        // Combine the meetings: positives -> zeros -> negatives
        List<Integer> orderedMeetings = new ArrayList<>();
        orderedMeetings.addAll(positives);
        orderedMeetings.addAll(zeros);
        orderedMeetings.addAll(negatives);

        // Simulate the meetings
        long index = 0;
        int count = 0;
        for (int eff : orderedMeetings) {
            index += eff;
            if (index > 0) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    // Method to run a single test case
    private static void runTestCase(int testCaseNumber, List<Integer> effectiveness, int expected) {
        int result = maxMeetings(effectiveness);
        if (result == expected) {
            System.out.println("Test Case " + testCaseNumber + ": PASS");
        } else {
            System.out.println("Test Case " + testCaseNumber + ": FAIL (Expected " + expected + ", Got " + result + ")");
        }
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 0
        testCases.add(new TestCase(0, Arrays.asList(-3, 0, 2, 1), 3));

        // Sample Case 1
        testCases.add(new TestCase(1, Arrays.asList(-3, 0, -2), 0));

        // Additional Test Cases
        // All positive
        testCases.add(new TestCase(2, Arrays.asList(5, 10, 15), 3));

        // All negative
        testCases.add(new TestCase(3, Arrays.asList(-1, -2, -3), 0));

        // Mix of positive and negative
        testCases.add(new TestCase(4, Arrays.asList(4, -1, 3, -2, 2, -3), 6)); // Updated Expected Output to 6

        // Zero effectiveness
        testCases.add(new TestCase(5, Arrays.asList(0, 0, 0), 0));

        // Large input with all positive
        List<Integer> largePositive = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            largePositive.add(1);
        }
        testCases.add(new TestCase(6, largePositive, 100000));

        // Large input with mixed values
        List<Integer> largeMixed = new ArrayList<>();
        for (int i = 1; i <= 50000; i++) {
            largeMixed.add(2);
        }
        for (int i = 1; i <= 50000; i++) {
            largeMixed.add(-1);
        }
        testCases.add(new TestCase(7, largeMixed, 100000));

        // Edge Case: Single meeting positive
        testCases.add(new TestCase(8, List.of(10), 1));

        // Edge Case: Single meeting negative
        testCases.add(new TestCase(9, List.of(-10), 0));

        // Edge Case: Effectiveness index becomes zero
        testCases.add(new TestCase(10, Arrays.asList(1, -1, 1), 3)); // Updated Expected Output to 3

        // Run all test cases
        for (TestCase tc : testCases) {
            runTestCase(tc.testCaseNumber, tc.effectiveness, tc.expected);
        }
    }

    // Helper class to represent a test case
    static class TestCase {
        int testCaseNumber;
        List<Integer> effectiveness;
        int expected;

        TestCase(int testCaseNumber, List<Integer> effectiveness, int expected) {
            this.testCaseNumber = testCaseNumber;
            this.effectiveness = effectiveness;
            this.expected = expected;
        }
    }
}
