package com.interview.notes.code.year.y2025.march.Glider.test2;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
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

 */
public class LargestOddNumber {

    public static int solve(String S) {
        // Extract numbers using regex
        Matcher matcher = Pattern.compile("\\d+").matcher(S);

        // Find largest odd number from all numeric groupings
        return Stream.generate(() -> matcher.find() ? matcher.group() : null)
                     .takeWhile(Objects::nonNull)
                     .mapToInt(Integer::parseInt)
                     .filter(num -> num % 2 != 0) // Keep only odd numbers
                     .max()                       // Get maximum odd number
                     .orElse(-1);                 // If none found, return -1
    }

    public static void main(String[] args) {
        // Provided test cases
        runTest("gt12cty65mt1", 65);
        runTest("mkf43kdlcmk32klmv123", 123);

        // Additional edge case tests
        runTest("abc", -1);                         // No numbers
        runTest("123456", -1);                      // Only even number
        runTest("1a2b3c", 3);                       // Mixed single-digit numbers
        runTest("abc999xyz888777", 999);            // Multiple groups

        // Large input test
        String largeInput = "a" + "1".repeat(499) + "2b99999999";
        runTest(largeInput, 99999999);
    }

    private static void runTest(String input, int expected) {
        int result = solve(input);
        if (result == expected) {
            System.out.println("PASS: Input=\"" + abbreviate(input) + "\" Output=" + result);
        } else {
            System.out.println("FAIL: Input=\"" + abbreviate(input) + "\" Output=" + result + " Expected=" + expected);
        }
    }

    // Helper to abbreviate large strings for readability
    private static String abbreviate(String input) {
        int len = input.length();
        if (len > 20) {
            return input.substring(0, 10) + "..." + input.substring(len - 10);
        }
        return input;
    }
}
