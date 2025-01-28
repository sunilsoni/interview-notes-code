package com.interview.notes.code.year.y2025.jan24.ibm.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
PARTIAL WORKING

6/15 Passed:



### Problem Statement
In a processor queue, there are \( n \) tasks. The priority levels of the tasks are represented by an array `priority`, in which each integer is a single digit. Tasks are classified as:
- **CPU-bound tasks**: Represented as odd integers in the array.
- **I/O-bound tasks**: Represented as even integers in the array.

To improve efficiency, the following operation can be performed:
- **Swap** two adjacent tasks such that one is a CPU-bound task and the other is an I/O-bound task.

The goal is to reorder the tasks to achieve the **lexicographically smallest possible priority sequence** after making any number of valid operations (including zero). Return this lexicographically smallest sequence.

**Note:**
A sequence is lexicographically smaller than another if it appears before it in dictionary order, comparing character by character from left to right.

---

#### Example
**Input:**
```
n = 6
priority = [2, 4, 6, 4, 3, 2]
```

**Output:**
```
[2, 3, 4, 6, 4, 2]
```

Explanation:
The tasks in **bold** are selected for swapping.

An optimal sequence of swaps is as follows:
| **Before swapping** | **After swapping** |
|----------------------|--------------------|
| [2, 4, **6, 4**, 3, 2] | [2, 4, **3, 6**, 4, 2] |
| [2, 4, 3, **6, 4**, 2] | [2, 3, **4, 6**, 4, 2] |

The lexicographically smallest possible priority sequence for the tasks is `[2, 3, 4, 6, 4, 2]`.

---

### Function Description
Complete the function `getOptimalPriority` in the editor below.

The function is expected to return an **integer array**.
The function accepts **INTEGER_ARRAY priority** as a parameter.

---

#### Constraints
- \( 1 \leq n \leq 2 \times 10^5 \)
- \( 0 \leq \text{priority}[i] \leq 9 \)

---

### Input Format for Custom Testing
1. The first line contains an integer \( n \), the size of the array `priority`.
2. Each of the next \( n \) lines contains an integer \( \text{priority}[i] \).

---

### Sample Test Cases

#### Sample Input 0:
```
4
0
7
0
9
```

**Output:**
```
[0, 0, 7, 9]
```

Explanation:
The tasks in **bold** are selected for swapping.

An optimal sequence of swaps is as follows:
| **Before swapping** | **After swapping** |
|----------------------|--------------------|
| [0, **7, 0**, 9] | [0, **0, 7**, 9] |

The lexicographically smallest possible priority sequence for the tasks is `[0, 0, 7, 9]`.

---

#### Sample Input 1:
```
5
9
4
8
6
3
```

**Output:**
```
[4, 8, 6, 9, 3]
```

Explanation:
The tasks in **bold** are selected for swapping.

An optimal sequence of swaps is as follows:
| **Before swapping** | **After swapping** |
|----------------------|--------------------|
| [**9, 4**, 8, 6, 3] | [**4, 9**, 8, 6, 3] |
| [4, **9, 8**, 6, 3] | [4, **8, 9**, 6, 3] |

The lexicographically smallest possible priority sequence for the tasks is `[4, 8, 6, 9, 3]`.

 */
class Result {

    /*
     * Complete the 'getOptimalPriority' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY priority as parameter.
     */

    public static List<Integer> getOptimalPriority(List<Integer> priority) {
        // Convert list to array for efficient access
        int n = priority.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = priority.get(i);
        }

        // Perform a variant of bubble sort with the given constraints
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i] < arr[i - 1] && isSwappable(arr[i], arr[i - 1])) {
                    // Swap
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        // Convert array back to list
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            result.add(num);
        }
        return result;
    }

    // Helper method to determine if two tasks can be swapped
    private static boolean isSwappable(int a, int b) {
        // One is CPU-bound (odd), and the other is I/O-bound (even)
        return (isCpuBound(a) && isIoBound(b)) || (isIoBound(a) && isCpuBound(b));
    }

    // Helper method to check if a task is CPU-bound
    private static boolean isCpuBound(int a) {
        return a % 2 != 0;
    }

    // Helper method to check if a task is I/O-bound
    private static boolean isIoBound(int a) {
        return a % 2 == 0;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase(
                Arrays.asList(0, 7, 0, 9),
                Arrays.asList(0, 0, 7, 9)
        ));

        // Sample Test Case 1
        testCases.add(new TestCase(
                Arrays.asList(9, 4, 8, 6, 3),
                Arrays.asList(4, 8, 6, 9, 3)
        ));

        // Additional Test Case 2: All I/O-bound
        testCases.add(new TestCase(
                Arrays.asList(2, 4, 6, 8),
                Arrays.asList(2, 4, 6, 8)
        ));

        // Additional Test Case 3: All CPU-bound
        testCases.add(new TestCase(
                Arrays.asList(1, 3, 5, 7),
                Arrays.asList(1, 3, 5, 7)
        ));

        // Additional Test Case 4: Already sorted
        testCases.add(new TestCase(
                Arrays.asList(0, 1, 2, 3, 4, 5),
                Arrays.asList(0, 1, 2, 3, 4, 5)
        ));

        // Additional Test Case 5: Reverse sorted with swappable pairs
        testCases.add(new TestCase(
                Arrays.asList(5, 4, 3, 2, 1, 0),
                Arrays.asList(4, 2, 0, 5, 3, 1) // Correct expected output
        ));

        // Additional Test Case 6: Large Input
        List<Integer> largeInput = new ArrayList<>();
        List<Integer> largeExpected = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            if (i % 2 == 0) {
                largeInput.add(8); // I/O-bound
                largeExpected.add(8);
            } else {
                largeInput.add(7); // CPU-bound
                largeExpected.add(7);
            }
        }
        testCases.add(new TestCase(largeInput, largeExpected));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long startTime = System.currentTimeMillis();
            List<Integer> output = getOptimalPriority(tc.input);
            long endTime = System.currentTimeMillis();
            boolean isPassed = output.equals(tc.expected);
            System.out.println("Test Case " + i + ": " + (isPassed ? "PASS" : "FAIL") +
                    " (Time: " + (endTime - startTime) + " ms)");
            if (isPassed) passed++;
            else {
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got     : " + output);
            }
        }
        System.out.println("Passed " + passed + " out of " + testCases.size() + " test cases.");
    }

    // Helper class for test cases
    static class TestCase {
        List<Integer> input;
        List<Integer> expected;

        TestCase(List<Integer> input, List<Integer> expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
