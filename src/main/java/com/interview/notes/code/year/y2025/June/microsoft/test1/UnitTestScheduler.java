package com.interview.notes.code.year.y2025.June.microsoft.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnitTestScheduler {
    public static Result scheduleTests(List<Test> tests) {
        // Sort tests by (buildTime + runTime) in ascending order for optimal scheduling
        tests.sort((a, b) -> (a.buildTime + a.runTime) - (b.buildTime + b.runTime));

        List<Integer> order = new ArrayList<>();
        int totalTime = 0;

        // Keep track of when each VM becomes available
        int vm1BuildEnd = 0;  // Time when VM1 finishes building
        int vm2BuildEnd = 0;  // Time when VM2 finishes building
        int lastRunEnd = 0;   // Time when last test run completes

        // Process each test
        for (Test test : tests) {
            // Find the VM that becomes available first
            int earliestStart = Math.min(vm1BuildEnd, vm2BuildEnd);

            // Schedule build on the first available VM
            if (vm1BuildEnd <= vm2BuildEnd) {
                vm1BuildEnd = earliestStart + test.buildTime;
            } else {
                vm2BuildEnd = earliestStart + test.buildTime;
            }

            // Calculate when this test's run can start (after build completes)
            int runStart = Math.max(lastRunEnd,
                    Math.min(vm1BuildEnd, vm2BuildEnd));
            lastRunEnd = runStart + test.runTime;

            order.add(test.id);
        }

        totalTime = lastRunEnd;  // Total time is when the last test run completes
        return new Result(order, totalTime);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic test
        List<Test> tests1 = Arrays.asList(
                new Test(1, 4, 5),
                new Test(2, 2, 3),
                new Test(3, 3, 2),
                new Test(4, 1, 4)
        );

        Result result1 = scheduleTests(tests1);
        System.out.println("Test Case 1:");
        System.out.println("Order: " + result1.order);
        System.out.println("Total Time: " + result1.totalTime);

        // Test Case 2: Large data test
        List<Test> tests2 = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            tests2.add(new Test(i, i % 5 + 1, i % 3 + 2));
        }

        Result result2 = scheduleTests(tests2);
        System.out.println("\nTest Case 2 (Large Data):");
        System.out.println("Order: " + result2.order);
        System.out.println("Total Time: " + result2.totalTime);
    }

    // Class to represent a test case with its properties
    static class Test {
        int id;
        int buildTime;
        int runTime;

        Test(int id, int buildTime, int runTime) {
            this.id = id;
            this.buildTime = buildTime;
            this.runTime = runTime;
        }
    }

    // Class to store the result with order and total time
    static class Result {
        List<Integer> order;
        int totalTime;

        Result(List<Integer> order, int totalTime) {
            this.order = order;
            this.totalTime = totalTime;
        }
    }
}
