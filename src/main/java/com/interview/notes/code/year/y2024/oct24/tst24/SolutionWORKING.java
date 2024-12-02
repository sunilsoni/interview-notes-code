package com.interview.notes.code.year.y2024.oct24.tst24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING


### Problem Statement

The owner of HackerMall loves organized items. A row of items is organized if the parity (even or odd) is different for each adjacent stack of items. To organize the row, half of the items in any stack can be removed. This can happen as many times and on as many stacks as required. Determine the minimum number of operations needed to organize a row.

More formally, given an array `items[]` of integers of length `n`, the array is organized if for each `x` less than `n - 1`, `items[x] mod 2 != items[x + 1] mod 2`. A mod B is the remainder of `A` divided by `B`. In one operation, one can choose an element and divide it by 2. That is, if one chooses index `x`, then do `items[x] = floor(items[x] / 2)`. The goal is to return the minimum number of operations required to organize the array.

### Function Description

Complete the function `getMinimumOperations` in the editor below.

`getMinimumOperations` has the following parameter(s):
- `int items[n]`: a row of stacks of items.

**Returns:**
- `int`: the minimum number of operations needed to organize the array.

### Constraints
- \( 1 \leq n \leq 10^5 \)
- \( 1 \leq items[i] \leq 2^{30} \)

### Input Format for Custom Testing
- The first line contains an integer, `n`, the size of the array `items`.
- Each of the next `n` lines contains an integer `items[i]`.

### Examples

#### Example 1
**Input:**
```
n = 5
items = [6, 5, 4, 7, 3]
```

**Output:**
```
3
```

**Explanation:**
The array is not organized since, for example, `items[2] mod 2 == items[3] mod 2`.
Here is a way to make `items` organized in 3 moves:
1. Choose the 3rd index and divide it by 2; the new array is `[6, 5, 4, 7, 3]`.
2. Choose the 5th index and divide it by 2; `items = [6, 5, 4, 7, 1]`.
3. Choose the 5th index and divide it by 2; `items = [6, 5, 4, 7, 0]`.

#### Example 2
**Input:**
```
n = 5
items = [2, 1, 4, 7, 2]
```

**Output:**
```
0
```

**Explanation:**
The array is already organized with alternating parity, so no operations are needed.

#### Example 3
**Input:**
```
items = [4, 10, 10, 6, 2]
```

**Output:**
```
2
```

**Explanation:**
The array is not organized since, for example, `items[2] mod 2 = items[3] mod 2`.
One way to organize the array is shown using 1-based indexing.
1. Choose the 2nd index and divide it by 2; the new array is `[4, 5, 10, 6, 2]`.
2. Choose the 4th index and divide it by 2; the new array is `[4, 5, 10, 3, 2]`.

`[4, 5, 10, 3, 2]` is an organized array, so the minimum number of operations needed is `2`.

---

### Code Skeleton

```java
/*
 * Complete the 'getMinimumOperations' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY items as parameter.


 */

/**
 * Solution class containing methods to organize the array with minimum operations.
 */
public class SolutionWORKING {

    /**
     * Computes the minimum number of operations needed to organize the array.
     *
     * @param items a list of integers representing the stacks of items.
     * @return the minimum number of operations required.
     */
    public static int getMinimumOperations(List<Integer> items) {
        int n = items.size();
        int costPattern1 = 0; // Starting with even parity at index 0
        int costPattern2 = 0; // Starting with odd parity at index 0


        for (int i = 0; i < n; i++) {
            int x = items.get(i);


            // Pattern 1: Even indices even, odd indices odd
            int desiredParity1 = i % 2;
            int cost1 = computeCost(x, desiredParity1);
            if (cost1 == Integer.MAX_VALUE) {
                costPattern1 = Integer.MAX_VALUE;
            } else if (costPattern1 != Integer.MAX_VALUE) {
                costPattern1 += cost1;
            }


            // Pattern 2: Even indices odd, odd indices even
            int desiredParity2 = (i + 1) % 2;
            int cost2 = computeCost(x, desiredParity2);
            if (cost2 == Integer.MAX_VALUE) {
                costPattern2 = Integer.MAX_VALUE;
            } else if (costPattern2 != Integer.MAX_VALUE) {
                costPattern2 += cost2;
            }
        }


        int minCost = Math.min(costPattern1, costPattern2);
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }


    /**
     * Computes the cost to change the parity of a number to the desired parity.
     *
     * @param x             the original number.
     * @param desiredParity the desired parity to achieve.
     * @return the minimum number of operations required to achieve the desired parity.
     */

    private static int computeCost(int x, int desiredParity) {
        int count = 0;
        while (x > 0) {
            if (x % 2 == desiredParity) {
                return count;
            }
            x /= 2;
            count++;
        }
        // If desired parity is odd and x becomes 0, it's impossible
        if (desiredParity == 1) {
            return Integer.MAX_VALUE;
        }
        return count;
    }


    /**
     * Main method to run test cases and check if they pass or fail.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                Arrays.asList(6, 5, 4, 7, 3),
                3
        ));

        // Example 2
        testCases.add(new TestCase(
                Arrays.asList(2, 1, 4, 7, 2),
                0
        ));

        // Example 3
        testCases.add(new TestCase(
                Arrays.asList(4, 10, 10, 6, 2),
                2
        ));

        // Additional test case: All elements have the same parity (even)
        int n = 10; // Adjust n for larger sizes
        List<Integer> allEvenItems = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allEvenItems.add(2);
        }
        testCases.add(new TestCase(
                allEvenItems,
                n / 2
        ));

        // Additional test case: All elements have the same parity (odd)
        List<Integer> allOddItems = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allOddItems.add(3);
        }
        testCases.add(new TestCase(
                allOddItems,
                (n + 1) / 2
        ));

        // Large input test case: Alternating parities (already organized)
        int largeN = 100000;
        List<Integer> largeItems = new ArrayList<>(largeN);
        for (int i = 0; i < largeN; i++) {
            largeItems.add(i % 2 == 0 ? 2 : 3);
        }
        testCases.add(new TestCase(
                largeItems,
                0
        ));

        // Run test cases
        int testNumber = 1;
        for (TestCase testCase : testCases) {
            int result = getMinimumOperations(testCase.items);
            if (result == testCase.expected) {
                System.out.println("Test Case " + testNumber + ": PASS");
            } else {
                System.out.println("Test Case " + testNumber + ": FAIL");
                System.out.println("Expected: " + testCase.expected + ", Got: " + result);
            }
            testNumber++;
        }
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        List<Integer> items;
        int expected;

        public TestCase(List<Integer> items, int expected) {
            this.items = items;
            this.expected = expected;
        }
    }
}
