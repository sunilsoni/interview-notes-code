package com.interview.notes.code.months.nov23.MorganStanley;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private String department;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "HR"),
                new Employee("Jane", "HR"),
                new Employee("Bob", "IT"),
                new Employee("Alice", "IT"),
                new Employee("Eva", "Finance")
        );

        Map<String, Long> departmentCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        System.out.println(departmentCount);
    }

    public String getDepartment() {
        return department;
    }
}
