package com.interview.notes.code.year.y2026.june.common.test4;

import java.util.Comparator;
import java.util.List;

record Employee(int id, String name, double salary) {}

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(1, "John", 100000),
                new Employee(2, "Mike", 100000),
                new Employee(3, "David", 90000),
                new Employee(4, "Sam", 80000)
        );

        double secondHighestSalary = employees.stream()
                .map(Employee::salary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow();

        employees.stream()
                .filter(e -> e.salary() == secondHighestSalary)
                .forEach(System.out::println);
    }
}