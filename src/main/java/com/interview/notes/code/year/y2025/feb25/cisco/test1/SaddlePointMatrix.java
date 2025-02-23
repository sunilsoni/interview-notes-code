package com.interview.notes.code.year.y2025.feb25.cisco.test1;
/*
WORKING


### **Problem Statement**
Write an algorithm that finds the **elements which are largest in a row** and **smallest in a column** in a given matrix.

---

### **Input**
- The first line of input consists of **two space-separated integers**:
  - `matrix_row` (N): **Number of rows in the matrix**.
  - `matrix_col` (M): **Number of columns in the matrix**.
- The next **N lines** consist of **M space-separated integers**, representing the elements of the matrix.

### **Output**
- Print a number that is **largest in its row** and **smallest in its column** in the given matrix.
- If no such element exists, print **`-1`**.

---

### **Constraints**
```
1 ≤ N, M ≤ 1000
```

---

### **Note**
- Each number in the matrix is a **non-negative integer**.

---

### **Example**
#### **Input:**
```
2 2
1 2
3 4
```
#### **Output:**
```
2
```
#### **Explanation:**
- The number **2** at index **(0,1)** is the **largest in its row** and **smallest in its column**.
- So, the output is **2**.

---

### **Java Implementation (Template)**
```java
public static void funcMatrix(int[][] matrix) {
    // Write your code here
}
```
 */
public class SaddlePointMatrix {

    /**
     * Finds and returns the element that is largest in its row
     * and smallest in its column. If no such element exists, returns -1.
     *
     * @param matrix Input matrix of non-negative integers.
     * @return The saddle point element or -1 if not found.
     */
    public static int findSaddlePoint(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        // Iterate over each row to find candidate saddle points.
        for (int i = 0; i < n; i++) {
            // Find the maximum element in row i and record all its column indexes.
            int maxVal = Integer.MIN_VALUE;
            // Store candidate column indices where the row maximum occurs.
            java.util.List<Integer> candidateCols = new java.util.ArrayList<>();
            
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] > maxVal) {
                    maxVal = matrix[i][j];
                    candidateCols.clear();
                    candidateCols.add(j);
                } else if (matrix[i][j] == maxVal) {
                    candidateCols.add(j);
                }
            }
            
            // Check each candidate to see if it is the smallest in its column.
            for (int col : candidateCols) {
                boolean isMinInCol = true;
                for (int k = 0; k < n; k++) {
                    if (matrix[k][col] < maxVal) {
                        isMinInCol = false;
                        break;
                    }
                }
                if (isMinInCol) {
                    return maxVal; // Found a saddle point.
                }
            }
        }
        return -1; // No saddle point found.
    }

    /**
     * Main method to test the solution with various test cases.
     * Uses simple print statements to display PASS/FAIL for each case.
     */
    public static void main(String[] args) {
        // Create a list of test cases.
        java.util.List<TestCase> testCases = new java.util.ArrayList<>();
        
        // Test case 1: Provided example.
        testCases.add(new TestCase(new int[][]{
            {1, 2},
            {3, 4}
        }, 2, "Example test case"));
        
        // Test case 2: No saddle point.
        testCases.add(new TestCase(new int[][]{
            {1, 2},
            {3, 1}
        }, -1, "No saddle point test case"));
        
        // Test case 3: Single row.
        testCases.add(new TestCase(new int[][]{
            {1, 2, 3, 4}
        }, 4, "Single row test case"));
        
        // Test case 4: Single column.
        testCases.add(new TestCase(new int[][]{
            {5},
            {2},
            {9}
        }, 2, "Single column test case"));
        
        // Test case 5: 3x3 matrix.
        testCases.add(new TestCase(new int[][]{
            {5, 7, 6},
            {7, 8, 9},
            {6, 7, 8}
        }, 7, "3x3 test case"));
        
        // Test case 6: Single element matrix.
        testCases.add(new TestCase(new int[][]{
            {7}
        }, 7, "Single element test case"));
        
        // Test case 7: Large matrix (1000x1000) with constant value.
        int n = 1000, m = 1000;
        int[][] largeMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                largeMatrix[i][j] = 5;
            }
        }
        testCases.add(new TestCase(largeMatrix, 5, "Large matrix test case (1000x1000)"));
        
        // Run all test cases.
        for (TestCase tc : testCases) {
            int result = findSaddlePoint(tc.matrix);
            if (result == tc.expected) {
                System.out.println("PASS: " + tc.description);
            } else {
                System.out.println("FAIL: " + tc.description + " | Expected: " + tc.expected + ", Got: " + result);
            }
        }
    }

    // Helper class to store test cases.
    static class TestCase {
        int[][] matrix;
        int expected;
        String description;

        TestCase(int[][] matrix, int expected, String description) {
            this.matrix = matrix;
            this.expected = expected;
            this.description = description;
        }
    }
}