package com.interview.notes.code.year.y2024.dec24.wallmart.test4;

import java.util.*;

/*
WOKING


```text
Constraints
- 1 ≤ n ≤ 10^5
- 1 ≤ nums[i] ≤ 10^4 (where 0 ≤ i < n)
- 1 ≤ k ≤ 2*10^6

Input Format For Custom Testing
The first line contains an integer, n, denoting the number of elements in nums.
Each line i of the n subsequent lines (where 0 ≤ i < n) contains an integer describing nums[i].
The last line contains an integer, k, denoting the number of moves.

Sample Case 0

Sample Input For Custom Testing
STDIN   Function
-----   --------
1       nums[] size n = 1
2       nums = [2]
1       k = 1

Sample Output
1

Explanation
In the first operation, the number 2 is reduced to 1.

---

Minimum Sum

Description
Given an array of integers, perform some number k of operations. Each operation consists of removing an element from the array, dividing it by 2 and inserting the ceiling of that result back into the array. Minimize the sum of the elements in the final array.

Example:
nums = [10, 20, 7]
k = 4

Table of Operations
| Pick       | Pick/2 | Ceiling | Result       |
|------------|--------|---------|--------------|
| Initial array |        |         | [10, 20, 7] |
| 7          | 3.5    | 4       | [10, 20, 4]  |
| 10         | 5      | 5       | [5, 20, 4]   |
| 20         | 10     | 10      | [5, 10, 4]   |
| 10         | 5      | 5       | [5, 5, 4]    |

The sum of the final array is 5 + 5 + 4 = 14, and that sum is minimal.

Function Description
Complete the function `minSum` in the editor below.

`minSum` has the following parameters:
- `int nums[n]`: an array of integers, indexed 0 to n-1
- `int k`: an integer

Returns:
- `int`: the minimum sum of the array after k steps

---

Code Template
/*
 * Complete the 'minSum' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER_ARRAY num
 *  2. INTEGER k

 */
public class MinimumSumCalculator {

    /**
     * Computes the minimum sum of the array after performing k operations.
     * Each operation consists of removing the largest element, dividing it by 2,
     * taking the ceiling of the result, and inserting it back into the array.
     *
     * @param num the initial list of integers
     * @param k   the number of operations to perform
     * @return the minimum sum of the array after k operations
     */
    public static int minSum(List<Integer> num, int k) {
        // Create a max heap using a priority queue with reversed order comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(num);

        // Perform k operations
        for (int i = 0; i < k; i++) {
            int max = maxHeap.poll(); // Remove the largest element
            int newVal = (max + 1) / 2; // Divide by 2 and take the ceiling
            maxHeap.add(newVal); // Insert the new value back into the heap
        }

        // Calculate the sum of the elements in the heap
        int sum = 0;
        while (!maxHeap.isEmpty()) {
            sum += maxHeap.poll();
        }

        return sum;
    }

    public static void main(String[] args) {
        // List of test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase(List.of(2), 1, 1));

        // Example Test Case from problem description
        // testCases.add(new TestCase(Arrays.asList(10, 20, 7), 4, 14));
        testCases.add(new TestCase(Arrays.asList(2, 3), 1, 4));


        // Edge Case 1: More operations than elements, with elements at minimum
        testCases.add(new TestCase(Arrays.asList(1, 1, 1), 5, 3)); // Expected sum is 3

        // Edge Case 2: Large value and large k
        testCases.add(new TestCase(List.of(10000), 2000000, 1)); // Expected sum is 1

        // Edge Case 3: No operations
        testCases.add(new TestCase(Arrays.asList(5, 5, 5, 5, 5), 0, 25)); // Expected sum is 25

        // Edge Case 4: All elements are at minimum
        testCases.add(new TestCase(Arrays.asList(1, 1, 1, 1, 1), 10, 5)); // Expected sum is 5

        // Edge Case 5: Maximum number of elements and operations
        List<Integer> numsEdge5 = new ArrayList<>(Collections.nCopies(100000, 10000));
        testCases.add(new TestCase(numsEdge5, 2000000, 100000)); // Sum is 100,000 * 1 = 100,000

        // Run test cases
        int testNumber = 1;
        for (TestCase testCase : testCases) {
            int actualOutput = minSum(new ArrayList<>(testCase.num), testCase.k);
            if (actualOutput == testCase.expectedOutput) {
                System.out.println("Test Case " + testNumber + ": PASS");
            } else {
                System.out.println("Test Case " + testNumber + ": FAIL");
                System.out.println("Expected Output: " + testCase.expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
            }
            testNumber++;
        }
    }

    // Helper class to represent a test case
    static class TestCase {
        List<Integer> num;
        int k;
        int expectedOutput;

        TestCase(List<Integer> num, int k, int expectedOutput) {
            this.num = num;
            this.k = k;
            this.expectedOutput = expectedOutput;
        }
    }
}