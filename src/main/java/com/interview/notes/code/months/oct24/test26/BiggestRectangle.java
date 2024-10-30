package com.interview.notes.code.months.oct24.test26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
WORKING


### Biggest Rectangle

You are given data about a bar graph as an array of integers in which each element represents the height of a bar from left to right. You depict this data as a bar graph and then realize that you can form rectangles by taking the filled areas of a bar or by combining a number of contiguous bars. Assuming that all bars have equal width, you are curious to find the area of the largest rectangle that can be thus formed.

For example, the largest rectangle that can be formed out of the bars in the bar graph represented as `7 3 6 5 6 0 7` is marked in red in the image given below.

---

**Input**

- The first line of input contains an integer `N`, representing the size of the array.
- The second line of input contains `N` space-separated integers of the array as \( B_1, B_2, \ldots, B_N \) where each element represents the height of a bar.

**Output**

- The maximum rectangular area possible from the contiguous bars.

**Constraints**

- \( 1 \leq N \leq 100 \)
- \( 0 \leq B[i] \leq 1000 \)

---

### Examples

#### Example #1

- **Input**:
  ```
  7
  7 3 6 5 6 0 7
  ```

- **Output**:
  ```
  15
  ```

**Explanation**:
As seen in the diagram, the largest rectangle area is \( 3 \times 5 = 15 \).

#### Example #2

- **Input**:
  ```
  4
  9 1 1 9
  ```

- **Output**:
  ```
  9
  ```

**Explanation**:
The largest rectangle will comprise either the first or the last bar, with an area of 9.

---

### Function Signature

```java
/*
 * Implement method/function with name 'maxArea' below.
 * The function accepts the following as parameters:
 *   1. B is of type List<Integer>.
 * Returns: int.

public static int maxArea(List<Integer> B){
    // Write your code here

    return; // return type "int".
}
```

        */
public class BiggestRectangle {

    public static int maxArea(List<Integer> B) {
        int n = B.size();
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : B.get(i);

            while (!stack.isEmpty() && h < B.get(stack.peek())) {
                int height = B.get(stack.pop());
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = new ArrayList<>();
        testCases.add(Arrays.asList(7, 3, 6, 5, 6, 0, 7));
        testCases.add(Arrays.asList(9, 1, 1, 9));
        testCases.add(Arrays.asList(1, 2, 3, 4, 5));
        testCases.add(Arrays.asList(5, 4, 3, 2, 1));
        testCases.add(Arrays.asList(2, 1, 5, 6, 2, 3));

        // Expected results
        int[] expectedResults = {15, 9, 9, 9, 10};

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> testCase = testCases.get(i);
            int result = maxArea(testCase);
            boolean passed = result == expectedResults[i];

            System.out.println("Test Case " + (i + 1) + ": " +
                    (passed ? "PASS" : "FAIL") +
                    " (Expected: " + expectedResults[i] +
                    ", Got: " + result + ")");
        }

        // Large data input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add((int) (Math.random() * 1000));
        }
        long startTime = System.currentTimeMillis();
        int largeResult = maxArea(largeInput);
        long endTime = System.currentTimeMillis();

        System.out.println("\nLarge Input Test:");
        System.out.println("Result: " + largeResult);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
