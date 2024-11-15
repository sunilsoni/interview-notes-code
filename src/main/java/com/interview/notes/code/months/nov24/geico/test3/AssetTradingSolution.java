package com.interview.notes.code.months.nov24.geico.test3;

import java.util.*;
/*
WORKING:


### Problem Statement:

You are given a record of the historical prices of an investment asset from the last \( N \) days. The goal is to analyze the record to calculate the maximum possible income you could make. Assume you started with one asset of this type and could hold at most one at a time. You could choose to sell the asset whenever you held one. If you did not hold an asset at some moment, you could always afford to buy an asset (assume you had infinite money available).

**Write a function**:
```java
class Solution {
    public int solution(int[] A);
}
```

### Constraints:

- \( N \) is an integer within the range \([1 \ldots 200,000]\).
- Each element of array \( A \) is an integer within the range \([0 \ldots 1,000,000,000]\).

The function should return the maximum income possible. If the result is large, return its last nine digits (i.e., return the result modulo \( 1,000,000,000 \)).

---

### Examples:

1. **Input**: \( A = [4, 1, 2, 3] \)
   - **Explanation**:
     Sell the product on the first day (price = 4), buy it on the second day (price = 1), and sell it again on the last day (price = 3).
     Income: \( 4 - 1 + 3 = 6 \).
   - **Output**: 6

2. **Input**: \( A = [1, 2, 3, 3, 2, 1, 5] \)
   - **Explanation**:
     Sell the product when its value was 3, buy it when it changed to 1, and sell it again when it was worth 5.
     Income: \( 3 - 1 + 5 = 7 \).
   - **Output**: 7

3. **Input**: \( A = [1000000000, 1, 2, 2, 1000000000, 1, 1000000000] \)
   - **Explanation**:
     The maximum possible income is \( 2999999998 \), whose last 9 digits are \( 999999998 \).
   - **Output**: \( 999999998 \).

---

**Objective**:
Design an efficient algorithm to solve this problem given the constraints.

 */
public class AssetTradingSolution {

    /**
     * Calculates the maximum possible income from buying and selling an asset given its prices over N days.
     * 
     * @param A An array of integers representing the asset prices over N days.
     * @return The maximum possible income modulo 1,000,000,000 (last nine digits).
     */
    public int solution(int[] A) {
        long income = 0;
        boolean holding = true; // Start with one asset

        int N = A.length;

        for (int i = 0; i < N - 1; i++) {
            if (holding) {
                // If the price is going to drop tomorrow, sell today
                if (A[i] > A[i + 1]) {
                    income += A[i];
                    holding = false;
                }
            } else {
                // If the price is going to rise tomorrow, buy today
                if (A[i] < A[i + 1]) {
                    income -= A[i];
                    holding = true;
                }
            }
        }

        // Sell any remaining asset on the last day
        if (holding) {
            income += A[N - 1];
        }

        // Return the last nine digits of the income
        return (int) (income % 1_000_000_000);
    }

    public static void main(String[] args) {
        AssetTradingSolution solution = new AssetTradingSolution();

        // Test Case 1
        int[] testCase1 = {4, 1, 2, 3};
        int expected1 = 6;
        int result1 = solution.solution(testCase1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL"));

        // Test Case 2
        int[] testCase2 = {1, 2, 3, 3, 2, 1, 5};
        int expected2 = 7;
        int result2 = solution.solution(testCase2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL"));

        // Test Case 3
        int[] testCase3 = {1000000000, 1, 2, 2, 1000000000, 1, 1000000000};
        int expected3 = 999999998;
        int result3 = solution.solution(testCase3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL"));

        // Edge Case: Increasing prices
        int[] testCase4 = {1, 2, 3, 4, 5};
        int expected4 = 5;
        int result4 = solution.solution(testCase4);
        System.out.println("Test Case 4 (Increasing prices): " + (result4 == expected4 ? "PASS" : "FAIL"));

        // Edge Case: Decreasing prices
        int[] testCase5 = {5, 4, 3, 2, 1};
        int expected5 = 5;
        int result5 = solution.solution(testCase5);
        System.out.println("Test Case 5 (Decreasing prices): " + (result5 == expected5 ? "PASS" : "FAIL"));

        // Edge Case: Constant prices
        int[] testCase6 = {5, 5, 5, 5};
        int expected6 = 5;
        int result6 = solution.solution(testCase6);
        System.out.println("Test Case 6 (Constant prices): " + (result6 == expected6 ? "PASS" : "FAIL"));

        // Large Data Test Case
        int[] testCase7 = new int[200000];
        Arrays.fill(testCase7, 1);
        int expected7 = 1;
        int result7 = solution.solution(testCase7);
        System.out.println("Test Case 7 (Large data input): " + (result7 == expected7 ? "PASS" : "FAIL"));
    }
}
