package com.interview.notes.code.year.y2025.feb.cisco.test1;

import java.util.*;

/*
WORKING



### **Problem Statement**
The current selected programming language is **Java**. We emphasize the submission of a fully working code over partially correct but efficient code. Once **submitted**, you cannot review this problem again. You can use **System.out.println()** to debug your code. However, **System.out.println()** may not work in case of a syntax/runtime error.
The version of **JDK being used is 1.8**.

#### **Note:**
- The main class name **must be** `"Solution"`.
- The arithmetic **mean** of `N` numbers is **the sum of all the numbers divided by N**.
- The **mode** of `N` numbers is the most **frequently occurring number**.
- If the **mean** is calculated as a decimal, print its **floor value** (Floor is the greatest integer less than or equal to that number: `floor(2.4) = 2`).

---

### **Input**
- The first line of input consists of an **integer** `inputNum`, representing the number of elements in the set (`N`).
- The second line consists of **N space-separated integers** representing the elements of the given set.

### **Output**
- Print **two space-separated integers**:
  1. The first number is the **mean** of the input numbers.
  2. The second number is the **mode**.

---

### **Example**
#### **Input:**
```
5
1 2 7 3 2
```
#### **Output:**
```
3 2
```
#### **Explanation:**
- The **mean** for the given set of numbers is **3**:
  - \( (1 + 2 + 7 + 3 + 2) = 15 \)
  - \( 15 / 5 = 3 \) (since we take the floor value)
- The **mode** is the most frequently occurring number, which is **2**.

---

### **Java Implementation (Template)**
```java
import java.util.*;
import java.lang.*;
import java.io.*;


 */
public class Solution {

    /**
     * Computes the mean and mode of the input array.
     * - Mean is computed as the floor of the arithmetic average.
     * - Mode is determined as the most frequently occurring number.
     * In case of a tie, the smallest number is chosen.
     *
     * @param inputArr the array of integers.
     * @return an int array where index 0 is the mean and index 1 is the mode.
     */
    public static int[] computeMeanMode(int[] inputArr) {
        if (inputArr.length == 0) {
            return new int[]{0, 0}; // Edge case: empty array (should not occur per problem statement)
        }

        long sum = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : inputArr) {
            sum += num;
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Compute mean (using Math.floor to get the floor value)
        int mean = (int) Math.floor(sum / (double) inputArr.length);

        // Compute mode: choose the number with the highest frequency.
        // In case of ties, choose the smallest number.
        int mode = inputArr[0];
        int maxCount = freq.get(mode);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int key = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount || (count == maxCount && key < mode)) {
                mode = key;
                maxCount = count;
            }
        }

        return new int[]{mean, mode};
    }

    /**
     * Reads input from standard input, computes the mean and mode,
     * and prints them as space-separated integers.
     *
     * @param inputArr the array of integers.
     */
    public static void funcMeanMode(int[] inputArr) {
        int[] result = computeMeanMode(inputArr);
        System.out.println(result[0] + " " + result[1]);
    }

    /**
     * Runs multiple test cases and prints PASS/FAIL for each.
     * This method tests the solution using a simple main method.
     */
    public static void runTests() {
        int testsPassed = 0;
        int testsFailed = 0;

        // Inner class to store a test case
        class TestCase {
            int[] input;
            int expectedMean;
            int expectedMode;
            String description;

            TestCase(int[] input, int expectedMean, int expectedMode, String description) {
                this.input = input;
                this.expectedMean = expectedMean;
                this.expectedMode = expectedMode;
                this.description = description;
            }
        }

        List<TestCase> testCases = new ArrayList<>();
        // Test Case 1: Provided sample input
        testCases.add(new TestCase(new int[]{1, 2, 7, 3, 2}, 3, 2, "Provided sample: 1 2 7 3 2"));
        // Test Case 2: Single element
        testCases.add(new TestCase(new int[]{5}, 5, 5, "Single element: 5"));
        // Test Case 3: All same numbers
        testCases.add(new TestCase(new int[]{4, 4, 4, 4, 4}, 4, 4, "All same numbers: 4 4 4 4 4"));
        // Test Case 4: Negative numbers
        testCases.add(new TestCase(new int[]{-2, -1, -3, -2}, -2, -2, "Negative numbers: -2 -1 -3 -2"));
        // Test Case 5: Tie in frequency (should choose the smallest number)
        testCases.add(new TestCase(new int[]{3, 3, 5, 5}, 4, 3, "Tie frequency: 3 3 5 5 (choose smallest mode 3)"));
        // Test Case 6: Large input
        int size = 100000;
        int[] largeInput = new int[size];
        for (int i = 0; i < size; i++) {
            // Fill with 10s, except first element is 5 (making 5 the mode when tie-breaking)
            largeInput[i] = 10;
        }
        largeInput[0] = 5;
        long sum = 5 + 10L * (size - 1);
        int expectedMean = (int) Math.floor(sum / (double) size);
        testCases.add(new TestCase(largeInput, expectedMean, 5, "Large input: 100000 elements"));

        // Run each test case and print PASS/FAIL
        int testNum = 1;
        for (TestCase tc : testCases) {
            int[] result = computeMeanMode(tc.input);
            if (result[0] == tc.expectedMean && result[1] == tc.expectedMode) {
                System.out.println("Test case " + testNum + " (" + tc.description + "): PASS");
                testsPassed++;
            } else {
                System.out.println("Test case " + testNum + " (" + tc.description + "): FAIL");
                System.out.println("   Input: " + Arrays.toString(tc.input));
                System.out.println("   Expected: " + tc.expectedMean + " " + tc.expectedMode);
                System.out.println("   Got: " + result[0] + " " + result[1]);
                testsFailed++;
            }
            testNum++;
        }
        System.out.println("Total tests passed: " + testsPassed + ", failed: " + testsFailed);
    }

    /**
     * Main method:
     * - If run with a command-line argument "test", it executes internal test cases.
     * - Otherwise, it reads input from standard input and outputs the result.
     */
    public static void main(String[] args) {
        // Check for testing mode
        if (args.length > 0 && args[0].equals("test")) {
            runTests();
        } else {
            Scanner in = new Scanner(System.in);
            int inputArr_size = in.nextInt();
            int inputArr[] = new int[inputArr_size];

            for (int idx = 0; idx < inputArr_size; idx++) {
                inputArr[idx] = in.nextInt();
            }

            funcMeanMode(inputArr);
        }
    }
}