package com.interview.notes.code.year.y2024.nov24.test10;

import java.util.*;

class Solution {
    /**
     * Evaluates an employee's training status on a specified checkDay.
     *
     * @param employee       - The employee's details
     * @param trainingWindow - The number of days an employee has to complete their training after their start day.
     * @param checkDay       - The day for which we are checking the employee's training status.
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

    /**
     * Calculates the total overdue days and number of overdue employees for each group,
     * considering the hierarchical group structure.
     *
     * @param employees      - List of all employees
     * @param groups         - List of all groups
     * @param trainingWindow - The number of days allowed to complete training from the start day
     * @param checkDay       - The day we are checking the employee’s training status
     * @return List of Datapoint objects summarizing each group's overdue data
     */
    public static List<Datapoint> getTotalDaysOverdueByGroups(List<Employee> employees, List<Group> groups, Integer trainingWindow, Integer checkDay) {
        // Map to store groupId to Group object for quick access
        Map<String, Group> groupMap = new HashMap<>();
        for (Group group : groups) {
            groupMap.put(group.id, group);
        }

        // Map to store groupId to list of employees directly belonging to that group
        Map<String, List<Employee>> groupToEmployees = new HashMap<>();
        for (Employee emp : employees) {
            groupToEmployees.computeIfAbsent(emp.groupId, k -> new ArrayList<>()).add(emp);
        }

        // Map to store groupId to Datapoint
        Map<String, Datapoint> groupDatapoints = new HashMap<>();

        // Function to recursively traverse groups and aggregate data
        // Using memoization to store already computed results
        Map<String, Datapoint> memo = new HashMap<>();

        for (Group group : groups) {
            computeGroupDatapoint(group.id, groupMap, groupToEmployees, trainingWindow, checkDay, memo);
        }

        // Collecting all Datapoints
        List<Datapoint> result = new ArrayList<>(memo.values());

        // Sorting the result for consistent output
        result.sort(Comparator.comparing(dp -> dp.groupId));

        return result;
    }

