package com.interview.notes.code.year.y2024.nov24.test8;

import java.util.*;

/**
 * Enhanced Training Compliance System Implementation
 * <p>
 * <p>
 * ## **Enhanced Training Compliance System**
 * <p>
 * ### **Extended Functionality Overview**
 * The training compliance system now includes grouping employees into hierarchical groups. Each employee belongs to one specific group, which may have a parent group, and that parent group may have its own parent, forming a hierarchy. The training compliance needs to be evaluated not just for individual employees but also at the group level to summarize overdue statuses across all associated groups.
 * <p>
 * *Key enhancements include:**
 * - Employees belong to a specific group and may indirectly belong to higher-level parent groups.
 * - Groups can have child groups, and each group can inherit employees from its child groups.
 * - The total days overdue and the count of employees overdue are calculated for each group, considering all employees within that group and its sub-groups.
 * <p>
 * ---
 * <p>
 * ### **Class Structure and Provided Code**
 * Below is the updated structure with new classes and methods to handle grouping:
 * <p>
 * ```java
 * class Solution {
 * static class Employee {
 * String groupId;  // Indicates the group to which the employee belongs
 * Integer startDay; // The day the employee started working at the company (in days)
 * Integer trainedDay; // (optional) The day the employee was trained (in days)
 * <p>
 * public Employee(Integer startDay, Integer trainedDay, String groupId) {
 * this.startDay = startDay;
 * this.trainedDay = trainedDay;
 * this.groupId = groupId;
 * }
 * }
 * <p>
 * static class Group {
 * String id; // Unique identifier for the group
 * String parentId; // ID of the parent group, if any
 * String[] childIds; // IDs of the child groups
 * <p>
 * public Group(String id, String parentId, String[] childIds) {
 * this.id = id;
 * this.parentId = parentId;
 * this.childIds = childIds;
 * }
 * }
 * <p>
 * static class Datapoint {
 * String groupId;
 * Integer numEmployees;
 * Integer totalDaysOverdue;
 * <p>
 * public Datapoint(String groupId, Integer numEmployees, Integer totalDaysOverdue) {
 * this.groupId = groupId;
 * this.numEmployees = numEmployees;
 * this.totalDaysOverdue = totalDaysOverdue;
 * }
 * <p>
 * public String toString() {
 * return this.groupId + "," + this.numEmployees + "," + this.totalDaysOverdue;
 * }
 * }
 * }
 * ```
 * <p>
 * ---
 * <p>
 * ### **Functionality Explanation**
 * - The new method `getTotalDaysOverdueByGroups` calculates the total overdue days for each group by considering both direct and indirect (through sub-groups) memberships.
 * - The output summarizes the overdue training status for each group, including the count of employees and total overdue days.
 * <p>
 * ---
 * <p>
 * ## **Examples with Detailed Diagrams**
 * <p>
 * ### **Example Setup**
 * <p>
 * *Groups Definition:**
 * - Group A: Parent with child Group B.
 * - Group B: Parent with child Group C.
 * - Group C: No children.
 * <p>
 * *Employees Data:**
 * - Employee 1: `{start_day: 100, trained_day: null, group: "a"}`
 * - Employee 2: `{start_day: 105, trained_day: null, group: "b"}`
 * - Employee 3: `{start_day: 115, trained_day: null, group: "b"}`
 * - Employee 4: `{start_day: 105, trained_day: null, group: "c"}`
 * <p>
 * *Training Window**: 10 days
 * *Check Day**: 120
 * <p>
 * *Expected Output**:
 * ```
 * [
 * { groupId: "a", numEmployees: 4, totalDaysOverdue: 20 },
 * { groupId: "b", numEmployees: 3, totalDaysOverdue: 10 },
 * { groupId: "c", numEmployees: 1, totalDaysOverdue: 5 }
 * ]
 * ```
 * <p>
 * *Explanation**:
 * - Group A includes all employees directly and through its sub-groups (B and C). Total overdue days = 20.
 * - Group B includes employees directly in B and in its child group C. Total overdue days = 10.
 * - Group C includes only the employee directly assigned to it. Total overdue days = 5.
 * <p>
 * ---
 * <p>
 * ### **Questions Based on the Examples**
 * <p>
 * #### **Understanding the Core Functionality**
 * 1. What is the purpose of extending the training compliance system with groups?
 * - A) To track individual employee training.
 * - B) To summarize overdue training at an organizational level.
 * - C) To assign multiple groups to a single employee.
 * - D) To track training status by departments only.
 * <p>
 * 2. If an employee belongs to Group B and Group B is a child of Group A, which groups will include this employee in their overdue calculations?
 * - A) Only Group B
 * - B) Only Group A
 * - C) Both Group A and Group B
 * - D) Group B and its siblings
 * <p>
 * 3. In the provided example, what is the total overdue days for Group A if the training window is 10 days and the check day is 120?
 * - A) 5
 * - B) 10
 * - C) 20
 * - D) 15
 * <p>
 * ---
 * <p>
 * #### **Scenario-Based Questions**
 * 4. If you add a new employee with `{start_day: 110, trained_day: null, group: "b"}`, what will be the new `totalDaysOverdue` for Group B?
 * - A) 15
 * - B) 10
 * - C) 5
 * - D) 20
 * <p>
 * 5. If the training window was extended to 15 days instead of 10, how would the output for Group C change?
 * - A) It would remain the same.
 * - B) Total overdue days would decrease.
 * - C) Total overdue days would increase.
 * - D) The number of employees would increase.
 * <p>
 * 6. How does the system handle employees who belong to multiple nested groups?
 * - A) They are counted in each group they belong to.
 * - B) They are counted only in their primary group.
 * - C) They are excluded from parent groups.
 * - D) They are counted only in child groups.
 * <p>
 * ---
 * <p>
 * #### **Code Comprehension**
 * 7. In the `Group` class, what is the purpose of the `parentId` and `childIds` fields?
 * - A) To store the list of employees.
 * - B) To establish hierarchical relationships between groups.
 * - C) To calculate overdue days directly.
 * - D) To assign training windows.
 * <p>
 * 8. What would happen if the `getTotalDaysOverdueByGroups` method was called with a `checkDay` earlier than all employees' `startDays`?
 * - A) All groups will show zero overdue days.
 * - B) All groups will have maximum overdue days.
 * - C) It will throw an error.
 * - D) The output will be unpredictable.
 * <p>
 * ---
 * <p>
 * ### **Output Interpretation**
 * 9. Given the expected output:
 * ```
 * [
 * { groupId: "a", numEmployees: 4, totalDaysOverdue: 20 },
 * { groupId: "b", numEmployees: 3, totalDaysOverdue: 10 },
 * { groupId: "c", numEmployees: 1, totalDaysOverdue: 5 }
 * ]
 * ```
 * What is the reason Group A has more overdue days than Group B?
 * - A) Group A includes employees from all sub-groups.
 * - B) Group B employees were trained late.
 * - C) Group C had no employees assigned.
 * - D) Group A has a longer training window.
 * <p>
 * ---
 */
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
     * This function returns the total days overdue for training completion for each group, considering both direct
     * and indirect (i.e., through sub-groups) employee memberships.
     *
     * @param employees      - An array of Employee objects.
     * @param groupsById     - A Map mapping a group id to the corresponding Group.
     * @param trainingWindow - An integer representing the duration (number of days) employees have to complete their training after their start day.
     * @param checkDay       - An integer representing the day for which we are checking the training statuses.
     * @return A list of Datapoints, one for each group.
     */
    public static List<Datapoint> getTotalDaysOverdueByGroups(Employee[] employees,
                                                              Map<String, Group> groupsById,
                                                              Integer trainingWindow,
                                                              Integer checkDay) {
        // Map to store groupId to list of employees directly belonging to that group
        Map<String, List<Employee>> groupToEmployees = new HashMap<>();
        for (Employee emp : employees) {
            groupToEmployees.computeIfAbsent(emp.groupId, k -> new ArrayList<>()).add(emp);
        }

        // Map to store groupId to Datapoint
        Map<String, Datapoint> memo = new HashMap<>();

        // Function to recursively traverse groups and aggregate data
        for (String groupId : groupsById.keySet()) {
            computeGroupDatapoint(groupId, groupsById, groupToEmployees, trainingWindow, checkDay, memo);
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
     * @param groupsById       - Map of groupId to Group
     * @param groupToEmployees - Map of groupId to list of Employees
     * @param trainingWindow   - Training window in days
     * @param checkDay         - Day to check the status
     * @param memo             - Memoization map to store computed Datapoints
     * @return Datapoint for the current group
     */
    private static Datapoint computeGroupDatapoint(String groupId, Map<String, Group> groupsById,
                                                   Map<String, List<Employee>> groupToEmployees,
                                                   Integer trainingWindow, Integer checkDay,
                                                   Map<String, Datapoint> memo) {
        if (memo.containsKey(groupId)) {
            return memo.get(groupId);
        }

        Group group = groupsById.get(groupId);
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
                Datapoint childDp = computeGroupDatapoint(childId, groupsById, groupToEmployees, trainingWindow, checkDay, memo);
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

        // Groups Definition:
        // - Group A: Parent with child Group B.
        // - Group B: Parent with child Group C.
        // - Group C: No children.
        Group groupA = new Group("a", null, new String[]{"b"});
        Group groupB = new Group("b", "a", new String[]{"c"});
        Group groupC = new Group("c", "b", new String[]{});

        Map<String, Group> groupsById = new HashMap<>();
        groupsById.put(groupA.id, groupA);
        groupsById.put(groupB.id, groupB);
        groupsById.put(groupC.id, groupC);

        // Test Case 1: Provided Example
        // Employees Data:
        // - Employee 1: {start_day: 100, trained_day: null, group: "a"}
        // - Employee 2: {start_day: 105, trained_day: null, group: "b"}
        // - Employee 3: {start_day: 115, trained_day: null, group: "b"}
        // - Employee 4: {start_day: 105, trained_day: null, group: "c"}
        Employee emp1 = new Employee(100, null, "a");
        Employee emp2 = new Employee(105, null, "b");
        Employee emp3 = new Employee(115, null, "b");
        Employee emp4 = new Employee(105, null, "c");

        Employee[] employees1 = new Employee[]{emp1, emp2, emp3, emp4};

        // Training Window: 10 days
        Integer trainingWindow = 10;

        // Check Day: 120
        Integer checkDay1 = 120;

        // Expected Output:
        // [
        //     { groupId: "a", numEmployees: 3, totalDaysOverdue: 20 },
        //     { groupId: "b", numEmployees: 2, totalDaysOverdue: 10 },
        //     { groupId: "c", numEmployees: 1, totalDaysOverdue: 5 }
        // ]
        List<Datapoint> expectedOutput1 = Arrays.asList(
                new Datapoint("a", 3, 20),
                new Datapoint("b", 2, 10),
                new Datapoint("c", 1, 5)
        );

        testCases.add(new TestCaseEnhanced(
                "Provided Example",
                employees1,
                groupsById,
                trainingWindow,
                checkDay1,
                expectedOutput1
        ));

        // Test Case 2: All Employees Completed Training
        // Employees Data:
        // - Employee 5: {start_day: 200, trained_day: 205, group: "a"}
        // - Employee 6: {start_day: 210, trained_day: 215, group: "b"}
        // - Employee 7: {start_day: 220, trained_day: 225, group: "c"}
        Employee emp5 = new Employee(200, 205, "a");
        Employee emp6 = new Employee(210, 215, "b");
        Employee emp7 = new Employee(220, 225, "c");

        Employee[] employees2 = new Employee[]{emp5, emp6, emp7};

        // Check Day: 230
        Integer checkDay2 = 230;

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
                groupsById,
                trainingWindow,
                checkDay2,
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

        Employee[] employees3 = new Employee[]{emp8, emp9, emp10};

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
                groupsById,
                trainingWindow,
                checkDay3,
                expectedOutput3
        ));

        // Test Case 4: Large Data Input
        // Creating 10000 employees distributed across groups a, b, c
        // Adjusted Check Day to 2100 for accurate overdue calculations
        List<Employee> largeEmployeesList = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            largeEmployeesList.add(new Employee(1000 + i, null, "a"));
        }
        for (int i = 0; i < 3000; i++) {
            largeEmployeesList.add(new Employee(2000 + i, 2050 + i, "b"));
        }
        for (int i = 0; i < 2000; i++) {
            largeEmployeesList.add(new Employee(3000 + i, null, "c"));
        }
        Employee[] largeEmployees = largeEmployeesList.toArray(new Employee[0]);

        // Check Day: 2100
        Integer checkDay4 = 2100;

        // Calculations:
        // Group A:
        // - Employees in "a": 5000, all trained_day=null
        //   - Overdue if checkDay > startDay + trainingWindow
        //   - Each employee's daysOverdue = 2100 - (1000 + i + 10) = 2090 - i
        //   - Overdue if i <1090 (i=0 to1089)
        //   - Number of overdue employees:1090
        //   - TotalDaysOverdue: Sum fromi=0 to1089 of (2090 -i) = Sum fromk=1001 to2090 ofk = (2090*2091)/2 - (1000*1001)/2 =2185695 -500500=1685195

        // Group B:
        // - Employees in "b": 3000
        //   - trained_day =2050 +i
        //   - Overdue if trained_day >2100 and daysOverdue >0
        //   - trained_day >2100 =>i >50
        //   - daysOverdue =2100 - (2000 +i +10) =2090 -i >0 =>i <2090
        //   - Thus, i=51 to2089 =>2040 employees
        //   - TotalDaysOverdue: Sum fromi=51 to2089 of (2090 -i) = Sum fromk=51 to2089 of (2090 -i) = Sum fromk=1 to2040 ofk = (2040*2041)/2=2081580

        // Group C:
        // - Employees in "c":2000, all trained_day=null
        //   - Overdue if checkDay > startDay + trainingWindow
        //   - Each employee's daysOverdue =2100 - (3000 +i +10)=2090 -i <0 => no overdue
        //   - Number of overdue employees:0
        //   - TotalDaysOverdue:0

        // Expected Output:
        // [
        //     { groupId: "a", numEmployees: 1090, totalDaysOverdue: 1685195 },
        //     { groupId: "b", numEmployees: 2040, totalDaysOverdue: 2081580 },
        //     { groupId: "c", numEmployees: 0, totalDaysOverdue: 0 }
        // ]
        // However, since group 'a' includes group 'b', total for 'a' should be 1090 +2040=3130, totalDaysOverdue=1685195 +2081580=3766775

        // But according to the problem statement, each group summarizes its own and its sub-groups' overdue data.
        // Therefore, group 'a' should have 3130 overdue employees, 3766775 totalDaysOverdue

        List<Datapoint> expectedOutput4 = Arrays.asList(
                new Datapoint("a", 3130, 3766775),
                new Datapoint("b", 2040, 2081580),
                new Datapoint("c", 0, 0)
        );

        testCases.add(new TestCaseEnhanced(
                "Large Data Input",
                largeEmployees,
                groupsById,
                trainingWindow,
                checkDay4,
                expectedOutput4
        ));

        // Run all test cases
        int passCount = 0;
        for (TestCaseEnhanced testCase : testCases) {
            List<Datapoint> actual = getTotalDaysOverdueByGroups(testCase.employees, testCase.groupsById, testCase.trainingWindow, testCase.checkDay);
            boolean pass = compareDatapoints(actual, testCase.expectedOutput);
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

    /**
     * Helper method to compare two lists of Datapoints irrespective of their order.
     *
     * @param actual   - The actual list of Datapoints
     * @param expected - The expected list of Datapoints
     * @return true if both lists contain the same Datapoints, false otherwise
     */
    private static boolean compareDatapoints(List<Datapoint> actual, List<Datapoint> expected) {
        if (actual.size() != expected.size()) {
            return false;
        }

        // Create maps for easy comparison
        Map<String, Datapoint> actualMap = new HashMap<>();
        for (Datapoint dp : actual) {
            actualMap.put(dp.groupId, dp);
        }

        for (Datapoint dp : expected) {
            Datapoint actualDp = actualMap.get(dp.groupId);
            if (actualDp == null || !actualDp.equals(dp)) {
                return false;
            }
        }

        return true;
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
        Employee[] employees;
        Map<String, Group> groupsById;
        Integer trainingWindow;
        Integer checkDay;
        List<Datapoint> expectedOutput;

        public TestCaseEnhanced(String name, Employee[] employees, Map<String, Group> groupsById,
                                Integer trainingWindow, Integer checkDay, List<Datapoint> expectedOutput) {
            this.name = name;
            this.employees = employees;
            this.groupsById = groupsById;
            this.trainingWindow = trainingWindow;
            this.checkDay = checkDay;
            this.expectedOutput = expectedOutput;
        }
    }
}
