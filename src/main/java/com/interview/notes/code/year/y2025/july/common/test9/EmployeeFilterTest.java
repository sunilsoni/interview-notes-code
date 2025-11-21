package com.interview.notes.code.year.y2025.july.common.test9;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeFilterTest {

    // Method that filters and sorts the list as required
    public static List<Employee> filterAndSort(List<Employee> employees) {
        return employees.stream()                                       // start a stream
                .filter(e -> e.getAge() > 30                               // keep only age > 30
                        && e.getSalary() > 10000)                        // and salary > 10000
                .sorted(Comparator.comparing(Employee::getName))           // sort by name
                .collect(Collectors.toList());                             // collect back to list
    }

    // Simple main method to run tests and large-data check
    public static void main(String[] args) {
        // --- Test case 1: Sample data from the prompt ---
        List<Employee> sample = Arrays.asList(
                new Employee("John", 35, 12000),
                new Employee("Alice", 28, 15000),
                new Employee("Buck", 40, 9500),
                new Employee("Catherine", 45, 20000),
                new Employee("Daniel", 32, 11000)
        );

        // Expected result after filtering+sorting:
        List<Employee> expected = Arrays.asList(
                new Employee("Catherine", 45, 20000),
                new Employee("Daniel", 32, 11000),
                new Employee("John", 35, 12000)
        );

        // Run the method under test
        List<Employee> result = filterAndSort(sample);

        // Compare result to expected and print PASS/FAIL
        if (result.equals(expected)) {
            System.out.println("Test 1 PASS");
        } else {
            System.out.println("Test 1 FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got     : " + result);
        }

        // --- Test case 2: Large random dataset ---
        int largeSize = 100_000;                            // number of random entries
        List<Employee> largeList = new ArrayList<>(largeSize);
        Random rnd = new Random();                          // random generator

        // Populate with random names, ages 20–60, salaries 5000–25000
        for (int i = 0; i < largeSize; i++) {
            String name = "Emp" + rnd.nextInt(100_000);     // random name
            int age = 20 + rnd.nextInt(41);                 // random age between 20 and 60
            double sal = 5000 + rnd.nextDouble() * 20000;   // random salary between 5k and 25k
            largeList.add(new Employee(name, age, sal));    // add to list
        }

        // Measure time to process largeList
        long start = System.currentTimeMillis();            // start timer
        List<Employee> filtered = filterAndSort(largeList); // run filter+sort
        long end = System.currentTimeMillis();              // end timer

        // Print summary of large-data test
        System.out.println("Large data test: input size = " + largeSize);
        System.out.println("Filtered size = " + filtered.size());
        System.out.println("Time taken = " + (end - start) + " ms");
    }

    // Define the Employee class with name, age, and salary
    static class Employee {
        private final String name;    // employee's name
        private final int age;        // employee's age
        private final double salary;  // employee's salary

        // Constructor to set all fields when creating an Employee
        public Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Getter for age
        public int getAge() {
            return age;
        }

        // Getter for salary
        public double getSalary() {
            return salary;
        }

        // Override equals so we can compare Employee objects in tests
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;                              // same reference?
            if (o == null || getClass() != o.getClass()) return false; // null or different class?
            Employee that = (Employee) o;                             // safe cast
            return age == that.age                                   // compare age
                    && Double.compare(that.salary, salary) == 0          // compare salary
                    && Objects.equals(name, that.name);                  // compare name
        }

        // Override hashCode whenever equals is overridden
        @Override
        public int hashCode() {
            return Objects.hash(name, age, salary);                  // generate hash
        }

        // Override toString for easy printing
        @Override
        public String toString() {
            return name + " (" + age + ", " + salary + ")";          // show name, age, salary
        }
    }
}