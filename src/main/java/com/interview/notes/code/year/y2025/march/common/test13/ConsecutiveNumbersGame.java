package com.interview.notes.code.year.y2025.march.common.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
WORKING 5 failed


### **Consecutive Numbers Game**

You are given an array `a` of `n` numbers, and you are playing a number game as follows:

1. In the first iteration, take number pairs - `a[i] and a[i+1]`, starting from `i=0`.
2. If the product of the pair is divisible by 3, replace `a[i]` with the product; otherwise, keep the element unchanged.
3. Repeat this for all number pairs, maintaining their order.
4. The last number remains unchanged as it has no pair.
5. Continue the game for multiple iterations until the first `n-1` elements in the array are multiples of 3.
6. Your task is to determine the number of rounds before the game ends.

---

### **Input Format**
- The first line contains an integer `n`, the number of elements in the list.
- The second line contains `n` integers representing the elements of the array.

---

### **Output Format**
- A single integer representing the number of rounds before the game ends.

---

### **Example 1**
#### **Input**
```
5
34 56 20 90 100
```
#### **Output**
```
3
```
#### **Explanation**
- **Round 1:** `{34, 56, 1800, 9000, 100}`
- **Round 2:** `{34, 100800, 16200000, 9000000, 100}`
- **Round 3:** `{3427200, 100800, 16200000, 9000000, 100}`
- The first 3 numbers become multiples of 3. Hence, the number of rounds is **3**.

---

### **Example 2**
#### **Input**
```
4
1 333 222 22
```
#### **Output**
```
1
```
#### **Explanation**
- **Round 1:** `{333, 333, 222, 22}`
- The first 3 numbers are now multiples of 3. Hence, the game ends in **1** round.

---

### **Function Signature**
You need to implement the following function:

```java
public static int solve(List<Integer> arr) {
    // Write your code here
}
```

- **Input:** `arr` is a `List<Integer>` representing the array.
- **Output:** Returns an `int` representing the number of rounds before the game ends.

---

 */
public class ConsecutiveNumbersGame {

    /**
     * Solves the \"Consecutive Numbers Game\" problem.
     *
     * @param arr input list of integers
     * @return number of rounds until all first n-1 elements are divisible by 3
     */
    public static int solve(List<Integer> arr) {
        int rounds = 0;
        List<Integer> currentArr = new ArrayList<>(arr);

        // Continue until the first n-1 elements are divisible by 3
        while (!currentArr.subList(0, currentArr.size() - 1).stream().allMatch(i -> i % 3 == 0)) {
            rounds++;
            // Create a temporary list to store updates for this round
            List<Integer> temp = new ArrayList<>(currentArr);

            // Effectively final reference for lambda expression
            final List<Integer> finalCurrentArr = currentArr;

            // Iterate pairs and update elements accordingly
            IntStream.range(0, finalCurrentArr.size() - 1).forEach(i -> {
                int product = finalCurrentArr.get(i) * finalCurrentArr.get(i + 1);
                // Update element if product divisible by 3
                if (product % 3 == 0) temp.set(i, product);
            });

            // Update array for next round
            currentArr = temp;
        }
        return rounds;
    }

    // Minimal reproducible example
    public static void main(String[] args) {
        runTests();
    }

    // Structured test method for verifying correctness
    private static void runTests() {
        List<TestCase> cases = Arrays.asList(
                new TestCase(Arrays.asList(34, 56, 20, 90, 100), 3),
                new TestCase(Arrays.asList(1, 333, 222, 22), 1),
                // Edge case: already multiples of 3
                new TestCase(Arrays.asList(3, 6, 9), 0),
                // Edge case: minimal input
                new TestCase(Arrays.asList(2), 0),
                // Edge case: Large numbers
                new TestCase(Arrays.asList(999999, 999999, 999999), 0),
                // Edge case: None divisible initially
                new TestCase(Arrays.asList(1, 1, 1, 1, 1, 1), 2)
        );

        cases.forEach(testCase -> {
            int result = solve(testCase.input);
            System.out.printf("Input: %s | Expected: %d | Got: %d | Result: %s%n",
                    testCaseToString(testCase.input), testCase.expected, result,
                    (result == testCase.expected ? "PASS" : "FAIL"));
        });

        System.out.println("\nAll tests completed.");
    }

    private static String testCaseToString(List<Integer> input) {
        return input.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    // Helper class to store test cases
    private static class TestCase {
        List<Integer> input;
        int expected;

        TestCase(List<Integer> input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}