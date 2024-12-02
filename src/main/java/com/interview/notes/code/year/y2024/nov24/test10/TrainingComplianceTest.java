package com.interview.notes.code.year.y2024.nov24.test10;

/*


## **Training Compliance System**

### **Overview**
The training compliance system checks if an employee has completed their mandatory training within a specified training window. The status of an employee’s training can be one of the following:
- **Pending**: Employee is within the training window but has not yet completed training.
- **Completed**: Employee has completed training within the training window.
- **Overdue**: Employee has missed completing training within the training window.
- **Not Required**: The check day is before the employee’s start day.

Each employee is represented with:
- `start_day`: The day the employee started working.
- `trained_day`: The day the employee completed their training (if applicable).
- `trainingWindow`: The number of days allowed to complete training from the start day.
- `check_day`: The day we are checking the employee’s training status.

---

## **Examples with Diagrams and Explanations**

### **Example 1**: Employee is still within the training window
- **Training Window**: 10 days
- **Employee**: `{start_day: 100}`
- **Check Day**: 104
- **Expected Result**: `{name: "pending", days_overdue: 0}`

**Explanation**: The employee started on day 100 and has a training window of 10 days (until day 110). Since we are checking on day 104 and the employee has not completed their training yet, the status is "pending".

```
|<------------------training window------------------->|
Time:  |-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
Days:  99    100   101   102   103   104   105   106   107   108   109   110
                  |                                        |
             start_day                                check_day
```

---

### **Example 2**: Employee completed training within the window
- **Training Window**: 10 days
- **Employee**: `{start_day: 100, trained_day: 105}`
- **Check Day**: 110
- **Expected Result**: `{name: "completed", days_overdue: 0}`

**Explanation**: The employee started on day 100 and completed training on day 105, which is within the 10-day training window. Thus, on check day 110, the status is "completed".

```
|<------------------training window------------------->|
Time:  |-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
Days:  99    100   101   102   103   104   105   106   107   108   109   110
                  |                       |                                  |
             start_day             trained_day                        check_day
```

---

### **Example 3**: Employee trained late but still considered compliant on check day
- **Training Window**: 5 days
- **Employee**: `{start_day: 100, trained_day: 107}`
- **Check Day**: 110
- **Expected Result**: `{name: "completed", days_overdue: 0}`

**Explanation**: Even though the employee trained on day 107, which is outside the 5-day training window, the check day of 110 still results in a "completed" status since we only care about whether the training was completed by the check day.

```
|<----------training window-------->|
Time:  |-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
Days:  99    100   101   102   103   104   105   106   107   108   109   110
                  |                       |                                  |
             start_day             trained_day                        check_day
```

---

### **Example 4**: Employee overdue for training
- **Training Window**: 5 days
- **Employee**: `{start_day: 100, trained_day: 109}`
- **Check Day**: 106
- **Expected Result**: `{name: "overdue", days_overdue: 1}`

**Explanation**: The employee has not completed their training within the 5-day window (ending on day 105). On check day 106, since the employee has not yet completed training, they are overdue by 1 day.

```
|<----------training window-------->|
Time:  |-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
Days:  99    100   101   102   103   104   105   106   107   108   109   110
                  |                             |                                  |
             start_day                  check_day                         trained_day
```

---

### **Example 5**: Check day is before the employee’s start day
- **Training Window**: 10 days
- **Employee**: `{start_day: 100}`
- **Check Day**: 95
- **Expected Result**: `{name: "not_required", days_overdue: 0}`

**Explanation**: Since the check day (95) is before the employee's start day (100), the training is "not required".

```
Time:  |-----|-----|-----|-----|-----|-----|-----|-----|-----|
Days:  90    91    92    93    94    95    96    97    98    99    100
                                    |                        |
                               check_day                start_day
```

---

## **Java Code Provided**

Below is the given Java code used to determine an employee’s training status:

```java
class Solution {
    static class Employee {
        Integer startDay;  // The day the employee started working at the company (in days)
        Integer trainedDay; // (optional) The day the employee was trained (in days)

        public Employee(Integer startDay, Integer trainedDay) {
            this.startDay = startDay;
            this.trainedDay = trainedDay;
        }
    }

    static class Status {
        String name; // one of {"not_required", "pending", "overdue", "completed"}
        Integer daysOverdue; // 0 if not overdue

        public Status(String name, Integer daysOverdue) {
            this.name = name;
            this.daysOverdue = daysOverdue;
        }

        public String toString() {
            return this.name + "," + this.daysOverdue;
        }
    }

    /**
     * Evaluates an employee's training status on a specified checkDay.
     * @param employee - The employee's details
     * @param trainingWindow - The number of days an employee has to complete their training after their start day.
     * @param checkDay - The day for which we are checking the employee's training status.
     * @return Status object with the training status

public static Status getTrainingStatus(Employee employee, Integer trainingWindow, Integer checkDay) {
    return new Status("not_required", 0);
}

public static void main(String[] args) {
    System.out.println(getTrainingStatus(new Employee(0, null), 0, 0));
}
}
        ```
 */
