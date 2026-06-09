package com.interview.notes.code.year.y2026.june.common.test3;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(1, "John", 50000),
                new Employee(2, "David", 90000),
                new Employee(3, "Mike", 70000),
                new Employee(4, "Sam", 80000)
        );

        Employee secondHighest = employees.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);

        System.out.println("Second Highest Employee:");
        System.out.println(secondHighest);
    }

    record Employee(int id, String name, double salary) {

        @Override
            public String toString() {
                return "Employee{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", salary=" + salary +
                        '}';
            }
        }
}