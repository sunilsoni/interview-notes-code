package com.interview.notes.code.months.oct24.test27;

import java.util.*;
/*
WORKING


### Grand Number Game

In a thrilling game scenario, you are presented with an array containing `2N` positive integers. Alongside this, you are equipped with `N` operations to strategically manipulate these numbers. In each operation, you have the power to choose any two positive integers from the array.

To determine your score in each round, the algorithm calculates the **greatest common divisor (GCD)** of the selected numbers, multiplying it by the corresponding operation number. The resulting value contributes to your total score. The ultimate objective is to optimize your actions and achieve the highest possible total score.

Can you devise a winning strategy and uncover the maximum total score?

---

**Input**

- The first line of input contains an integer `N`, representing the number of operations.
- The second line of input contains `2N` space-separated integers, representing the array.

**Output**

- Print the maximum total score.

**Constraints**

- \( 1 \leq N \leq 10 \)
- \( 1 \leq arr_i \leq 10^9 \)

---

### Examples

#### Example #1

- **Input**:
  ```
  2
  3 4 9 5
  ```

- **Output**:
  ```
  7
  ```

**Explanation**:
Max score is \( 1 \times \text{gcd}(4, 5) + 2 \times \text{gcd}(3, 9) = 7 \).

#### Example #2

- **Input**:
  ```
  3
  1 2 3 4 5 6
  ```

- **Output**:
  ```
  14
  ```

**Explanation**:
Max score is \( (1 \times \text{gcd}(1, 5) = 1) + (2 \times \text{gcd}(2, 4) = 4) + (3 \times \text{gcd}(3, 6) = 9) = 14 \).

---

### Function Signature

```java
/*
 * Implement method/function with name 'solve' below.
 * The function accepts the following as parameters:
 *   1. arr is of type List<Integer>.
 * Returns: int.

public static int solve(List<Integer> arr){
    // Write your code here

    return; // return type "int".
}
```
 */
/**
 * Grand Number Game Solver
 *
 * Solution Approach:
 * - Use recursive dynamic programming with memoization and bitmasking to represent used numbers.
 * - At each step, try all possible pairs of unused numbers.
 * - Calculate the operation number based on the number of pairs formed so far.
 * - Use 'long' data type to handle large numbers.
 */
public class GrandNumberGame {

    private static int N;
    private static long[] numbers;
    private static Map<Integer, Long> memo;

    /**
     * Solves the Grand Number Game problem.
     *
     * @param arr List of integers representing the array.
     * @return The maximum total score.
     */
    public static int solve(List<Integer> arr) {
        N = arr.size() / 2;
        numbers = new long[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            numbers[i] = arr.get(i);
        }
        memo = new HashMap<>();
        long result = dfs(0);
        return (int) result;
    }

    /**
     * Recursive function to compute the maximum score using dynamic programming.
     *
     * @param bitmask Bitmask representing the used numbers.
     * @return The maximum score achievable from the current state.
     */
    private static long dfs(int bitmask) {
        if (memo.containsKey(bitmask)) {
            return memo.get(bitmask);
        }
        if (bitmask == (1 << (2 * N)) - 1) {
            return 0; // All numbers are used
        }
        long maxScore = 0;
        List<Integer> unusedIndices = new ArrayList<>();
        for (int i = 0; i < 2 * N; i++) {
            if ((bitmask & (1 << i)) == 0) {
                unusedIndices.add(i);
            }
        }
        int k = (Integer.bitCount(bitmask) / 2) + 1;
        for (int i = 0; i < unusedIndices.size(); i++) {
            int idx1 = unusedIndices.get(i);
            for (int j = i + 1; j < unusedIndices.size(); j++) {
                int idx2 = unusedIndices.get(j);
                int newBitmask = bitmask | (1 << idx1) | (1 << idx2);
                long gcdValue = gcd(numbers[idx1], numbers[idx2]);
                long currentScore = k * gcdValue;
                long totalScore = currentScore + dfs(newBitmask);
                if (totalScore > maxScore) {
                    maxScore = totalScore;
                }
            }
        }
        memo.put(bitmask, maxScore);
        return maxScore;
    }

    /**
     * Computes the Greatest Common Divisor (GCD) of two long integers using the
     * Euclidean algorithm.
     *
     * @param a First long integer.
     * @param b Second long integer.
     * @return The GCD of a and b.
     */
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    /**
     * Main method to test the solve function with various test cases.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // List of test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test case 1: Example provided in the problem
        testCases.add(new TestCase(Arrays.asList(3, 4, 9, 5), 7));

        // Test case 2: Another example provided in the problem
        testCases.add(new TestCase(Arrays.asList(1, 2, 3, 4, 5, 6), 14));

        // Test case 3: Edge case with N = 1
        testCases.add(new TestCase(Arrays.asList(1, 1), 1));

        // Test case 4: All numbers are the same
        testCases.add(new TestCase(Arrays.asList(5, 5, 5, 5), 15));

        // Test case 5: All numbers are co-prime
        testCases.add(new TestCase(Arrays.asList(2, 3, 5, 7), 7));

        // Test case 6: Large numbers
        testCases.add(new TestCase(Arrays.asList(1000000000, 999999999, 999999998, 999999997), 2999999995L));

        // Test case 7: N = 10 with small numbers
        List<Integer> largeTestCase = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            largeTestCase.add(i);
        }
        testCases.add(new TestCase(largeTestCase, 167));

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = solve(tc.arr);
            boolean pass = result == tc.expected;
            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + tc.expected + ", Got: " + result);
            }
        }
    }

    /**
     * Helper class to store test cases.
     */
    static class TestCase {
        List<Integer> arr;
        long expected;

        TestCase(List<Integer> arr, long expected) {
            this.arr = arr;
            this.expected = expected;
        }
    }
}
