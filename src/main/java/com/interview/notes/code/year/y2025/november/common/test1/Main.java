package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Employee {
    int id;
    double salary;

    public Employee(int id, double salary) {
        this.id = id;
        this.salary = salary;
    }

    public int getId() { return id; }
    public double getSalary() { return salary; }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(101, 50000),
            new Employee(102, 75000),
            new Employee(103, 60000),
            new Employee(104, 90000),
            new Employee(105, 85000)
        );

        Optional<Integer> thirdHighestId = employees.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
            .skip(2)
            .map(Employee::getId)
            .findFirst();

        thirdHighestId.ifPresent(id -> System.out.println("Third highest salary belongs to Employee ID: " + id));
    }
}
