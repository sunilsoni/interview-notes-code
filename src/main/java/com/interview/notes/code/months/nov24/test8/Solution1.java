package com.interview.notes.code.months.nov24.test8;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    static class Employee {
        Integer startDay;   // The day the employee started working at the company (in days)
        Integer trainedDay; // (optional) The day the employee was trained (in days)

        public Employee(Integer startDay, Integer trainedDay) {
            this.startDay = startDay;
            this.trainedDay = trainedDay;
        }
    }

    static class Status {
        String name;          // one of {"not_required", "pending", "overdue", "completed"}
        Integer daysOverdue;  // 0 if not overdue

        public Status(String name, Integer daysOverdue) {
            this.name = name;
            this.daysOverdue = daysOverdue;
        }

        @Override
        public String toString() {
            return this.name + "," + this.daysOverdue;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Status)) {
                return false;
            }
            Status other = (Status) obj;
            return this.name.equals(other.name) && this.daysOverdue.equals(other.daysOverdue);
        }
    }

    /**
     * Evaluates an employee's training status on a specified checkDay.
     * @param employee - The employee's details
     * @param trainingWindow - The number of days an employee has to complete their training after their start day.
     * @param checkDay - The day for which we are checking the employee's training status.
     * @return Status object with the training status
     */
    public static Status getTrainingStatus(Employee employee, Integer trainingWindow, Integer checkDay) {
        if (employee.startDay == null || trainingWindow == null || checkDay == null) {
            throw new IllegalArgumentException("Employee startDay, trainingWindow, and checkDay must not be null.");
        }

        Integer startDay = employee.startDay;
        Integer trainedDay = employee.trainedDay;
        Integer windowEndDay = startDay + trainingWindow;

        if (checkDay < startDay) {
            return new Status("not_required", 0);
        }

        if (trainedDay != null && trainedDay <= checkDay) {
            return new Status("completed", 0);
        }

        if (checkDay <= windowEndDay) {
            return new Status("pending", 0);
        }

        // If training was not completed by checkDay and the window has passed
        if (trainedDay == null || trainedDay > checkDay) {
            int daysOverdue = checkDay - windowEndDay;
            return new Status("overdue", daysOverdue);
        }

        // Default case (should not be reached)
        return new Status("not_required", 0);
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Provided Example Test Cases
        // Example 1
        testCases.add(new TestCase(
                "Example 1",
                new Employee(100, null),
                10,
                104,
                new Status("pending", 0)
        ));

        // Example 2
        testCases.add(new TestCase(
                "Example 2",
                new Employee(100, 105),
                10,
                110,
                new Status("completed", 0)
        ));

        // Example 3
        testCases.add(new TestCase(
                "Example 3",
                new Employee(100, 107),
                5,
                110,
                new Status("completed", 0)
        ));

        // Example 4
        testCases.add(new TestCase(
                "Example 4",
                new Employee(100, 109),
                5,
                106,
                new Status("overdue", 1)
        ));

        // Example 5
        testCases.add(new TestCase(
                "Example 5",
                new Employee(100, null),
                10,
                95,
                new Status("not_required", 0)
        ));

        // Additional Edge Cases
        // Edge Case 1: Trained exactly on the window end day
        testCases.add(new TestCase(
                "Edge Case 1",
                new Employee(200, 210),
                10,
                210,
                new Status("completed", 0)
        ));

        // Edge Case 2: Check day exactly on start day
        testCases.add(new TestCase(
                "Edge Case 2",
                new Employee(300, null),
                15,
                300,
                new Status("pending", 0)
        ));

        // Edge Case 3: Trained after check day
        testCases.add(new TestCase(
                "Edge Case 3",
                new Employee(400, 420),
                15,
                415,
                new Status("overdue", 0)
        ));

        // Edge Case 4: Training window is zero
        testCases.add(new TestCase(
                "Edge Case 4",
                new Employee(500, 500),
                0,
                500,
                new Status("completed", 0)
        ));

        // Edge Case 5: Negative days
        testCases.add(new TestCase(
                "Edge Case 5",
                new Employee(-10, null),
                5,
                -5,
                new Status("pending", 0)
        ));

        // Large Data Input Test Case
        testCases.add(new TestCase(
                "Large Data Test",
                new Employee(1000000, 1005000),
                1000,
                1006000,
                new Status("completed", 0)
        ));

        // Run all test cases
        int passCount = 0;
        for (TestCase testCase : testCases) {
            Status actual = getTrainingStatus(testCase.employee, testCase.trainingWindow, testCase.checkDay);
            boolean pass = actual.equals(testCase.expectedStatus);
            if (pass) {
                passCount++;
                System.out.println(testCase.name + ": PASS");
            } else {
                System.out.println(testCase.name + ": FAIL");
                System.out.println("  Expected: " + testCase.expectedStatus);
                System.out.println("  Actual:   " + actual);
            }
        }

        // Summary
        System.out.println("\nTest Summary: " + passCount + "/" + testCases.size() + " passed.");
    }

    // Helper class for test cases
    static class TestCase {
        String name;
        Employee employee;
        Integer trainingWindow;
        Integer checkDay;
        Status expectedStatus;

        public TestCase(String name, Employee employee, Integer trainingWindow, Integer checkDay, Status expectedStatus) {
            this.name = name;
            this.employee = employee;
            this.trainingWindow = trainingWindow;
            this.checkDay = checkDay;
            this.expectedStatus = expectedStatus;
        }
    }
}
