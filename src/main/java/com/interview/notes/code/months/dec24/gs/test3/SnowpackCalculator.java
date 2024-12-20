package com.interview.notes.code.months.dec24.gs.test3;

/*
WORKING

**Instructions:**

- Given a list of student test scores, find the best average grade.
- Each student may have more than one test score in the list.
- Complete the `bestAverageGrade` function in the editor below.
- It has one parameter, `scores`, which is an array of student test scores.
- Each element in the array is a two-element array of the form `[student name, test score]`.
  - E.g., `["Bobby", "87"]`.
- Test scores may be positive or negative integers.
- If you end up with an average grade that is not an integer, you should:
  - Use a floor function to return the largest integer less than or equal to the average.
- Return `0` for an empty input.

**Example:**

**Input:**

```plaintext
[["Bobby", "87"],
 ["Charles", "100"],
 ["Eric", "64"],
 ["Charles", "22"]]
```

**Expected output:**

```plaintext
87
```

**Explanation:**

The average scores are 87, 61, and 64 for Bobby, Charles, and Eric, respectively. 87 is the highest.

 */
public class SnowpackCalculator {
    public static int computeSnowpack(int[] heights) {
        if (heights == null || heights.length < 3) {
            return 0;
        }

        int length = heights.length;
        int[] leftMax = new int[length];
        int[] rightMax = new int[length];
        int totalSnow = 0;

        // Find maximum height to the left of each position
        leftMax[0] = heights[0];
        for (int i = 1; i < length; i++) {
            leftMax[i] = Math.max(leftMax[i-1], heights[i]);
        }

        // Find maximum height to the right of each position
        rightMax[length-1] = heights[length-1];
        for (int i = length-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], heights[i]);
        }

        // Calculate snow at each position
        for (int i = 1; i < length-1; i++) {
            int minHeight = Math.min(leftMax[i], rightMax[i]);
            if (minHeight > heights[i]) {
                totalSnow += minHeight - heights[i];
            }
        }

        return totalSnow;
    }

    public static void main(String[] args) {
        runAllTests();
    }

    private static void runAllTests() {
        // Test cases with expected results
        runTest("Basic Case", 
                new int[]{0,1,3,0,1,2,0,4,2,0,3,0}, 13);
        
        runTest("Empty Array", 
                new int[]{}, 0);
        
        runTest("Single Element", 
                new int[]{5}, 0);
        
        runTest("Two Elements", 
                new int[]{3,5}, 0);
        
        runTest("Flat Surface", 
                new int[]{2,2,2,2}, 0);
        
        runTest("Valley", 
                new int[]{3,0,3}, 3);
        
        runTest("Mountain", 
                new int[]{0,3,0}, 0);
        
        runTest("Large Numbers", 
                new int[]{1000,0,1000}, 1000);
        
        runTest("Increasing Heights", 
                new int[]{0,1,2,3}, 0);
        
        runTest("Decreasing Heights", 
                new int[]{3,2,1,0}, 0);

        // Large data test
        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i % 100;
        }
        runTest("Large Data Test", largeArray, 0);
    }

    private static void runTest(String testName, int[] input, int expected) {
        long startTime = System.nanoTime();
        int result = computeSnowpack(input);
        long endTime = System.nanoTime();
        
        boolean passed = result == expected;
        System.out.printf("Test %s: %s%n", testName, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: %d, Got: %d%n", expected, result);
        }
        System.out.printf("Execution time: %.3f ms%n%n", 
                         (endTime - startTime) / 1_000_000.0);
    }
}
