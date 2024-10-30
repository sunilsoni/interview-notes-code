package com.interview.notes.code.months.oct24.wallmart.test3.test;

import java.util.*;
/*

WORKING FINAL:

### Disk Space Analysis

**Description:**

A company is performing an analysis on the computers at its main office. The computers are spaced along a single row. For each group of contiguous computers of a certain length, that is, for each segment, determine the minimum amount of disk space available on a computer. Return the maximum of these values as your answer.

**Example:**

- **n** = 4, the number of computers
- **space** = [8, 2, 4, 6]
- **x** = 2, the segment length

The free disk space of computers in each of the segments is [8, 2], [2, 4], [4, 6]. The minima of the three segments are [2, 2, 4]. The maximum of these is 4.

---

### Sample Input and Output

#### Sample Case 1

**Input:**
```
STDIN Function
2 > length of segments x = 2
3 > size of space n = 3
1 > space = [1, 1, 1]
1
1
```

**Output:**
```
1
```

**Explanation:**
The segments of size x = 2 are [1, 1] and [1, 1]. The minimum value for both segments is 1. The maximum of these values is 1.

---

#### Sample Case 2

**Input:**
```
STDIN Function
3 > length of segments x = 3
5 > size of space n = 5
2 > space = [2, 5, 4, 6, 8]
5
4
6
8
```

**Output:**
```
4
```

**Explanation:**
The segments of size x = 3 are [2, 5, 4], [5, 4, 6], and [4, 6, 8]. The respective minimum values are 2, 4, and 4. The maximum of these values is 4.

---

### Function Description

Complete the function `segment` in the editor below.

**Function Signature:**
```java
public static int segment(int x, List<Integer> space) {
    // Write your code here
}
```

**Parameters:**
- `int x`: the segment length to analyze
- `int space[n]`: the available hard disk space on each of the computers

**Returns:**
- `int`: the maximum of the minimum values of available hard disk space found while analyzing the computers in segments of length x

**Constraints:**
- \( 1 \leq n \leq 10^6 \)
- \( 1 \leq x \leq n \)
- \( 1 \leq space[i] \leq 10^9 \)

---

### Input Format for Custom Testing

The first line contains an integer, x, the segment length for analyzing the row of computers.
The second line contains an integer, n, the size of the array space.
Each line i of the n subsequent lines (where \( 0 \leq i < n \)) contains an integer, space[i].

 */
public class DiskSpaceAnalyzer {
    
    // Main solution method
    public static int segment(int x, List<Integer> space) {
        if (space == null || space.isEmpty() || x > space.size()) {
            return 0;
        }
        
        int maxOfMins = Integer.MIN_VALUE;
        
        // Use sliding window approach
        for (int i = 0; i <= space.size() - x; i++) {
            int minInWindow = findMinInRange(space, i, i + x);
            maxOfMins = Math.max(maxOfMins, minInWindow);
        }
        
        return maxOfMins;
    }
    
    private static int findMinInRange(List<Integer> space, int start, int end) {
        int min = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            min = Math.min(min, space.get(i));
        }
        return min;
    }
    
    // Test method
    public static void main(String[] args) {
        // Test case 1
        testCase(2, Arrays.asList(1, 1, 1), 1, "Basic test with same values");
        
        // Test case 2
        testCase(3, Arrays.asList(2, 5, 4, 6, 8), 4, "Example from problem statement");
        
        // Test case 3
        testCase(2, Arrays.asList(8, 2, 4, 6), 4, "Another example from problem");
        
        // Edge case - single element
        testCase(1, Arrays.asList(5), 5, "Single element");
        
        // Edge case - segment size equals array size
        testCase(3, Arrays.asList(1, 2, 3), 1, "Segment equals array size");
        
        // Large data test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(i % 1000 + 1);
        }
        testCase(1000, largeInput, 1, "Large data test");
        
        // Additional test cases
        testCase(2, Arrays.asList(1, 2, 3, 1, 2), 2, "Multiple segments test");
        testCase(3, Arrays.asList(7, 6, 5, 4, 3, 2, 1), 5, "Decreasing sequence");
    }
    
    private static void testCase(int x, List<Integer> space, int expected, String description) {
        try {
            long startTime = System.currentTimeMillis();
            int result = segment(x, space);
            long endTime = System.currentTimeMillis();
            
            boolean passed = result == expected;
            System.out.printf("Test Case: %s - %s%n", description, passed ? "PASSED" : "FAILED");
            if (!passed) {
                System.out.printf("Expected: %d, Got: %d%n", expected, result);
            }
            System.out.printf("Execution time: %d ms%n", endTime - startTime);
            System.out.println("--------------------");
            
        } catch (Exception e) {
            System.out.printf("Test Case: %s - FAILED (Exception: %s)%n", description, e.getMessage());
            System.out.println("--------------------");
        }
    }
}