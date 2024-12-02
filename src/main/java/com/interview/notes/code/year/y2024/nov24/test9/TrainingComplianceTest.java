package com.interview.notes.code.year.y2024.nov24.test9;

class TrainingComplianceTest {
    public static Status getTrainingStatus(Employee employee, Integer trainingWindow, Integer checkDay) {
        // Check if not required (check day before start day)
        if (checkDay < employee.startDay) {
            return new Status("not_required", 0);
        }

        // Calculate training deadline
        int deadline = employee.startDay + trainingWindow;

        // If employee hasn't been trained yet
        if (employee.trainedDay == null) {
            if (checkDay <= deadline) {
                return new Status("pending", 0);
            } else {
                return new Status("overdue", checkDay - deadline);
            }
        }

        // If employee has been trained
        if (checkDay >= employee.trainedDay) {
            return new Status("completed", 0);
        }

        // If check day is before training day but after deadline
        if (checkDay > deadline) {
            return new Status("overdue", checkDay - deadline);
        }

        // If check day is before training day but within window
        return new Status("pending", 0);
    }

    public static void runTest(String testName, Status expected, Status actual) {
        boolean passed = expected.equals(actual);
        System.out.printf("Test %s: %s\n", testName, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: %s, Got: %s\n", expected, actual);
        }
    }

    public static void main(String[] args) {
        // Basic test cases
        runTest("Pending within window",
                new Status("pending", 0),
                getTrainingStatus(new Employee(100, null), 10, 104));

        runTest("Completed within window",
                new Status("completed", 0),
                getTrainingStatus(new Employee(100, 105), 10, 110));

        runTest("Trained late but compliant",
                new Status("completed", 0),
                getTrainingStatus(new Employee(100, 107), 5, 110));

        runTest("Overdue training",
                new Status("overdue", 1),
                getTrainingStatus(new Employee(100, 109), 5, 106));

        runTest("Not required yet",
                new Status("not_required", 0),
                getTrainingStatus(new Employee(100, null), 10, 95));

        // Edge cases
        runTest("Same day start and check",
                new Status("pending", 0),
                getTrainingStatus(new Employee(100, null), 5, 100));

        runTest("Zero training window",
                new Status("overdue", 1),
                getTrainingStatus(new Employee(100, null), 0, 101));

        // Large data inputs
        runTest("Large days",
                new Status("completed", 0),
                getTrainingStatus(new Employee(1000000, 1000005), 10, 1000010));

        runTest("Very large training window",
                new Status("pending", 0),
                getTrainingStatus(new Employee(100, null), 1000000, 105));

        // Boundary cases
        runTest("Training on deadline day",
                new Status("completed", 0),
                getTrainingStatus(new Employee(100, 105), 5, 110));

        runTest("Check on deadline day",
                new Status("pending", 0),
                getTrainingStatus(new Employee(100, null), 5, 105));
    }

    static class Employee {
        Integer startDay;
        Integer trainedDay;

        public Employee(Integer startDay, Integer trainedDay) {
            this.startDay = startDay;
            this.trainedDay = trainedDay;
        }
    }

    static class Status {
        String name;
        Integer daysOverdue;

        public Status(String name, Integer daysOverdue) {
            this.name = name;
            this.daysOverdue = daysOverdue;
        }

        public String toString() {
            return this.name + "," + this.daysOverdue;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Status)) return false;
            Status other = (Status) obj;
            return this.name.equals(other.name) &&
                    this.daysOverdue.equals(other.daysOverdue);
        }
    }
}