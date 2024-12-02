package com.interview.notes.code.year.y2024.july24.test16;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private String gender;
    private int age;

    public Employee(int id, String name, String department, String gender, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}

public class EmployeeNamesByDepartment {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", "IT", "Male", 30),
                new Employee(2, "Jane", "HR", "Female", 25),
                new Employee(3, "Bob", "IT", "Male", 35),
                new Employee(4, "Alice", "Finance", "Female", 28),
                new Employee(5, "Charlie", "HR", "Male", 40),
                new Employee(6, "Diana", "Finance", "Female", 32),
                new Employee(7, "Eve", "IT", "Female", 27)
        );

        Map<String, List<String>> namesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));

        System.out.println("Names by Department:");
        namesByDepartment.forEach((department, names) ->
                System.out.println(department + ": " + String.join(", ", names)));

        // Print distinct departments
        System.out.println("\nDistinct Departments:");
        employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .forEach(System.out::println);
    }
}
