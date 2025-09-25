package com.interview.notes.code.year.y2025.september.common.test8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

record Employee(String name, double salary, String location) {

    @Override
    public String toString() {
        return name + " (" + salary + ", " + location + ")";
    }
}

public class SecondHighestSalary {
    public static void main(String[] args) {
        // Create employee list
        List<Employee> employees = Arrays.asList(
                new Employee("John", 5000, "New York"),
                new Employee("Alice", 7000, "London"),
                new Employee("Bob", 9000, "Paris"),
                new Employee("Eve", 7000, "Berlin"),
                new Employee("Charlie", 12000, "Tokyo")
        );

        // Step 1: Find second highest salary
        Optional<Double> secondHighestSalary = employees.stream()
                .map(Employee::salary)      // extract salaries
                .distinct()                    // remove duplicates
                .sorted(Comparator.reverseOrder()) // sort descending
                .skip(1)                       // skip highest
                .findFirst();                  // get second highest

        if (secondHighestSalary.isPresent()) {
            double targetSalary = secondHighestSalary.get();

            // Step 2: Find employees with that salary
            List<Employee> result = employees.stream()
                    .filter(emp -> emp.salary() == targetSalary)
                    .collect(Collectors.toList());

            System.out.println("Second Highest Salary: " + targetSalary);
            System.out.println("Employees with Second Highest Salary: " + result);
        } else {
            System.out.println("No second highest salary found.");
        }
    }
}