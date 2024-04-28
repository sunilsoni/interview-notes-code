package com.interview.notes.code.months.april24.test13;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(50000));
        employees.add(new Employee(60000));
        employees.add(new Employee(70000));
        employees.add(new Employee(80000));
        employees.add(new Employee(90000));

        Optional<Employee> thirdHighestSalary = employees.stream()
            .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary())) // Sort in descending order
            .skip(2)  // Skip the two highest salaries
            .findFirst();  // Get the third one

        thirdHighestSalary.ifPresent(e -> System.out.println("Third highest salary: " + e.getSalary()));
    }

    static class Employee {
        private double salary;

        public Employee(double salary) {
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }
    }
}
