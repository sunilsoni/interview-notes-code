package com.interview.notes.code.year.y2024.nov24.CodeSignal.test2;

import java.util.*;

/*
WORKING


**Function Signature**
```java
int[] solution(int[] memory, int[][] queries) {
    // Implementation here
}
```

**Problem Description**
You are given an array of integers `memory` consisting of `0`s and `1`s, where:
- `memory[i] = 0` indicates that the \(i\)-th memory unit is free.
- `memory[i] = 1` indicates that the \(i\)-th memory unit is occupied.

The memory is aligned in segments of 8 units, so all occupied memory blocks must start at an index divisible by 8 (e.g., 0, 8, 16, etc.).

Your task is to perform two types of queries:
1. **alloc X**: Find the leftmost aligned memory block of `X` consecutive free memory units and mark these units as occupied.
   - If there is no aligned memory block of `X` consecutive free units, return `-1`.
   - Otherwise, return the index of the first position of the allocated block, assign an ID to this block (incremented with each successful `alloc`), and mark the block as occupied.

2. **erase ID**: If there exists an allocated memory block with the given `ID`, free all its memory units (set all bits in that block to `0`).
   - Return the length of the deleted memory block.
   - If there is no block with this `ID` or it has already been deleted, return `-1`.

**Query Format**
- `queries[i]` is a 2-element array where:
  - If `queries[i][0] = 0`, it's an `alloc` query where `X = queries[i][1]`.
  - If `queries[i][0] = 1`, it's an `erase` query where `ID = queries[i][1]`.

Return an array containing the results of all queries.

**Input/Output**
- **Execution time limit**: 3 seconds (Java)
- **Memory limit**: 1 GB
- **Input**:
  - `memory`: An array of `0`s and `1`s representing bits of memory.
    - Constraints:
      - \(8 \leq \text{memory.length} \leq 320\)
      - The length of `memory` is guaranteed to be divisible by 8.
  - `queries`: A 2D array representing the queries of type `alloc` or `erase`.
    - Constraints:
      - \(2 \leq \text{queries.length} \leq 300\)
      - \(1 \leq \text{queries[i][1]} \leq \text{memory.length}\) for `alloc` queries.
      - \(1 \leq \text{queries[i][1]} \leq \text{queries.length - 1}\) for `erase` queries.

---

**Example**
Given:
- `memory = [0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1]`
- `queries = [[0, 2], [0, 3], [1, 1], [0, 4]]`

The expected output would be:
```java
solution(memory, queries) = [8, -1, 2, -1]
```

Explanation:
1. `[0, 2]` corresponds to `alloc 2`, finding an aligned position with 2 free slots at index 8. Returns 8.
2. `[0, 3]` tries to `alloc 3`, but no aligned memory block with 3 free slots exists. Returns -1.
3. `[1, 1]` attempts to `erase` the block with `ID = 1`, successfully freeing it. Returns 2.
4. `[0, 4]` tries to `alloc 4`, but there is no aligned block with 4 free slots. Returns -1.

---

*Note*: You are not expected to provide the most optimal solution but one with time complexity no worse than \(O(\text{queries.length} \times \text{memory.length}^2)\), which should fit within the execution time limit.
 */
public class MemoryManager {

    /**
     * Processes memory allocation and erasure queries.
     *
     * @param memory  An array representing memory units (0 for free, 1 for occupied).
     * @param queries A 2D array where each sub-array represents a query.
     * @return An array of integers representing the result of each query.
     */
    public static int[] solution(int[] memory, int[][] queries) {
        int[] results = new int[queries.length];
        Map<Integer, Block> allocatedBlocks = new HashMap<>();
        int currentID = 1;

        for (int i = 0; i < queries.length; i++) {
            int queryType = queries[i][0];
            int param = queries[i][1];

            if (queryType == 0) { // alloc X
                int X = param;
                int allocatedIndex = -1;

                // Iterate through memory in steps of 8 to ensure alignment
                for (int start = 0; start <= memory.length - X; start += 8) {
                    boolean canAllocate = true;
                    for (int j = start; j < start + X; j++) {
                        if (memory[j] != 0) {
                            canAllocate = false;
                            break;
                        }
                    }
                    if (canAllocate) {
                        allocatedIndex = start;
                        // Mark memory as occupied
                        for (int j = start; j < start + X; j++) {
                            memory[j] = 1;
                        }
                        // Store the allocated block with currentID
                        allocatedBlocks.put(currentID, new Block(start, X));
                        results[i] = start;
                        currentID++;
                        break;
                    }
                }

                // If no suitable block found
                if (allocatedIndex == -1) {
                    results[i] = -1;
                }

            } else if (queryType == 1) { // erase ID
                int ID = param;
                if (allocatedBlocks.containsKey(ID)) {
                    Block block = allocatedBlocks.get(ID);
                    // Free the memory
                    for (int j = block.start; j < block.start + block.size; j++) {
                        memory[j] = 0;
                    }
                    results[i] = block.size;
                    // Remove the block from allocatedBlocks
                    allocatedBlocks.remove(ID);
                } else {
                    results[i] = -1;
                }
            } else {
                // Invalid query type
                results[i] = -1;
            }
        }

        return results;
    }

