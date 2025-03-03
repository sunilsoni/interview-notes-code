package com.interview.notes.code.year.y2025.feb.cisco.test1;

/*
WORKING:

### **Problem Statement**
The current selected programming language is **Java**. We emphasize the submission of a fully working code over partially correct but efficient code. Once **submitted**, you cannot review this problem again. You can use **System.out.println()** to debug your code. However, **System.out.println()** may not work in case of a syntax/runtime error.
The version of **JDK being used is 1.8**.

#### **Note:**
- The main class name **must be** `"Solution"`.
- There is a set of **N jars** containing chocolates. Some jars may be **empty**.
- Andrew wants to pick **the maximum number of chocolates** but **cannot pick chocolates from adjacent jars**.

---

### **Task**
Write an algorithm to find the **maximum number of chocolates** that can be picked from the jars in such a way that **no chocolates are picked from jars next to each other**.

---

### **Input**
- The first line of input consists of an **integer** `numJars`, representing the number of jars (`N`).
- The next line consists of **N space-separated integers** representing the number of chocolates in each jar.

### **Output**
- Print the **maximum number of chocolates** that can be picked **without picking from adjacent jars**.

---

### **Constraints**
```
1 < N ≤ 1000
```

---

### **Example**
#### **Input:**
```
6
5 30 99 60 5 10
```
#### **Output:**
```
114
```
#### **Explanation:**
- Andrew picks chocolates from **1st (5), 3rd (99), and 6th (10) jars**.
- Maximum chocolates collected = **5 + 99 + 10 = 114**.

---

 */
public class MaxChocolates {

    // Function to find the maximum sum of chocolates without picking adjacent jars
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        if (n == 1) return arr[0];

        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], arr[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases array: each test case is an array where the first element is the expected output
        // followed by the input jar values.
        Object[][] testCases = {
                // Provided example: 6 jars -> [5, 30, 99, 60, 5, 10], expected output: 114
                {114, new int[]{5, 30, 99, 60, 5, 10}},
                // Edge case: Only one jar.
                {7, new int[]{7}},
                // Edge case: Two jars.
                {10, new int[]{10, 5}},
                // More test cases:
                {0, new int[]{}}, // No jar case.
                // Test case with alternating pattern
                {25, new int[]{5, 5, 5, 5, 5}},
                // Large input test: simulate 1000 jars with random numbers between 1 and 100
                // For simplicity, we won't manually list all 1000 values; we generate them.
                // The expected value is calculated using the same method, ensuring it runs fast.
        };

        // Testing simple cases
        System.out.println("Running basic test cases:");
        for (int i = 0; i < testCases.length; i++) {
            // Skip the large input here; we'll run it separately.
            if (i == testCases.length - 1) break;
            int expected = (Integer) testCases[i][0];
            int[] inputArr = (int[]) testCases[i][1];
            int result = maxSum(inputArr);
            System.out.println("Test " + (i + 1) + ": Expected = " + expected + ", Got = " + result + " -> " + (expected == result ? "PASS" : "FAIL"));
        }

        // Testing with large data input
        int n = 1000;
        int[] largeInput = new int[n];
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < n; i++) {
            largeInput[i] = rand.nextInt(100) + 1; // Random number between 1 and 100
        }
        // For large input, we only test for performance and that the function completes.
        long startTime = System.currentTimeMillis();
        int largeResult = maxSum(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test: Result = " + largeResult + ", Time taken = " + (endTime - startTime) + " ms");
    }
}
