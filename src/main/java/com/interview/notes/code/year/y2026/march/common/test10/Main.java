package com.interview.notes.code.year.y2026.march.common.test10;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    double salary;
    String dept;

    Employee(String name, double salary, String dept) {
        this.name = name;
        this.salary = salary;
        this.dept = dept;
    }

    public double getSalary() { return salary; }
    public String getDept() { return dept; }

    public String toString() {
        return name + " - " + salary + " - " + dept;
    }
}

public class Main {
    public static void main(String[] args) {

        List<Employee> list = List.of(
            new Employee("A", 60000, "IT"),
            new Employee("B", 70000, "IT"),
            new Employee("C", 40000, "HR"),
            new Employee("D", 80000, "HR"),
            new Employee("E", 55000, "Sales"),
            new Employee("F", 30000, "Sales")
        );

        Map<String, Employee> result =
            list.stream()
                .filter(e -> e.getSalary() > 50000)
                .collect(Collectors.groupingBy(
                    Employee::getDept,
                    TreeMap::new, // sorted by dept
                    Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                        Optional::get
                    )
                ));

        result.forEach((k,v) -> System.out.println(k + " -> " + v));
    }
}