    /**
     * Main method to run test cases and validate the solution.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Provided Example
        testCases.add(new TestCase(
                "Provided Example",
                new int[]{0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                new int[][]{{0, 2}, {0, 3}, {1, 1}, {0, 4}},
                new int[]{8, -1, 2, 8} // Corrected expected output
        ));

        // Test Case 2: Single Allocation and Erasure
        testCases.add(new TestCase(
                "Single Allocation and Erasure",
                new int[]{0, 0, 0, 0, 0, 0, 0, 0},
                new int[][]{{0, 8}, {1, 1}, {0, 8}},
                new int[]{0, 8, 0}
        ));

        // Test Case 3: Multiple Allocations Without Erasure
        testCases.add(new TestCase(
                "Multiple Allocations Without Erasure",
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[][]{{0, 8}, {0, 8}, {0, 8}},
                new int[]{0, 8, -1}
        ));

        // Test Case 4: Erase Non-existent ID
        testCases.add(new TestCase(
                "Erase Non-existent ID",
                new int[]{0, 0, 0, 0, 0, 0, 0, 0},
                new int[][]{{1, 1}},
                new int[]{-1}
        ));

        // Test Case 5: Large Memory and Many Queries
        int[] largeMemory = new int[320];
        Arrays.fill(largeMemory, 0);
        // Pre-occupy some blocks
        for (int i = 0; i < 320; i += 16) {
            largeMemory[i] = 1;
            largeMemory[i + 1] = 1;
            // Occupy two units at the start of every 16-unit segment
        }
        int[][] largeQueries = new int[300][2];
        for (int i = 0; i < 300; i++) {
            if (i % 2 == 0) {
                largeQueries[i][0] = 0;
                largeQueries[i][1] = 8;
            } else {
                largeQueries[i][0] = 1;
                largeQueries[i][1] = (i + 1) / 2;
            }
        }
        // For simplicity, expected results are not precomputed here
        testCases.add(new TestCase(
                "Large Memory and Many Queries",
                largeMemory,
                largeQueries,
                null // We'll skip expected results due to complexity
        ));

        // Execute test cases
        for (TestCase testCase : testCases) {
            System.out.println("Running Test Case: " + testCase.name);
            int[] result = solution(Arrays.copyOf(testCase.memory, testCase.memory.length), testCase.queries);
            if (testCase.expected != null) {
                if (Arrays.equals(result, testCase.expected)) {
                    System.out.println("Result: PASS");
                } else {
                    System.out.println("Result: FAIL");
                    System.out.println("Expected: " + Arrays.toString(testCase.expected));
                    System.out.println("Got:      " + Arrays.toString(result));
                }
            } else {
                // For large test cases, we can perform partial checks or simply indicate completion
                System.out.println("Large test case executed. Verify manually if needed.");
            }
            System.out.println("---------------------------------------------------");
        }
    }

    // Block class to store information about allocated memory blocks
    static class Block {
        int start;
        int size;

        Block(int start, int size) {
            this.start = start;
            this.size = size;
        }
    }

    // Helper class to define a test case
    static class TestCase {
        String name;
        int[] memory;
        int[][] queries;
        int[] expected;

        TestCase(String name, int[] memory, int[][] queries, int[] expected) {
            this.name = name;
            this.memory = memory;
            this.queries = queries;
            this.expected = expected;
        }
    }
}
