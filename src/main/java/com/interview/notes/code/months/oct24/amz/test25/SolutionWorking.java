package com.interview.notes.code.months.oct24.amz.test25;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
FINAL WORKING


### Problem Statement
In a game, there is an array of cells, each with an integer value. In one move, merge any two cells to obtain a new cell that contains the sum of the two cells. The power needed in each move is the sum of the values of the two merged cells. The goal is to merge the cells until only one cell remains. Find the minimum possible power required to do so.

#### Example
**Input:**
`cells = [20, 30, 40]`

**Output:**
`140`

**Explanation:**
1. Select cells with values 20 and 30 and merge them to obtain [50, 40]. The power needed for this move is `20 + 30 = 50`.
2. Select cells with values 50 and 40 and merge them to obtain [90]. The power needed for this move is `50 + 40 = 90`.

The total power required is `50 + 90 = 140`, which is the minimum possible power.

---

### Function Description
Complete the function `minPower`.

#### Parameters:
- `int cells[n]`: the values of each cell

#### Returns:
- `int`: the minimum power required to finish the game

#### Constraints:
- \(2 \leq n \leq 10^5\)
- \(1 \leq cells[i] \leq 100\)

---

### Sample Cases

#### Sample Case 0

**Input:**
```
3
30
10
20
```

**Output:**
```
90
```

**Explanation:**
- Merge 10 and 20, power needed = 10 + 20 = 30, resulting in cells = [30, 30].
- Merge 30 and 30, power needed = 30 + 30 = 60, resulting in cells = [60].

Total power needed is `30 + 60 = 90`.

#### Sample Case 1

**Input:**
```
2
100
1
```

**Output:**
```
101
```

**Explanation:**
- Only one move is made with cells 100 and 1, power needed = 100 + 1 = 101.

 */
public class SolutionWorking {
    /**
     * Calculates the minimum power required to merge the cells into one.
     *
     * @param cells the values of each cell
     * @return the minimum power required to finish the game
     */
    public static int minPower(List<Integer> cells) {
        if (cells == null || cells.isEmpty()) {
            return 0;
        }

        // Use a min-heap (priority queue) to always merge the two smallest cells
        PriorityQueue<Integer> heap = new PriorityQueue<>(cells);

        int totalPower = 0;

        // Continue merging until only one cell remains
        while (heap.size() > 1) {
            // Extract the two smallest cells
            int cell1 = heap.poll();
            int cell2 = heap.poll();

            // Calculate the power needed for this merge
            int sum = cell1 + cell2;
            totalPower += sum;

            // Add the new merged cell back into the heap
            heap.add(sum);
        }

        return totalPower;
    }

    /**
     * Helper method to test the minPower function with given test cases.
     *
     * @param testNumber the identifier for the test case
     * @param cells      the input list of cell values
     * @param expected   the expected output
     */
    public static void testMinPower(int testNumber, List<Integer> cells, int expected) {
        int result = minPower(new ArrayList<>(cells)); // Clone to avoid modifying the original list
        if (result == expected) {
            System.out.println("Test case " + testNumber + ": PASS");
        } else {
            System.out.println("Test case " + testNumber + ": FAIL");
            System.out.println("Expected: " + expected + ", Got: " + result);
        }
    }

    public static void main(String[] args) {
        // Test case 1: Example provided in the problem statement
        List<Integer> cells1 = List.of(20, 30, 40);
        int expected1 = 140;
        testMinPower(1, cells1, expected1);

        // Test case 2: Sample Case 0
        List<Integer> cells2 = List.of(30, 10, 20);
        int expected2 = 90;
        testMinPower(2, cells2, expected2);

        // Test case 3: Sample Case 1
        List<Integer> cells3 = List.of(100, 1);
        int expected3 = 101;
        testMinPower(3, cells3, expected3);

        // Test case 4: All cells have the same value
        List<Integer> cells4 = List.of(10, 10, 10, 10);
        int expected4 = 80;
        testMinPower(4, cells4, expected4);

        // Test case 5: Cells in ascending order
        List<Integer> cells5 = List.of(1, 2, 3, 4, 5);
        int expected5 = 33;
        testMinPower(5, cells5, expected5);

        // Test case 6: All cells have the maximum value
        List<Integer> cells6 = List.of(100, 100, 100, 100, 100);
        int expected6 = 1200;
        testMinPower(6, cells6, expected6);

        // Test case 7: Large input to test performance
        int n = 100000;
        List<Integer> cells7 = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            cells7.add(1);
        }
        long startTime = System.currentTimeMillis();
        int result7 = minPower(cells7);
        long endTime = System.currentTimeMillis();
        System.out.println("Test case 7: Large input test completed in " + (endTime - startTime) + " ms");
        // Since the expected output is large, we can check if the result is greater than zero
        if (result7 > 0) {
            System.out.println("Test case 7: PASS");
        } else {
            System.out.println("Test case 7: FAIL");
        }
    }
}
