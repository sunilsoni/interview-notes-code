package com.interview.notes.code.year.y2025.June.microsoft.test3;

import java.util.*;

public class UnitTestScheduler {
    public static Result scheduleTests(List<Test> tests) {
        // Handle edge cases
        if (tests == null || tests.isEmpty()) {
            return new Result(new ArrayList<>(), 0);
        }

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

    // Helper method to visualize the execution timeline
    private static void printTimeline(List<Test> tests, Result result) {
        System.out.println("\nExecution Timeline:");

        // Create a map of id to original test for easy lookup
        Map<Integer, Test> testMap = new HashMap<>();
        for (Test t : tests) {
            testMap.put(t.id, t);
        }

        int currentTime = 0;
        int vm1Time = 0;
        int vm2Time = 0;
        int runTime = 0;

        // Print header with time markers
        System.out.print("Time: ");
        for (int i = 0; i <= result.totalTime; i++) {
            System.out.print(i % 10);
        }
        System.out.println();

        // Print separator
        System.out.print("      ");
        for (int i = 0; i <= result.totalTime; i++) {
            System.out.print("-");
        }
        System.out.println();

        for (Integer testId : result.order) {
            Test test = testMap.get(testId);
            StringBuilder timeline = new StringBuilder();
            timeline.append(String.format("T%-3d: ", testId));

            // Fill timeline with spaces up to current position
            for (int i = 0; i < currentTime; i++) {
                timeline.append(" ");
            }

            // Add build phase
            for (int i = 0; i < test.buildTime; i++) {
                timeline.append("B");
            }

            // Add spaces between build and run
            int gap = Math.max(0, runTime - (currentTime + test.buildTime));
            for (int i = 0; i < gap; i++) {
                timeline.append(" ");
            }

            // Add run phase
            for (int i = 0; i < test.runTime; i++) {
                timeline.append("R");
            }

            System.out.println(timeline.toString());

            // Update timings
            if (vm1Time <= vm2Time) {
                vm1Time = currentTime + test.buildTime;
            } else {
                vm2Time = currentTime + test.buildTime;
            }
            runTime = Math.max(runTime, Math.min(vm1Time, vm2Time)) + test.runTime;
            currentTime += 2; // Offset for visual clarity
        }

        System.out.println("\nLegend:");
        System.out.println("B = Build phase");
        System.out.println("R = Run phase");
    }

    public static void main(String[] args) {
        // Test Case 1: Basic test
        List<Test> tests1 = Arrays.asList(
                new Test(1, 4, 5),
                new Test(2, 2, 3),
                new Test(3, 3, 2),
                new Test(4, 1, 4)
        );

        System.out.println("Test Case 1:");
        System.out.println("Input: " + tests1);
        Result result1 = scheduleTests(tests1);
        System.out.println("Order: " + result1.order);
        System.out.println("Total Time: " + result1.totalTime);
        printTimeline(tests1, result1);

        // Test Case 2: Specific scenario
        List<Test> tests2 = Arrays.asList(
                new Test(1, 5, 2),
                new Test(2, 3, 6),
                new Test(3, 4, 1),
                new Test(4, 2, 4)
        );

        System.out.println("\n\nTest Case 2:");
        System.out.println("Input: " + tests2);
        Result result2 = scheduleTests(tests2);
        System.out.println("Order: " + result2.order);
        System.out.println("Total Time: " + result2.totalTime);
        printTimeline(tests2, result2);

        // Test Case 3: Large data test
        List<Test> tests3 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tests3.add(new Test(i, i % 5 + 1, i % 3 + 2));
        }

        System.out.println("\n\nTest Case 3 (Large Data):");
        System.out.println("Input: " + tests3);
        Result result3 = scheduleTests(tests3);
        System.out.println("Order: " + result3.order);
        System.out.println("Total Time: " + result3.totalTime);
        printTimeline(tests3, result3);

        // Test Case 4: Edge cases
        System.out.println("\n\nTest Case 4 (Edge Cases):");
        System.out.println("Empty list: " + scheduleTests(new ArrayList<>()).order);
        System.out.println("Single test: " +
                scheduleTests(Arrays.asList(new Test(1, 1, 1))).order);
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

        @Override
        public String toString() {
            return String.format("Test(id=%d, build=%d, run=%d)", id, buildTime, runTime);
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
