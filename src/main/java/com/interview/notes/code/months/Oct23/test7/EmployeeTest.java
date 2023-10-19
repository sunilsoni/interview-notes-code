package com.interview.notes.code.months.Oct23.test7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private int age;

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    // Override equals() and hashCode() to compare employees based on their ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

public class EmployeeTest {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John", 30));
        employees.add(new Employee(2, "Alice", 25));
        employees.add(new Employee(1, "John", 30)); // Duplicate
        employees.add(new Employee(3, "Bob", 28));
        employees.add(new Employee(2, "Alice", 25)); // Duplicate

        // Remove duplicates using Java 8 stream
        List<Employee> uniqueEmployees = employees.stream()
                .distinct()
                .collect(Collectors.toList());

        // Display the unique employee list
        uniqueEmployees.forEach(employee -> System.out.println(employee.getId() + ": " + employee.getName()));
    }
}
