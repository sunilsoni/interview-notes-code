package com.interview.notes.code.months.oct24.amazon.test4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING:


The management team at one of Amazon’s warehouse facilities is planning to install a smart lighting system.

A total of **n** smart bulbs will be installed along a straight line. A smart bulb automatically turns **OFF** if the sum of the brightness of the ON bulbs in front of it is greater than its own brightness. The bulbs can be rearranged in any order. The team wants to find out the **minimum number of bulbs** that will turn **OFF** across all possible permutations of the sequence of bulbs.

### Problem Statement:

Given an array, **brightness**, that represents the brightness of the bulbs placed along a straight line, find the **minimum number of bulbs** that will turn **OFF** if the bulbs can be rearranged in any order.

Note: All bulbs are turned **ON** initially. They turn **OFF** (in order, from left to right) based on the condition mentioned above.

#### Example:

Suppose, **n = 5**, brightness = [2, 1, 3, 4, 3].

Some of the possible arrangements of bulbs, their final states, and the corresponding number of **OFF** bulbs are as follows:

| Arrangement       | Final State      | OFF Count |
|-------------------|------------------|-----------|
| [2, 1, 3, 4, 3]   | [T, T, F, F, F]  | 3         |
| [2, 3, 4, 1, 3]   | [T, T, F, F, F]  | 3         |
| [3, 2, 4, 1, 3]   | [T, T, F, F, F]  | 3         |
| [4, 3, 3, 2, 1]   | [T, F, F, F, F]  | 4         |
| [1, 2, 3, 4, 3]   | [T, T, F, F, F]  | 2         |
| [3, 3, 4, 1, 2]   | [T, T, F, F, F]  | 3         |

**T = ON state, F = OFF state**

One of the optimal arrangements is [1, 2, 3, 4, 3]. The minimum number of **OFF** bulbs in the final state is **2**.

---

### Function Description:

Complete the function **getMinimumOffBulbs** in the editor below.

```java
public static int getMinimumOffBulbs(List<Integer> brightness) {
    // Implement your solution here
}
```

**getMinimumOffBulbs** has the following parameters:
- **int brightness[n]**: an array representing the brightness values of the bulbs.

**Returns**:
- **int**: the minimum number of bulbs that will turn **OFF** in the final state, across all possible arrangements of the bulbs.

---

### Constraints:

- \(1 \leq n \leq 10^5\)
- \(1 \leq brightness[i] \leq 10^9\), where \(0 \leq i < n\)

---

### Input Format For Custom Testing:

The first line contains an integer, **n**, the number of elements in **brightness**.
Each of the next **n** lines contains an integer, **brightness[i]**.

---

### Sample Case 0:

**Sample Input For Custom Testing**:

```
3
3
4
2
```

**Sample Output**:
```
1
```

**Explanation**:

Here, **n = 3** and **brightness = [3, 4, 2]**.

The original arrangement [3, 4, 2] is an optimal arrangement. It results in [T, T, F].

- The bulb at index 0 has no bulb before it, hence it remains ON.
- 3 < 4, so the second bulb is ON.
- 3 + 4 > 2 so the third bulb is OFF.

---

### Sample Case 1:

**Sample Input For Custom Testing**:

```
4
1
5
10
20
```

**Sample Output**:
```
0
```

**Explanation**:

All bulbs are ON with the initial arrangement [1, 5, 10, 20].

- The bulb at index 0 has no bulb before it, hence it remains ON.
- 1 < 5, ON.
- 1 + 5 < 10, ON.
- 1 + 5 + 10 < 20, ON.

 */
public class SmartLightingSystem {
    public static void main(String[] args) throws IOException {
        // Initialize test cases
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(Arrays.asList(3, 4, 2), 1)); // Sample Input 0
        testCases.add(new TestCase(Arrays.asList(1, 5, 10, 20), 0)); // Sample Input 1
        testCases.add(new TestCase(Arrays.asList(2, 1, 3, 4, 3), 2)); // Example from problem description

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = getMinimumOffBulbs(tc.brightness);
            boolean pass = result == tc.expectedOutput;
            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + tc.expectedOutput + ", Got: " + result);
            }
        }

        // Process custom input if needed
        processCustomInput();
    }

    /**
     * Reads custom input from stdin and outputs the result.
     */
    private static void processCustomInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        if (line == null || line.isEmpty()) {
            return; // No custom input provided
        }
        int n = Integer.parseInt(line.trim());
        List<Integer> brightness = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            brightness.add(Integer.parseInt(reader.readLine().trim()));
        }
        int result = getMinimumOffBulbs(brightness);
        System.out.println(result);
    }

    /**
     * Calculates the minimum number of bulbs that will turn OFF across all possible arrangements.
     *
     * @param brightness List of brightness values of the bulbs.
     * @return Minimum number of bulbs that will turn OFF.
     */
    public static int getMinimumOffBulbs(List<Integer> brightness) {
        // Copy brightness list to an array for efficient access
        int n = brightness.size();
        int[] bulbs = new int[n];
        for (int i = 0; i < n; i++) {
            bulbs[i] = brightness.get(i);
        }

        // Sort the bulbs in increasing order of brightness
        Arrays.sort(bulbs);

        // Initialize sum of brightness of ON bulbs
        long sum = 0;
        int onBulbs = 0;

        // Simulate the process from left to right
        for (int i = 0; i < n; i++) {
            if (sum <= bulbs[i]) {
                // Bulb stays ON
                sum += bulbs[i];
                onBulbs++;
            }
            // Bulb turns OFF if sum > bulbs[i]; no action needed as sum remains the same
        }

        // Minimum bulbs that turn OFF is total bulbs minus bulbs that stay ON
        return n - onBulbs;
    }
}

/**
 * Helper class to store test cases.
 */
class TestCase {
    List<Integer> brightness;
    int expectedOutput;

    TestCase(List<Integer> brightness, int expectedOutput) {
        this.brightness = brightness;
        this.expectedOutput = expectedOutput;
    }
}
