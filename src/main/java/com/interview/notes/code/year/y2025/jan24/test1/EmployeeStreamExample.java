package com.interview.notes.code.year.y2025.jan24.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String address;
    private int age;

    public Employee(int id, String name, String address, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    // Getters
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
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John Doe", "123 Main St", 32));
        employees.add(new Employee(2, "Jane Smith", "456 Oak Ave", 28));
        employees.add(new Employee(3, "Bob Johnson", "789 Pine Rd", 35));
        employees.add(new Employee(4, "Alice Brown", "321 Elm St", 29));
        employees.add(new Employee(5, "Charlie Wilson", "654 Maple Dr", 40));

        // 1. Collect names of employees older than 30
        List<String> namesAbove30 = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("Names of employees above 30: " + namesAbove30);

        // 2. Collect all details of employees older than 30
        List<Employee> employeesAbove30 = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .collect(Collectors.toList());
        System.out.println("\nFull details of employees above 30:");
        employeesAbove30.forEach(System.out::println);

        // 3. Count employees older than 30
        long countAbove30 = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .count();
        System.out.println("\nNumber of employees above 30: " + countAbove30);

        // 4. Get average age of employees older than 30
        double avgAgeAbove30 = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .mapToInt(Employee::getAge)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage age of employees above 30: " + avgAgeAbove30);

        // 5. Get names and ages of employees older than 30
        Map<String, Integer> nameAgeMapAbove30 = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getAge
                ));
        System.out.println("\nNames and ages of employees above 30: " + nameAgeMapAbove30);

        // 6. Group employees older than 30 by their age
        Map<Integer, List<Employee>> groupedByAgeAbove30 = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .collect(Collectors.groupingBy(Employee::getAge));
        System.out.println("\nEmployees above 30 grouped by age: ");
        groupedByAgeAbove30.forEach((age, empList) ->
                System.out.println("Age " + age + ": " + empList));
    }
}
