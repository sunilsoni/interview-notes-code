package com.interview.notes.code.year.y2025.jan25.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Employee class
class Employee {
    private int id;
    private String name;
    private String address;
    private int age;

    // Constructor
    public Employee(int id, String name, String address, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}

public class EmployeeStreamExample {
    public static void main(String[] args) {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John Doe", "123 Main St", 30));
        employees.add(new Employee(2, "Jane Smith", "456 Oak Ave", 25));
        employees.add(new Employee(3, "Bob Johnson", "789 Pine Rd", 35));
        employees.add(new Employee(4, "Alice Brown", "321 Elm St", 28));
        employees.add(new Employee(5, "Charlie Wilson", "654 Maple Dr", 40));

        // Different ways to print using streams

        // 1. Print all employees
        System.out.println("All Employees:");
        employees.stream()
                .forEach(System.out::println);

        // 2. Print employees older than 30
        System.out.println("\nEmployees older than 30:");
        employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .forEach(System.out::println);

        // 3. Print employee names only
        System.out.println("\nEmployee names:");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        // 4. Print employees sorted by age
        System.out.println("\nEmployees sorted by age:");
        employees.stream()
                .sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()))
                .forEach(System.out::println);

        // 5. Count employees older than 30
        long count = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .count();
        System.out.println("\nNumber of employees older than 30: " + count);

        // 6. Collect employees names in a new list
        List<String> employeeNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("\nEmployee names in list: " + employeeNames);

        // 7. Find average age of employees
        double averageAge = employees.stream()
                .mapToInt(Employee::getAge)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage age of employees: " + averageAge);

        // 8. Find employees with name containing "John"
        System.out.println("\nEmployees with 'John' in their name:");
        employees.stream()
                .filter(emp -> emp.getName().contains("John"))
                .forEach(System.out::println);

        // 9. Get the oldest employee
        System.out.println("\nOldest employee:");
        employees.stream()
                .max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()))
                .ifPresent(System.out::println);

        // 10. Group employees by age
        System.out.println("\nEmployees grouped by age:");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge))
                .forEach((age, empList) -> System.out.println("Age " + age + ": " + empList));
    }
}
