package com.interview.notes.code.months.nov24.test10;

import java.util.*;

class Solution1 {
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
        int totalEmployees = 0;

        // Aggregate data from child groups first
        if (group.childIds != null) {
            for (String childId : group.childIds) {
                Datapoint childDp = computeGroupDatapoint(childId, groupMap, groupToEmployees, trainingWindow, checkDay, memo);
                totalOverdueDays += childDp.totalDaysOverdue;
                totalOverdueEmployees += childDp.numEmployees;
                totalEmployees += childDp.numEmployees;
            }
        }

        // Aggregate data from employees directly in this group
        List<Employee> directEmployees = groupToEmployees.getOrDefault(groupId, new ArrayList<>());
        for (Employee emp : directEmployees) {
            Status status = getTrainingStatus(emp, trainingWindow, checkDay);
            totalEmployees++;
            if (status.name.equals("overdue")) {
                totalOverdueEmployees++;
                totalOverdueDays += status.daysOverdue;
            }
        }

        // Add overdue data from this group
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
        //     { groupId: "a", numEmployees: 4, totalDaysOverdue: 20 },
        //     { groupId: "b", numEmployees: 3, totalDaysOverdue: 10 },
        //     { groupId: "c", numEmployees: 1, totalDaysOverdue: 5 }
        // ]
        List<Datapoint> expectedOutput = Arrays.asList(
                new Datapoint("a", 4, 20),
                new Datapoint("b", 3, 10),
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

        // Additional Test Cases

        // Test Case 2: Employees with completed training within window
        Employee emp5 = new Employee(200, 205, "a");
        Employee emp6 = new Employee(210, 215, "b");
        Employee emp7 = new Employee(220, 225, "c");
        List<Employee> employees2 = Arrays.asList(emp5, emp6, emp7);

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

        // Test Case 3: Mixed statuses with some overdue
        Employee emp8 = new Employee(300, null, "a");
        Employee emp9 = new Employee(305, 320, "b");
        Employee emp10 = new Employee(310, null, "c");
        List<Employee> employees3 = Arrays.asList(emp8, emp9, emp10);

        List<Datapoint> expectedOutput3 = Arrays.asList(
                new Datapoint("a", 2, 10),
                new Datapoint("b", 1, 5),
                new Datapoint("c", 1, 5)
        );

        testCases.add(new TestCaseEnhanced(
                "Mixed Statuses",
                employees3,
                groups,
                trainingWindow,
                330,
                expectedOutput3
        ));

        // Test Case 4: Large Data Input
        // Creating 10000 employees distributed across groups a, b, c
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

        // Expected Output:
        // Group A: 5000 overdue employees, totalDaysOverdue = (checkDay - windowEndDay) * 5000
        // Assuming trainingWindow = 10, checkDay = 4000
        // windowEndDay for Group A employees: 1000 + i + 10
        // Since trainedDay is null, daysOverdue for each employee = 4000 - (1000 + i + 10) = 2990 - i
        // TotalDaysOverdue = sum from i=0 to 4999 of (2990 - i)
        // Which is 5000*2990 - sum from i=0 to 4999 of i = 5000*2990 - (4999*5000)/2
        // = 14,950,000 - 12,497,500 = 2,452,500
        // Group B: 3000 employees with trainingDay = 2050 + i
        // Overdue for each = 4000 - (startDay + 10) if trainedDay > 4000 or null
        // But trainedDay = 2050 + i
        // If trainedDay <= 4000, then completed, else overdue
        // i ranges from 0 to 2999
        // trainedDay = 2050 + i <= 4000 when i <= 4000 - 2050 = 1950
        // So first 1951 employees have trainedDay <= 4000 -> completed
        // Remaining 1049 have trainedDay > 4000 -> overdue by (4000 - (startDay + 10))
        // startDay = 2000 + i
        // daysOverdue = 4000 - (2000 + i + 10) = 1990 - i
        // for i = 1951 to 2999, i from 1951 to 2999 (1049 employees)
        // totalDaysOverdue for Group B:
        // sum from i=1951 to 2999 of (1990 - i)
        // Let j = i - 1951, j from 0 to 1048
        // daysOverdue = 1990 - (1951 + j) = 39 - j
        // sum = sum from j=0 to 1048 of (39 - j) = 1049*39 - sum from j=0 to 1048 of j
        // = 40,711 - (1048*1049)/2 = 40,711 - 549,976 = -509,265 (Negative overdue doesn't make sense)
        // This indicates an error in calculation. Likely, no employees in Group B are overdue since trainedDay <= checkDay
        // Alternatively, adjust the checkDay to ensure some overdue
        // To simplify, let's set checkDay = 2100
        // Then, for Group A:
        // daysOverdue for each employee = 2100 - (1000 + i + 10) = 2090 - i
        // i from 0 to 4999
        // For i <= 2090, daysOverdue >=0
        // For i > 2090, daysOverdue <0 (no overdue)
        // So only first 2091 employees have overdue
        // totalDaysOverdue for Group A:
        // sum from i=0 to 2090 of (2090 - i) = (2091 * 2090) - (2090 * 2091)/2 = 2091*1045 = 2,185,695
        // Group B:
        // startDay = 2000 + i, trainedDay = 2050 + i
        // trainingWindowEndDay = 2000 + i + 10 = 2010 + i
        // If trainedDay <= checkDay (2100), completed
        // trainedDay = 2050 + i <= 2100 => i <= 50
        // So first 51 employees completed
        // Remaining 2949 employees are overdue by (2100 - (2000 + i + 10)) = 90 - i
        // For i from 51 to 2999, daysOverdue = 90 - i
        // Only when 90 - i > 0 => i < 90
        // But i starts at 51, so i < 90 => i from 51 to 89 (39 employees)
        // totalDaysOverdue for Group B:
        // sum from i=51 to 89 of (90 - i) = sum from j=1 to 39 of j = (39*40)/2 = 780
        // Group C:
        // startDay = 3000 + i, trainedDay = null
        // trainingWindowEndDay = 3000 + i + 10
        // daysOverdue = 2100 - (3000 + i + 10) = 2100 - 3010 - i = -910 - i <0, no overdue
        // totalOverdueDays for Group C = 0
        List<Employee> largeEmployees2 = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            largeEmployees2.add(new Employee(1000 + i, null, "a"));
        }
        for (int i = 0; i < 3000; i++) {
            largeEmployees2.add(new Employee(2000 + i, 2050 + i, "b"));
        }
        for (int i = 0; i < 2000; i++) {
            largeEmployees2.add(new Employee(3000 + i, null, "c"));
        }

        List<Datapoint> expectedOutput4 = Arrays.asList(
                new Datapoint("a", 2091, 2185695),
                new Datapoint("b", 39, 780),
                new Datapoint("c", 0, 0)
        );

        testCases.add(new TestCaseEnhanced(
                "Large Data Input",
                largeEmployees2,
                groups,
                trainingWindow,
                2100,
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
        Integer numEmployees;
        Integer totalDaysOverdue;

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
