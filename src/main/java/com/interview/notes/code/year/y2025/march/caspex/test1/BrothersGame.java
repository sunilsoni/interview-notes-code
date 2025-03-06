package com.interview.notes.code.year.y2025.march.caspex.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/*
WORKING 100%

### **Brothers' Game**

Two brothers were playing a game. Their mother gave them an array of numbers, with each element being either **0** or **1**. She then asked them to find out the maximum number of **1s** they could obtain by **inverting** the bits, i.e., changing all the **0s** to **1** and all the **1s** to **0** for any contiguous portion of the array.

The younger brother thought he could solve the problem in his head, but the elder brother chose to write a program to find the optimal solution. **Can you help the elder brother?**

---

### **Input**
- The first line of input contains an integer **N**, representing the size of the array.
- The second line of input contains **N** space-separated integers, representing the array elements.

---

### **Output**
The output is the **maximum number of 1s** that you can make in **one inversion**.

---

### **Constraints**
- \( 1 \leq N \leq 100 \)

---

### **Time Complexity**
Optimize your solution for at least **O(N)**; otherwise, some large test cases may fail.

---

### **Example #1**
**Input**
```
5
0 1 0 0 1
```
**Output**
```
4
```
**Explanation:** We can change the numbers from the third element till the fourth element to get: **0 1 1 1 1**, which has **four** ones.

---

### **Example #2**
**Input**
```
6
1 0 0 1 0 0
```
**Output**
```
5
```
**Explanation:** We can change the numbers from the second element to the last element to get: **1 1 1 0 1 1**, which has **five** ones.

---

 */
public class BrothersGame {

    /**
     * This method computes the maximum number of 1s that can be obtained by
     * inverting one contiguous segment of the input list.
     *
     * @param ar List<Integer> representing the binary array.
     * @return maximum number of 1s after one inversion.
     */
    public static int solve(List<Integer> ar) {
        // Count the number of 1s in the original array.
        int totalOnes = (int) ar.stream().filter(x -> x == 1).count();

        // Create a new list of values where 0 becomes +1 and 1 becomes -1.
        // This transformation represents the net gain in 1s if the bit is flipped.
        List<Integer> gain = ar.stream()
                .map(x -> (x == 0) ? 1 : -1)
                .collect(Collectors.toList());

        // Use Kadane's algorithm to find the maximum sum subarray in the gain list.
        int maxGain = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int value : gain) {
            currentSum = Math.max(value, currentSum + value);
            maxGain = Math.max(maxGain, currentSum);
        }

        // If all numbers are 1 then flipping any segment will reduce the count by at least one.
        // Thus, if maxGain is less than or equal to 0, we must subtract one from totalOnes.
        if (maxGain <= 0) {
            return totalOnes - 1;
        }

        return totalOnes + maxGain;
    }

    /**
     * A helper method to run tests and print the result.
     *
     * @param testName Name/description of the test.
     * @param input    List of integers representing the test array.
     * @param expected Expected output value.
     */
    public static void runTest(String testName, List<Integer> input, int expected) {
        int result = solve(input);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL - Expected " + expected + " but got " + result);
        }
    }

    public static void main(String[] args) {
        // Provided test cases:
        runTest("Test Case 1", Arrays.asList(0, 1, 0, 0, 1), 4);
        runTest("Test Case 2", Arrays.asList(1, 0, 0, 1, 0, 0), 5);

        // Additional test cases:

        // Edge Case: All zeros
        runTest("Edge Case - All zeros", Arrays.asList(0, 0, 0, 0), 4);

        // Edge Case: All ones (must flip at least one bit, so answer is total ones - 1)
        runTest("Edge Case - All ones", Arrays.asList(1, 1, 1, 1), 3);

        // Edge Case: Single element, 0
        runTest("Edge Case - Single 0", Arrays.asList(0), 1);

        // Edge Case: Single element, 1
        runTest("Edge Case - Single 1", Arrays.asList(1), 0);

        // Random Test: Mixed small case
        runTest("Mixed Test", Arrays.asList(1, 0, 1, 0, 1, 0), 4);

        // Large Input Test: Create a large array (100 elements)
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random();
        int onesCount = 0;
        for (int i = 0; i < 100; i++) {
            int val = rand.nextBoolean() ? 1 : 0;
            largeInput.add(val);
            if (val == 1) onesCount++;
        }
        // The expected output is not fixed here; we just run the code to ensure it handles large inputs.
        int largeResult = solve(largeInput);
        System.out.println("Large Input Test (100 random elements): " + largeResult + " ones after inversion");
    }
}
