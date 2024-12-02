package com.interview.notes.code.year.y2024.nov24.CodeSignal.test1;

import java.util.*;

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
                new int[]{8, -1, 2, -1}
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
