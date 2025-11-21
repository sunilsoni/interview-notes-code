package com.interview.notes.code.year.y2025.november.common.test2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    int id;
    double salary;

    Employee(int id, double salary) {
        this.id = id;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }
}

public class ThirdDistinctHighest {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, 50000),
                new Employee(2, 70000),
                new Employee(3, 60000),
                new Employee(4, 90000),
                new Employee(5, 80000),
                new Employee(6, 70000)
        );

        // Step 1: get all distinct salaries in descending order
        List<Double> distinctSalaries = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        if (distinctSalaries.size() < 3) {
            System.out.println("Less than 3 distinct salaries.");
            return;
        }

        double thirdSalary = distinctSalaries.get(2);

        // Step 2: find employees with that third highest salary
        List<Integer> employeeIds = employees.stream()
                .filter(e -> e.getSalary() == thirdSalary)
                .map(Employee::getId)
                .collect(Collectors.toList());

        System.out.println("Employee ID(s) with 3rd distinct highest salary: " + employeeIds);
    }
}
