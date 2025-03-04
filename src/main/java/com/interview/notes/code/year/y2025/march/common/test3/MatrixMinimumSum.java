package com.interview.notes.code.year.y2025.march.common.test3;

import java.util.Arrays;
/*

### Task 1
You are given a two-dimensional array (a matrix) of integers **A** made of **N** rows and **M** columns. Choose one column and one row in **A** and change all values in them to zero. What is the **minimum sum** of elements in **A** that can be achieved this way?

#### Function Definition
Write a function:
```java
class Solution {
    public int solution(int[][] A);
}
```
that, given a two-dimensional array **A**, returns the minimum sum of elements of **A** that can be achieved after the change.

#### Examples:

1. **Input:**
   ```A = [[1, 3], [3, 2], [4, 5]]```
   **Output:** `4`
   **Explanation:** The result can be achieved by changing to zero values in the **right column** and **bottom row** (marked red in the picture below).

2. **Input:**
   ```A = [[1, 3, 3], [10, 2, 2], [1, 3, 3]]```
   **Output:** `8`
   **Explanation:** The result can be achieved by changing to zero values in the **middle column** and **middle row**.

#### Constraints:
- The number of elements in matrix **A** is within the range **[1..1,000,000]**.
- **N** and **M** are integers within the range **[1..1,000,000]**.
- Each element of matrix **A** is an integer within the range **[-1,000..1,000]**.

 */
public class MatrixMinimumSum {
    
    public static int solution(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        
        int n = A.length;       // Number of rows
        int m = A[0].length;    // Number of columns
        
        // Calculate sum of each row
        int[] rowSums = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowSums[i] += A[i][j];
            }
        }
        
        // Calculate sum of each column
        int[] colSums = new int[m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                colSums[j] += A[i][j];
            }
        }
        
        // Calculate total sum of the matrix
        int totalSum = Arrays.stream(rowSums).sum();
        
        // Find minimum sum after zeroing one row and one column
        int minSum = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // When we zero out row i and column j:
                // We remove all elements in row i (rowSums[i])
                // We remove all elements in column j (colSums[j])
                // But we double-counted the intersection A[i][j], so add it back
                int currentSum = totalSum - rowSums[i] - colSums[j] + A[i][j];
                minSum = Math.min(minSum, currentSum);
            }
        }
        
        return minSum;
    }
    
    // Test method
    public static void main(String[] args) {
        // Test case 1
        int[][] test1 = {{1, 3}, {3, 2}, {4, 5}};
        int expected1 = 4;
        int result1 = solution(test1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected1 + ", Got: " + result1 + ")");
        
        // Test case 2
        int[][] test2 = {{1, 3, 3}, {10, 2, 2}, {1, 3, 3}};
        int expected2 = 8;
        int result2 = solution(test2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected2 + ", Got: " + result2 + ")");
        
        // Test case 3 - Edge case with single element
        int[][] test3 = {{5}};
        int expected3 = 0;  // When we zero out the only row and column, sum becomes 0
        int result3 = solution(test3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected3 + ", Got: " + result3 + ")");
        
        // Test case 4 - Large matrix with negative values
        int[][] test4 = generateLargeMatrix(1000, 1000, -10, 10);
        long startTime = System.currentTimeMillis();
        int result4 = solution(test4);
        long endTime = System.currentTimeMillis();
        System.out.println("Test Case 4 (Large Matrix): Completed in " + (endTime - startTime) + "ms");
        
        // Test case 5 - Matrix with all negative values
        int[][] test5 = {{-1, -2}, {-3, -4}};
        int expected5 = -1;  // Zeroing out row 0 and column 0 gives sum of -4
        int result5 = solution(test5);
        System.out.println("Test Case 5: " + (result5 == expected5 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected5 + ", Got: " + result5 + ")");
    }
    
    // Helper method to generate large test matrices
    private static int[][] generateLargeMatrix(int rows, int cols, int min, int max) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = min + (int)(Math.random() * ((max - min) + 1));
            }
        }
        return matrix;
    }
}