    /**
     * Helper method to compute Datapoint for a group recursively.
     *
     * @param groupId          - Current group ID
     * @param groupMap         - Map of groupId to Group
     * @param groupToEmployees - Map of groupId to list of Employees
     * @param trainingWindow   - Training window in days
     * @param checkDay         - Day to check the status
     * @param memo             - Memoization map to store computed Datapoints
     * @return Datapoint for the current group
     */
    private static Datapoint computeGroupDatapoint(String groupId, Map<String, Group> groupMap, Map<String, List<Employee>> groupToEmployees, Integer trainingWindow, Integer checkDay, Map<String, Datapoint> memo) {
        if (memo.containsKey(groupId)) {
            return memo.get(groupId);
        }

        Group group = groupMap.get(groupId);
        if (group == null) {
            // If the group does not exist, return zeroed Datapoint
            Datapoint dp = new Datapoint(groupId, 0, 0);
            memo.put(groupId, dp);
            return dp;
        }

        int totalOverdueDays = 0;
        int totalOverdueEmployees = 0;

        // Aggregate data from child groups first
        if (group.childIds != null) {
            for (String childId : group.childIds) {
                Datapoint childDp = computeGroupDatapoint(childId, groupMap, groupToEmployees, trainingWindow, checkDay, memo);
                totalOverdueDays += childDp.totalDaysOverdue;
                totalOverdueEmployees += childDp.numEmployees;
            }
        }

        // Aggregate data from employees directly in this group
        List<Employee> directEmployees = groupToEmployees.getOrDefault(groupId, new ArrayList<>());
        for (Employee emp : directEmployees) {
            Status status = getTrainingStatus(emp, trainingWindow, checkDay);
            if (status.name.equals("overdue")) {
                totalOverdueEmployees++;
                totalOverdueDays += status.daysOverdue;
            }
        }

        // Create Datapoint for the current group
        Datapoint currentGroupDp = new Datapoint(groupId, totalOverdueEmployees, totalOverdueDays);
        memo.put(groupId, currentGroupDp);
        return currentGroupDp;
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCaseEnhanced> testCases = new ArrayList<>();

        // Provided Example Test Case
        // Groups Definition:
        // - Group A: Parent with child Group B.
        // - Group B: Parent with child Group C.
        // - Group C: No children.
        Group groupA = new Group("a", null, new String[]{"b"});
        Group groupB = new Group("b", "a", new String[]{"c"});
        Group groupC = new Group("c", "b", new String[]{});

        List<Group> groups = Arrays.asList(groupA, groupB, groupC);

        // Employees Data:
        // - Employee 1: {start_day: 100, trained_day: null, group: "a"}
        // - Employee 2: {start_day: 105, trained_day: null, group: "b"}
        // - Employee 3: {start_day: 115, trained_day: null, group: "b"}
        // - Employee 4: {start_day: 105, trained_day: null, group: "c"}
        Employee emp1 = new Employee(100, null, "a");
        Employee emp2 = new Employee(105, null, "b");
        Employee emp3 = new Employee(115, null, "b");
        Employee emp4 = new Employee(105, null, "c");

        List<Employee> employees = Arrays.asList(emp1, emp2, emp3, emp4);

        // Training Window: 10 days
        Integer trainingWindow = 10;

        // Check Day: 120
        Integer checkDay = 120;

        // Expected Output:
        // [
        //     { groupId: "a", numEmployees: 3, totalDaysOverdue: 20 },
        //     { groupId: "b", numEmployees: 2, totalDaysOverdue: 10 },
        //     { groupId: "c", numEmployees: 1, totalDaysOverdue: 5 }
        // ]
        List<Datapoint> expectedOutput = Arrays.asList(
                new Datapoint("a", 3, 20),
                new Datapoint("b", 2, 10),
                new Datapoint("c", 1, 5)
        );

        testCases.add(new TestCaseEnhanced(
                "Provided Example",
                employees,
                groups,
                trainingWindow,
                checkDay,
                expectedOutput
        ));

        // Test Case 2: All Employees Completed Training
        // Employees Data:
        // - Employee 5: {start_day: 200, trained_day: 205, group: "a"}
        // - Employee 6: {start_day: 210, trained_day: 215, group: "b"}
        // - Employee 7: {start_day: 220, trained_day: 225, group: "c"}
        Employee emp5 = new Employee(200, 205, "a");
        Employee emp6 = new Employee(210, 215, "b");
        Employee emp7 = new Employee(220, 225, "c");
        List<Employee> employees2 = Arrays.asList(emp5, emp6, emp7);

        // Expected Output:
        // [
        //     { groupId: "a", numEmployees: 0, totalDaysOverdue: 0 },
        //     { groupId: "b", numEmployees: 0, totalDaysOverdue: 0 },
        //     { groupId: "c", numEmployees: 0, totalDaysOverdue: 0 }
        // ]
        List<Datapoint> expectedOutput2 = Arrays.asList(
                new Datapoint("a", 0, 0),
                new Datapoint("b", 0, 0),
                new Datapoint("c", 0, 0)
        );

        testCases.add(new TestCaseEnhanced(
                "All Employees Completed Training",
                employees2,
                groups,
                trainingWindow,
                230,
                expectedOutput2
        ));

        // Test Case 3: Mixed Statuses
        // Employees Data:
        // - Employee 8: {start_day: 300, trained_day: null, group: "a"} => Overdue by 20
        // - Employee 9: {start_day: 305, trained_day: 320, group: "b"} => Completed
        // - Employee 10: {start_day: 310, trained_day: null, group: "c"} => Overdue by 10
        Employee emp8 = new Employee(300, null, "a");
        Employee emp9 = new Employee(305, 320, "b");
        Employee emp10 = new Employee(310, null, "c");
        List<Employee> employees3 = Arrays.asList(emp8, emp9, emp10);

        // Check Day: 330
        Integer checkDay3 = 330;

        // Expected Output:
        // [
        //     { groupId: "a", numEmployees: 2, totalDaysOverdue: 30 },
        //     { groupId: "b", numEmployees: 1, totalDaysOverdue: 10 },
        //     { groupId: "c", numEmployees: 1, totalDaysOverdue: 10 }
        // ]
        List<Datapoint> expectedOutput3 = Arrays.asList(
                new Datapoint("a", 2, 30),
                new Datapoint("b", 1, 10),
                new Datapoint("c", 1, 10)
        );

        testCases.add(new TestCaseEnhanced(
                "Mixed Statuses",
                employees3,
                groups,
                trainingWindow,
                checkDay3,
                expectedOutput3
        ));

        // Test Case 4: Large Data Input
        // Creating 10000 employees distributed across groups a, b, c
        // Adjusted Check Day to 2100 for accurate overdue calculations
        List<Employee> largeEmployees = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            largeEmployees.add(new Employee(1000 + i, null, "a"));
        }
        for (int i = 0; i < 3000; i++) {
            largeEmployees.add(new Employee(2000 + i, 2050 + i, "b"));
        }
        for (int i = 0; i < 2000; i++) {
            largeEmployees.add(new Employee(3000 + i, null, "c"));
        }

        // Check Day: 2100
        Integer checkDay4 = 2100;

        // Expected Output:
        // Group A:
        // - Employees in "a": 5000, all trained_day=null
        //   - Overdue if checkDay > startDay + trainingWindow
        //   - Each employee's daysOverdue = 2100 - (1000 + i + 10) = 2090 - i
        //   - Only employees with 2090 - i > 0 => i < 2090
        //   - Number of overdue employees: 2090
        //   - TotalDaysOverdue: Sum from i=0 to 2089 of (2090 - i) = (2090 * 2091)/2 = 2185695

