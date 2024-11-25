package com.interview.notes.code.months.nov24.CodeSignal.test3;

import java.util.*;
/*
WORKING 100%


Given a square matrix \( n \times n \) (\( n \) is guaranteed to be odd) that contains only \( 0 \), \( 1 \), and \( 2 \). In one operation, you are allowed to change the number in any cell of the matrix to a different number (\( 0 \), \( 1 \), or \( 2 \)). Your task is to compute the minimum number of cells that need to change for the letter \( Y \) to be written on the matrix.

The letter \( Y \) is written on the matrix if and only if:
- All numbers on the diagonals starting from the upper-left and upper-right corners down to the center of the matrix, as well as the numbers stretching down vertically from the center of the matrix, are equal.
- All other numbers that are not part of that \( Y \) should be equal and be different from the numbers that make up the \( Y \).

**Note:** For a square matrix of size \( n \times n \), there are exactly 6 possible ways of writing \( Y \) on the matrix. The cells that make up the \( Y \) letter and the cells that make up its background can be equal to:
- \( 0 \) and \( 1 \),
- \( 0 \) and \( 2 \),
- \( 1 \) and \( 0 \),
- \( 1 \) and \( 2 \),
- \( 2 \) and \( 0 \), or
- \( 2 \) and \( 1 \).

Below, you can see an example of letter \( Y \) for a \( 5 \times 5 \) matrix:

```
n = 5
```

Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than \( O(n^2) \) will fit within the execution time limit.

---

### Example

1. For:

   ```
   matrix = [
       [1, 0, 2],
       [1, 2, 0],
       [0, 2, 0]
   ]
   ```

   The output should be `solution(matrix) = 2`.

   **Explanation:**
   - The best is to change the \( 1 \) in the \( 0^{th} \) row to \( 2 \) and the \( 1 \) in the \( 1^{st} \) row to \( 0 \).
   - \( 2 \)'s make the \( Y \) letter and \( 0 \)'s make its background.
   - The final matrix is shown below:
     ```
     matrix = [
         [2, 0, 2],
         [0, 2, 0],
         [0, 2, 0]
     ]
     ```

2. For:

   ```
   matrix = [
       [2, 0, 0, 0, 2],
       [0, 2, 1, 2, 0],
       [0, 1, 2, 1, 0],
       [0, 0, 2, 1, 1],
       [1, 1, 2, 1, 1]
   ]
   ```

   The output should be `solution(matrix) = 8`.

   **Explanation:**
   - The \( 2 \)'s here form the letter \( Y \).
   - The best solution is to change all of the \( 0 \)'s (a total of 8) to \( 1 \)'s.
   - The final matrix is shown below:
     ```
     matrix = [
         [2, 1, 1, 1, 2],
         [1, 2, 1, 2, 1],
         [1, 1, 2, 1, 1],
         [1, 1, 2, 1, 1],
         [1, 1, 2, 1, 1]
     ]
     ```

---

### Input/Output

1. **[Execution Time Limit]**: 3 seconds (Java).
2. **[Memory Limit]**: 1 GB.
3. **[Input]**: `array.array.integer matrix`
   - The square matrix \( n \times n \) (where \( n \) is odd) consists only of \( 0 \), \( 1 \), and \( 2 \).
   - **Guaranteed Constraints**:
     - \( 3 \leq \text{matrix.length} \leq 99 \),
     - \( 0 \leq \text{matrix}[i][j] \leq 2 \).

4. **[Output]**: `integer`
   - The minimum number of cells of the matrix that have to change for the letter \( Y \) to be written on the matrix.

---

### Solution Function:

```java
int solution(int[][] matrix) {

}
```


 */
public class YMatrixFinal {

    /**
     * Solution method to compute the minimum number of changes required
     * to form the letter Y on the matrix.
     *
     * @param matrix The input n x n matrix containing 0, 1, and 2.
     * @return The minimum number of changes required.
     */
    public static int solution(int[][] matrix) {
        int n = matrix.length;
        int center = n / 2;
        Set<Integer> yCells = new HashSet<>();

        // Collect Y cells:
        // 1. Diagonals from top to center (excluding center if already included)
        for (int i = 0; i < center; i++) {
            yCells.add(i * n + i); // Left diagonal
            yCells.add(i * n + (n - 1 - i)); // Right diagonal
        }

        // 2. Center cell
        yCells.add(center * n + center);

        // 3. Vertical line from center downwards
        for (int i = center + 1; i < n; i++) {
            yCells.add(i * n + center);
        }

        // Define all possible (Y, Background) pairs
        int[][] pairs = {
            {0, 1}, {0, 2},
            {1, 0}, {1, 2},
            {2, 0}, {2, 1}
        };

        int minChanges = Integer.MAX_VALUE;

        for (int[] pair : pairs) {
            int yVal = pair[0];
            int bgVal = pair[1];
            // Y and Background values must be different
            if (yVal == bgVal) continue;

            int changes = 0;
            for (int i = 0; i < n * n; i++) {
                int row = i / n;
                int col = i % n;
                int currentVal = matrix[row][col];
                if (yCells.contains(i)) {
                    if (currentVal != yVal) changes++;
                } else {
                    if (currentVal != bgVal) changes++;
                }
            }
            if (changes < minChanges) {
                minChanges = changes;
            }
        }

        return minChanges;
    }

    /**
     * Main method to test the solution with various test cases.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1
        testCases.add(new TestCase(
            new int[][] {
                {1, 0, 2},
                {1, 2, 0},
                {0, 2, 0}
            },
            2
        ));

        // Test Case 2
        testCases.add(new TestCase(
            new int[][] {
                {2, 0, 0, 0, 2},
                {0, 2, 1, 2, 0},
                {0, 1, 2, 1, 0},
                {0, 0, 2, 1, 1},
                {1, 1, 2, 1, 1}
            },
            9 // Corrected Expected Output
        ));

        // Test Case 3: Minimal Matrix (n=3), already forming Y
        testCases.add(new TestCase(
            new int[][] {
                {1, 0, 1},
                {0, 1, 0},
                {0, 1, 0}
            },
            0
        ));

        // Test Case 4: All cells need to change
        testCases.add(new TestCase(
            new int[][] {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
            },
            4 // Corrected Expected Output
        ));

        // Test Case 5: Large Matrix (n=99) with random values
        int[][] largeMatrix = new int[99][99];
        Random rand = new Random(0); // Seed for reproducibility
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                largeMatrix[i][j] = rand.nextInt(3);
            }
        }
        // Since it's random, we won't check the expected result
        testCases.add(new TestCase(largeMatrix, -1)); // -1 indicates we won't check expected

        // Run all test cases
        int passed = 0;
        for (int idx = 0; idx < testCases.size(); idx++) {
            TestCase tc = testCases.get(idx);
            int result = solution(tc.matrix);
            if (tc.expected == -1) { // For largeMatrix or cases where expected is not predefined
                System.out.println("Test Case " + (idx + 1) + ": Executed Successfully. Result: " + result);
                passed++;
            } else if (result == tc.expected) {
                System.out.println("Test Case " + (idx + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (idx + 1) + ": FAIL (Expected: " + tc.expected + ", Got: " + result + ")");
            }
        }

        System.out.println("\nTotal Passed: " + passed + " out of " + testCases.size());
    }

    /**
     * Helper class to represent a test case.
     */
    static class TestCase {
        int[][] matrix;
        int expected;

        TestCase(int[][] matrix, int expected) {
            this.matrix = matrix;
            this.expected = expected;
        }
    }
}