class TrainingComplianceTest {
    // Method to get the training status of an employee
    public static Status getTrainingStatus(Employee employee, Integer trainingWindow, Integer checkDay) {
        // Check if training is not required yet (check day is before the start day)
        if (checkDay < employee.startDay) {
            return new Status("not_required", 0); // Training not required
        }

        // Calculate the training deadline
        int deadline = employee.startDay + trainingWindow;

        // If the employee hasn't been trained yet
        if (employee.trainedDay == null) {
            if (checkDay <= deadline) {
                return new Status("pending", 0); // Training is still pending within the window
            } else {
                return new Status("overdue", checkDay - deadline); // Training is overdue
            }
        }

        // If the employee has been trained
        if (checkDay >= employee.trainedDay) {
            return new Status("completed", 0); // Training has been completed
        }

        // If the check day is before the training day but after the deadline
        if (checkDay > deadline) {
            return new Status("overdue", checkDay - deadline); // Training is overdue
        }

        // If the check day is before the training day but within the training window
        return new Status("pending", 0); // Training is still pending
    }

    // Method to run a test case and compare the expected and actual results
    public static void runTest(String testName, Status expected, Status actual) {
        boolean passed = expected.equals(actual); // Check if the test passed
        System.out.printf("Test %s: %s\n", testName, passed ? "PASS" : "FAIL"); // Print the result
        if (!passed) {
            System.out.printf("Expected: %s, Got: %s\n", expected, actual); // Print expected and actual if test fails
        }
    }

    public static void main(String[] args) {
        // Basic test cases
        runTest("Pending within window",
                new Status("pending", 0),
                getTrainingStatus(new Employee(100, null), 10, 104)); // Training is still pending within the window

        runTest("Completed within window",
                new Status("completed", 0),
                getTrainingStatus(new Employee(100, 105), 10, 110)); // Training completed within the window

        runTest("Trained late but compliant",
                new Status("completed", 0),
                getTrainingStatus(new Employee(100, 107), 5, 110)); // Training was late but eventually completed

        runTest("Overdue training",
                new Status("overdue", 1),
                getTrainingStatus(new Employee(100, 109), 5, 106)); // Training is overdue by 1 day

        runTest("Not required yet",
                new Status("not_required", 0),
                getTrainingStatus(new Employee(100, null), 10, 95)); // Training not required yet (check day before start day)

        // Edge cases
        runTest("Same day start and check",
                new Status("pending", 0),
                getTrainingStatus(new Employee(100, null), 5, 100)); // Check day is the same as start day, training pending

        runTest("Zero training window",
                new Status("overdue", 1),
                getTrainingStatus(new Employee(100, null), 0, 101)); // Zero training window, check day is overdue

        // Large data inputs
        runTest("Large days",
                new Status("completed", 0),
                getTrainingStatus(new Employee(1000000, 1000005), 10, 1000010)); // Test with large day values

        runTest("Very large training window",
                new Status("pending", 0),
                getTrainingStatus(new Employee(100, null), 1000000, 105)); // Very large training window, still pending

        // Boundary cases
        runTest("Training on deadline day",
                new Status("completed", 0),
                getTrainingStatus(new Employee(100, 105), 5, 110)); // Training completed on deadline day

        runTest("Check on deadline day",
                new Status("pending", 0),
                getTrainingStatus(new Employee(100, null), 5, 105)); // Check day is the deadline day, training pending
    }

    // Inner class to represent an Employee
    static class Employee {
        Integer startDay; // The day the employee starts
        Integer trainedDay; // The day the employee was trained, or null if not yet trained

        // Constructor to initialize an Employee with start day and trained day
        public Employee(Integer startDay, Integer trainedDay) {
            this.startDay = startDay;
            this.trainedDay = trainedDay;
        }
    }

    // Inner class to represent the training Status of an employee
    static class Status {
        String name; // Status name (e.g., "pending", "completed", etc.)
        Integer daysOverdue; // Number of days overdue, if applicable

        // Constructor to initialize a Status with name and days overdue
        public Status(String name, Integer daysOverdue) {
            this.name = name;
            this.daysOverdue = daysOverdue;
        }

        // toString method to return a string representation of the status
        public String toString() {
            return this.name + "," + this.daysOverdue;
        }

        // Override equals method to compare Status objects
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Status)) return false;
            Status other = (Status) obj;
            return this.name.equals(other.name) &&
                    this.daysOverdue.equals(other.daysOverdue);
        }
    }
}
