package com.interview.notes.code.year.y2024.oct24.amazon.test21;

import java.util.*;

/*

WORKING


**Explanation**
We can apply prefix sum on the array `[1, 2, 3, -6]`.
The prefix sum array will be `[1, 3, 6, 0]`.
The index of the first non-positive is `3`, which is our answer.

---

**Constraints**
- \(1 \leq n \leq 10^5\)
- \(-10^9 \leq importance[i] \leq 10^9\)

---

**Input Format For Custom Testing**
The first line contains an integer, \( n \), the number of `importance`.
Each of the next \( n \) lines (where \( 0 \leq i < n \)) contains an integer `importance[i]`.

---

**Sample Case 0**
Sample Input For Custom Testing

STDIN        FUNCTION
```
2            → importance[] size n = 2
1            → importance = [1, 2]
2
```
Sample Output
```
-1
```
Explanation:
Two possible permutations of `[1, 2]` are `[1, 2]` and `[2, 1]`.
In any case, we can see that the prefix sum of both never turns non-negative.

---

**Sample Case 1**
Sample Input For Custom Testing

STDIN        FUNCTION
```
4            → importance[] size n = 4
1            → importance = [1, 2, -6, 3]
2
-6
3
```
Sample Output
```
3
```
Explanation:
We can apply prefix sum on the array `[1, 2, 3, -6]`.

---

**Code Question 1**
Amazon Fulfillment Center needs to optimize the arrangement of its packages. Each package has an importance level, represented by an array of integers called `importance`. The packages can be arranged in any order. The goal of the Fulfillment Center is to arrange the packages so that the prefix sum of `importance` never becomes non-positive (zero or negative).

If it is impossible to arrange the packages so that the prefix sum of `importance` never becomes non-positive, the objective is to maximize the index of the first non-positive (zero or negative) prefix sum. If there is no way to arrange the packages to achieve a positive prefix sum, the function should return `-1`.

Given \( n \) products and an array, determine the positive prefix sum of `importance`.

**Example**
Given, \( n = 3 \), `importance = [2, 1, -4]`
There is no way to get a `-1`.

**Prefix Sum**
| Prefix Sum                                      | First Index (0-based)                                  |
|-------------------------------------------------|-------------------------------------------------------|
| If we find the prefix sum of `importance` in this manner only, it will be `[2, 3, -1]`. | The first index where the prefix sum array turns negative is `2` |
| We can apply prefix sum on the array `[2, -4, 1]`.                 | The first index where the prefix sum array turns negative is `1` |
| We can apply prefix sum on the array `[-4, 1, 2]`.                | The first index where the prefix sum array turns negative is `1` |

So the answer is `2`. It can be proved that this is one of the most optimal arrangements by taking every possible permutation.

**Function Description**
Complete the function `getMaximizeIndex` in the editor below.

`getMaximizeIndex` has the following parameter:
- `int[] importance`: the importance of packages

**Returns**
- `int`: the maximum index of the first non-positive integer after arranging the array \( A \). If there is no such index, return `-1`.


 */
public class PackageImportanceOptimizer {
    public static int getMaximizeIndex(List<Integer> importance) {
        if (importance == null || importance.isEmpty()) {
            return -1;
        }

        int n = importance.size();
        if (n == 1) {
            return importance.get(0) <= 0 ? 0 : -1;
        }

        // Sort in descending order to maximize prefix sum
        List<Integer> sortedImportance = new ArrayList<>(importance);
        Collections.sort(sortedImportance, Collections.reverseOrder());

        // Calculate prefix sums
        long prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += sortedImportance.get(i);
            if (prefixSum <= 0) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase("Test Case 1", Arrays.asList(1, 2), -1);
        runTestCase("Test Case 2", Arrays.asList(1, 2, -6, 3), 3);
        runTestCase("Test Case 3", Arrays.asList(2, 1, -4), 2);
        runTestCase("Test Case 4", List.of(-1), 0);
        runTestCase("Test Case 5", Arrays.asList(1, -1), 1);

        // Edge cases
        runTestCase("Empty List", new ArrayList<>(), -1);
        runTestCase("Single Positive", List.of(5), -1);
        runTestCase("All Negative", Arrays.asList(-1, -2, -3), 0);
        runTestCase("Mixed Values", Arrays.asList(-5, 4, 2, -1, 3), 3);

        // Large input test cases
        testLargeInput(100);
        testLargeInput(1000);
        testLargeInput(10000);
        testLargeInput(100000);
    }

    private static void runTestCase(String testName, List<Integer> input, int expectedOutput) {
        System.out.println("\n" + testName + ":");
        System.out.println("Input: " + input);

        long startTime = System.nanoTime();
        int result = getMaximizeIndex(input);
        long endTime = System.nanoTime();

        boolean passed = result == expectedOutput;

        System.out.println("Expected: " + expectedOutput);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    private static void testLargeInput(int size) {
        List<Integer> largeInput = generateLargeInput(size);
        System.out.println("\nLarge Input Test (" + size + " elements):");

        long startTime = System.nanoTime();
        int result = getMaximizeIndex(largeInput);
        long endTime = System.nanoTime();

        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    private static List<Integer> generateLargeInput(int size) {
        List<Integer> largeInput = new ArrayList<>(size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            largeInput.add(random.nextInt(2000) - 1000); // Random numbers between -1000 and 1000
        }

        return largeInput;
    }
}