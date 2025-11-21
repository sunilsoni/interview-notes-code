package com.interview.notes.code.year.y2025.march.Glider.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
WORKING 100



### **Biggest Rectangle Problem Statement**

You are given data about a **bar graph** as an array of integers, where each element represents the **height** of a bar from **left to right**. You depict this data as a **bar graph** and realize that you can form **rectangles** by taking the filled areas of a bar or by combining a number of contiguous bars.

Assuming that all bars have **equal width**, you are curious to **find the area of the largest rectangle** that can be formed.

For example, the **largest rectangle** that can be formed out of the bars in the bar graph represented as **7 3 6 5 6 0 7** is marked in red in the image. The **maximum rectangular area** is **5 × 3 = 15**.

---

### **Input**
1. The first line of input contains an integer **N**, representing the **size of the array**.
2. The second line of input contains **N** space-separated integers, representing the heights of the bars.

---

### **Output**
Print the **maximum rectangular area** possible from the **contiguous bars**.

---

### **Constraints**
- \(1 \leq N \leq 100\)
- \(0 \leq B[i] \leq 1000\)

---

### **Example 1**
#### **Input**
```
7
7 3 6 5 6 0 7
```
#### **Output**
```
15
```
#### **Explanation**
The largest rectangle area is **3 × 5 = 15**.

---

### **Example 2**
#### **Input**
```
4
9 1 1 9
```
#### **Output**
```
9
```
#### **Explanation**
The largest rectangle will comprise either the **first or the last bar**, and their **area is 9**.

---
 */
public class LargestRectangle {

    /**
     * Returns the maximum rectangular area in a histogram given by list B.
     * Uses a stack-based approach to achieve O(n) complexity.
     *
     * @param B List of bar heights.
     * @return Maximum rectangular area.
     */
    public static int maxArea(List<Integer> B) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int index = 0;
        int n = B.size();

        // Process every bar in the histogram
        while (index < n) {
            // If the current bar is higher than the bar at stack top or stack is empty, push index.
            if (stack.isEmpty() || B.get(index) >= B.get(stack.peek())) {
                stack.push(index++);
            } else {
                // Calculate area with the bar at the top as the smallest bar.
                int top = stack.pop();
                int width = stack.isEmpty() ? index : index - stack.peek() - 1;
                maxArea = Math.max(maxArea, B.get(top) * width);
            }
        }

        // Process remaining bars in the stack.
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int width = stack.isEmpty() ? index : index - stack.peek() - 1;
            maxArea = Math.max(maxArea, B.get(top) * width);
        }
        return maxArea;
    }

    /**
     * Runs a test case by comparing the expected output with the actual result.
     *
     * @param testName Name of the test case.
     * @param bars     List of bar heights.
     * @param expected Expected maximum rectangular area.
     */
    public static void runTestCase(String testName, List<Integer> bars, int expected) {
        int result = maxArea(bars);
        if (result == expected) {
            System.out.println(testName + " : PASS");
        } else {
            System.out.println(testName + " : FAIL. Expected: " + expected + ", Got: " + result);
        }
    }

    public static void main(String[] args) {
        // Provided test cases
        runTestCase("Test Case 1 (Example 1)", Arrays.asList(7, 3, 6, 5, 6, 0, 7), 15);
        runTestCase("Test Case 2 (Example 2)", Arrays.asList(9, 1, 1, 9), 9);

        // Additional test cases

        // Single bar test
        runTestCase("Test Case 3 (Single Bar)", List.of(5), 5);

        // Uniform bars test
        runTestCase("Test Case 4 (Uniform Bars)", Arrays.asList(2, 2, 2, 2, 2), 10);

        // Decreasing order test: [5, 4, 3, 2, 1]
        // Expected maximum area is 9 (formed by the bars [3,2,1] with height 3).
        runTestCase("Test Case 5 (Decreasing Order)", Arrays.asList(5, 4, 3, 2, 1), 9);

        // Increasing order test: [1, 2, 3, 4, 5]
        // Expected maximum area is also 9 (formed by the bars [3,4,5] with height 3).
        runTestCase("Test Case 6 (Increasing Order)", Arrays.asList(1, 2, 3, 4, 5), 9);

        // All zeros test
        runTestCase("Test Case 7 (All Zeros)", Arrays.asList(0, 0, 0), 0);

        // Large input test: 100 bars, each of height 100.
        // Expected maximum area = 100 * 100 = 10000.
        List<Integer> largeTest = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeTest.add(100);
        }
        runTestCase("Test Case 8 (Large Input)", largeTest, 10000);
    }
}