package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("A", 50000),
                new Employee("B", 70000),
                new Employee("C", 60000),
                new Employee("D", 70000)
        );

        Optional<Double> secondHighest =
                employees.stream()
                        .map(Employee::getSalary)
                        .distinct()
                        .sorted(Comparator.reverseOrder())
                        .skip(1)
                        .findFirst();

        if (secondHighest.isPresent()) {
            System.out.println("Second Highest Salary: " + secondHighest.get());
        } else {
            System.out.println("Not enough data");
        }
    }
}