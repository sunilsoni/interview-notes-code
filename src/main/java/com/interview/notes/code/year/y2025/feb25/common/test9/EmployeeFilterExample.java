package com.interview.notes.code.year.y2025.feb25.common.test9;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFilterExample {
    public static void main(String[] args) {
        // Assume 'employees' is your list of Employee objects
        List<Employee> employees = getEmployees();

        // Filter employees with designation "Developer" and convert their first name to uppercase
        List<Employee> filteredEmployees = employees.stream()
            .filter(emp -> "Developer".equalsIgnoreCase(emp.getDesignation()))
            .map(emp -> {
                emp.setFirstName(emp.getFirstName().toUpperCase());
                return emp;
            })
            .collect(Collectors.toList());

        // Process or print the filtered list
        filteredEmployees.forEach(System.out::println);
    }
    
    // Dummy method to represent fetching employee data
    private static List<Employee> getEmployees() {
        // ... fetch or create employee list
        return List.of(
            new Employee(1, "John", "Doe", "Developer"),
            new Employee(2, "Jane", "Smith", "Manager"),
            new Employee(3, "Alice", "Johnson", "Developer")
        );
    }
}

// Sample Employee class
class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String designation;

    public Employee(int id, String firstName, String lastName, String designation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public String getDesignation() { return designation; }
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}