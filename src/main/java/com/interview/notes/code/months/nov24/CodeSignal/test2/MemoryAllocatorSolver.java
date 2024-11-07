package com.interview.notes.code.months.nov24.CodeSignal.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*

 */
public class MemoryAllocatorSolver {
    public static int[] solution(int[] memory, int[][] queries) {
        int[] result = new int[queries.length];
        List<MemoryBlock> allocatedBlocks = new ArrayList<>();
        int nextId = 1;

        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == 0) { // alloc
                int size = queries[i][1];
                result[i] = findAndAllocateMemory(memory, size, allocatedBlocks, nextId);
                if (result[i] != -1) {
                    nextId++;
                }
            } else { // erase
                result[i] = eraseMemory(memory, queries[i][1], allocatedBlocks);
            }
        }
        return result;
    }

    private static int findAndAllocateMemory(int[] memory, int size, List<MemoryBlock> blocks, int nextId) {
        // Check each aligned position
        for (int start = 0; start <= memory.length - size; start += 8) {
            if (hasEnoughConsecutiveFreeSpace(memory, start, size)) {
                // Allocate the memory
                for (int j = start; j < start + size; j++) {
                    memory[j] = 1;
                }
                blocks.add(new MemoryBlock(start, size, nextId));
                return start;
            }
        }
        return -1;
    }

    private static boolean hasEnoughConsecutiveFreeSpace(int[] memory, int start, int size) {
        // Check if there's enough space from the start position
        if (start + size > memory.length) {
            return false;
        }

        // Check if all required units are free
        for (int i = start; i < start + size; i++) {
            if (memory[i] == 1) {
                return false;
            }
        }
        return true;
    }

    private static int eraseMemory(int[] memory, int id, List<MemoryBlock> blocks) {
        for (Iterator<MemoryBlock> it = blocks.iterator(); it.hasNext(); ) {
            MemoryBlock block = it.next();
            if (block.id == id) {
                // Free the memory
                for (int i = block.start; i < block.start + block.length; i++) {
                    memory[i] = 0;
                }
                int length = block.length;
                it.remove();
                return length;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Test case class
        class TestCase {
            int[] memory;
            int[][] queries;
            int[] expected;
            String description;

            TestCase(int[] memory, int[][] queries, int[] expected, String description) {
                this.memory = memory.clone();
                this.queries = queries;
                this.expected = expected;
                this.description = description;
            }
        }

        // Create test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example test case
        testCases.add(new TestCase(
                new int[]{0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                new int[][]{{0, 2}, {0, 3}, {1, 1}, {0, 4}},
                new int[]{8, -1, 2, -1},
                "Example test case"
        ));

        // Additional test cases
        testCases.add(new TestCase(
                new int[]{0, 0, 0, 0, 0, 0, 0, 0},
                new int[][]{{0, 8}, {1, 1}, {0, 8}},
                new int[]{0, 8, 0},
                "Full allocation and reuse"
        ));

        testCases.add(new TestCase(
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[][]{{0, 4}, {0, 8}, {1, 1}, {0, 4}},
                new int[]{0, 8, 4, 0},
                "Multiple allocations with varying sizes"
        ));

        // Run all test cases
        int passedTests = 0;
        int totalTests = testCases.size();

        for (int i = 0; i < totalTests; i++) {
            TestCase tc = testCases.get(i);
            System.out.printf("\nTest Case %d: %s\n", i + 1, tc.description);

            try {
                int[] result = solution(tc.memory.clone(), tc.queries);
                boolean passed = Arrays.equals(result, tc.expected);

                System.out.println("Input memory: " + Arrays.toString(tc.memory));
                System.out.println("Queries: " + Arrays.deepToString(tc.queries));
                System.out.println("Expected: " + Arrays.toString(tc.expected));
                System.out.println("Got: " + Arrays.toString(result));
                System.out.println(passed ? "PASS" : "FAIL");

                if (passed) passedTests++;

                // Debug output for failing cases
                if (!passed) {
                    System.out.println("\nDebug - Memory state after each operation:");
                    int[] debugMemory = tc.memory.clone();
                    for (int j = 0; j < tc.queries.length; j++) {
                        System.out.printf("After query %d: %s\n", j + 1,
                                Arrays.toString(debugMemory));
                    }
                }
            } catch (Exception e) {
                System.out.println("FAIL - Exception occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Print summary
        System.out.println("\nTest Summary:");
        System.out.printf("Passed: %d/%d tests\n", passedTests, totalTests);
        System.out.printf("Success Rate: %.2f%%\n", (passedTests * 100.0) / totalTests);
    }

    static class MemoryBlock {
        int start;
        int length;
        int id;

        MemoryBlock(int start, int length, int id) {
            this.start = start;
            this.length = length;
            this.id = id;
        }
    }
}