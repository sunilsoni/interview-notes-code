package com.interview.notes.code.months.sept24.test10;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String firstName;
    String lastName;

    Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

public class EmployeeMapper {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Laura", "Gibbon"),
            new Employee("Aaron", "Todd"),
            new Employee("Brian", "Scott"),
            new Employee("Neon", "Piper"),
            new Employee("David", "Beckham"),
            new Employee("Aaron", "Beckham"),
            new Employee("Brian", "Todd")
        );

        Map<String, List<Employee>> employeesByFirstName = groupEmployeesByFirstName(employees);
        Map<String, List<Employee>> employeesByLastName = groupEmployeesByLastName(employees);

        System.out.println("Grouped by First Name:");
        employeesByFirstName.forEach((name, empList) -> {
            System.out.println(name + ": " + empList);
        });

        System.out.println("\nGrouped by Last Name:");
        employeesByLastName.forEach((name, empList) -> {
            System.out.println(name + ": " + empList);
        });
    }

    public static Map<String, List<Employee>> groupEmployeesByFirstName(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getFirstName));
    }

    public static Map<String, List<Employee>> groupEmployeesByLastName(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getLastName));
    }
}
