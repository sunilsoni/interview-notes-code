package com.interview.notes.code.year.y2025.april.common.test8;

import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Data
class Employee {
    private int id;
    private String name;
    private String email;
    
    // Constructor, getters, setters...
}

public class EmployeeService {
    private List<Employee> employees;

    // Method 1: Using findFirst() with Optional
    public Optional<Employee> findEmployeeByEmail(String email) {
        return employees.stream()
                .filter(emp -> emp.getEmail().equals(email))
                .findFirst();
    }

    // Method 2: Using findFirst() with default value
    public Employee findEmployeeByEmailOrDefault(String email) {
        return employees.stream()
                .filter(emp -> emp.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    // Method 3: Using findFirst() with exception
    public Employee findEmployeeByEmailOrThrow(String email) throws Exception {
        return employees.stream()
                .filter(emp -> emp.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new Exception("Employee not found"));
    }

    // Method 4: Using collect with limiting to one result
    public Employee findSingleEmployee(String email) {
        return employees.stream()
                .filter(emp -> emp.getEmail().equals(email))
                .limit(1)
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .orElse(null);
    }
}

// Usage examples:
 class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();

        // Example 1: Using Optional
        Optional<Employee> employeeOpt = service.findEmployeeByEmail("john@example.com");
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            // Process employee
        }

        // Example 2: Using default value
        Employee employee = service.findEmployeeByEmailOrDefault("john@example.com");
        if (employee != null) {
            // Process employee
        }

        // Example 3: Using exception handling
        try {
            Employee emp = service.findEmployeeByEmailOrThrow("john@example.com");
            // Process employee
        } catch (Exception e) {
            // Handle exception
        }

        // Example 4: Direct usage with null check
        Employee singleEmployee = service.findSingleEmployee("john@example.com");
        if (singleEmployee != null) {
            // Process employee
        }
    }
}
