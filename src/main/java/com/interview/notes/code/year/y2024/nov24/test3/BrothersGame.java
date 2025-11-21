package com.interview.notes.code.year.y2024.nov24.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING ALL


### Brothers’ Game

Two brothers were playing a game. Their mother gave them an array of numbers, with each element being either 0 or 1. She then asked them to find out the maximum number of 1s they could obtain by inversion of the bits, i.e., changing all the 0s to 1 and all the 1s to 0 for any contiguous portion of the array.

The younger brother thought he could solve the problem in his head, but the elder brother chose to write a program to find the optimal solution. Can you help the elder brother?

#### Input
- The first line of input contains an integer **N**, representing the size of the array.
- The second line of input contains **N** space-separated integers, representing the array elements.

#### Output
- The output is the maximum number of 1s which you can make in one inversion.

#### Constraints
- \(1 \leq N \leq 100\)

#### Time Complexity
Optimize your solution for at least \(O(N)\); otherwise, some large test cases may fail.

#### Example #1
**Input**
```
5
0 1 0 0 1
```

**Output**
```
4
```

**Explanation:** We can change the numbers from the third element till the fourth element to get: 0 1 1 1 1, which has four ones.

---

#### Example #2
**Input**
```
6
1 0 0 1 0 0
```

**Output**
```
5
```

**Explanation:** We can change the numbers from the second element to the last element to get: 1 1 1 1 0 1, which has five ones.

---

### Function Signature

```java
public static int solve(List<Integer> ar) {
    // Write your code here

    return 0; // return type "int"
}
```

---

 */
public class BrothersGame {

    /**
     * Solves the Brothers' Game problem by finding the maximum number of 1s
     * achievable after inverting a single contiguous subarray.
     *
     * @param ar List of integers representing the binary array (0s and 1s)
     * @return Maximum number of 1s after inversion
     */
    public static int solve(List<Integer> ar) {
        int totalOnes = 0;
        // Calculate the total number of 1s in the original array
        for (int num : ar) {
            if (num == 1) {
                totalOnes++;
            }
        }

        // Initialize variables for Kadane's algorithm
        int maxGain = Integer.MIN_VALUE;
        int currentGain = 0;

        for (int num : ar) {
            // Assign +1 for 0 (since flipping it increases the count of 1s)
            // Assign -1 for 1 (since flipping it decreases the count of 1s)
            int value = (num == 0) ? 1 : -1;

            // Update current gain
            currentGain += value;

            // Update maxGain if currentGain is better
            if (currentGain > maxGain) {
                maxGain = currentGain;
            }

            // Reset currentGain if it's negative
            if (currentGain < 0) {
                currentGain = 0;
            }
        }

        // Edge case: If all elements are 1, maxGain would be -1
        // To maximize, we need to flip exactly one element to minimize loss
        if (maxGain < 0) {
            return totalOnes - 1;
        }

        // The maximum number of 1s is the original count plus the maximum gain
        return totalOnes + maxGain;
    }

    /**
     * Runs predefined test cases to validate the solve method.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example Test Case 1
        testCases.add(new TestCase(
                Arrays.asList(0, 1, 0, 0, 1),
                4,
                "Example Test Case 1"
        ));

        // Example Test Case 2
        testCases.add(new TestCase(
                Arrays.asList(1, 0, 0, 1, 0, 0),
                5,
                "Example Test Case 2"
        ));

        // Edge Case: All 1s
        testCases.add(new TestCase(
                Arrays.asList(1, 1, 1, 1),
                3,
                "All 1s"
        ));

        // Edge Case: All 0s
        testCases.add(new TestCase(
                Arrays.asList(0, 0, 0, 0),
                4,
                "All 0s"
        ));

        // Edge Case: Single Element - 0
        testCases.add(new TestCase(
                List.of(0),
                1,
                "Single Element - 0"
        ));

        // Edge Case: Single Element - 1
        testCases.add(new TestCase(
                List.of(1),
                0,
                "Single Element - 1"
        ));

        // Large Test Case - Alternating 0s and 1s
        List<Integer> largeTest = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeTest.add(i % 2); // Alternating 0s and 1s
        }
        testCases.add(new TestCase(
                largeTest,
                51, // Corrected Expected Output
                "Large Test Case - Alternating 0s and 1s"
        ));

        // Additional Test Case: Multiple Possible Inversions
        testCases.add(new TestCase(
                Arrays.asList(0, 0, 1, 0, 1, 0, 0),
                5, // Corrected Expected Output
                "Multiple Possible Inversions"
        ));

        // Run all test cases
        int passed = 0;
        for (TestCase tc : testCases) {
            int result = solve(tc.input);
            if (result == tc.expectedOutput) {
                System.out.println(tc.description + ": PASS");
                passed++;
            } else {
                System.out.println(tc.description + ": FAIL (Expected " + tc.expectedOutput + ", Got " + result + ")");
            }
        }

        System.out.println("\nTotal Passed: " + passed + " out of " + testCases.size());
    }

    /**
     * Helper class to represent a test case.
     */
    static class TestCase {
        List<Integer> input;
        int expectedOutput;
        String description;

        TestCase(List<Integer> input, int expectedOutput, String description) {
            this.input = input;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }
    }
}
