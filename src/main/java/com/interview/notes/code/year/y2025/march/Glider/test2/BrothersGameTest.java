package com.interview.notes.code.year.y2025.march.Glider.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
WORKING 100

### **Brothers' Game**

Two brothers were playing a game. Their mother gave them an **array of numbers**, with each element being either **0 or 1**. She then asked them to **find out the maximum number of 1s** they could obtain by **inverting** a **contiguous** portion of the array.
(i.e., changing all **0s to 1s** and all **1s to 0s** for any contiguous subarray).

The younger brother thought he could solve the problem in his head, but the elder brother chose to **write a program** to find the **optimal solution**. Can you help the elder brother?

---

### **Input**
1. The first line of input contains an **integer N**, representing the **size of the array**.
2. The second line of input contains **N** space-separated integers, representing the **array elements**.

---

### **Output**
Print the **maximum number of 1s** that can be obtained after **one inversion**.

---

### **Constraints**
- \(1 \leq N \leq 100\)

---

### **Time Complexity**
- Optimize your solution for at least **O(N)**; otherwise, some **large test cases may fail**.

---

### **Example 1**
#### **Input**
```
5
0 1 0 0 1
```
#### **Output**
```
4
```
#### **Explanation**
We can **change the numbers from the third element till the fourth element** to get:
**`0 1 1 1 1`**, which has **four ones**.

---

### **Example 2**
#### **Input**
```
6
1 0 0 1 0 0
```
#### **Output**
```
5
```
#### **Explanation**
We can **change the numbers from the second element to the last element** to get:
**`1 1 1 0 1 1`**, which has **five ones**.

---
 */
public class BrothersGameTest {

    public static int solve(List<Integer> ar) {
        int totalOnes = (int) ar.stream().filter(x -> x == 1).count();

        // Convert the problem into finding the maximum subarray sum (Kadane's algorithm)
        int maxGain = 0;
        int currentGain = 0;

        for (int num : ar) {
            int val = (num == 0) ? 1 : -1;
            currentGain = Math.max(val, currentGain + val);
            maxGain = Math.max(maxGain, currentGain);
        }

        // Edge case: If all are ones, flipping any subarray decreases total ones by 1
        if (maxGain <= 0) {
            return totalOnes - 1;
        }

        return totalOnes + maxGain;
    }


    public static void main(String[] args) {
        // Provided test cases
        runTest(Arrays.asList(0, 1, 0, 0, 1), 4);
        runTest(Arrays.asList(1, 0, 0, 1, 0, 0), 5);

        // Edge case: All ones
        runTest(Arrays.asList(1, 1, 1, 1), 3);

        // Edge case: All zeros
        runTest(Arrays.asList(0, 0, 0, 0), 4);

        // Large input test case
        List<Integer> largeTest = Stream.generate(() -> 1).limit(100).collect(Collectors.toList());
        runTest(largeTest, 99);

        // Mixed large input
        largeTest = Stream.iterate(0, n -> n + 1)
                .limit(100)
                .map(i -> i % 2)
                .collect(Collectors.toList());
        runTest(largeTest, 51);
    }

    private static void runTest(List<Integer> input, int expected) {
        int result = solve(input);
        if (result == expected) {
            System.out.println("PASS - Input: " + input + " | Expected: " + expected + " | Got: " + result);
        } else {
            System.out.println("FAIL - Input: " + input + " | Expected: " + expected + " | Got: " + result);
        }
    }
}
