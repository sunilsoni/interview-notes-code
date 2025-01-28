package com.interview.notes.code.year.y2025.jan24.ibm.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Result {

    /*
     * Complete the 'getOptimalPriority' function below.
     *
     * The function is expected to return an INTEGER_LIST.
     * The function accepts INTEGER_LIST priority as parameter.
     */

    public static List<Integer> getOptimalPriority(List<Integer> priority) {
        // Lists to store I/O-bound and CPU-bound tasks
        List<Integer> ioTasks = new ArrayList<>();
        List<Integer> cpuTasks = new ArrayList<>();

        // Separate the tasks based on their type
        for (int task : priority) {
            if (isIoBound(task)) {
                ioTasks.add(task);
            } else {
                cpuTasks.add(task);
            }
        }

        // Merge the two lists to form the lex smallest sequence
        List<Integer> result = new ArrayList<>(priority.size());
        int ioIndex = 0;
        int cpuIndex = 0;
        int ioSize = ioTasks.size();
        int cpuSize = cpuTasks.size();

        while (ioIndex < ioSize && cpuIndex < cpuSize) {
            int ioTask = ioTasks.get(ioIndex);
            int cpuTask = cpuTasks.get(cpuIndex);
            if (ioTask < cpuTask) {
                result.add(ioTask);
                ioIndex++;
            } else if (cpuTask < ioTask) {
                result.add(cpuTask);
                cpuIndex++;
            } else { // Equal tasks, prefer I/O-bound to maintain stability
                result.add(ioTask);
                ioIndex++;
            }
        }

        // Append any remaining tasks
        while (ioIndex < ioSize) {
            result.add(ioTasks.get(ioIndex++));
        }
        while (cpuIndex < cpuSize) {
            result.add(cpuTasks.get(cpuIndex++));
        }

        return result;
    }

    // Helper method to check if a task is I/O-bound
    private static boolean isIoBound(int task) {
        return task % 2 == 0;
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
                Arrays.asList(4, 6, 8, 3, 9)
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
                Arrays.asList(4, 2, 1, 0, 5, 3) // Correct achievable output
        ));

        // Additional Test Case 6: Multiple Identical Priorities
        testCases.add(new TestCase(
                Arrays.asList(2, 2, 3, 3, 4, 4),
                Arrays.asList(2, 2, 3, 3, 4, 4)
        ));

        // Additional Test Case 7: Alternating Types
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 6)
        ));

        // Additional Test Case 8: Tasks with Priority 0
        testCases.add(new TestCase(
                Arrays.asList(0, 7, 0, 9),
                Arrays.asList(0, 0, 7, 9)
        ));

        // Additional Test Case 9: Single Element (I/O-bound)
        testCases.add(new TestCase(
                Arrays.asList(2),
                Arrays.asList(2)
        ));

        // Additional Test Case 10: Single Element (CPU-bound)
        testCases.add(new TestCase(
                Arrays.asList(5),
                Arrays.asList(5)
        ));

        // Additional Test Case 11: Large Input
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
