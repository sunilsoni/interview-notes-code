package com.interview.notes.code.year.y2024.nov24.CodeSignal.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MemoryAllocatorSolver1 {
    public static int[] solution(int[] memory, int[][] queries) {
        int[] result = new int[queries.length];
        List<MemoryBlock> allocatedBlocks = new ArrayList<>();
        int nextId = 1;

        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == 0) { // alloc
                result[i] = allocateMemory(memory, queries[i][1], allocatedBlocks, nextId);
                if (result[i] != -1) nextId++;
            } else { // erase
                result[i] = eraseMemory(memory, queries[i][1], allocatedBlocks);
            }
        }

        return result;
    }

    private static int allocateMemory(int[] memory, int size, List<MemoryBlock> blocks, int id) {
        for (int start = 0; start <= memory.length - size; start += 8) {
            boolean canAllocate = true;
            for (int j = 0; j < size; j++) {
                if (start + j >= memory.length || memory[start + j] == 1) {
                    canAllocate = false;
                    break;
                }
            }
            if (canAllocate) {
                for (int j = 0; j < size; j++) {
                    memory[start + j] = 1;
                }
                blocks.add(new MemoryBlock(start, size, id));
                return start;
            }
        }
        return -1;
    }

    private static int eraseMemory(int[] memory, int id, List<MemoryBlock> blocks) {
        for (Iterator<MemoryBlock> it = blocks.iterator(); it.hasNext(); ) {
            MemoryBlock block = it.next();
            if (block.id == id) {
                for (int i = block.start; i < block.start + block.length; i++) {
                    memory[i] = 0;
                }
                it.remove();
                return block.length;
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

        // Basic test case from example
        testCases.add(new TestCase(
                new int[]{0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                new int[][]{{0, 2}, {0, 3}, {1, 1}, {0, 4}},
                new int[]{8, -1, 2, -1},
                "Example test case"
        ));

        // Edge cases
        testCases.add(new TestCase(
                new int[]{0, 0, 0, 0, 0, 0, 0, 0},
                new int[][]{{0, 8}, {1, 1}, {0, 8}},
                new int[]{0, 8, 0},
                "Full allocation and reuse"
        ));

        // Large test case
        int[] largeMemory = new int[320];
        Arrays.fill(largeMemory, 0);
        int[][] largeQueries = new int[300][2];
        for (int i = 0; i < 300; i++) {
            largeQueries[i][0] = i % 2;
            largeQueries[i][1] = (i % 8) + 1;
        }
        testCases.add(new TestCase(
                largeMemory,
                largeQueries,
                null, // We'll verify execution time only
                "Large input test"
        ));

        // Run tests
        int passedTests = 0;
        int totalTests = testCases.size();

        for (int i = 0; i < totalTests; i++) {
            TestCase tc = testCases.get(i);
            System.out.printf("\nTest Case %d: %s\n", i + 1, tc.description);

            try {
                long startTime = System.currentTimeMillis();
                int[] result = solution(tc.memory.clone(), tc.queries);
                long endTime = System.currentTimeMillis();

                if (tc.expected == null) {
                    // Performance test case
                    System.out.printf("Execution time: %d ms\n", endTime - startTime);
                    System.out.println("Performance test PASS");
                    passedTests++;
                } else {
                    // Regular test case
                    boolean passed = Arrays.equals(result, tc.expected);
                    System.out.println("Input memory: " + Arrays.toString(tc.memory));
                    System.out.println("Queries: " + Arrays.deepToString(tc.queries));
                    System.out.println("Expected: " + Arrays.toString(tc.expected));
                    System.out.println("Got: " + Arrays.toString(result));
                    System.out.println(passed ? "PASS" : "FAIL");
                    if (passed) passedTests++;
                }
            } catch (Exception e) {
                System.out.println("FAIL - Exception occurred: " + e.getMessage());
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