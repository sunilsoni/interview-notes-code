package com.interview.notes.code.months.nov24.geico.test2;

import java.util.*;
/*
WORKING


### Problem Statement:

You are given an array \( A \) consisting of \( N \) integers. The task is to find the maximum sum of two integers from \( A \) that share their first and last digits.

- For example:
  - 1007 and 167 share their first (1) and last (7) digits.
  - 2002 and 55 do not share both first and last digits.

**Write a function**:
```java
class Solution {
    public int solution(int[] A);
}
```

### Constraints:

- \( N \) is an integer within the range \([1 \ldots 100,000]\).
- Each element of array \( A \) is an integer within the range \([10 \ldots 1,000,000,000]\).

The function should return the maximum sum of two integers that share the same first and last digits. If no such pairs exist, the function should return \(-1\).

---

### Examples:

1. **Input**: \( A = [130, 191, 200, 10] \)
   - **Explanation**:
     The only integers in \( A \) that share first and last digits are 130 and 10.
     Sum: \( 130 + 10 = 140 \).
   - **Output**: 140

2. **Input**: \( A = [405, 45, 300, 300] \)
   - **Explanation**:
     There are two pairs of integers that share first and last digits:
     - \( (405, 45) \)
     - \( (300, 300) \)
     The sum of the two 300s is greater than the sum of 405 and 45.
     Sum: \( 300 + 300 = 600 \).
   - **Output**: 600

3. **Input**: \( A = [50, 222, 49, 52, 25] \)
   - **Explanation**:
     There are no two integers that share their first and last digits.
   - **Output**: \(-1\)

4. **Input**: \( A = [30, 909, 3190, 99, 3990, 9009] \)
   - **Explanation**:
     The maximum sum is \( 9009 + 909 = 9918 \).
   - **Output**: 9918

---

**Objective**:
Design an efficient algorithm to solve this problem given the constraints.

 */
public class MaxSumSharedDigits {
    public static void main(String[] args) {
        // Instantiate the Solution class
        Solution solution = new Solution();

        // Test Case 1
        int[] testCase1 = {130, 191, 200, 10};
        int expected1 = 140;
        test("Test Case 1", testCase1, expected1, solution);

        // Test Case 2
        int[] testCase2 = {405, 45, 300, 300};
        int expected2 = 600;
        test("Test Case 2", testCase2, expected2, solution);

        // Test Case 3
        int[] testCase3 = {50, 222, 49, 52, 25};
        int expected3 = -1;
        test("Test Case 3", testCase3, expected3, solution);

        // Test Case 4
        int[] testCase4 = {30, 909, 3190, 99, 3990, 9009};
        int expected4 = 9918;
        test("Test Case 4", testCase4, expected4, solution);

        // Additional Edge Case: No pairs with matching first and last digits
        int[] testCase5 = {11, 22, 33, 44};
        int expected5 = -1;
        test("Edge Case - No Pairs", testCase5, expected5, solution);

        // Additional Edge Case: All numbers have the same first and last digits
        int[] testCase6 = {101, 111, 121, 131};
        int expected6 = 252; // 131 + 121
        test("Edge Case - All Same First and Last Digits", testCase6, expected6, solution);

        // Large Data Test Case
        int N = 100000;
        int[] largeTestCase = new int[N];
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            // Generate numbers with first digit 1 and last digit 1
            largeTestCase[i] = 100000000 + rand.nextInt(90000000) * 10 + 1;
        }
        // Introduce two large numbers to ensure maximum sum
        largeTestCase[0] = 999999991;
        largeTestCase[1] = 999999981;
        int expectedLarge = largeTestCase[0] + largeTestCase[1];
        test("Large Data Test Case", largeTestCase, expectedLarge, solution);
    }

    private static void test(String testName, int[] A, int expected, Solution solution) {
        int result = solution.solution(A);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expected + ", Got: " + result);
        }
    }
}

class Solution {
    /**
     * Finds the maximum sum of two integers in the array that share the same first and last digits.
     * If no such pair exists, returns -1.
     *
     * @param A the input array of integers
     * @return the maximum sum of two integers with matching first and last digits, or -1 if none
     */
    public int solution(int[] A) {
        // Map to hold pairs of (firstDigit * 10 + lastDigit) to a list of numbers
        Map<Integer, int[]> digitPairMap = new HashMap<>();

        for (int number : A) {
            int firstDigit = getFirstDigit(number);
            int lastDigit = getLastDigit(number);
            int key = firstDigit * 10 + lastDigit;

            // Retrieve or initialize the array holding the top two numbers for this digit pair
            int[] topTwo = digitPairMap.getOrDefault(key, new int[]{-1, -1});

            // Update the top two numbers if necessary
            if (number > topTwo[0]) {
                topTwo[1] = topTwo[0];
                topTwo[0] = number;
            } else if (number > topTwo[1]) {
                topTwo[1] = number;
            }

            digitPairMap.put(key, topTwo);
        }

        int maxSum = -1;

        // Iterate through the map to find the maximum sum
        for (int[] topTwo : digitPairMap.values()) {
            if (topTwo[1] != -1) {
                int sum = topTwo[0] + topTwo[1];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    /**
     * Helper method to extract the first digit of a number.
     *
     * @param num the input number
     * @return the first digit of num
     */
    private int getFirstDigit(int num) {
        while (num >= 10) {
            num /= 10;
        }
        return num;
    }

    /**
     * Helper method to extract the last digit of a number.
     *
     * @param num the input number
     * @return the last digit of num
     */
    private int getLastDigit(int num) {
        return num % 10;
    }
}
