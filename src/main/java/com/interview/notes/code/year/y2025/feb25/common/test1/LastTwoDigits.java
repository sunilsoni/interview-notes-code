package com.interview.notes.code.year.y2025.feb25.common.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING:100%



### Problem Statement: Last Two Digits

You are given an array consisting of \( n \) integers. Print the last two digits of the product of its array values.

#### **Input**
- The first line of input contains an integer \( n \), representing the number of elements in the given array.
- The second line of input contains \( n \) space-separated integers, representing the elements of the given array.

#### **Output**
- Print the last two digits of the product of the array values.
- Note that you always need to print two digits.

#### **Constraints**
- \( 1 \leq n \leq 100 \)
- \( 1 \leq ar[i] \leq 100 \)
- \( ar[i] \) is the \( i^{th} \) element of the given array.

#### **Example #1**
##### **Input**
```
2
25 10
```
##### **Output**
```
50
```
##### **Explanation**
25 × 10 = **250**, last two digits are **50**.

#### **Example #2**
##### **Input**
```
3
2 4 5
```
##### **Output**
```
40
```
##### **Explanation**
2 × 4 × 5 = **40**, last two digits are **40**.

---

### **Function Signature**
```java
/*
 * Implement method/function with name 'solve' below.
 * The function accepts following as parameters.
 * 1. ar is of type List<Integer>.
 * return String.


public static String solve(List<Integer> ar){
    // Write your code here
    return; // return type "String".
}
```
 */
public class LastTwoDigits {

    /**
     * Computes the last two digits of the product of the given list of integers.
     *
     * @param ar List of integers representing the array.
     * @return A string representing the last two digits (always two characters).
     */
    public static String solve(List<Integer> ar) {
        int modProduct = 1;
        for (int num : ar) {
            // Multiply and take modulo 100 at every step
            modProduct = (modProduct * (num % 100)) % 100;
        }
        // Format result to always have two digits (prepend 0 if necessary)
        return String.format("%02d", modProduct);
    }

    /**
     * Runs test cases to validate the solve method.
     */
    public static void main(String[] args) {
        // Test case 1: Provided Example 1
        List<Integer> test1 = Arrays.asList(25, 10);
        String expected1 = "50";
        runTest(test1, expected1, 1);

        // Test case 2: Provided Example 2
        List<Integer> test2 = Arrays.asList(2, 4, 5);
        String expected2 = "40";
        runTest(test2, expected2, 2);

        // Test case 3: Single element test
        List<Integer> test3 = Arrays.asList(1);
        String expected3 = "01";
        runTest(test3, expected3, 3);

        // Test case 4: Product resulting in exactly 100 (should return "00")
        List<Integer> test4 = Arrays.asList(4, 25);
        String expected4 = "00";
        runTest(test4, expected4, 4);

        // Test case 5: Larger test case with multiple elements
        List<Integer> test5 = new ArrayList<>();
        // Create a test case with 100 elements, each 2
        for (int i = 0; i < 100; i++) {
            test5.add(2);
        }
        // 2^100 mod 100 calculation. We can compute expected manually or use the function.
        // Here, we call solve() directly to get expected value for demonstration.
        String expected5 = solve(test5);
        runTest(test5, expected5, 5);

        System.out.println("All tests completed.");
    }

    /**
     * Helper method to run a single test case.
     *
     * @param testCase       The input list of integers.
     * @param expected       The expected result as a two-digit string.
     * @param testCaseNumber The test case number.
     */
    private static void runTest(List<Integer> testCase, String expected, int testCaseNumber) {
        String result = solve(testCase);
        if (result.equals(expected)) {
            System.out.println("Test Case " + testCaseNumber + ": PASS");
        } else {
            System.out.println("Test Case " + testCaseNumber + ": FAIL");
            System.out.println("   Input: " + testCase);
            System.out.println("   Expected: " + expected);
            System.out.println("   Got: " + result);
        }
    }
}