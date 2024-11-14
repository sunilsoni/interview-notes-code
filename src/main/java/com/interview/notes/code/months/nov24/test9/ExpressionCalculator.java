package com.interview.notes.code.months.nov24.test9;

/*

**Problem Statement:**

There is a string `S` made of words `"one"` and `"two"` separated by single `"+"` and `"-"` signs. The string represents a valid sequence of additions and subtractions of the digits 1 and 2.

Calculate the result of the expression represented by `S`.

Write a function:

```java
class Solution {
    public int solution(String S);
}
```

that, given a string `S`, returns the result of the mathematical expression it represents.

### Examples:

1. Given `S = "one+two-one-one+two+one"`, the string represents the expression `1 + 2 - 1 - 1 + 2 + 1`. Its result is equal to `4`.
   The function should return `4`.

2. Given `S = "two-two-one-two"`, the function should return `-3`.

3. Given `S = "two"`, the function should return `2`.

### Assumptions:

- The length of string `S` is within the range `[3..499]`.
- String `S` is made only of words `"one"` and `"two"` separated by single `"+"` or `"-"` signs.
- String `S` begins and ends with a word.

---

### Additional Notes:
The focus of the solution should be on **correctness**, not on optimizing performance.

 */
public class ExpressionCalculator {

    public static int solution(String S) {
        String[] tokens = S.split("[-+]");

        // If there's only one token, return its numeric value
        if (tokens.length == 1) {
            return wordToNumber(tokens[0]);
        }

        String[] operators = S.replaceAll("[^-+]", "").split("");

        int result = wordToNumber(tokens[0]);

        for (int i = 0; i < operators.length; i++) {
            int nextNumber = wordToNumber(tokens[i + 1]);
            if (operators[i].equals("+")) {
                result += nextNumber;
            } else {
                result -= nextNumber;
            }
        }

        return result;
    }

    private static int wordToNumber(String word) {
        return word.equals("one") ? 1 : 2;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "one+two-one-one+two+one",
                "two-two-one-two",
                "two",
                "one",
                "one+one+one+one+one",
                "two-one-one-one-one-one",
                "two+two+two+two+two+two+two+two+two+two"
        };

        int[] expectedResults = {4, -3, 2, 1, 5, -3, 20};

        for (int i = 0; i < testCases.length; i++) {
            int result = solution(testCases[i]);
            boolean passed = result == expectedResults[i];
            System.out.println("Test case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            System.out.println("Input: " + testCases[i]);
            System.out.println("Expected: " + expectedResults[i] + ", Got: " + result);
            System.out.println();
        }

        // Large input test
        StringBuilder largeInput = new StringBuilder("two");
        for (int i = 0; i < 100; i++) {
            largeInput.append("+one");
        }
        System.out.println("Large input test:");
        System.out.println("Input length: " + largeInput.length());
        System.out.println("Result: " + solution(largeInput.toString()));
    }
}
