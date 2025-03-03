package com.interview.notes.code.year.y2025.jan.CASPEX.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

WORKING :


Here is the extracted and properly formatted text from the images:

---

### Biggest Rectangle

You are given data about a bar graph as an array of integers, where each element represents the height of a bar from left to right. You depict this data as a bar graph and realize that you can form rectangles by taking the filled areas of a bar or by combining a number of contiguous bars.

Assuming that all bars have equal width, you are curious to find the area of the largest rectangle that can be formed.

For example, the largest rectangle that can be formed out of the bars in the bar graph represented as `7 3 6 5 6 0 7` is marked in red in the image and has an area of `3*5 = 15`.

---

### Input
- The first line of input contains an integer **N**, representing the size of the array.
- The second line of input contains **N** space-separated integers \( B_1, B_2, ..., B_N \), where each element represents the height of a bar.

---

### Output
The maximum rectangular area possible from the contiguous bars.

---

### Constraints
- \( 1 \leq N \leq 100 \)
- \( 0 \leq B[i] \leq 1000 \)

---

### Example #1

**Input**
```
7
7 3 6 5 6 0 7
```

**Output**
```
15
```

**Explanation:**
The largest rectangular area is `3 * 5 = 15`.

---

### Example #2

**Input**
```
4
9 1 1 9
```

**Output**
```
9
```

**Explanation:**
The largest rectangle will comprise either the first or the last bar, and its area is `9`.

---

### Function Template

```java
class Outcome {
    /**
     * Implement method/function with the name 'maxArea' below.
     * The function accepts the following parameters:
     * 1. B is of type List<Integer>.
     *
     * Return:
     * int

public static int maxArea(List<Integer> B) {
    // Write your code here

    return 0; // return type "int".
}
}
        ```

        Let me know if you need help with the solution!
 */
public class Outcome {
    public static int maxArea(List<Integer> B) {
        if (B == null || B.isEmpty()) return 0;

        int maxArea = 0;
        int n = B.size();

        // Check each bar as potential minimum height
        for (int i = 0; i < n; i++) {
            // Expand left and right while maintaining minimum height
            int minHeight = B.get(i);

            // Expand left
            int left = i;
            while (left > 0 && B.get(left - 1) >= minHeight) {
                left--;
            }

            // Expand right
            int right = i;
            while (right < n - 1 && B.get(right + 1) >= minHeight) {
                right++;
            }

            // Calculate area with current bar as minimum height
            int width = right - left + 1;
            int area = width * minHeight;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(Arrays.asList(7, 3, 6, 5, 6, 0, 7), 15, "Test 1: Given example");
        runTest(Arrays.asList(9, 1, 1, 9), 9, "Test 2: Example with equal ends");
        runTest(Arrays.asList(1), 1, "Test 3: Single element");
        runTest(Arrays.asList(2, 2, 2), 6, "Test 4: Equal heights");
        runTest(Arrays.asList(1, 2, 3, 4), 6, "Test 5: Increasing heights");

        // Large input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeInput.add(i % 5 + 1);
        }
        runTest(largeInput, 100, "Test 6: Large input");
    }

    private static void runTest(List<Integer> input, int expected, String testName) {
        System.out.println(testName);
        System.out.println("Input: " + input);
        int result = maxArea(input);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