        // Group B:
        // - Employees in "b": 3000
        //   - trained_day = 2050 + i
        //   - Overdue if trained_day > 2100
        //   - trained_day > 2100 => 2050 + i > 2100 => i > 50
        //   - Number of overdue employees: 3000 - 51 = 2949
        //   - For each overdue employee, daysOverdue = 2100 - (2000 + i + 10) = 2090 - i
        //   - i ranges from 51 to 2999
        //   - Sum of daysOverdue = Sum from i=51 to 2999 of (2090 - i)
        //   - Sum = Sum from j=51 to 2999 of (2090 - j) = Sum from j=51 to 2999 of (2090 - j)
        //   - To simplify:
        //     - Let k = j - 51, j=51 to 2999 → k=0 to 2948
        //     - Sum = Sum from k=0 to 2948 of (2090 - (51 + k)) = Sum from k=0 to 2948 of (2039 - k)
        //     - Sum = 2949 * 2039 - Sum from k=0 to 2948 of k
        //     - Sum = 2949 * 2039 - (2948 * 2949) / 2
        //     - Sum = 2949 * (2039 - 1474) = 2949 * 565 = 1,669,785

        // Group C:
        // - Employees in "c": 2000, all trained_day=null
        //   - Overdue if checkDay > startDay + trainingWindow
        //   - Each employee's daysOverdue = 2100 - (3000 + i + 10) = 2090 - i
        //   - Since startDay >=3000, 2090 - i <0 for all i ≥0
        //   - No overdue employees
        //   - TotalDaysOverdue: 0

        List<Datapoint> expectedOutput4 = Arrays.asList(
                new Datapoint("a", 2090, 2185695),
                new Datapoint("b", 2949, 1669785),
                new Datapoint("c", 0, 0)
        );

        testCases.add(new TestCaseEnhanced(
                "Large Data Input",
                largeEmployees,
                groups,
                trainingWindow,
                checkDay4,
                expectedOutput4
        ));

        // Run all test cases
        int passCount = 0;
        for (TestCaseEnhanced testCase : testCases) {
            List<Datapoint> actual = getTotalDaysOverdueByGroups(testCase.employees, testCase.groups, testCase.trainingWindow, testCase.checkDay);
            boolean pass = actual.containsAll(testCase.expectedOutput) && testCase.expectedOutput.containsAll(actual);
            if (pass) {
                passCount++;
                System.out.println(testCase.name + ": PASS");
            } else {
                System.out.println(testCase.name + ": FAIL");
                System.out.println("  Expected:");
                for (Datapoint dp : testCase.expectedOutput) {
                    System.out.println("    " + dp);
                }
                System.out.println("  Actual:");
                for (Datapoint dp : actual) {
                    System.out.println("    " + dp);
                }
            }
        }

        // Summary
        System.out.println("\nTest Summary: " + passCount + "/" + testCases.size() + " passed.");
    }

    // Employee class with group association
    static class Employee {
        String groupId;      // Indicates the group to which the employee belongs
        Integer startDay;    // The day the employee started working at the company (in days)
        Integer trainedDay;  // (optional) The day the employee was trained (in days)

        public Employee(Integer startDay, Integer trainedDay, String groupId) {
            this.startDay = startDay;
            this.trainedDay = trainedDay;
            this.groupId = groupId;
        }
    }

    // Group class representing hierarchical groups
    static class Group {
        String id;           // Unique identifier for the group
        String parentId;     // ID of the parent group, if any
        String[] childIds;   // IDs of the child groups

        public Group(String id, String parentId, String[] childIds) {
            this.id = id;
            this.parentId = parentId;
            this.childIds = childIds;
        }
    }

    // Datapoint class representing the overdue summary for a group
    static class Datapoint {
        String groupId;
        Integer numEmployees;       // Number of overdue employees
        Integer totalDaysOverdue;   // Total days overdue

        public Datapoint(String groupId, Integer numEmployees, Integer totalDaysOverdue) {
            this.groupId = groupId;
            this.numEmployees = numEmployees;
            this.totalDaysOverdue = totalDaysOverdue;
        }

        @Override
        public String toString() {
            return "{ groupId: \"" + this.groupId + "\", numEmployees: " + this.numEmployees + ", totalDaysOverdue: " + this.totalDaysOverdue + " }";
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Datapoint)) {
                return false;
            }
            Datapoint other = (Datapoint) obj;
            return this.groupId.equals(other.groupId) &&
                    this.numEmployees.equals(other.numEmployees) &&
                    this.totalDaysOverdue.equals(other.totalDaysOverdue);
        }
    }

    // Status class reused from previous implementation
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

    // Helper class for enhanced test cases
    static class TestCaseEnhanced {
        String name;
        List<Employee> employees;
        List<Group> groups;
        Integer trainingWindow;
        Integer checkDay;
        List<Datapoint> expectedOutput;

        public TestCaseEnhanced(String name, List<Employee> employees, List<Group> groups, Integer trainingWindow, Integer checkDay, List<Datapoint> expectedOutput) {
            this.name = name;
            this.employees = employees;
            this.groups = groups;
            this.trainingWindow = trainingWindow;
            this.checkDay = checkDay;
            this.expectedOutput = expectedOutput;
        }
    }
}
